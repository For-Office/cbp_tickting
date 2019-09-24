package com.cbp.ticketing.action.implService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.nodes.IExpressionVisitor;
import gudusoft.gsqlparser.nodes.TExpression;
import gudusoft.gsqlparser.nodes.TParseTreeNode;


public class WhereConditionTraverser implements IExpressionVisitor{
	public List<String> getListOfValuesInWhereCondition(TCustomSqlStatement customSqlStatement) {
		values = new ArrayList<String>();
		 	
        	analyzeStatementForWhereClause(customSqlStatement);
        
		return values;
	}

	public  void analyzeStatementForWhereClause(TCustomSqlStatement customSqlStatement) {
		if(customSqlStatement.getWhereClause()!=null){
			TExpression expr = customSqlStatement.getWhereClause().getCondition();
			expr.preOrderTraverse(new WhereConditionTraverser());
		}
		if(customSqlStatement.getStatements().size()>0){
			for (int j = 0; j < customSqlStatement.getStatements().size(); j++) {
				TCustomSqlStatement stmt = customSqlStatement.getStatements()
						.get(j);
				if (stmt.getWhereClause() != null) {
					TExpression expr = stmt.getWhereClause().getCondition();
					expr.preOrderTraverse(new WhereConditionTraverser());
				} else {
					analyzeStatementForWhereClause(stmt);
				}
			}
		}/* else {
			if(customSqlStatement.getWhereClause() != null){
				TExpression expr = customSqlStatement.getWhereClause().getCondition();
				expr.preOrderTraverse(new WhereConditionTraverser());
			}
		}*/
	}

	static List<String> values = null;

	int counter = 0;

	public boolean exprVisit(TParseTreeNode pNode, boolean isLeafNode) {

		Set<String> hs = new HashSet<String>();
		if (isLeafNode) {
			if (counter % 2 == 0) {
				values.add(pNode.toString());
			} else {
				//values.add(pNode.toString());
			}
			counter++;
		}
		
		hs.addAll(values);
		values.clear();
		values.addAll(hs);

		return true;
	};
}
