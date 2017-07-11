package dataEstructures.elementary;

public class StackUnderflowException extends Exception {

	public StackUnderflowException() {
		super("Stack is empty");
	}
}
