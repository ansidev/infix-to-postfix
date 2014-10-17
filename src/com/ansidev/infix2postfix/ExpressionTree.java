/**
 * Written by ansidev
 * @date: Oct 10, 2014
 * @author: ansidev
 * @email: ansidev@gmail.com
 * @homepage: http://blog.ansidev.tk/
 */
package com.ansidev.infix2postfix;

import java.util.ArrayList;


//import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

//import com.ansidev.infix2postfix.ExpressionNode.Type;


public class ExpressionTree {
	public ExpressionNode Node;
	public ExpressionTree leftExprTree;
	public ExpressionTree rightExprTree;
	
	
	public void Init() {
		this.Node = null;
		this.leftExprTree = this.rightExprTree = null;
	}
	
	public boolean isLeaf() {
		return (this.leftExprTree == null && this.rightExprTree == null);
	}
	
	public static String formatExpression(String expression)
	{
		//Remove white space from expression string
	    expression = expression.replaceAll(" ", "");
	    //Add * between ) and ( or operand and (
	    expression = Parser.addMulOp(expression);
	    expression = expression.trim();
	    return expression;
	}
	
	public static void createSubTree(Stack<ExpressionTree> opStack, Stack<ExpressionTree> nodeStack) {
//		String funcRegex = "^(sin|cos|tan|log|ln|factor|opposite){1}\\($";
//		System.out.println("Root Node is " + opStack.peek().Node.Value);
		ExpressionTree exprTree = opStack.pop();
		if(exprTree.Node.Type == 3) { //check if it is Function Name
			exprTree.Node.Value = exprTree.Node.Value.replaceAll("\\(", ""); //Remove ( from string
		}
		else {
//			System.out.println("Right Child is " + nodeStack.peek().Node.Value);
			exprTree.rightExprTree = nodeStack.pop();			
		}
//		System.out.println("Left Child is " + nodeStack.peek().Node.Value);
		exprTree.leftExprTree = nodeStack.pop();
		nodeStack.push(exprTree);
//		System.out.println("Pushed " + nodeStack.peek().Node.Value + " to nodeStack");
	}
	public ExpressionTree buildTree(String infixExpr) {
		infixExpr = ExpressionTree.formatExpression(infixExpr);
		int j = 0;
		System.out.println(infixExpr);
		ArrayList<ExpressionNode>  exprArray = Parser.queueToArrList(Parser.getAllExpressionNode(infixExpr));
		Stack<ExpressionTree> opStack = new Stack<ExpressionTree>();
		Stack<ExpressionTree> nodeStack = new Stack<ExpressionTree>();
		String regex = "^(sin|cos|tan|log|ln|factor|opposite)?\\($";
		for(int i = 0; i < exprArray.size(); i++) {
			System.out.println("i  = " + i);
			System.out.println("\nElement = " + exprArray.get(i).Value);
			if(exprArray.get(i).Type == 1) {//isOperand
				System.out.println("isOperand");
				nodeStack.push(new ExpressionTree(exprArray.get(i)));
//				System.out.println("Pushed " + nodeStack.peek().Node.Value + " to nodeStack");
//				if(!nodeStack.isEmpty()) System.out.println("\nnodeStack Top: " + nodeStack.peek().Node.Value);
//				if(!opStack.isEmpty()) {
//					System.out.println("opStack Top: " + opStack.peek().Node.Value);
//				}
//				else {
//					System.out.println("opStack Top: null");
//				}
			}
			else if(exprArray.get(i).Value.matches("^\\($") || exprArray.get(i).Type == 3) {//isOpenBracket OR isFunctionName
				System.out.println("is ( OR functionName");
				opStack.push(new ExpressionTree(exprArray.get(i)));
//				System.out.println("Pushed " + opStack.peek().Node.Value + " to opStack");
//				System.out.println("nodeStack Top: " + nodeStack.peek().Node.Value);
//				System.out.println("opStack Top: " + opStack.peek().Node.Value);
			}
			else if(exprArray.get(i).Value.matches("^\\)$")) {//isCloseBracket
				j++;
				System.out.println(") " + i);
				while(!opStack.isEmpty() && opStack.peek().Node.Type != 4) {
						System.out.println("Not (");
					System.out.println("\nopStack Top Element = " + opStack.peek().Node.Value);
					System.out.println("opStack.peek().Node.Type = " + opStack.peek().Node.Type);
					if(opStack.peek().Node.Type == 3) {
						System.out.println("is Function");
//						System.out.println("\nStart creating subtree");
						createSubTree(opStack, nodeStack);
//						System.out.println("Finished creating subtree");
//						System.out.println("\nnodeStack Top: " + nodeStack.peek().Node.Value);
//						System.out.println("opStack Top: " + opStack.peek().Node.Value);
						System.out.println("Break");
						System.out.println("Ended looping");
						break;
					}
					else {
						System.out.println("is Operator");
					createSubTree(opStack, nodeStack);
//					System.out.println("Finished creating subtree");
//					System.out.println("\nnodeStack Top: " + nodeStack.peek().Node.Value);
//					System.out.println("opStack Top: " + opStack.peek().Node.Value);
//					System.out.println("Continue");
				}
				System.out.println("Ended looping");
//				System.out.println("\nopStack Top Element = " + opStack.peek().Node.Value);
//				System.out.println("opStack.peek().Node.Type = " + opStack.peek().Node.Type);
//				System.out.println("\nnodeStack Top Element = " + nodeStack.peek().Node.Value);
//				System.out.println("nodeStack.peek().Node.Type = " + nodeStack.peek().Node.Type);
				if(!opStack.isEmpty() && opStack.peek().Node.Type == 4) {
					System.out.println("Pop "+ opStack.peek().Node.Value + " out of opStack");//Pop ( out
					opStack.pop();
				}
//				System.out.println("nodeStack Top: " + nodeStack.peek().Node.Value);
//				System.out.println("opStack Top: " + opStack.peek().Node.Value);
				}
			}
			else if(exprArray.get(i).Type == 2) {//isOperator
				System.out.println("is Operator");
				while(!opStack.isEmpty() && Parser.Precedence(opStack.peek().Node.Value) >= Parser.Precedence(exprArray.get(i).Value)) {
					createSubTree(opStack, nodeStack);
//					System.out.println("nodeStack Top: " + nodeStack.peek().Node.Value);
//					System.out.println("opStack Top: " + opStack.peek().Node.Value);
				}
//				System.out.println("Push " + exprArray.get(i).Value + " to opStack");
				opStack.push(new ExpressionTree(exprArray.get(i)));
//				System.out.println("nodeStack Top: " + nodeStack.peek().Node.Value);
//				System.out.println("opStack Top: " + opStack.peek().Node.Value);
			}
		}
		
		while(!opStack.isEmpty()) {
				createSubTree(opStack, nodeStack);
		}
		System.out.println("Root Node of Expression Tree is " + nodeStack.peek().Node.Value);
		return nodeStack.pop();
	}
	public void Print(ExpressionTree rootNode) {
		if(rootNode.Node.Value != null) {
			Print(rootNode.leftExprTree);
			Print(rootNode);
			Print(rootNode.rightExprTree);
		}
		else {
			System.out.println("Empty expression tree");
		}
	}
	
//	public ExpressionTree(String infixExpr) {
//		infixExpr = ExpressionTree.formatExpression(infixExpr);
//		this.buildTree(infixExpr);
//	}
	public ExpressionTree(ExpressionNode s) {
		this.Node = s;
		this.leftExprTree = this.rightExprTree = null;
	}
	
	public ExpressionTree() {
		this.Node = null;
		this.leftExprTree = this.rightExprTree = null;
	}
}