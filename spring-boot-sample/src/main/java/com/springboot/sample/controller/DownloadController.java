package com.springboot.sample.controller;

import com.google.common.collect.Lists;
import com.kit.common.util.common.ListUtils;
import com.kit.common.util.download.ExcelExportUtils;
import com.springboot.sample.domain.DTO.PageDTO;
import com.springboot.sample.domain.DTO.SimpleDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: lingjun.jlj
 * @date: 2019-04-10 22:09
 * @description:
 */
@RestController
@RequestMapping(value = "/download/")
public class DownloadController {


    @RequestMapping(value = "excel")
    public void downloadExcel(HttpServletRequest request,
                              HttpServletResponse response,
                              PageDTO pageDTO) {
        String title = "姓名,手机,生日";
        List<SimpleDTO> simpleDTOS = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            SimpleDTO simpleDTO = new SimpleDTO();
            simpleDTO.setName("蒋" + i + "号");
            simpleDTO.setPhone("17826852173");
            simpleDTO.setBirth("1995-09-08");
            simpleDTOS.add(simpleDTO);
        }
        ExcelExportUtils.exportCsv(response, "信息导出", title, simpleDTOS, _param -> {
            StringBuffer stringBuffer = new StringBuffer();

            if (ListUtils.isNotEmpty(simpleDTOS)) {
                for (SimpleDTO simple : simpleDTOS) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(simple.getName()).append(",");
                    sb.append(simple.getPhone()).append(",");
                    sb.append(simple.getBirth());

                    stringBuffer.append(sb.toString().replace("null", "-").replace("\n", "")).append("\n");
                }
            }

            return stringBuffer.toString();
        });
    }
}
