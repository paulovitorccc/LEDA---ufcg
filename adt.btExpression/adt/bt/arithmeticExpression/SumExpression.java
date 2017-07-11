package adt.bt.arithmeticExpression;

public class SumExpression extends ArithmeticExpression {

	public SumExpression(String data, ArithmeticExpression left, ArithmeticExpression right,
			ArithmeticExpression parent) {
		super(data, left, right, parent);
	}

	public SumExpression() {

	}

	@Override
	public Integer evaluate() {
		ArithmeticExpression nodeLeft = ((ArithmeticExpression) getLeft());
		ArithmeticExpression nodeRight = ((ArithmeticExpression) getRight());
		return nodeLeft.evaluate() + nodeRight.evaluate();
	}
}
