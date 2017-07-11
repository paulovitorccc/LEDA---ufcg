package adt.bt.arithmeticExpression;

public class ModExpression extends ArithmeticExpression {

	public ModExpression(String data, ArithmeticExpression left, ArithmeticExpression right,
			ArithmeticExpression parent) {
		super(data, left, right, parent);
	}

	public ModExpression() {

	}

	@Override
	public Integer evaluate() {
		ArithmeticExpression nodeLeft = ((ArithmeticExpression) getLeft());
		ArithmeticExpression nodeRight = ((ArithmeticExpression) getRight());
		return nodeLeft.evaluate() % nodeRight.evaluate();
	}
}
