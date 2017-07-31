package pers.tbsowa.root.commons;

import java.io.Serializable;

public class SimplePage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**页码*/
	private int pageNum;
	/**每页显示数量*/
	private int pageSize;
	/**是否进行count查询*/
	private boolean count;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public boolean getCount() {
		return count;
	}
	public void setCount(boolean count) {
		this.count = count;
	}

}
