package adt.bt.arithmeticExpression;

public class DivisionExpression extends ArithmeticExpression {

	public DivisionExpression(String data, ArithmeticExpression left, ArithmeticExpression right,
			ArithmeticExpression parent) {
		super(data, left, right, parent);
	}

	public DivisionExpression() {

	}

	@Override
	public Integer evaluate() {
		ArithmeticExpression nodeLeft = ((ArithmeticExpression) getLeft());
		ArithmeticExpression nodeRight = ((ArithmeticExpression) getRight());
		return nodeLeft.evaluate() / nodeRight.evaluate();
	}
}
