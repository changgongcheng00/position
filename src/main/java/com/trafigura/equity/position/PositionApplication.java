package com.trafigura.equity.position;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.trafigura.equity.position.dao")
public class PositionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionApplication.class, args);
	}

}
