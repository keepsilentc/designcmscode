package com.design.cms.service.api;

public class PageVo {
	private int current = 1;
	private int next = 2;
	private int prev = 0;
	private int pageSize = 10;
	private int sumPage = 0;
	private int begin = 0;
	private int end = 10;
	private int total = -1;
	
	public void reset(){
		 this.current = 1;
		 this.next = 2;
		 this.prev = 0;
		 this.pageSize = 10;
		 this.sumPage = 0;
		 this.begin = 0;
		 this.end = 10;
		 this.total = -1;
	}
	
	public boolean hasNext(){
		return next<sumPage+1;
	}
	public boolean hasPrev(){
		return prev>0;
	}
	
	public int getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		if(current==null){
			current = 1;
		}
		if(current<1){
			reset();
			return;
		}
		this.current = current;
		this.prev = this.current -1;
		this.next = this.current +1;
		this.begin = (this.current-1)*this.pageSize;
		this.end = this.pageSize;
	}
	public int getNext() {
		return next;
	}
	public int getPrev() {
		return prev;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getSumPage() {
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public int getBegin() {
		return begin;
	}
	public int getEnd() {
		return end;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
