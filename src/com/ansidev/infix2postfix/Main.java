package com.ansidev.infix2postfix;

//import java.util.ArrayList;
//import java.util.regex.Pattern;
//import java.util.regex.Matcher;



/**
 * Written by ansidev
 * @date: Oct 11, 2014
 * @author: ansidev
 * @email: ansidev@gmail.com
 * @homepage: http://blog.ansidev.tk/
 */
import com.ansidev.infix2postfix.Parser;
import com.ansidev.infix2postfix.ExpressionTree;

public class Main {
	public static void main(String[] args) {
//		String regex = "^\\($";
//		String[] s = {"sin(", "(" , "cos("};
//		for(String ss : s) {
//			boolean match = ss.matches(regex);
//			System.out.println(match);
//		}
//		String s = "factor(x)";
////		String[] enumer = s.split("");
//		if(Parser.isFunction(s)) {
//			System.out.println("Case 1: True");
//		}
//		else {
//			System.out.println(s.substring(0, 3));
//			if(Parser.isFunction(s.substring(0, 3))) {
//				System.out.println("Case 2: True");
//			}
//			else {
//				if(Parser.isFunction(s.substring(0, 6))) {
//					System.out.println("Case 3: True");
//				}
//				else {
//					if(Parser.isFunction(s.substring(0, 8))) {
//						System.out.println("True");
//					}
//					else {
//						System.out.println("False");
//					}
//				}
//			}
//		}
		
//		ArrayList<String> arrStr = new ArrayList<String>();
//		arrStr.add("Hello");
//		arrStr.add("world");
//		System.out.println(arrStr.get(1));
		
//		System.out.println(Parser.factor(20000).toString());
//		System.out.println("Finished");
		
//		String infixExpr = "2( 1 + sin( π / 2 + 1/ 4))- 3.32 + e * 8.3 - cos( 2 π / 5) * factor( 20) + (1+2) (3+4)";
//		System.out.println("Before: " + infixExpr);
//		ExpressionTree exprTree = new ExpressionTree();
//		exprTree.buildTree(infixExpr);
		String s = "sin";
//		double result = exprTree.evaluateExpressionTree(exprTree.buildTree(infixExpr));
		System.out.println(s.concat("("));

//		infixExpr = ExpressionTree.formatExpression(infixExpr);
//		System.out.println("After: " + infixExpr);
//		Parser.queueToArrList(Parser.getAllExpressionNode(infixExpr));
//		String newinfixExpr = new String();
//		System.out.println(infixExpr);
//		final String TEXT = "1+sin(2/3)+e";
//		String regex = "(?<function> *(sin|cos|tan|log|ln|factor|opposite){1})(?<openbracket>\\()(?<expression>.+)(?<closebracket>\\()";
//		String regex = "(?<operand>([0-9.e\\)]|\u03C0|^[sin|cos|tan|log|ln|factor|opposite]){1})(?<bracket>\\({1})";
//      String regex = "\\s+";
//        System.out.println(Parser.addMulOp(infixExpr));
//		Queue<ExpressionNode> arr = Parser.getAllExpressionNode(infixExpr);
//		Queue<ExpressionNode> arr = Parser.getAllOperands(infixExpr);
//		Queue<ExpressionNode> arr = Parser.getAllOperators(infixExpr);
//		Queue<ExpressionNode> arr = Parser.getAllFunctionName(infixExpr);
//		ExpressionNode exprNode = new ExpressionNode();
//		Parser.queueToList(arr);
//		while (!arr.isEmpty()) {
//			ExpressionNode node = arr.dequeue(); 
//			
//			String regex = "^(sin|cos|tan|log|ln|factor|opposite)?\\($";
//			boolean match = node.Value.matches(regex);
//			if(match) {
//				node.Print();
//				System.out.print(" " + match);
//				System.out.println("");
//			}
//		}
	}
}