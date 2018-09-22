package cn.itcast.mapper;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUserName(@Param("username") String username);

    /**
     * 保存用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 用户季候
     * @param code
     * @return
     */
    int active(@Param("code") String code);
}
