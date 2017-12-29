package com.monitor.model;

import java.util.Date;

public class Tomcatlog{
		/**
          * 
          **
       **/
        private String call_ip;
	public String getCall_ip(){
		return this.call_ip;
		
	}		
	public void setCall_ip(String call_ip){
		this.call_ip=call_ip;
	}
		/**
          * 
          **
       **/
        private String call_url;
	public String getCall_url(){
		return this.call_url;
		
	}		
	public void setCall_url(String call_url){
		this.call_url=call_url;
	}
		/**
          * 
          **
       **/
        private int call_time;
	public int getCall_time(){
		return this.call_time;
		
	}		
	public void setCall_time(int call_time){
		this.call_time=call_time;
	}
		/**
          * 
          **
       **/
        private Date call_date;
	public Date getCall_date(){
		return this.call_date;
		
	}		
	public void setCall_date(Date call_date){
		this.call_date=call_date;
	}
		/**
          * 
          **
       **/
        private String call_status;
	public String getCall_status(){
		return this.call_status;
		
	}		
	public void setCall_status(String call_status){
		this.call_status=call_status;
	}
		/**
          * 
          **
       **/
        private java.util.Date created_date;
	public java.util.Date getCreated_date(){
		return this.created_date;
		
	}		
	public void setCreated_date(java.util.Date created_date){
		this.created_date=created_date;
	}
	
}