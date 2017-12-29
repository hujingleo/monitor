package com.monitor.utils;

import java.util.ArrayList;
import java.util.List;


public class SimpleListObject<T> extends SimpleNetObject {
	
	public SimpleListObject(){
		this.rows=new ArrayList<T>();
		
	}
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	private int limit;
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	private int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
//	private int records;
//	
//	public int getRecords() {
//		return records;
//	}
//
//	public void setRecords(int records) {
//		this.records = records;
//	}
	private List<T> rows;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public SimpleListObject(String successmesage){super(successmesage);}
	public SimpleListObject(int result,String successmesage){super(result,successmesage);}
//	public SimpleListObject(PageList<T> pl){
//		this.rows=pl;
//		if(pl!=null){
//			this.setResult(1);
//			Paginator pager=pl.getPaginator();
//			if(pager!=null){
//				this.setRecords(pl.getPaginator().getTotalCount());
//				this.setTotal(pl.getPaginator().getTotalPages());	
//			}
//			else{
//				this.setRecords(pl.size());
//				this.setTotal(1);	
//			}
//		}
//	}
}
