package bank.manager.data.models;

public class InsufficientFundsException extends Exception {

	public InsufficientFundsException(String m) {
		super(m);
	}
}
