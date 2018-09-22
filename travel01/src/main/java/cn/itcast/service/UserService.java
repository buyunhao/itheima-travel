package cn.itcast.service;

import cn.itcast.domain.User;
import cn.itcast.exception.UserNotExistsException;

/**
 * Created by 卜云浩 on 2018/9/14 at 11:18.
 */
public interface UserService {
    void register(User user) throws Exception;

    /**
     * 注册用户激活
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password) throws Exception;

    /**
     * restful风格接口查询用户
     * @param uid
     * @return
     */
    User findUserById(Integer uid);

    /**
     * restful风格接口保存用户
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * restful风格接口更新用户
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * restful风格删除用户
     * @param uid
     * @return
     */
    Boolean deleteUser(Integer uid);
}
