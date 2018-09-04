package com.example.springboot.controller;

import com.example.springboot.model.DO.UserDO;
import com.example.springboot.model.DTO.SimpleDTO;
import com.example.springboot.service.common.DemoService;
import com.example.springboot.service.common.UserService;
import com.example.springboot.utils.http.NewResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2018/8/30 11:52
 * @description:
 */
@RestController
@RequestMapping(value = "/view/")
public class TestController {


    @Resource
    private UserService userService;
    @Autowired
    private DemoService demoService;
    @Autowired
    private Environment env;

    @GetMapping(value = "getView")
    public NewResponseModel getView() {
        NewResponseModel responseModel = NewResponseModel.Success();
        List<UserDO> users = userService.findUser();
        System.out.println(users);
        responseModel.setData(users);
        return responseModel;
    }

    @RequestMapping("sample")
    public String sample() {
        return "spring boot success ! and profile is ==>" +
                env.getProperty("spring.profiles.active") + "=====>" + env.getProperty("ftp");
    }

    @RequestMapping("view")
    public NewResponseModel view() {
        NewResponseModel responseModel = NewResponseModel.Success();
        responseModel.setData(demoService.getNumer());
        return responseModel;
    }

    @RequestMapping("notnull")
    public NewResponseModel notNull(@RequestBody @Validated SimpleDTO simpleDTO) {
        NewResponseModel responseModel = NewResponseModel.Success();
        return responseModel;
    }


}
