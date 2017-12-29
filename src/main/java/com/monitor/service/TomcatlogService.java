package com.monitor.service;

import java.io.IOException;
import java.util.List;

import com.monitor.model.Tomcatlog;


public interface TomcatlogService {
	List<Tomcatlog> testget();
	int copylog(String file);
	long copytomcatlog(String readfilepath, String writefilepath);
	 long copytomcatloglastline(String readfilepath, String writefilepath,long lines) throws IOException;
}
