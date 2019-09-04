package org.yash.tcvm.exception;

import java.util.InputMismatchException;

public class InvalidChoiceException extends InputMismatchException {

	private static final long serialVersionUID = 1L;

	public InvalidChoiceException(String msg) {
		super(msg);
	}

}
