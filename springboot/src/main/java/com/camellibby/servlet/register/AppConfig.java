package com.camellibby.servlet.register;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import java.util.EventListener;

@Configuration
public class AppConfig {
    @Bean
    public ServletRegistrationBean<HttpServlet> getServletRegistrationBean() {
        ServletRegistrationBean<HttpServlet> bean = new ServletRegistrationBean<>(new DemoServlet(), "/demo");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<Filter> getFilterRegistrationBean(ServletRegistrationBean<HttpServlet> servletServletRegistrationBean) {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new DemoFilter(), servletServletRegistrationBean);
        bean.addUrlPatterns("/demo");
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new DemoRequestListener());
        bean.setListener(new DemoSessionListener());
        return bean;
    }
}
