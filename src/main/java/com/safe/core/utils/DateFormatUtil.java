package com.safe.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
public class DateFormatUtil {
	/**
	 * 
	 * @Title: longToString
	 * @Description:��13λ�ĺ���ֵת��ΪString���͸�ʽ����ʱ���ʽ
	 * @param time
	 * @return:
	 * @throws
	 */
	public static String longToString(long time) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = sdf.format(date);
		return newDate;

	}

	/**
	 * 
	 * @Title: DateToString
	 * @Description:
	 * @param time
	 * @return:
	 * @throws
	 */
	public static String DateToString(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = sdf.format(time);
		return newDate;
	}

	public static String DateToString(Date time, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		String newDate = sdf.format(time);
		return newDate;
	}

	/**
	 * 
	 * @Title: parseDate
	 * @Description: ��String������ʽ��ʱ��ת��Ϊdate
	 * @param stringTime
	 * @return:
	 * @throws
	 */
	public static Date parseDate(String stringTime) {
		Date date = null;
		if (StringUtils.isNotBlank(stringTime)) {
			String[] pattern = new String[] { "yyyy��MM��dd��", "yyyy��MM��dd",
					"yyyy-MM", "yyyy��MM��dd��", "yyyyMM", "yyyy/MM", "yyyyMMdd",
					"yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMddHHmmss",
					"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
					"yyyy/MM/dd HH:mm:ss",
					"MM��dd��", "MM��dd",
					"MM", "MM��dd��", "MM", "MMdd",
					"MM-dd", "/MM/dd", "MMddHHmmss",
					"MM-dd HH:mm:ss", "MM-dd HH:mm",
					"MM/dd HH:mm:ss",
			};
			try {
				date = DateUtils.parseDate(stringTime, pattern);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;

	}

	/**
	 * 
	 * @Description: ��String��ʽ��ʱ��ת��Ϊdate
	 * @param stringTime
	 * @return:
	 * @throws
	 */
	public static Date formatDate(String stringTime) {
		Date date = null;
		if (StringUtils.isNotBlank(stringTime)) {
			String[] pattern = new String[] { "yyyy��MM��dd��HHʱmm��ss��",
					"yyyy��MM��dd�� HHʱmm��ss��", "yyyy��MM��dd��HHʱmm��",
					"yyyy��MM��dd�� HHʱmm��", "yyyy��MM��dd�� HHʱ", "yyyy��MM��dd��HHʱ",
					"yyyy��MM��dd��", "yyyy��MM��dd��HH:mm:ss",
					"yyyy��MM��dd�� HH:mm:ss", "yyyy��MM��dd��HH:mm",
					"yyyy��MM��dd�� HH:mm", "yyyy��MM��dd�� HH", "yyyy��MM��dd��HH",
					"yyyy-MM-dd HHʱmm��ss��", "yyyy-MM-dd HHʱmm��",
					"yyyy-MM-dd HHʱ", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
					"yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
					"yyyy/MM/dd HHʱmm��ss��", "yyyy/MM/dd HHʱmm��",
					"yyyy/MM/dd HHʱ", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
					"yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy.MM.dd HH:mm:ss",
					"yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM.dd",
					"yyyyMMdd", };
			try {
				date = DateUtils.parseDate(stringTime, pattern);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;

	}

	/**
	 * 
	 * @Description: ���ַ����н�ȡ����ȷ��ʱ��
	 * @param stringTime
	 * @return:
	 * @throws
	 */
	public static Date cutDate(String stringTime) {
		String regs[] = { "\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}��\\d{2}��\\d{2}��\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{1}ʱ\\d{2}��",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}��\\d{2}��\\d{2}��\\d{2}ʱ\\d{2}��",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}ʱ",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{1}ʱ",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}ʱ",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{1}ʱ",
				"\\d{4}��\\d{2}��\\d{2}��\\d{2}ʱ", "\\d{4}��\\d{2}��\\d{2}��",
				"\\d{4}��\\d{2}��\\d{1}��", "\\d{4}��\\d{1}��\\d{2}��",
				"\\d{4}��\\d{1}��\\d{1}��",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}:\\d{1}:\\d{2}",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}:\\d{1}:\\d{2}",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}:\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{1}:\\d{2}",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}:\\d{2}",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{1}:\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\d{2}:\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{2}",
				"\\d{4}��\\d{2}��\\d{2}��\\s\\d{1}",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{2}",
				"\\d{4}��\\d{1}��\\d{2}��\\s\\d{1}",
				"\\d{4}��\\d{2}��\\d{2}��\\d{2}",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{2}", "\\d{4}-\\d{2}-\\d{2}",
				"\\d{4}-\\d{2}-\\d{1}", "\\d{4}-\\d{1}-\\d{2}",
				"\\d{4}-\\d{1}-\\d{1}",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}-\\d{1}-\\d{1}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{2}ʱ",
				"\\d{4}-\\d{2}-\\d{2}\\s\\d{1}ʱ",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{2}ʱ",
				"\\d{4}-\\d{1}-\\d{2}\\s\\d{1}ʱ", "\\d{4}.\\d{2}.\\d{2}",
				"\\d{4}.\\d{2}.\\d{1}", "\\d{4}.\\d{1}.\\d{2}",
				"\\d{4}.\\d{1}.\\d{1}",
				"\\d{4}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}.\\d{2}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}.\\d{1}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}.\\d{1}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}.\\d{1}.\\d{1}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{4}.\\d{2}.\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{4}.\\d{1}.\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{4}.\\d{1}.\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{4}.\\d{2}.\\d{2}\\s\\d{2}",
				"\\d{4}.\\d{2}.\\d{2}\\s\\d{1}",
				"\\d{4}.\\d{1}.\\d{2}\\s\\d{2}",
				"\\d{4}.\\d{1}.\\d{2}\\s\\d{1}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{2}ʱ",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{1}ʱ",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{2}ʱ",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{1}ʱ",
				"\\d{4}/\\d{2}/\\d{2}",
				"\\d{4}/\\d{2}/\\d{1}",
				"\\d{4}/\\d{1}/\\d{2}",
				"\\d{4}/\\d{1}/\\d{1}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{2}",
				"\\d{4}/\\d{2}/\\d{2}\\s\\d{1}",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{2}",
				"\\d{4}/\\d{1}/\\d{2}\\s\\d{1}",
				"\\d{2}��\\d{2}��\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{2}��\\d{2}��\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{1}��\\d{2}��\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{1}��\\d{2}��\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{2}��\\d{2}��\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{2}��\\d{2}��\\s\\d{2}ʱ\\d{2}��",
				"\\d{1}��\\d{2}��\\s\\d{2}ʱ\\d{2}��",
				"\\d{1}��\\d{2}��\\s\\d{1}ʱ\\d{2}��",
				"\\d{1}��\\d{2}��\\s\\d{2}ʱ\\d{2}��",
				"\\d{2}��\\d{2}��\\d{2}ʱ\\d{2}��",
				"\\d{2}��\\d{2}��\\s\\d{2}ʱ",
				"\\d{2}��\\d{2}��\\s\\d{1}ʱ",
				"\\d{1}��\\d{2}��\\s\\d{2}ʱ",
				"\\d{1}��\\d{2}��\\s\\d{1}ʱ",
				"\\d{2}��\\d{2}��\\d{2}ʱ", "\\d{4}��\\d{2}��\\d{2}��",
				"\\d{2}��\\d{1}��", "\\d{4}��\\d{1}��\\d{2}��",
				"\\d{1}��\\d{1}��",
				"\\d{2}��\\d{2}��\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{2}��\\d{2}��\\s\\d{2}:\\d{1}:\\d{2}",
				"\\d{1}��\\d{2}��\\s\\d{2}:\\d{1}:\\d{2}",
				"\\d{1}��\\d{2}��\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{2}��\\d{2}��\\d{2}:\\d{2}:\\d{2}",
				"\\d{2}��\\d{2}��\\s\\d{2}:\\d{2}",
				"\\d{2}��\\d{2}��\\s\\d{1}:\\d{2}",
				"\\d{1}��\\d{2}��\\s\\d{2}:\\d{2}",
				"\\d{1}��\\d{2}��\\s\\d{1}:\\d{2}",
				"\\d{2}��\\d{2}��\\d{2}:\\d{2}",
				"\\d{2}��\\d{2}��\\s\\d{2}",
				"\\d{2}��\\d{2}��\\s\\d{1}",
				"\\d{1}��\\d{2}��\\s\\d{2}",
				"\\d{1}��\\d{2}��\\s\\d{1}",
				"\\d{2}��\\d{2}��\\d{2}",
				"\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{2}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{1}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{1}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{2}-\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{2}-\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{2}-\\d{2}\\s\\d{2}", "\\d{4}-\\d{2}-\\d{2}",
				"\\d{2}-\\d{1}", "\\d{4}-\\d{1}-\\d{2}",
				"\\d{1}-\\d{1}",
				"\\d{2}-\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{2}-\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{1}-\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{1}-\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{1}-\\d{1}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{2}-\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{2}-\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{1}-\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{1}-\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{2}-\\d{2}\\s\\d{2}ʱ",
				"\\d{2}-\\d{2}\\s\\d{1}ʱ",
				"\\d{1}-\\d{2}\\s\\d{2}ʱ",
				"\\d{1}-\\d{2}\\s\\d{1}ʱ", "\\d{4}.\\d{2}.\\d{2}",
				"\\d{2}.\\d{1}", "\\d{4}.\\d{1}.\\d{2}",
				"\\d{1}.\\d{1}",
				"\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{2}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{1}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{1}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{1}.\\d{1}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{2}.\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{2}.\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{1}.\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{1}.\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{2}.\\d{2}\\s\\d{2}",
				"\\d{2}.\\d{2}\\s\\d{1}",
				"\\d{1}.\\d{2}\\s\\d{2}",
				"\\d{1}.\\d{2}\\s\\d{1}",
				"\\d{2}/\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{2}/\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{1}/\\d{2}\\s\\d{2}ʱ\\d{2}��\\d{2}��",
				"\\d{1}/\\d{2}\\s\\d{1}ʱ\\d{2}��\\d{2}��",
				"\\d{2}/\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{2}/\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{1}/\\d{2}\\s\\d{2}ʱ\\d{2}��",
				"\\d{1}/\\d{2}\\s\\d{1}ʱ\\d{2}��",
				"\\d{2}/\\d{2}\\s\\d{2}ʱ",
				"\\d{2}/\\d{2}\\s\\d{1}ʱ",
				"\\d{1}/\\d{2}\\s\\d{2}ʱ",
				"\\d{1}/\\d{2}\\s\\d{1}ʱ",
				"\\d{2}/\\d{2}",
				"\\d{2}/\\d{1}",
				"\\d{1}/\\d{2}",
				"\\d{1}/\\d{1}",
				"\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{2}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{1}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
				"\\d{1}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
				"\\d{2}/\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{2}/\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{1}/\\d{2}\\s\\d{2}:\\d{2}",
				"\\d{1}/\\d{2}\\s\\d{1}:\\d{2}",
				"\\d{2}/\\d{2}\\s\\d{2}",
				"\\d{2}/\\d{2}\\s\\d{1}",
				"\\d{1}/\\d{2}\\s\\d{2}",
				"\\d{1}/\\d{2}\\s\\d{1}",
		};

		String str = "";
		Date date = null;
		for (String reg : regs) {
			String temp = match(reg, stringTime);
			if (temp.length() > str.length()) {
				str = temp;
				if (!"".equals(str)) {
					// System.err.println(reg);
					date = formatDate(str);
				}
			}

		}
		return date;

	}

	public static String match(String reg, String stringTime) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(stringTime);
		String s = "";
		if (m.find()) {
			s += m.group();
		}
		return s;
	}

	public static String addMonth(String time) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = parseDate(time);
		date = DateUtils.addDays(date, 1);
		String first = df.format(date);
		return first;
	}

	@Test
	public void formateTest() {
		// Date date =
		// cutDate("���Թ��������ַ���873����2015��12��22��02:09	���ֳ����վ�Ԯָ2015��12��22�ջӲ���������Ϣ������22���賿���ӱ�ʧ����Ա���½�Ϊ81�ˡ�");
		// System.err.println(date.getTime());
		// System.err.println(new Date(1462352940632l));

		String stringTime = "http://www.chinasafety.gov.cn/newpage/Contents/Channel_20135/2011/0921/151059/content_151059.htm";
		if (StringUtils.isNotBlank(stringTime)) {
			String reg = "Channel_\\d{4,6}";
			String channelStr = match(reg, stringTime);
			String channelNo = match("\\d{4,6}", channelStr);
			System.out.println(channelNo);
		}

	}

	/*
	 * public static void main(String[] args) { // String
	 * str="Thu Sep 08 2011 00:00:00 GMT+0800 "; String
	 * str="Thu May 28 14:45:43 +0800 2015"; SimpleDateFormat sdf=new
	 * SimpleDateFormat("EEE MMM dd HH:mm:ss +0800 yyyy",Locale.ENGLISH); try {
	 * Date date = sdf.parse(str); String time = DateToString(date);
	 * System.err.println(date); System.err.println(time); } catch
	 * (ParseException e) { e.printStackTrace(); } }
	 */
	public static void main(String[] args) {
		// String time = "20131101";
		// System.out.println(formatDate(time));
		// long timeLong = formatDate(time).getTime();
		// System.out.println(timeLong/1000);
		// System.err.println(new Date(1482328437000l));
		// System.err.println(cutDate("2016-10-6 "));
		// System.err.println(parseDate("2016��03��01��"));
		// System.err.println(cutDate("2016/08/15").getTime());
		// String targetId = "http://news.sohu.com/20160323/n441639453.shtml";
		// if (targetId.contains("/n") && targetId.contains(".shtml")) {
		// String newsId = targetId.substring(targetId.lastIndexOf("/n") + 2,
		// targetId.lastIndexOf(".shtml"));
		// System.err.println(newsId);
		// }
		// Date now = new Date();
		// long endTime = now.getTime() + 7 * 24 * 60 * 60 * 1000;
		String stringTime = "2017��01��10�� 19��35�����㽭ʡ�����д�Ϫ�У�����������һ���·������ҵ��·�����¹ʣ����1��������0�����ˣ�����0�����ˣ���";
		Date date = cutDate(stringTime);
		System.out.println(date);
		System.out.println(DateToString(date));
	}
}
