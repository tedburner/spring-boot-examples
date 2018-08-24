package com.example.springboot.config;

import com.example.springboot.filters.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lingjun.jlj
 * @date: 2018/7/26 17:51
 * @description: 过滤器配置
 */
@Configuration
public class FilterConfig {

    private final CORSFilter corsFilter;

    //private final ExecuteTimeFilter executeTimeFilter;

    @Autowired
    public FilterConfig(CORSFilter corsFilter) {
        this.corsFilter = corsFilter;
        //this.executeTimeFilter = executeTimeFilter;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(corsFilter);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

//    @Bean
//    public FilterRegistrationBean executeTimeFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(executeTimeFilter);
//        filterRegistrationBean.setOrder(2);
//        return filterRegistrationBean;
//    }
}
