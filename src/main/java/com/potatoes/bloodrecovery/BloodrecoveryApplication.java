package com.potatoes.bloodrecovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BloodrecoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodrecoveryApplication.class, args);
	}

}
