package adt.queue;

public class QueueOverFlowException extends Exception {

	public QueueOverFlowException() {
		super("Fila cheia!");
	}
}
