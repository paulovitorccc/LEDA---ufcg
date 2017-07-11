package adt.bt.arithmeticExpression;

public class MultiplicationExpression extends ArithmeticExpression {

	public MultiplicationExpression(String data, ArithmeticExpression left, ArithmeticExpression right,
			ArithmeticExpression parent) {
		super(data, left, right, parent);
	}

	public MultiplicationExpression() {

	}

	@Override
	public Integer evaluate() {
		ArithmeticExpression nodeLeft = ((ArithmeticExpression) getLeft());
		ArithmeticExpression nodeRight = ((ArithmeticExpression) getRight());
		return nodeLeft.evaluate() * nodeRight.evaluate();
	}
}
