package aOPtool;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringEscapeUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import ezvcard.util.HtmlUtils;
import javassist.expr.NewArray;

public class jsonSeri {

	public static void main(String[] args) {
		String html = "第       一     个    。。";
	//	System.out.println(StringEscapeUtils.unescapeHtml4(html));
		System.out.println(htmlEncode(html));
		System.out.println(JSON.toJSON(new LinkedHashMap () {
			{
				put("con", html);
				put("age", 1);
				put("con_htmlEncode", htmlEncode(html));

			}
		}));
	//	Maps.new
		

	}
	
	
	public static String htmlEncode(String source) {
        if(source == null) {
           return "";
        }
       String html = "";
       StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < source.length(); i++) {
           char c = source.charAt(i);
           switch (c) {
           case '<':
               buffer.append("<");
               break;
           case '>':
               buffer.append(">");
               break;
           case '&':
               buffer.append("&");
               break;
           case '"':
               buffer.append("\"");
               break;
           case ' ':
               buffer.append("&nbsp;");
               break;
           case 10:
           case 13:
        	   buffer.append("<br>");
               break;
           default:
               buffer.append(c);
           }
        }
       html = buffer.toString();
       return html;
    }
 
 

}
