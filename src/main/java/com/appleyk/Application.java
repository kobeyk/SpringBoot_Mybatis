package com.appleyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * @Description
 *
/**
 *
 * 下面是一个典型的结构：

com
+- example
+- myproject
+- Application.java -- 注意这个位置，习惯性的放在项目的一开始，也就是根包的第一层【切记不要乱放】
| +
- domain
| +- Customer.java
| +- CustomerRepository.java
| +
- service
| +- CustomerService.java
| +
- web
+- CustomerController.java


 * @Author Appleyk
 * @Blob https://blog.csdn.net/appleyk
 * @Date Created on 上午 9:32 2018-8-31
 */
@SpringBootApplication// same as @Configuration @EnableAutoConfiguration  @ComponentScan
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    //打成war包需要继承SpringBootServletInitializer，并覆盖其config(SpringApplicationBuilder)方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
