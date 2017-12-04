package com.example.SpringBoot.controller;

import com.example.SpringBoot.service.ProvinceService;
import com.example.SpringBoot.utils.http.NewResponseModel;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lingjun.jlj
 * @create 2017-12-04
 **/
@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/getProvince")
    public NewResponseModel getProvince(Long id){
        NewResponseModel responseModel = NewResponseModel.Success();
        responseModel.setData(provinceService.getProvince(id));
        return responseModel;
    }

}
