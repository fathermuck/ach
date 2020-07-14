package com.achieve.utils;

import com.achieve.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI

    static {
        NOT_INTERCEPT_URI.add("/index");
        NOT_INTERCEPT_URI.add("/user/loginb");
        NOT_INTERCEPT_URI.add("/static/**");
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPT_URI.contains(uri)) {
            System.out.println("不拦截 定义为不拦截的资源：" + uri);
            return true;
        }
        if (uri.indexOf(".")!=-1 && !(uri.indexOf(".html")!=-1)) {
            System.out.println("不拦截 除 .html 外有 . 的资源" + uri);
            return true;
        }
        System.out.println("拦截 资源 验证用户是否" + uri);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            System.out.println("无该用户");
            response.sendRedirect("/index");
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv) throws Exception {
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
    }
}
