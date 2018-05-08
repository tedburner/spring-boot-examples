package com.example.springboot.controller;

import com.example.springboot.service.ProvinceService;
import com.example.springboot.utils.http.NewResponseModel;
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
