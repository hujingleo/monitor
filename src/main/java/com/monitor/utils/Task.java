package com.monitor.utils;

import org.springframework.scheduling.annotation.Scheduled;

import com.monitor.model.Tomcatlog;
import com.monitor.service.TomcatlogService;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling // 启用定时任务
public class Task {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${spring.profiles.lines}")
	private long lines;
	@Value("${spring.profiles.logfilepath}")
	private String logfilepath;
	@Value("${spring.profiles.targetfilepath}")
	private String targetfilepath;
	@Autowired
	private TomcatlogService tomcatlogService;

	@Scheduled(cron = "0 0/5  *  * * ? ") // 每5分钟执行一次
	public void scheduler() {
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String start_date = sfDateFormat.format(new Date());
		logger.info(start_date + "开始执行定时转存tomcat日志任务");
		if (lines == 0) {
			lines = 1000;
		}
			SimpleDateFormat sfDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			String date = sfDateFormat1.format(new Date());
			String readfilepath = logfilepath+"/localhost_access_log." + date + ".txt";
			String writefilepath = targetfilepath+"/accesslog." + date + ".txt";
		int totallines = 0;
		try {
			totallines = IoUtil.countLines(readfilepath);
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("统计日志行数异常");
		}
		if (totallines < lines) {
			lines = totallines;
		}
		try {
			logger.info("get lines is "+ lines);
			logger.info("get logfilepath is "+ logfilepath);
			logger.info("get targetfilepath is "+ targetfilepath);
			logger.info("get readfilepath is "+ readfilepath);
			logger.info("get writefilepath is "+ writefilepath);
			//先将日志转格式写入文档
			long time = tomcatlogService.copytomcatloglastline(readfilepath, writefilepath, lines);
			Thread.sleep(2000);
			//在通过sql将文档写入数据库
			tomcatlogService.copylog(writefilepath);
			logger.info("转存日志耗时" + time);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("转存异常");
		}

	}
}
