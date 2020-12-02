package com.ccqstark.stugrademanage.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    // 后台监控
    public ServletRegistrationBean statViewServlet(){

        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        // 存储账号密码
        HashMap<String,String> initParameters = new HashMap<>();

        // 设置后台登录账号密码
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");

        // 谁都可以访问
        initParameters.put("allow","");

        bean.setInitParameters(initParameters); // 设置初始化参数
        return bean;

    }

    // filter
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        // 被过滤的请求
        Map<String,String> initParameters = new HashMap<>();

        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);

        return bean;
    }

}
