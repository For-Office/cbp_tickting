package com.cbp.ticketing.model;

import java.util.LinkedHashSet;
import java.util.Set;

import gudusoft.gsqlparser.EExpressionType;
import gudusoft.gsqlparser.nodes.TJoin;
import gudusoft.gsqlparser.nodes.TJoinItem;
import gudusoft.gsqlparser.nodes.TJoinItemList;
import gudusoft.gsqlparser.nodes.TResultColumn;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import gudusoft.gsqlparser.nodes.TTable;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;


public class SelectMetadata
{
	
	 
	 public Set<String> analyzeSelectStatement( TSelectSqlStatement stmt )
		{

			Set<String> tables = new LinkedHashSet<String>( );
			if ( stmt.getSetOperator( ) != TSelectSqlStatement.setOperator_none )
			{
				tables.addAll( analyzeSelectStatement( stmt.getLeftStmt( ) ) );
				tables.addAll( analyzeSelectStatement( stmt.getRightStmt( ) ) );
			}
			else
			{
				
				for ( int i = 0; i < stmt.getResultColumnList( ).size( ); i++ )
				{
					//System.out.println("Inside mesg 7");
					System.out.println("testting stmt.getResultColumnList( ).size( )"+stmt.getResultColumnList( ).size( ));
					TResultColumn field = stmt.getResultColumnList( )
							.getResultColumn( i );
					System.out.println("testting stmt.getResultColumnList( ).size( )"+field.getColumnNameOnly());
					if ( field.getExpr( ).getExpressionType( ) == EExpressionType.subquery_t )
					{
						//System.out.println("Inside mesg 8");
						tables.addAll( analyzeSelectStatement( field.getExpr( )
								.getSubQuery( ) ) );
					}
				}

				/*if ( stmt.getWhereClause( ) != null
						&& stmt.getWhereClause( ).getCondition( ) != null )
				{
					new ExtractTable(this,stmt.getWhereClause( ).getCondition( ),
							tables ).searchTable( );
				}*/

				if ( stmt.joins != null )
				{
					for ( int i = 0; i < stmt.joins.size( ); i++ )
					{
						TJoin join = stmt.joins.getJoin( i );
						if ( join.getTable( ).isBaseTable( ) )
							tables.add( join.getTable( ).getFullName( ) );
						else if ( join.getTable( ).getSubquery( ) != null )
						{
							tables.addAll( analyzeSelectStatement( join.getTable( )
									.getSubquery( ) ) );
						}
						TJoinItemList items = join.getJoinItems( );
						if ( items != null )
						{
							for ( int j = 0; j < items.size( ); j++ )
							{
								TJoinItem item = items.getJoinItem( j );
								if ( item.getTable( ).isBaseTable( ) )
									tables.add( item.getTable( ).getFullName( ) );
								else if ( item.getTable( ).getSubquery( ) != null )
								{
									tables.addAll( analyzeSelectStatement( item.getTable( )
											.getSubquery( ) ) );
								}
								/*if ( item.getOnCondition( ) != null )
								{
									new ExtractTable( this,
											item.getOnCondition( ),
											tables ).searchTable( );
								}*/
							}
						}
					}
				}

				if ( stmt.getTargetTable( ) != null )
				{
					if ( stmt.getTargetTable( ).isBaseTable( ) )
						tables.add( stmt.getTargetTable( ).getFullName( ) );
					else if ( stmt.getTargetTable( ).getSubquery( ) != null )
					{
						tables.addAll( analyzeSelectStatement( stmt.getTargetTable( )
								.getSubquery( ) ) );
					}
				}

				if ( stmt.tables != null )
				{
					for ( int i = 0; i < stmt.tables.size( ); i++ )
					{
						TTable table = stmt.tables.getTable( i );
						if ( table.isBaseTable( ) )
							tables.add( table.getFullName( ) );
						else if ( table.getSubquery( ) != null )
						{
							tables.addAll( analyzeSelectStatement( table.getSubquery( ) ) );
						}
					}

				}
			}

			return tables;
		}
	
	 public Set<String> getListOfResultColumns(TSelectSqlStatement statement){
		 
			Set<String>  columnSet = new LinkedHashSet<String>();
			
					analyzeStatementForResultColumns(statement, columnSet);
				
			
	        return columnSet;
		}
	 
	 private void analyzeStatementForResultColumns(TSelectSqlStatement statement, Set<String> columnSet){
			if (statement.getSetOperator() != TSelectSqlStatement.setOperator_none) {
				analyzeStatementForResultColumns(statement.getLeftStmt(), columnSet);
			} else {
				TResultColumnList columns = statement.getResultColumnList();
				for (int i = 0; i < columns.size(); i++) {
					if(columns.getResultColumn(i).getColumnAlias() != null && !columns.getResultColumn(i).getColumnAlias().equals("")) {
						columnSet.add(columns.getResultColumn(i).getColumnAlias().toLowerCase().replace("[", "").replace("]", ""));
		        	} else if(columns.getResultColumn(i).getColumnNameOnly() != null && !columns.getResultColumn(i).getColumnNameOnly().equals("")) {
		        		columnSet.add(columns.getResultColumn(i).getColumnNameOnly().toLowerCase().replace("[", "").replace("]", ""));
		        	} else {
		        		columnSet.add(columns.getResultColumn(i).toString().toLowerCase().replace("[", "").replace("]", ""));
		        	}
				}
			}
		}
	 
	
	 

   
	 
	 
	 	 

	 
	
}