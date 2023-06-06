package com.emids.springbootdemo.exception;

public class ShareNotFoundException extends Exception{

	private static final long serialVersionUID = 4281772684268915406L;

	public ShareNotFoundException() {
		super();	
	}

	public ShareNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ShareNotFoundException(String message) {
		super(message);		
	}

	public ShareNotFoundException(Throwable cause) {
		super(cause);
	}
	
	

}
