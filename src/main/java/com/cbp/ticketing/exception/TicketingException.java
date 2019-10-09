package com.cbp.ticketing.exception;


public class TicketingException extends Exception {
	private static final long serialVersionUID = -910323501351378247L;

	private REErrorCodes errorCode = REErrorCodes.UNEXPECTED_ERROR;
	
	private String additionalInfo;

	public REErrorCodes getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public TicketingException(REErrorCodes errorCode, String message) {
		super(message);
		this.additionalInfo = message;
		this.errorCode = errorCode;
	}

	public TicketingException(REErrorCodes userNameAlreadyExists) {
		// TODO Auto-generated constructor stub
	}


}
