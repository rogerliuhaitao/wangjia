package com.online.wangjialife.util;

import java.util.regex.PatternSyntaxException;

public class UtilRegex
{
	private static String	USER_NAME_ENG				= "^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$";
	private static String	USER_NAME_CHN				= "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
	private static String	EMAIL						= "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)";
	private static String	DIGITS						= "^[\\d]*$";
	private static String	ELEVEN_DIGITS				= "^(\\d{11})$";
	private static String	EIGHT_OR_ELEVEN_DIGITS		= "^(\\d{8}|\\d{11})$";
	private static String	LEAD_WITH_1_THEN_TEN_DIGITS	= "^[1][\\d]{10}$";
	private static String	LEAD_WITH_86	= "^[+|0]{1}86|^86";
	private static String	ALPHABET	                = "[a-zA-Z]";
	private static String	PASSWORD	                = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

	public static boolean isUserNameEng(String pTargetString)
	{
		return matches(pTargetString, USER_NAME_ENG);
	}

	public static boolean isUserNameChn(String pTargetString)
	{
		return matches(pTargetString, USER_NAME_CHN);
	}

	public static boolean isEmail(String pTargetString)
	{
		return matches(pTargetString, EMAIL);
	}

	public static boolean isDigits(String pTargetString)
	{
		return matches(pTargetString, DIGITS);
	}
	
	public static boolean isElevenDigits(String pTargetString)
	{
		return matches(pTargetString, ELEVEN_DIGITS);
	}
	
	public static boolean isElevenDigits(int pTarget)
	{
		return matches(pTarget, ELEVEN_DIGITS);
	}
	
	public static boolean isEightOrElevenDigits(String pTargetString)
	{
		return matches(pTargetString, EIGHT_OR_ELEVEN_DIGITS);
	}
	
	public static boolean isEightOrElevenDigits(int pTarget)
	{
		return matches(pTarget, EIGHT_OR_ELEVEN_DIGITS);
	}
	
	public static boolean isLeadWith1ThenTenDigits(String pTargetString)
	{
		return matches(pTargetString, LEAD_WITH_1_THEN_TEN_DIGITS);
	}
	
	public static boolean isLeadWith1ThenTenDigits(int pTarget)
	{
		return matches(pTarget, LEAD_WITH_1_THEN_TEN_DIGITS);
	}
	
	public static boolean isLeadWith86(String pTargetString)
	{
		return matches(pTargetString, LEAD_WITH_86);
	}

	public static boolean isLeadWith86(int pTarget)
	{
		return matches(pTarget, LEAD_WITH_86);
	}
	
	public static boolean isAlphabet(String pTargetString)
	{
		return matches(pTargetString, ALPHABET);
	}
	
	public static boolean isPassword(String pTargetString)
	{
		return matches(pTargetString, PASSWORD);
	}
	
	public static boolean matches(String pTargetString, String pRegex)
	{
		try {
			return pTargetString.matches(pRegex);
		} catch (NullPointerException e) {
			return false;
		} catch (PatternSyntaxException e) {
			return false;
		}
	}
	
	public static boolean matches(int pTarget, String pRegex)
	{
		String _TargetString = String.valueOf(pTarget);
		return matches(_TargetString, pRegex);
	}
}
