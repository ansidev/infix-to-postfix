package com.ansidev.infix2postfix;
//import java.beans.Expression;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.math.BigInteger;

//import javax.management.RuntimeErrorException;


/**
 * Written by ansidev
 * @author: ansidev
 * @email: ansidev@gmail.com
 * @homepage: http://blog.ansidev.tk/
 */
public class Parser {
	public static BigInteger factor(int n) {
		BigInteger k = new BigInteger("1");
		BigInteger result = new BigInteger("1");
		if ( n < 0 ) {
//			System.out.println("Invalid value!");
			return BigInteger.ZERO;
		}
		else
		{
			for (int i = 1 ; i <= n ; i++) {
				result = result.multiply(k);
				k = k.add(BigInteger.ONE);
			}
//				System.out.println("Factorial of "+ n + " is = "+ result);
		}
		return result;
	}
	
	public static void printStack(Stack<ExpressionTree> s) {
		Stack<ExpressionTree> stack = s;
		System.out.println("\nCurrent stack status:");
		while(!stack.isEmpty()) {
			stack.pop().Node.Print();
		}
	}
	
	public static boolean isBinaryOperator(String s)
	{
		Pattern pattern = Pattern.compile("^(+|-|*|/|%|^)$");
	    Matcher matcher = pattern.matcher(s);
	    return matcher.matches();
	}
	
	public static boolean isFunction(String s) {
		Pattern pattern = Pattern.compile("^(sin|cos|tan|log|ln|factor|opposite)$");
	    Matcher matcher = pattern.matcher(s);
	    return matcher.lookingAt();
	}
	
	public static boolean isOperand(String s)
	{
		//Operand is 0-9, e, π, .
		Pattern pattern = Pattern.compile("^([0-9.e]|\u03C0)+$");
	    Matcher matcher = pattern.matcher(s);
	    return matcher.lookingAt();
	}
	
