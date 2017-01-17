package br.com.sample.util;

public class StringUtil {

	private static StringUtil stringUtil;

	private StringUtil() {
	}

	public static StringUtil getInstance() {
		if (stringUtil == null) {
			synchronized (StringUtil.class) {
				if (stringUtil == null) {

					stringUtil = new StringUtil();
				}
			}
		}

		return stringUtil;
	}

	public static StringUtil getNewInstance() {
		return new StringUtil();
	}

	public boolean isEmpty(String... strings) {
		if (strings != null) {
			for (String string : strings) {
				if (string == null || string.trim().isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

}
