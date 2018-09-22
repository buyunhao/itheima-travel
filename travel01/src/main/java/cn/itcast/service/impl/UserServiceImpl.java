package cn.itcast.service.impl;

import cn.itcast.domain.User;
import cn.itcast.exception.*;
import cn.itcast.mapper.NewUserMapper;
import cn.itcast.service.UserService;
import cn.itcast.util.MailUtil;
import cn.itcast.util.Md5Util;
import cn.itcast.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 卜云浩 on 2018/9/14 at 11:18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
//    private UserMapper userMapper;
    private NewUserMapper userMapper;

    @Override
    public void register(User user) throws Exception {
        //数据验证,用户名不能为空
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            throw new UserNameNotNullException("用户名不能为空");
        }
        //判断用户名是否已被注册
//        User dbUser = userMapper.getUserByUserName(user.getUsername());
        User u = new User();
        u.setUsername(user.getUsername());
        User dbUser = userMapper.selectOne(u);
        if (dbUser != null) {
            //抛出自定义异常
            throw new UserExistsException("用户名已存在");
        }
        //封装业务字段-激活状态为未激活
        user.setStatus("N");
        //封装业务字段-激活码(唯一,uuid)
        user.setCode(UuidUtil.getUuid());
        //密码加密,使用md5加密,md5号称不可逆的加密算法
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        //发送邮件
        MailUtil.sendMail(user.getEmail(),
                "<a href='http://localhost:8080/user/active?code=" + user.getCode() + "'>用户激活</a>");
        //注册用户添加用户
//        userMapper.addUser(user);
        userMapper.insertSelective(user);
    }

    @Override
    public boolean active(String code) {
//        int row = userMapper.active(code);
        User u = new User();
        u.setCode(code);
        u = userMapper.selectOne(u);
        u.setStatus("Y");
        int row = userMapper.updateByPrimaryKeySelective(u);
        return row > 0;
    }

    @Override
    public User login(String username, String password) throws Exception {
        //数据校验
        if (username == null || username.equalsIgnoreCase("")) {
            throw new UserNameNotNullException("用户名不能为空");
        }
//        User dbUser = userMapper.getUserByUserName(username);
        User u = new User();
        u.setUsername(username);
        User dbUser = userMapper.selectOne(u);
        if (dbUser == null) {
            throw new UserNotExistsException("用户名不存在");
        }
        if (!dbUser.getPassword().equals(password)) {
            throw new PasswordErrorException("密码错误");
        }
        if (dbUser.getStatus().equals("N")) {
            throw new UserNoActiveException("用户未激活");
        }
        return dbUser;
    }

    @Override
    public User findUserById(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public boolean saveUser(User user) {
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        int i = userMapper.insertSelective(user);
        return i>0;
    }

    @Override
    public boolean updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i>0;
    }

    @Override
    public Boolean deleteUser(Integer uid) {
        int i = userMapper.deleteByPrimaryKey(uid);
        return i>0;
    }
}
