package com.cbp.ticketing.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


import gudusoft.gsqlparser.EExpressionType;
import gudusoft.gsqlparser.EFunctionType;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.nodes.IExpressionVisitor;
import gudusoft.gsqlparser.nodes.TCaseExpression;
import gudusoft.gsqlparser.nodes.TExpression;
import gudusoft.gsqlparser.nodes.TFunctionCall;
import gudusoft.gsqlparser.nodes.TGroupByItem;
import gudusoft.gsqlparser.nodes.TInExpr;
import gudusoft.gsqlparser.nodes.TJoin;
import gudusoft.gsqlparser.nodes.TJoinItem;
import gudusoft.gsqlparser.nodes.TJoinItemList;
import gudusoft.gsqlparser.nodes.TOrderByItem;
import gudusoft.gsqlparser.nodes.TOrderByItemList;
import gudusoft.gsqlparser.nodes.TParseTreeNode;
import gudusoft.gsqlparser.nodes.TParseTreeNodeList;
import gudusoft.gsqlparser.nodes.TResultColumn;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.nodes.TTable;
import gudusoft.gsqlparser.nodes.TTrimArgument;
import gudusoft.gsqlparser.nodes.TWhenClauseItem;
import gudusoft.gsqlparser.nodes.TWhenClauseItemList;
import gudusoft.gsqlparser.stmt.TDeleteSqlStatement;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;


public class DeleteMetadata
{
	public Set<String>  analyzeDeleteStatement( TDeleteSqlStatement delete,TCustomSqlStatement stmt )
	{
	 SelectMetadata selectMetadata=new SelectMetadata();
	 
		Set<String> tables = new LinkedHashSet<String>( );

		if ( delete.getTargetTable( ) != null )
		{
			System.out.println( "Target: "
					+ delete.getTargetTable( ).getFullName( ) );
			tables.add( delete.getTargetTable( ).getFullName( ) );
		}

		if ( delete.getResultColumnList( ) != null )
		{
			for ( int i = 0; i < delete.getResultColumnList( ).size( ); i++ )
			{
				TResultColumn field = delete.getResultColumnList( )
						.getResultColumn( i );
				System.out.println("delete"+field.getColumnNameOnly());
				if ( field.getExpr( ).getRightOperand( ) != null
						&& field.getExpr( )
								.getRightOperand( )
								.getExpressionType( ) == EExpressionType.subquery_t )
				{
					tables.addAll( selectMetadata.analyzeSelectStatement( field.getExpr( )
							.getRightOperand( )
							.getSubQuery( ) ) );
				}
			}
		}

		if ( delete.joins != null )
		{
			for ( int i = 0; i < delete.joins.size( ); i++ )
			{
				TJoin join = delete.joins.getJoin( i );
				if ( join.getTable( ).isBaseTable( ) )
					tables.add( join.getTable( ).getFullName( ) );
				TJoinItemList items = join.getJoinItems( );
				if ( items != null )
				{
					for ( int j = 0; j < items.size( ); j++ )
					{
						TJoinItem item = items.getJoinItem( j );
						if ( item.getTable( ).isBaseTable( ) )
							tables.add( item.getTable( ).getFullName( ) );
						if ( item.getOnCondition( ) != null )
						{
							new  ExtractTable(selectMetadata,item.getOnCondition( ),
									tables ).searchTable( );
						}
					}
				}
			}
		}

		if ( delete.getWhereClause( ) != null
				&& delete.getWhereClause( ).getCondition( ) != null )
		{
			System.out.println("whereeeeeeee");
			
			new  ExtractTable(selectMetadata,delete.getWhereClause( ).getCondition( ),
					tables).searchTable( );
		}
		if ( !tables.isEmpty( ) )
		{
			System.out.print( "Source: " );
			String[] tableArray = tables.toArray( new String[0] );
			for ( int i = 0; i < tableArray.length; i++ )
			{
				System.out.print( tableArray[i] );
				if ( i < tableArray.length - 1 )
				{
					System.out.print( ", " );
				}
			}
			System.out.println( );
		}
		return tables;
	}
	
	 class ExtractTable implements IExpressionVisitor {

			private Set<String> tables;
			private TExpression expr;
			private SelectMetadata  impact;
			
			public ExtractTable(SelectMetadata impact, TExpression expr,
					Set<String> tables) {
				this.impact = impact;
				this.expr = expr;
				this.tables = tables;
			}

