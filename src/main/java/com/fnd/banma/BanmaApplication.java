package com.fnd.banma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fnd.banma.mapper")
public class BanmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanmaApplication.class, args);
	}

}
