package com.example.demo.Controller;

import com.example.demo.Dao.UserMapper;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
//说明类的作用
@Api("登陆相关的接口 相关接口说明")
//返回的错误类型
@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "客户端请求错误"),
        @ApiResponse(code = 404, message = "找不到路径"),
        @ApiResponse(code = 500, message = "编译异常")
})

public class login {
    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    //描述某个接口
    @ApiOperation(value = "跳转到登陆界面",httpMethod = "GET", notes="无需传值")
    public ModelAndView toLogin(){
        return new ModelAndView("login");
    }

    @RequestMapping("login")
    @ApiOperation(value = "登陆",httpMethod = "POST")
    public ModelAndView login(User user){
        User user1=userService.selectByPrimaryKey(user.getUsername());
        if(user.getPassword().equals(user1.getPassword())){
            return new ModelAndView("success");
        }
        else {
            return new ModelAndView("fail");
        }
    }
}