			private void addColumnToList(TParseTreeNodeList list) {
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						List<TExpression> exprList = new ArrayList<TExpression>();
						Object element = list.getElement(i);

						if (element instanceof TGroupByItem) {
							exprList.add(((TGroupByItem) element).getExpr());
						}
						if (element instanceof TOrderByItem) {
							exprList.add(((TOrderByItem) element).getSortKey());
						} else if (element instanceof TExpression) {
							exprList.add((TExpression) element);
						} else if (element instanceof TWhenClauseItem) {
							exprList.add(((TWhenClauseItem) element)
									.getComparison_expr());
							exprList.add(((TWhenClauseItem) element)
									.getReturn_expr());
						}

						for (TExpression expr : exprList) {
							expr.inOrderTraverse(this);
						}
					}
				}
			}

			public boolean exprVisit(TParseTreeNode pNode, boolean isLeafNode) {
				TExpression lcexpr = (TExpression) pNode;
				if (lcexpr.getExpressionType() == EExpressionType.simple_object_name_t) {

				} else if (lcexpr.getExpressionType() == EExpressionType.between_t) {

				} else if (lcexpr.getExpressionType() == EExpressionType.function_t) {
					TFunctionCall func = (TFunctionCall) lcexpr.getFunctionCall();
					if (func.getFunctionType() == EFunctionType.trim_t) {
						TTrimArgument args = func.getTrimArgument();
						TExpression expr = args.getStringExpression();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
						expr = args.getTrimCharacter();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
					} else if (func.getFunctionType() == EFunctionType.cast_t) {
						TExpression expr = func.getExpr1();
						if (expr != null
								&& !expr.toString().trim().equals("*")
								|| func.getFunctionType() == EFunctionType.extract_t) {
							expr.inOrderTraverse(this);
						}
					} else if (func.getFunctionType() == EFunctionType.convert_t) {
						TExpression expr = func.getExpr1();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
						expr = func.getExpr2();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
					} else if (func.getFunctionType() == EFunctionType.contains_t
							|| func.getFunctionType() == EFunctionType.freetext_t) {
						TExpression expr = func.getExpr1();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
						TInExpr inExpr = func.getInExpr();
						if (inExpr.getExprList() != null) {
							for (int k = 0; k < inExpr.getExprList().size(); k++) {
								expr = inExpr.getExprList().getExpression(k);
								if (expr.toString().trim().equals("*"))
									continue;
								expr.inOrderTraverse(this);
							}
							if (expr != null && !expr.toString().trim().equals("*")) {
								expr.inOrderTraverse(this);
							}
						}
						expr = inExpr.getFunc_expr();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
					} else if (func.getFunctionType() == EFunctionType.extractxml_t) {
						TExpression expr = func.getXMLType_Instance();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
						expr = func.getXPath_String();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
						expr = func.getNamespace_String();
						if (expr != null && !expr.toString().trim().equals("*")) {
							expr.inOrderTraverse(this);
						}
					}

					if (func.getFunctionType() == EFunctionType.rank_t) {
						TOrderByItemList orderByList = func.getOrderByList();
						for (int k = 0; k < orderByList.size(); k++) {
							TExpression expr = orderByList.getOrderByItem(k)
									.getSortKey();
							if (expr.toString().trim().equals("*"))
								continue;
							expr.inOrderTraverse(this);
						}
					} else if (func.getArgs() != null) {
						for (int k = 0; k < func.getArgs().size(); k++) {
							TExpression expr = func.getArgs().getExpression(k);
							if (expr.toString().trim().equals("*"))
								continue;
							expr.inOrderTraverse(this);
						}
					}
					if (func.getAnalyticFunction() != null) {
						TParseTreeNodeList list = func.getAnalyticFunction()
								.getPartitionBy_ExprList();
						addColumnToList(list);

						if (func.getAnalyticFunction().getOrderBy() != null) {
							list = func.getAnalyticFunction().getOrderBy()
									.getItems();
							addColumnToList(list);
						}
					}

				} else if (lcexpr.getExpressionType() == EExpressionType.subquery_t) {
					tables.addAll(impact.analyzeSelectStatement(lcexpr
							.getSubQuery()));
				} else if (lcexpr.getExpressionType() == EExpressionType.case_t) {
					TCaseExpression expr = lcexpr.getCaseExpression();
					TExpression conditionExpr = expr.getInput_expr();
					if (conditionExpr != null) {
						conditionExpr.inOrderTraverse(this);
					}
					TExpression defaultExpr = expr.getElse_expr();
					if (defaultExpr != null) {
						defaultExpr.inOrderTraverse(this);
					}
					TWhenClauseItemList list = expr.getWhenClauseItemList();
					addColumnToList(list);
				}
				return true;
			}

			public void searchTable() {
				System.out.println("testingggggggggg");
				this.expr.inOrderTraverse(this);
			}
		}
	
	 }