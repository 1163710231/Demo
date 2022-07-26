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

@Configuration
public class DruidConfiguration {
    @ConfigurationProperties(prefix = "spring.datasource")	// 用来扫描配置文件中前缀为spring.datasource的配置信息
    @Bean			//用来注册到配置容器中
    public DataSource druid() {
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> srb = new ServletRegistrationBean
                (new StatViewServlet(), "/druid/*");
        //IP白名单(没有配置或者为空，则允许所有访问)
        srb.addInitParameter("allow", "127.0.0.1");
        //IP黑名单(黑白均有时，deny优先于allow) :
        //如果满足deny的即提示：Sorry, you are not permitted to view this page.
        srb.addInitParameter("deny", "192.168.1.100");
        //账号参数名必须为loginUsername
        srb.addInitParameter("loginUsername", "123");
        //密码参数名必须为loginPassword
        srb.addInitParameter("loginPassword", "123");
        //是否能够重置数据
        srb.addInitParameter("resetEnable", "false");
        return srb;
    }

    //注册一个filters
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
//        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
