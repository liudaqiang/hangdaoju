package com.hangdaoju.core;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 集成工具类
 * 
 * @author Wangchun
 */
public class Tools {

	public static String str; // 中转缓存

	/**
	 * 判断一个字符串的值是否为null或为空
	 * 
	 * @param param
	 *            要判断的字符串
	 * @return 若该字符串的值为null或为空，返回true，否则返回false
	 */
	public static boolean isNullOrEmptyString(String param) {
		return param == null || param.isEmpty();
	}

	/**
	 * 判断一个数组是否为null或为空
	 * 
	 * @param params
	 *            要判断的数组
	 * @return 若该数组为null或为空，返回true，否则返回false
	 */
	public static boolean isNullOrEmptyObjects(Object[] params) {
		return params == null || params.length == 0;
	}

	/**
	 * 判断一个集合是否为null或为空
	 * 
	 * @param collection
	 *            要判断的集合
	 * @return 若该集合为null或为空，返回true，否则返回false
	 */
	public static boolean isNullOrEmptyCollection(Collection<? extends Object> collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * 判断一个对象是否为null或为空
	 * 
	 * @param params
	 *            要判断的数组
	 * @return 若该数组为null或为空，返回true，否则返回false
	 */
	public static boolean isNullOrEmptyObject(Object obj) {
		return obj == null || obj.toString().length() == 0;
	}

	/**
	 * 接口返回信息
	 * 
	 * @param code
	 *            状态码
	 * 
	 * @param info
	 *            返回的信息
	 * 
	 * @return 接口返回的信息
	 */
	public static ResponseModel apiReturn(int code, Object info, String information) {

		Map<String, Object> data = new HashMap<>();
		data.put("code", code);
		data.put("info", info);
		return new ResponseModel().setRet(200).setData(data).setInformation(information);
	}

	/**
	 * 验证是否为邮箱格式
	 * 
	 * @param email
	 *            邮箱账号
	 * 
	 * @return true 是,false不是
	 */
	public static boolean isEmail(String email) {
		return email.matches("^[a-zA-Z0-9][-\\w.+]*@([\\w][-\\w]*\\.)+[a-zA-Z]{2,14}$");
	}

	/**
	 * 验证是否为手机号格式
	 * 
	 * @param mobile
	 *            邮箱账号
	 * 
	 * @return true 是,false不是
	 */
	public static boolean isMobile(String mobile) {
		return mobile.matches("^1[0-9]{10}$");
	}

	/**
	 * 验证是否为密码格式
	 * 
	 * @param password
	 *            密码
	 * 
	 * @return true 是,false不是
	 */
	public static boolean isPassword(String password) {
		return password.matches("^[\\p{Punct}a-zA-Z0-9]{6,16}$");
	}

	/**
	 * 字符串中是否含有空字符
	 * 
	 * @param name
	 * 
	 * @return true 是,false不是
	 */
	public static boolean isName(String name) {
		Matcher matcher = Pattern.compile(" ").matcher(name);
		return matcher.find();
	}

	/**
	 * 获得客户端真实IP地址
	 * 
	 * @return ip 客户端真实ip地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		ip = getTrueIp(ip);

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			ip = getTrueIp(ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			ip = getTrueIp(ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			ip = getTrueIp(ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			ip = getTrueIp(ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			ip = getTrueIp(ip);
		}
		return ip;
	}

	/**
	 * 过滤私有ip
	 * 
	 * @return 过滤后的ip
	 */
	private static String getTrueIp(String ip) {
		if (ip == null || "".equals(ip))
			return null;
		if (ip.indexOf(",") != -1) {
			String[] ipAddr = ip.split(",");
			for (int i = 0; i < ipAddr.length; i++) {
				if (isIp(ipAddr[i].trim()) && !ipAddr[i].trim().startsWith("10.")
						&& !ipAddr[i].trim().startsWith("172.16") && !ipAddr[i].trim().startsWith("192.168")) {
					return ipAddr[i].trim();
				}
			}
		} else {
			if (isIp(ip.trim()) && !ip.trim().startsWith("10.") && !ip.trim().startsWith("172.16")
					&& !ip.trim().startsWith("192.168"))
				return ip.trim();
		}
		return null;
	}

	/**
	 * 验证是否符合ip地址格式
	 * 
	 * @param ip
	 *            ip地址
	 * 
	 * @return ture 符合ip格式 ,false不符合ip格式
	 */
	private static boolean isIp(String ip) {
		Pattern pattern = Pattern.compile(
				"\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ip);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 生成随机字符串
	 * 
	 * @return 字符串
	 */
	public static String producedString() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 检验是否大于指定字符个数
	 * 
	 * @param count
	 *            指定个数
	 * 
	 * @param str
	 *            字符串
	 * 
	 * @return true 超过指定个数, false 没超
	 */
	public static boolean isStrCount(String str, int count) {
		char[] cha = str.toCharArray();
		int chaCount = cha.length;
		if (count < chaCount) {
			return true;
		}
		return false;
	}

	public static java.util.Date parseDate(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(date, pos);
	}
	
	public static Date parseDateLQ(String date,String format){
		java.text.SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return   formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public static boolean isCountInInterval(String str, int min, int max) {
		return 0 != min ? !isNullOrEmptyString(str) : true && str.length() >= min && str.length() <= max;
	}
	
	/** 
	*字符串的日期格式的计算 
	*/  
	    public static int daysBetween(String smdate,String bdate,String format) throws ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat(format);  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	    }  
	    
	    /**  
	     * 计算两个日期之间相差的天数  
	     * @param smdate 较小的时间 
	     * @param bdate  较大的时间 
	     * @return 相差天数 
	     * @throws ParseException  
	     */    
	    public static int daysBetween(Date smdate,Date bdate,String format) throws ParseException    
	    {   
	    	long time1 = smdate.getTime();
	    	long time2 = bdate.getTime();
//	        SimpleDateFormat sdf=new SimpleDateFormat(format);  
//	        smdate=sdf.parse(sdf.format(smdate));  
//	        bdate=sdf.parse(sdf.format(bdate));  
//	        Calendar cal = Calendar.getInstance();    
//	        cal.setTime(smdate);    
//	        long time1 = cal.getTimeInMillis();                 
//	        cal.setTime(bdate);    
//	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	       return Integer.parseInt(String.valueOf(between_days));           
	    }  
	    /**
	    * 字符串转换成日期
	    * @param str
	    * @return date
	    */
	    public static Date StrToDate(String str) {
	      
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	       Date date = null;
	       try {
	        date = format.parse(str);
	       } catch (ParseException e) {
	        e.printStackTrace();
	       }
	       return date;
	    }

}
