package gordon.mySpring.util;

import java.text.SimpleDateFormat;

public class SimpleTypeConverter {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

	public static Object convertTo(Class<?> type, String propertyValue) throws Exception {
		if (type == String.class) {
			return propertyValue;
		} else if (type == Integer.class || type == int.class) {
			return Integer.parseInt(propertyValue);
		} else if (type == Long.class || type == long.class) {
			return Long.parseLong(propertyValue);
		} else if (type == Float.class || type == float.class) {
			return Float.parseFloat(propertyValue);
		} else if (type == Double.class || type == double.class) {
			return Double.parseDouble(propertyValue);
		} else if (type == Boolean.class || type == boolean.class) {
			return Boolean.parseBoolean(propertyValue);
		} else if (type == Character.class || type == char.class) {
			return propertyValue.charAt(0);
		} else if (type == Short.class || type == short.class) {
			return Short.parseShort(propertyValue);
		} else if (type == Byte.class || type == byte.class) {
			return Byte.parseByte(propertyValue);
		}

		if (type == java.util.Date.class) {
			return sdf.parse(propertyValue);
		}

		return propertyValue;
	}

}
