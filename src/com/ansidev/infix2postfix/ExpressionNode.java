package com.ansidev.infix2postfix;

public class ExpressionNode {
	String Value;
	int start, end;
	int Type;
	// 1: 0-9 e {pi}
	// 2: + - * / % ^
	// 3: sin cos tan log ln factor opposite 
	// 4: ( )
	
//	public enum Type {
//		OPERAND(1),
//		OPERATOR(2),
//		FUNCTION(3);
//		private int Value;
//		private Type(int value) {
//			Value = value;
//		}
//		public int get() {
//			return Value;
//		}
//		public void set(int value) {
//			Value = value;
//		}
//		
//	};
	
	public ExpressionNode() {
		Value = null;
		start = end = -1;
	}
	
	public ExpressionNode(String expr, int sPos, int ePos, int type) {
		Value = expr;
		start = sPos;
		end = ePos;
//		Type.set(type);
		Type = type;
	}
	
	public ExpressionNode(String expr, int sPos, int ePos) {
		Value = expr;
		start = sPos;
		end = ePos;
	}

	public void Print() {
		if(this.Value != null) {
//			System.out.println("Value: " + Value);
//			System.out.println("Start: " + Integer.toString(start));
//			System.out.println("End: " + Integer.toString(end));
		}
	}
}
