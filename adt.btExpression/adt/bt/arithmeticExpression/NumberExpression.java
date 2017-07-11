package adt.bt.arithmeticExpression;

public class NumberExpression extends ArithmeticExpression {

	public NumberExpression(String data, ArithmeticExpression left, ArithmeticExpression right,
			ArithmeticExpression parent) {
		super(data, left, right, parent);
	}

	public NumberExpression() {

	}

	@Override
	public Integer evaluate() {
		return Integer.parseInt(getData());
	}
}
