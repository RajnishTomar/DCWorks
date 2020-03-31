package com.hcl.bpm.dcworks.exception;

/**
 * The Interface Errors.
 * 
 * this interface will implements by every error code class
 */
public interface Errors {

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode();

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage();

}
