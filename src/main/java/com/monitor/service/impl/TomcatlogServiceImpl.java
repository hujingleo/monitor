package com.monitor.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.dao.TomcatlogDao;
import com.monitor.model.Tomcatlog;
import com.monitor.service.TomcatlogService;
import com.monitor.utils.IoUtil;



@Service
public class TomcatlogServiceImpl implements TomcatlogService{
    @Autowired
    private TomcatlogDao tomcatlogDao;
	@Override
	public int copylog(String file) {
		
		return tomcatlogDao.copylog(file);
	}
    public long copytomcatlog(String readfilepath, String writefilepath){
		long startat = System.currentTimeMillis();
		IoUtil.conversionFile(readfilepath,writefilepath);
		long endat = System.currentTimeMillis();
		long time = endat - startat;
		System.out.println("读取转换耗时"+ time);
		return time;
    }
    public long copytomcatloglastline(String readfilepath, String writefilepath,long lines) throws IOException{
		long startat = System.currentTimeMillis();
		IoUtil.conversionFileLastLine(readfilepath, writefilepath, lines);
		long endat = System.currentTimeMillis();
		long time = endat - startat;
		System.out.println("读取转换耗时"+ time);
		return time;
    }
	@Override
	public List<Tomcatlog> testget() {
		// TODO Auto-generated method stub
		return tomcatlogDao.testget();
	}
}
