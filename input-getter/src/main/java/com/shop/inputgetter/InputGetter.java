package com.shop.inputgetter;

import java.util.Scanner;

/**
 * To get input.
 * 
 * @author SathishKumarS
 */
public class InputGetter {

	private static InputGetter inputGetter;
	public static final Scanner SCANNER = new Scanner(System.in);

	private InputGetter() {

	}

	/**
	 * Gets string value
	 *
	 * @param content
	 * @return
	 */
	public String getString(String content) {
		System.out.println(content);
		return SCANNER.next().trim();
	}

	/**
	 * Gets integer value
	 *
	 * @param content
	 * @return
	 */
	public int getInt(String content) {
		System.out.println(content);

		try {
			return SCANNER.nextInt();
		} catch (Exception exception) {
			System.out.println("\n Invalid Input \n  Please Use Numbers Only");
			return getInt(content);
		}
	}

	/**
	 * Gets double value
	 *
	 * @param content
	 * @return
	 */
	public double getDouble(String content) {
		System.out.println(content);

		try {
			return SCANNER.nextDouble();
		} catch (Exception exception) {
			System.out.println("\n Invalid Input \n  Please Use Numbers Only");
			return getDouble(content);
		}
	}

	/**
	 * Gets character value
	 *
	 * @param content
	 * @return
	 */
	public char getChar(String content) {
		System.out.println(content);

		try {
			return SCANNER.next().charAt(0);
		} catch (Exception exception) {
			System.out.println("\n Invalid Input \n  Please Use Single Character Only");
			return getChar(content);
		}
	}

	public static InputGetter getInstance() {
		if (inputGetter == null) {
			inputGetter = new InputGetter();
		}
		return inputGetter;
	}
}
