package adt.bt.arithmeticExpression;

public class SubtractionExpression extends ArithmeticExpression {

	public SubtractionExpression(String data, ArithmeticExpression left, ArithmeticExpression right,
			ArithmeticExpression parent) {
		super(data, left, right, parent);
	}

	public SubtractionExpression() {

	}

	@Override
	public Integer evaluate() {
		ArithmeticExpression nodeLeft = ((ArithmeticExpression) getLeft());
		ArithmeticExpression nodeRight = ((ArithmeticExpression) getRight());
		return nodeLeft.evaluate() - nodeRight.evaluate();
	}
}
