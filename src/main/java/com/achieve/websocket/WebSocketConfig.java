package com.achieve.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Auther: zj
 * @Date: 2018/8/16 17:21
 * @Description: 编写一个WebSocketConfig配置类，注入对象ServerEndpointExporter，
 *      这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
 */


@Configuration
public class WebSocketConfig {
/*    @Autowired
    private RequestListener requestListener;*/

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

/*    @Bean
    public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(requestListener);
        return servletListenerRegistrationBean;
    }*/


}
