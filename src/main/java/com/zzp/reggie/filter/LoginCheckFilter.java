package com.zzp.reggie.filter;

/**
 * @Author zzp
 * @Date 2023/2/16 21:23
 * @Description: TODO
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.zzp.reggie.common.BaseContext;
import com.zzp.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经完成登录
 */
@Slf4j
@Configuration
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求的uri
        String requestURI = request.getRequestURI();

        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/category/**",
                "/common/**",
        };
        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3.如果不需要处理，则直接放行
        if (check) {
            filterChain.doFilter(request, response);//放行
            return;
        }
        //4.判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null) {
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);//放行
            return;
        }
        //5.如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("未登录，已拦截：{}", requestURI);
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
