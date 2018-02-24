package com.monitor.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.dao.TomcatlogDao;
import com.monitor.model.Tomcatlog;
import com.monitor.utils.IoUtil;



@Service
public class TomcatlogService {
    @Autowired
    private TomcatlogDao tomcatlogDao;

	public int copylog(String file) {
		
		return tomcatlogDao.copylog(file);
	}
	/**
	 * 将整个日志文件转格式存到新文档并写入数据库
	 */
    public long copytomcatlog(String readfilepath, String writefilepath){
		long startat = System.currentTimeMillis();
		IoUtil.conversionFile(readfilepath,writefilepath);
		long endat = System.currentTimeMillis();
		long time = endat - startat;
		return time;
    }
    /**
     * 将日志文件的最后指定行数转格式存到新文档并写入数据库
     */
    public long copytomcatloglastline(String readfilepath, String writefilepath,long lines) throws IOException{
		long startat = System.currentTimeMillis();
		IoUtil.conversionFileLastLine(readfilepath, writefilepath, lines);
		long endat = System.currentTimeMillis();
		long time = endat - startat;
		return time;
    }

	public List<Tomcatlog> testget() {
		// TODO Auto-generated method stub
		return tomcatlogDao.testget();
	}
}
