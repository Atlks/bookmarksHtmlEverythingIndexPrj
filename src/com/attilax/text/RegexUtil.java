package com.attilax.text;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RegexUtil {

	public static void main(String[] args) {
		String s = "���-������-134859633������";
		// String s =
		// "�ҵ��ֻ�����18837112195�������ù�18888888888�����ù�18812345678";
		// List<String> li=getTels(s);
		List<String> li = getYears("2019年1月23日danshi1997gengge 2007dthing,562012年30 302019城市排名");
		System.out.println(li);

	}

	public static String getTel(String s) {
		List<String> li = getTels(s);
		if (li.isEmpty())
			return "";
		return li.get(0);

	}

	public static List<String> getYears(String s) {
		List<String> li = Lists.newArrayList();
		String regex = "([^\\d]?19\\d{2}[^\\d]?)|([^\\d]?20[0-2]\\d{1}[^\\d]?)"; // [12]d{3}\\d+{4}
	//	 regex = "[^\\d]?20[0-2]\\d{1}[^\\d]?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);

		while (m.find()) { // һ����Ҫ�Ȳ����ٵ���group��ȡ�绰����
			String group = m.group().trim();
			group = group.trim();
			// trimNoDigitChar(group)
			group = trimNoDigitChar(group);
			li.add(group);
			// System.out.println(group);
			// System.out.println(JSON.toJSONString(m));
		}
		return li;
	}

	private static String trimNoDigitChar(String group) {
		if (!Character.isDigit(group.charAt(0))) {
			group = group.substring(1);
		}
		if (!Character.isDigit(group.charAt(group.length() - 1))) // last pos
		{
			group = group.substring(0, group.length() - 1);
		}
		return group;
	}

	public static List<String> getTels(String s) {
		List<String> li = Lists.newArrayList();
		String regex = "1\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);

		while (m.find()) { // һ����Ҫ�Ȳ����ٵ���group��ȡ�绰����
			String group = m.group().trim();

			li.add(group);
			// System.out.println(group);
			// System.out.println(JSON.toJSONString(m));
		}
		return li;
	}

	public static Set<String> getYears(String html, int start, int end) {
		Set<String> st = Sets.newLinkedHashSet();
		List<String> years = getYears(html);
		st.addAll(years);
		List<String> collect = st.stream().filter(s -> Integer.parseInt(s) > start && Integer.parseInt(s) < end)
				.collect(Collectors.toList());
		Set<String> st2 = Sets.newLinkedHashSet();
		st2.addAll(collect);
		return st2;
	}

}
