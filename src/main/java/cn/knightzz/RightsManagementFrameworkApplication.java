package cn.knightzz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author knightzz98
 */
@MapperScan("cn.knightzz.mapper")
@SpringBootApplication
public class RightsManagementFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(RightsManagementFrameworkApplication.class, args);
    }

}