	public static String addMulOp(String s) {
		String regex = "(?<first>([0-9.e\\)]|\u03C0|^[sin|cos|tan|log|ln|factor|opposite]){1})(?<second>[\\(|e|\u03C0]{1})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
        	String oldStr = matcher.group();
//        	System.out.println("oldStr: " + oldStr);
        	StringBuilder newStr = new StringBuilder(); 
			newStr.append(matcher.group("first")).append("*").append(matcher.group("second"));
			s = s.replace(oldStr, newStr.toString());
//			System.out.println(s);
        }
        return s;

	}
	public static Queue<ExpressionNode> getAllMatchRegex(String str, String regex, int type) {
		Queue<ExpressionNode> resultArr = new Queue<ExpressionNode>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
//		if(type != -1) {
			while (matcher.find()) {
				resultArr.enqueue(new ExpressionNode(matcher.group(), matcher.start(), matcher.end(),type));
			}
//		}
//		else {
//			while (matcher.find()) {
//				resultArr.enqueue(new ExpressionNode(matcher.group(), matcher.start(), matcher.end()));
//			}
//		}
		return resultArr;
	}
	
	public static Queue<ExpressionNode> getAllOperands(String infixExpr) {
		String regex = "([0-9.e]|\u03C0)+";
		int type = 1;
		return getAllMatchRegex(infixExpr, regex, type);
	}
	
	public static Queue<ExpressionNode> getAllBrackets(String infixExpr) {
		String regex = "(\\(|\\))";
		int type = 4;
		ArrayList<ExpressionNode> bracketArr = queueToArrList(getAllMatchRegex(infixExpr, regex, type));
		ArrayList<ExpressionNode> functionArr = queueToArrList(getAllFunctionName(infixExpr));
		for(int i = 0; i < bracketArr.size(); i++) {
//			System.out.println("i = " + i);
//			System.out.println(bracketArr.get(i).Value + " [" + bracketArr.get(i).start + ", " + bracketArr.get(i).end + "]");
			for(int j = 0; j < functionArr.size(); j++) {
//				System.out.println("j = " + j);
//				System.out.println(functionArr.get(j).Value + "[" + functionArr.get(j).start + ", " + functionArr.get(j).end + "]");
				if(functionArr.get(j).end == bracketArr.get(i).end) {
					bracketArr.remove(i);
//					System.out.println("Removed bracketArr" + "[" + i + "]");
					continue;
				}
			}
		}
		Queue<ExpressionNode> bracketQueue = arrToQueue(bracketArr);
		return bracketQueue;
	}	
	
	public static Queue<ExpressionNode> getAllOperators(String infixExpr) {
		String regex = "[\\+|\\-|\\*|/|%|^]";
		int type = 2;
		return getAllMatchRegex(infixExpr, regex, type);
	}	

	public static Queue<ExpressionNode> getAllFunctionName(String infixExpr) {
		String regex = "(sin|cos|tan|log|ln|factor|opposite){1}\\(";
		int type = 3;
		return getAllMatchRegex(infixExpr, regex, type);
	}	

	public static Queue<ExpressionNode> getAllExpressionNode(String infixExpr) {
		Queue<ExpressionNode> q = new Queue<ExpressionNode>();
		ArrayList<Queue<ExpressionNode>> arr = new ArrayList<Queue<ExpressionNode>>();
		ExpressionNode minElement = new ExpressionNode();
		arr.add(getAllOperands(infixExpr));
		arr.add(getAllOperators(infixExpr));
		arr.add(getAllBrackets(infixExpr));
		arr.add(getAllFunctionName(infixExpr));
		ArrayList<String> strArr = new ArrayList<String>();
//		int time = 1;
		while(!arr.isEmpty()) {
			minElement = arr.get(0).peek();
			int minPosIndex = 0;

//			System.out.println("Time = " + time);
			for(int i = 1; i < arr.size(); i++) {
//				System.out.print("Value = " + minElement.Value);
//				System.out.print(" Start = " + minElement.start);
//				System.out.println(" End = " + minElement.end);
//				System.out.print("Value = " + arr.get(i).peek().Value);
//				System.out.print(" Start = " + arr.get(i).peek().start);
//				System.out.println(" End = " + arr.get(i).peek().end);
				
				if (arr.get(i).peek().start < minElement.start) {
					minPosIndex = i;
//					System.out.println("Min Element Changed");
					minElement = arr.get(minPosIndex).peek();
					if(arr.get(i).isEmpty()) {
						arr.remove(i);
					}
				}
				else {
//					System.out.println("No change");
				}
			}
//			System.out.println("Min = " + arr.get(minPosIndex).peek().Value);
			minElement = arr.get(minPosIndex).dequeue();
//			System.out.println("Dequeue: " + minElement.Value);
			strArr.add(minElement.Value);
//			System.out.println(strArr.toString());
			q.enqueue(minElement);
			if(arr.get(minPosIndex).isEmpty()) {
				arr.remove(minPosIndex);
			}
//			System.out.println("End " + time);
			if(arr.size() == 1) {
//				time++;
//				System.out.println("Time = " + time);
				while(!arr.get(0).isEmpty()) {
//					System.out.println(" Three ");
					minElement = arr.get(0).dequeue();
					q.enqueue(minElement);
					strArr.add(minElement.Value);
//					System.out.println(strArr.toString());
//					System.out.println("End " + time);
//					time++;
				}
				return q;
			}
//			time++;
		}

		return q;
	}
	
	public static ArrayList<ExpressionNode> queueToArrList(Queue<ExpressionNode> queue) {
		ArrayList<ExpressionNode> resultArr = new ArrayList<ExpressionNode>();
		while(!queue.isEmpty()) {
			resultArr.add(queue.dequeue());
		}
		//Print Array List
//		for(int i = 0; i < resultArr.size(); i++) {
//			System.out.println(resultArr.get(i).Value);
//		}
		
		return resultArr;
	}
	
	public static Queue<ExpressionNode> arrToQueue(ArrayList<ExpressionNode> arr) {
		Queue<ExpressionNode> resultQueue = new Queue<ExpressionNode>();
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).Print();
			resultQueue.enqueue(arr.get(i));
		}
		return resultQueue;
	}
	
//	public static String[] stringToInfix(String s) {
//		String[] infix = new String[];
//		return infix;
//	}
	
	public static int Precedence(String op)
	{
	    if (op.equalsIgnoreCase("*") || op.equalsIgnoreCase("/") || op.equalsIgnoreCase("%")) {
	    	return 2;
	    }
	    else if (op.equalsIgnoreCase("+") || op.equalsIgnoreCase("-")) {
	        return 1;
	    }
	    return 0;
	}
	
	public static int min(int a1, int a2) {
		int min = a1;
		if(a2 < min) {
			min = a2;
		}
		return min;
	}
	public static int min(int a1, int a2, int a3) {
		return min(a1, min(a2, a3));
	}
	public static Long Calc(String opr1, String op, String opr2) {
		Long result = Long.parseLong("0");
		switch(op) {
			case "+":
				result = Long.parseLong(opr1) + Long.parseLong(opr2);
				break;
			case "-":
				result = Long.parseLong(opr1) - Long.parseLong(opr2);
				break;
			case "*":
				result = Long.parseLong(opr1) * Long.parseLong(opr2);
				break;
			case "/":
				result = Long.parseLong(opr1) / Long.parseLong(opr2);
				break;
			case "^":
				result = (long) Math.pow(Long.parseLong(opr1),Long.parseLong(opr2));
				break;
			default:
				break;
		}
		return result;
	}
}