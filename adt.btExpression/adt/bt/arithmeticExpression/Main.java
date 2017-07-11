package adt.bt.arithmeticExpression;

import adt.bt.BTNode;

public class Main {

	private static ArithmeticExpression num1;
	private static ArithmeticExpression num2;
	private static ArithmeticExpression num3;
	private static ArithmeticExpression num4;
	private static ArithmeticExpression soma;
	private static ArithmeticExpression sub;

	public static void main(String[] args) {

			BTExpressionImpl bt = new BTExpressionImpl();
			
			
		
			num1 = new NumberExpression("3", null, null, null);
			num2 = new NumberExpression("2", null, null, null);
			num3 = new NumberExpression("3", null, null, null);
			num4 = new NumberExpression("1", null, null, null);
			soma = new SumExpression("+", num1, num2, null);
			sub = new SubtractionExpression("-", num3, num4, null);
			
			ArithmeticExpression mult = new MultiplicationExpression("*", soma,	sub, null); 
			bt.setRoot(mult);
			
			soma.setParent(mult);
			sub.setParent(mult);
			
			System.out.println(bt.evaluate());
		
		
		
	}

}
