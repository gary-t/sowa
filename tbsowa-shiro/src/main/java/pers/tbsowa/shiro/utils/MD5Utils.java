package pers.tbsowa.shiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 密码加盐加密
 * @author dell
 *
 */
public class MD5Utils {

	/**
	 * MD5加密
	 * @param source 
	 * @param salt  盐
	 * @return
	 */
	public static String Encrypt(String source,String salt){
		//散列次数
        int hashInterration=2;
        Md5Hash md5Hash=new Md5Hash(source,salt,hashInterration);
        return md5Hash.toString();
	}
}
