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
 * File    : Properties.java
 *
 * Copyright 2015 Miracle Software Systems, Inc. All rights reserved.
 * MIRACLE SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * *****************************************************************************
 */
package com.mss.msp.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author MrutyumjayaRao Chennu<mchennu@miraclesoft.com>
 *
 *This Class.... ENTER THE DESCRIPTION HERE
 */
public class Properties {

	private static final String BUNDLE_NAME = "com/mss/msp/config/msb";
	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getProperty(String property) {
		try {
			return RESOURCE_BUNDLE.getString(property);
		} catch (MissingResourceException e) {
			return '!' + property + '!';
		}
	}


}
