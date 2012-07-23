package tw.elliot.util;

public abstract class StringUtils {
	public static final boolean isEmpty(String src) {
		return (null == src || "".equals(src.trim()));
	}
	
	public static final boolean isNotEmpty(String src) {
		return !isEmpty(src);
	}
}
