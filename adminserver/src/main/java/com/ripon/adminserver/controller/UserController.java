package com.ripon.adminserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ripon.adminserver.dto.Response;
import com.ripon.adminserver.annotation.VerifyToken;
import com.ripon.adminserver.model.User;
import com.ripon.adminserver.service.UserService;
import com.ripon.adminserver.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public Response login(@RequestBody User user) {
        User userForBase = userService.getUserByUsername(user.getUsername());
        if (userForBase == null) {
            return Response.fail("用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return Response.fail("密码错误");
            } else {
                String token = Auth.createToken(userForBase);
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("user", userForBase);
                return Response.success(map);
            }
        }
    }

    @VerifyToken
    @GetMapping("/user/info")
    public User getUserInfo(HttpServletRequest request) {
        String username = request.getAttribute("username").toString();
        return userService.getUserByUsername(username);
    }

    @GetMapping("/user/list")
    public Map<String, Object> getUserList(@RequestParam("currentPage") Integer currentPage,
                                           @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> list = userService.getAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", pageInfo.getTotal());
        return map;
    }
    @DeleteMapping("/user/{id}")
    public Response deleteUser(@PathVariable("id") Integer id) {
        if (userService.deleteUser(id) == 1) {
            return Response.success();
        }
        return Response.fail("删除用户失败");

    }

}
