package pers.tbsowa.common.utils;

import java.util.UUID;

/**
 * Primary Key generate
 * @author dell
 *
 */
public class KeyGeneratorUtils {
	
	public static String getUuid(){
		/**1.UUID.randomUUID().toString()
		 *    get 78ceb6c1-1ec1-4cfd-91a6-11b672573cf6
		 * 2.remove '-'   
		 * */
		return UUID.randomUUID().toString().replace("-", "");
	}
}
