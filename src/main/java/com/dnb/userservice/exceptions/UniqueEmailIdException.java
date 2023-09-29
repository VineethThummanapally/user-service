package com.dnb.userservice.exceptions;

public class UniqueEmailIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public UniqueEmailIdException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return super.toString() + " " + super.getMessage();
	}
}
