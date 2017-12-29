package com.monitor.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.service.TomcatlogService;
import com.monitor.utils.IoUtil;
import com.monitor.utils.SimpleNetObject;




@RestController
@RequestMapping("/admin")
public class AdminController {
    
	
    @Autowired
    private TomcatlogService tomcatlogService;

    @RequestMapping("/copytomcatloglastline")
	public SimpleNetObject copytomcatloglastline(String readfilepath, String writefilepath, long lines) {
		SimpleNetObject sno = new SimpleNetObject();

		if (lines == 0) {
			lines = 1000;
		}
		if (null==readfilepath) {
			SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date =sfDateFormat.format(new Date());
			readfilepath= "/usr/software/ymall/test/apache-tomcat-7.0.53/logs/localhost_access_log."+date+".txt";
		}
		if (null==writefilepath) {
			SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			String date =sfDateFormat.format(new Date());
			writefilepath = "/usr/software/ymall/test/apache-tomcat-7.0.53/logs/accesslog."+date+".txt";
		}
		int totallines = 0;
		try {
			totallines = IoUtil.countLines(readfilepath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (totallines < lines) {
			// return new SimpleNetObject(99,"文件总行数小于所需行数,请重新输入");
			lines = totallines;
		}
		try {
			long time = tomcatlogService.copytomcatloglastline(readfilepath, writefilepath, lines);
			Thread.sleep(2000);
			tomcatlogService.copylog(writefilepath);
			sno.setMessage("转存日志耗时" + time);
			return sno;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new SimpleNetObject(99, "转存异常");
		}
	}
//    public static void main(String[] args) {
//		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//		String date =sfDateFormat.format(new Date());
//		String writefilepath = "/usr/software/ymall/test/apache-tomcat-7.0.53/logs/accesslog."+date+".txt";
//		System.out.println(writefilepath);
//	}
}