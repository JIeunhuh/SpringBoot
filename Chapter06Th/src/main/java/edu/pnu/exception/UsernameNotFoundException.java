package edu.pnu.exception;

public class UsernameNotFoundException extends BoardException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException(String message) {
		super(message);
	}
}
