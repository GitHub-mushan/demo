package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")   //注入DataSourceConfig对象读取配置文件，前缀为   ，之后字段和实体类的属性一致进行匹配
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }
    //配置监控
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
        initParams.put("deny","");//后台拒绝谁访问

        bean.setInitParameters(initParams);
        return bean;
    }

    //配置druid监控之web监控的filter
    //webstatfilter：用于配置web和druid数据源之间的管理关联监控统计
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //exclusions:设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.json,*.css,/druid/*");

        bean.setInitParameters(initParams);

        //“/*” 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));

        return  bean;
    }
}