package com.monitor.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class IoUtil {
	private static final Logger logger = LoggerFactory.getLogger(IoUtil.class);
	/*
	 * 将整个日志文件转格式存到新文档并写入数据库
	 */
	public static void conversionFile(String readfilepath, String writefilepath) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		long linenum = 0;
		try {
			File file = new File(writefilepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			StringBuffer sb = new StringBuffer();
			reader = new BufferedReader(new FileReader(readfilepath));
			String line = null;
			// 按行读取
			while ((line = reader.readLine()) != null) {
				// 先将日志用,分割
				String[] strings = line.split(";");
				// 分割后取第四个元素得到[27/Dec/2017:16:10:32 +0800]
				String calldatestr = strings[3];
				// 去除第一个[
				String str2 = calldatestr.substring(1, calldatestr.length());
				// 空格分割,取第一个得到27/Dec/2017:16:10:32
				String[] datestr = str2.split(" ");
				String calldatestring = datestr[0];
				// 将27/Dec/2017:16:10:32转换格式
				Date calldate = new Date();
				String newdatestr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					calldate = sdf.parse(calldatestring);
					// 转换得到yyyy-MM-dd HH:mm:ss格式的请求时间
					newdatestr = sdfnew.format(calldate);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("ioutil日期转换出错");

				}
				// 将各字符串以分号;连接,最后加上换行符
				sb.append(strings[0]).append(";").append(strings[1]).append(";").append(strings[2]).append(";")
						.append("\"").append(newdatestr).append("\"").append(";").append(strings[4]).append("\r\n");
				linenum++;
			}
			// 写入新的文件
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取tomcat日志出错");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("关闭BufferedReader异常");
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("关闭BufferedWriter异常");
				}
			}
		}
	}

	/*
	 * 将日志文件的最后指定行数转格式存到新文档并写入数据库
	 */
	public static void conversionFileLastLine(String readfilepath, String writefilepath, long lines)
			throws IOException {

		File writefile = new File(writefilepath);
		File readfile = new File(readfilepath);
		int counter = 1;
		StringBuffer sb = new StringBuffer();
		ReversedLinesFileReader object = new ReversedLinesFileReader(readfile, 4096, "UTF-8");
		BufferedWriter writer = null;
		try {
			while (counter <= lines) {
				// 读取下一行
				String line = object.readLine();
				String[] strings = line.split(";");
				// 分割后取第四个元素得到[27/Dec/2017:16:10:32 +0800]
				String calldatestr = strings[3];
				// 去除第一个[
				String str2 = calldatestr.substring(1, calldatestr.length());
				// 空格分割,取第一个得到27/Dec/2017:16:10:32
				String[] datestr = str2.split(" ");
				String calldatestring = datestr[0];
				// 将27/Dec/2017:16:10:32转换格式
				Date calldate = new Date();
				String newdatestr = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
				SimpleDateFormat sdfnew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					calldate = sdf.parse(calldatestring);
					// 转换得到newdatestr格式为yyyy-MM-dd HH:mm:ss
					newdatestr = sdfnew.format(calldate);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
				// 将各字符串以分号;连接,最后加上换行符
				sb.append(strings[0]).append(";").append(strings[1]).append(";").append(strings[2]).append(";")
						.append("\"").append(newdatestr).append("\"").append(";").append(strings[4]).append("\r\n");
				counter++;
			}
			try {
				writer = new BufferedWriter(new FileWriter(writefile));
				writer.write(sb.toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.error("读取tomcat日志出错");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (object != null) {
				try {
					object.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("关闭BufferedReader异常");
				}
			}
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("关闭BufferedWriter异常");
				}
			}
		}
		object.close();
	}

	public static int countLines(String filename) throws IOException {
		LineNumberReader reader = new LineNumberReader(new FileReader(filename));
		int cnt = 0;
		String lineRead = "";
		while ((lineRead = reader.readLine()) != null) {
		}
		cnt = reader.getLineNumber();
		reader.close();
		return cnt;
	}
}
