package cn.itcast.controller;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("rest/user")
public class NewUserController {
    @Autowired
    private UserService userService;

    @GetMapping("{uid}")
    public ResponseEntity<User> findUserById(@PathVariable("uid") Integer uid) {
        try {
            if (uid == null || uid <= 0) {
                //参数列表错误 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            User user = userService.findUserById(uid);
            if (user == null) {
                //资源找不到 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            //查询成功 200
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务器错误 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveUser(User user) {
        try {
            if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
                //参数列表错误 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            boolean flag = userService.saveUser(user);
            if (flag) {
                //保存成功 201
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务器错误 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateUser(User user) {
        try {
            //检测请求是否合法
            if (user.getUid() == null || user.getUid() <= 0) {
                //参数列表错误 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            boolean flag = userService.updateUser(user);
            if (flag) {
                //更新成功 204
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务器错误 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam("uid") Integer uid) {
        try {
            //检测uid是否合法
            if (uid == null || uid <= 0) {
                //参数列表错误 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            boolean flag = userService.deleteUser(uid);
            if(flag){
                //删除用户 204
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务器错误 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
