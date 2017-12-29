package com.monitor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.monitor.model.Tomcatlog;


public interface TomcatlogDao {
	int copylog(@Param("file") String file);
	List<Tomcatlog> testget();
}
