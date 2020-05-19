package com.springboot.sample.filters;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author: lingjun.jlj
 * @create 2017-11-30
 * @descrip 过滤器
 **/

@WebFilter(filterName = "ExecuteTimeFilter", urlPatterns = {"/*"})
public class ExecuteTimeFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(ExecuteTimeFilter.class);

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String uri = httpServletRequest.getRequestURI();
        if (StringUtils.isNotEmpty(httpServletRequest.getQueryString())) {
            uri = uri + "?" + httpServletRequest.getQueryString();
        }

        Long startTime = System.currentTimeMillis();
        //转发个下一个过滤器
        chain.doFilter(request, response);

        Long endTime = System.currentTimeMillis();

        Long executeTime = endTime - startTime;

        Gson gson = new Gson();

        if (uri.contains("upload") || uri.contains("addCustomerLog") || uri.contains("performance/metric")) {
            log.info("request uri =" + uri + ", executeTime = " + executeTime + "ms");
        } else {
            Map<String, String[]> paramMap = httpServletRequest.getParameterMap();
            Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
            Map<String, Object> headerMap = Maps.newHashMap();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = httpServletRequest.getHeader(name);
                headerMap.put(name, value);
            }
            log.info("request uri =" + uri + ", executeTime = " + executeTime + "ms,paramMap =" + gson.toJson(paramMap) + ",headerMap=" + gson.toJson(headerMap));
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
