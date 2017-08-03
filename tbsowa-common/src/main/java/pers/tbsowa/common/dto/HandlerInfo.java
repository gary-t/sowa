package pers.tbsowa.common.dto;

import java.io.Serializable;

public class HandlerInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**操作人编号*/
	private String handlerId;
	/**操作人名称*/
	private String handlerName;
	
	public String getHandlerId() {
		return handlerId;
	}
	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	
}
