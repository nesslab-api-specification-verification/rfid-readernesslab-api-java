package api.reader.nesslab.exceptions;

public class SessionFullException extends Exception {

	private static /*@ spec_public @*/final long serialVersionUID = 1L;
	//@ public constraint serialVersionUID == \old(serialVersionUID);
	
	public SessionFullException(String message) {
		super(message);
	}

}
