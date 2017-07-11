package adt.bt.arithmeticExpression;

import adt.bt.BTNode;

/**
 * An implementation of a BTExpression. Its root contains an Arithmetic Expression.
 * Some methods do not make sense in this kind of tree.  
 */
public class BTExpressionImpl implements BTExpression {

	private ArithmeticExpression root;
	
	public BTExpressionImpl() {
		root = new NumberExpression();
	}
	
	@Override
	public BTNode<String> getRoot() {
		return this.root;
	}

	public void setRoot(ArithmeticExpression newRoot) {
		this.root = newRoot;
	}
	
	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BTNode<String> search(String elem) {
		throw new RuntimeException("Method does not make sense");
	}

	@Override
	public void insert(String value) {
		throw new RuntimeException("Method does not make sense");
	}

	@Override
	public void remove(String key) {
		throw new RuntimeException("Method does not make sense");	
	}

	@Override
	public String[] preOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] order() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] postOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer evaluate() {
		return this.root.evaluate();
	}

}
