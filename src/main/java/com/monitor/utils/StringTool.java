package com.monitor.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StringTool {
	
	public static long  HOUR=3600;
	public static long DAY=86400;
	
	public static long MINUTE=60;
	/**
	 * 判断字符串为null或者空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str){
		return str==null||str.trim().equals("");
	}
	
	
	
	/**
	 * 使用指定的字符填充到字符串左边以补齐字符串长度
	 * @param str
	 * @param size
	 * @param fill
	 * @return
	 */
	public static String padLeft(String str ,int size ,char fill) {
        if (str == null)
            str = "";
        int str_size = str.length();
        int pad_len = size - str_size;
        StringBuffer retvalue = new StringBuffer();
        for (int i = 0; i < pad_len; i++) {
            retvalue.append(fill);
        }
        
        return retvalue.append(str).toString();
        
    }
	/**
	 * 使用指定的字符填充到字符串右边以补齐字符串长度
	 * @param str
	 * @param size
	 * @param fill
	 * @return
	 */
	public static String padRight(String str ,int size ,char fill) {
        if (str == null)
            str = "";
        int str_size = str.length();
        int pad_len = size - str_size;
        StringBuffer retvalue = new StringBuffer();
        for (int i = 0; i < pad_len; i++) {
            retvalue.append(fill);
        }
                
        return retvalue.insert(0, str).toString();
    }
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * 对字符串加密
	 * @param strSrc
	 * @param encName
	 * @return
	 */
	public static String Encrypt(String strSrc, String encName) {
		// parameter strSrc is a string will be encrypted,
		// parameter encName is the algorithm name will be used.
		// encName dafault to "MD5"
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			if (encName == null || encName.equals("")) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = getFormattedText(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}
	
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) { 			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	
	
	/**
	 * 将字符串首字母变成大写
	 * @param str
	 * @return
	 */
	public static String firstLetter2Uppercase(String str){
		if(isNullOrEmpty(str))
			return str;
		return str.substring(0,1).toUpperCase()+str.substring(1);
		
	}
	
	/**
	 * 获得随机码
	 * @param num 几位随机码，不够的补0
	 * @return
	 */
	public static String getRamCode(int num){
		Random random=new Random();
		int max=(int)Math.pow(10.0, (double)num);
		return StringTool.padRight(String.valueOf(random.nextInt(max)),num,'0');
	}
	
	public static Pattern mobilePattern = Pattern.compile("^((1[3456789][0-9]))\\d{8}$");  
	public static boolean isMobile(String mobile){
		
		  
		Matcher m =mobilePattern.matcher(mobile);
		return m.matches();
	}
	
	
	/**
	 * 使用逗号将数组字符串拼接起来
	 * @param lstStrs
	 * @return
	 */
	public static <T> String join(List<T> lstStrs){
		return join(lstStrs,",");
	}
	
	/**
	 * 使用指定分隔符将数组字符串拼接起来
	 * @param lstStrs
	 * @param seperator
	 * @return
	 */
	public static <T> String join(List<T> lstStrs,String seperator){
		if(lstStrs!=null&&lstStrs.size()>0){
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < lstStrs.size(); i++) {
				if(lstStrs.get(i)!=null&&!lstStrs.get(i).toString().trim().equals("")){
					sb.append(seperator+lstStrs.get(i));
				}
			}
			return sb.toString().substring(seperator.length());
		}
		else
			return "";
	}
	public static enum Type {  
        UPPERCASE,              //全部大写  
        LOWERCASE,              //全部小写  
        FIRSTUPPER              //首字母大写  
    }  
	public static String toPinYin(String str) throws BadHanyuPinyinOutputFormatCombination{  
        return toPinYin(str, "", Type.UPPERCASE);  
    } 
	public static String toPinYin(String str,String spera) throws BadHanyuPinyinOutputFormatCombination{  
        return toPinYin(str, spera, Type.UPPERCASE);  
    }  
	public static String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {  
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
		 format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
		 format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		if(str == null || str.trim().length()==0)  
            return "";  
        if(type == Type.UPPERCASE)  
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        else  
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
  
        String py = "";  
        String temp = "";  
        String[] t;  
        for(int i=0;i<str.length();i++){  
            char c = str.charAt(i);  
            if((int)c <= 128)  
                py += c;  
            else{  
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);  
                if(t == null)  
                    py += c;  
                else{  
                    temp = t[0];  
                    if(type == Type.FIRSTUPPER)  
                        temp = t[0].toUpperCase().charAt(0)+temp.substring(1);  
                    py += temp+(i==str.length()-1?"":spera);  
                }  
            }  
        }  
        return py.trim();  
    }
	
	/**
	 * 将这种格式的208986af6776 mac地址格式化成20:89:86:af:67:76以做mac地址查询
	 * 
	 * @param mac
	 * @return
	 */
	public static String formatMac2Maohao(String mac) {
		if(StringTool.isNullOrEmpty(mac)||mac.length()!=12){
			return "";
		}
		char[] arrMac=mac.toCharArray();
		StringBuffer sbFormatMac=new StringBuffer();
		for(int i=0;i<12;i+=2){
			sbFormatMac.append(arrMac[i]);
			sbFormatMac.append(arrMac[i+1]);
			sbFormatMac.append(":");
		}
		String strResult=sbFormatMac.toString();
		return strResult.substring(0,strResult.length()-1);
	}

	/**
	 * 格式化mac为统一的格式，传入的mac有可能有如下几种格式
	 * 	1、20-89-86-af-66-93
	 *  2、20:89:86:af:66:93
	 *  3、208986af6693
	 *  统一格式化输出成20-89-86-AF-66-93(大写)
	 * @param mac
	 * @return
	 * @throws MacFormatErrorException 
	 */
	public static String getFommatMac(String mac) throws MacFormatErrorException{
		return getFommatMac(mac,"-");
	}
	/**
	 * 格式化mac为统一的格式，传入的mac有可能有如下几种格式
	 * 	1、20-89-86-af-66-93
	 *  2、20:89:86:af:66:93
	 *  3、208986af6693
	 *  统一格式化输出成20-89-86-AF-66-93(大写)
	 * @param mac
	 * @return
	 * @throws MacFormatErrorException 
	 */
	public static String getFommatMac(String mac,String sep) throws MacFormatErrorException{
		if(StringTool.isNullOrEmpty(mac))
			return null;
		String processMac=mac.replace("-","");
		processMac=processMac.replace(":","");
		char[] arrMac=processMac.toCharArray();
		StringBuffer sbBuffer=new StringBuffer();
		if(processMac.length()!=12){
			throw new MacFormatErrorException();
		}
		for (int i = 0; i < arrMac.length; i=i+2) {
			
			sbBuffer.append(arrMac[i]);
			sbBuffer.append(arrMac[i+1]);
			sbBuffer.append(sep);
		}
		if(!StringTool.isNullOrEmpty(sep)){
			String result=sbBuffer.substring(0,sbBuffer.length()-1);
			return result.toUpperCase();
		}
		else
			return sbBuffer.toString().toUpperCase();
	}
	
	/**
	 * 
	 * Description：解码：对js前台传输过来的字符串进行解码操作
	 * 
	 * @param @param target
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String decode(String target) {
		String dataname = "";
		if (null == target || "".equals(target)) {
			return "";
		}
		try {
			dataname = java.net.URLDecoder.decode(target, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dataname;
	}
	
	
	
	/**
	 * 获取时间差中文表示
	 * @param end
	 * @param start
	 * @return
	 */
	public static String getIntervalTime(Date end,Date start){
		long endtime=end.getTime();
		long starttime=start.getTime();
		long offset=endtime-starttime;
		long offsetSecond=offset/1000; //换算成s
		
		long day=offsetSecond/DAY;
		long hour=offsetSecond%DAY/HOUR;
		long minute=offsetSecond%HOUR/MINUTE;
		long second=offsetSecond%MINUTE;
		StringBuffer sbResult=new StringBuffer();
		if(day>0){
			sbResult.append(day+"天");
		}
		if(hour>0){
			sbResult.append(hour+"小时");
			
		}
		if(minute>0){
			sbResult.append(minute+"分");			
		}
		if(second>0){
			sbResult.append(second+"秒");
		}
		String strResult= sbResult.toString();
		if(StringTool.isNullOrEmpty(strResult))
		{
			return offset+"毫秒";
		}
		else{
			return strResult;
		}
		 
	}
	public static String getIntervalTime(long offset){
		
		long offsetSecond=offset/1000;
		long day=offsetSecond/DAY;
		long hour=offsetSecond%DAY/HOUR;
		long minute=offsetSecond%HOUR/MINUTE;
		long second=offsetSecond%MINUTE;
		StringBuffer sbResult=new StringBuffer();
		if(day>0){
			sbResult.append(day+"天");
		}
		if(hour>0){
			sbResult.append(hour+"小时");			
		}
		if(minute>0){
			sbResult.append(minute+"分");			
		}
		if(second>0){
			sbResult.append(second+"秒");
		}
		String strResult= sbResult.toString();
		if(StringTool.isNullOrEmpty(strResult))
		{
			return offset+"毫秒";
		}
		else{
			return strResult;
		}
		 
	}
	
	public static String getIntervalTimeToMinute(long offset) {
		long offsetSecond=offset/1000;
		long day=offsetSecond/DAY;
		long hour=offsetSecond%DAY/HOUR;
		long minute=offsetSecond%HOUR/MINUTE;
		StringBuffer sbResult=new StringBuffer();
		if(day>0){
			sbResult.append(day+"天");
		}
		if(hour>0){
			sbResult.append(hour+"小时");			
		}
		if(minute>0){
			sbResult.append(minute+"分钟");			
		}
		String strResult= sbResult.toString();
		return strResult;
	}
	
	
	public static String getIntervalTimeMaoHao(long offset)
	{
		
		long offsetSecond=offset/1000;
		long day=offsetSecond/DAY;
		long hour=offsetSecond%DAY/HOUR;
		long minute=offsetSecond%HOUR/MINUTE;
		long second=offsetSecond%MINUTE;
		StringBuffer sbResult=new StringBuffer();
		if(day>0)
		{
			sbResult.append(day+":");
		}
		if(hour>0)
		{
			if(minute < 10)
			{
				sbResult.append("0"+hour+":");
			}
			else
			{
				sbResult.append(hour+":");			
			}
		}
		else
		{
			sbResult.append("00:");
		}
		if(minute>0)
		{
			if(minute < 10)
			{
				sbResult.append("0"+minute+":");
			}
			else
			{
				sbResult.append(minute+":");			
			}
		}
		else 
		{
			sbResult.append("00:");
		}
		if(second>0)
		{
			if(second < 10)
			{
				sbResult.append("0"+second);
			}
			else
			{
				sbResult.append(second);			
			}
		}else 
		{
			sbResult.append("00");
		}
		return sbResult.toString();
	}
	public static boolean isIp(String ip){//判断是否是一个IP  
        boolean b = false;  
        ip = ip.trim();
        if(ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")){  
            String s[] = ip.split("\\.");  
            if(Integer.parseInt(s[0])<255)  
                if(Integer.parseInt(s[1])<255)  
                    if(Integer.parseInt(s[2])<255)  
                        if(Integer.parseInt(s[3])<255)  
                            b = true;  
        }  
        return b;  
    } 
	
	public static boolean isIpInRange(String ip,String iprange){//判断是否是一个IP  
		String[] networkips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(networkips[0]) << 24)
                | (Integer.parseInt(networkips[1]) << 16)
                | (Integer.parseInt(networkips[2]) << 8)
                | Integer.parseInt(networkips[3]);
        int type = Integer.parseInt(iprange.replaceAll(".*/", ""));
        int mask1 = 0xFFFFFFFF << (32 - type);
        String maskIp = iprange.replaceAll("/.*", "");
        String[] maskIps = maskIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
                | (Integer.parseInt(maskIps[1]) << 16)
                | (Integer.parseInt(maskIps[2]) << 8)
                | Integer.parseInt(maskIps[3]);

        return (ipAddr & mask1) == (cidrIpAddr & mask1);
        
    } 
	
//	public static void main(String[] args) {
//		System.out.println(getIntervalTimeMaoHao(1000*60*60*25));
//	}



	
}
