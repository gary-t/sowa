package pers.tbsowa.root.commons;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract Map<String, Object> toMap();

}
