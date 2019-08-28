package com.zhenhua.microread;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching  // 开启缓存支持
@SpringBootApplication
@MapperScan("com.zhenhua.microread.mapper")
public class MicroreadApplication extends SpringBootServletInitializer {

    // Spring Boot导出war包部署到外部Tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MicroreadApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroreadApplication.class, args);
    }

    // 使用fastjson
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
