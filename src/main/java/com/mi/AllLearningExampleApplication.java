package com.mi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //扫描TraceIdFilter
public class AllLearningExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllLearningExampleApplication.class, args);
	}

}
