/*******************************************************************************
 *
 * Project : ServicesBay V1
 *
 * Package :
 *
 * Date    :  Feb 16, 2015, 7:53 PM
 *
 * Author  : Services Bay Team
 *
 * File    : CacheManager.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;


/**
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 *This Class.... ENTER THE DESCRIPTION HERE
 */
public class ServiceLocatorException extends Exception {

	/**
	 * 
	 */
	public ServiceLocatorException() {
		super();

	}

	/**
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceLocatorException(Throwable cause) {
		super(cause);
	}

}
