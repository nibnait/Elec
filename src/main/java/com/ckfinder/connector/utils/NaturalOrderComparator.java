/*
 * CKFinder
 * ========
 * http://ckfinder.com
 * Copyright (C) 2007-2013, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 */
package com.ckfinder.connector.utils;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator to sort files and directores in correct oredr.
 */
public class NaturalOrderComparator implements Comparator<String>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6904946416126759199L;
	private static final char ZERO = '0';
	private static final char NINE = '9';

	/**
	 * comapres two string in natural sort order.
	 *
	 * @param string1 first string
	 * @param string2 second string
	 * @return 1 if first string is greater, 0 if both are equals or -1 when
	 * second string is greater.
	 */
	@Override
	public int compare(final String string1, final String string2) {
		String string1Copy = string1.toLowerCase();
		String string2Copy = string2.toLowerCase();
		int len1 = string1.length();
		int len2 = string2.length();
		String liczba1 = "";
		String liczba2 = "";
		for (int i = 0; i < ((len1 > len2) ? len1 : len2); i++) {
			if (i == len1 && len2 > len1) {
				return -1;
			}
			if (i == len2 && len1 > len2) {
				return 1;
			}
			Character c1 = string1Copy.charAt(i);
			Character c2 = string2Copy.charAt(i);
			if (isDigit(c1) && isDigit(c2)) {
				liczba1 += c1;
				liczba2 += c2;
			} else if (isDigit(c1) && liczba1.length() > 0) {
				return 1;
			} else if (isDigit(c2) && liczba1.length() > 0) {
				return -1;
			} else {
				if (!liczba1.equals(liczba2)) {
					return liczba1.compareTo(liczba2);
				}
				liczba1 = "";
				liczba2 = "";
				if (!c1.equals(c2)) {
					return c1.compareTo(c2);
				}
			}
		}
		return string1Copy.compareTo(string2Copy);
	}

	/**
	 * checks if char is digit.
	 *
	 * @param c char to check
	 * @return true if char is digit
	 */
	private boolean isDigit(final char c) {
		return (c >= ZERO && c < NINE);
	}
}
