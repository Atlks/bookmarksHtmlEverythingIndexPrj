package com.attilax.util;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;
import static com.mysql.cj.util.StringUtils.safeTrim;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.exceptions.WrongArgumentException;

/**
 * package com.mysql.cj.conf;ConnectionUrlParser
 * @author zhoufeiyue
 *
 */
public class NaveValuePairUtil {
    private static final Pattern PROPERTIES_PTRN = Pattern.compile("[&\\s]*(?<key>[\\w\\.\\-\\s%]*)(?:=(?<value>[^&=]*))?");

	public static void main(String[] args) {
		String mysqlConnUrl = "jdbc:mysql://47.100.12.36:3306/tt_pre?user=root&password=hhh";
	//	System.out.println( processKeyValuePattern (mysqlConnUrl ));
		System.out.println( processKeyValuePattern ("user=root&password=hhh" ));

	}
	
	public static Map<String, String> processKeyValuePattern(  String input)
	{
		return new NaveValuePairUtil().processKeyValuePattern(PROPERTIES_PTRN, input);
	}
	
	 /**
     * Takes a two-matching-groups (respectively named "key" and "value") pattern which is successively tested against the given string and produces a key/value
     * map with the matched values. The given pattern must ensure that there are no leftovers between successive tests, i.e., the end of the previous match must
     * coincide with the beginning of the next.
     * 
     * @param pattern
     *            the regular expression pattern to match against to
     * @param input
     *            the input string
     * @return a key/value map containing the matched values
     */
    private Map<String, String> processKeyValuePattern(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        int p = 0;
        Map<String, String> kvMap = new HashMap<>();
        while (matcher.find()) {
            if (matcher.start() != p) {
                throw ExceptionFactory.createException(WrongArgumentException.class,
                        Messages.getString("ConnectionString.4", new Object[] { input.substring(p) }));
            }
            String key = decode(safeTrim(matcher.group("key")));
            String value = decode(safeTrim(matcher.group("value")));
            if (!isNullOrEmpty(key)) {
                kvMap.put(key, value);
            } else if (!isNullOrEmpty(value)) {
                throw ExceptionFactory.createException(WrongArgumentException.class,
                        Messages.getString("ConnectionString.4", new Object[] { input.substring(p) }));
            }
            p = matcher.end();
        }
        if (p != input.length()) {
            throw ExceptionFactory.createException(WrongArgumentException.class, Messages.getString("ConnectionString.4", new Object[] { input.substring(p) }));
        }
        return kvMap;
    }
    
    /**
     * URL-decode the given string.
     * 
     * @param text
     *            the string to decode
     * @return
     *         the decoded string
     */
    private static String decode(String text) {
        if (isNullOrEmpty(text)) {
            return text;
        }
        try {
            return URLDecoder.decode(text, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // Won't happen.
        }
        return "";
    }

}
