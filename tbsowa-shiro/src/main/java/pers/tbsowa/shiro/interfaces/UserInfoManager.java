package pers.tbsowa.shiro.interfaces;

import org.springframework.stereotype.Service;

/**
 *  备注：1.需要用户实际业务中实现此接口，以提供用户认证、权限认证
 *
 * Created by DELL on 2017/8/7.
 */
public interface UserInfoManager {

    /**
     * 获取UserInfo
     * @param userName
     * @return
     */
    public Authorizable getUserInfoByUsername(String userName);
}
