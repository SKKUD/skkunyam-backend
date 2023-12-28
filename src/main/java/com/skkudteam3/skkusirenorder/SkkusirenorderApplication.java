package com.skkudteam3.skkusirenorder;

import com.skkudteam3.skkusirenorder.common.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {
		"classpath:env/env.yml",
		}, factory = EnvConfig.class)
public class SkkusirenorderApplication {
	public static void main(String[] args) {
		SpringApplication.run(SkkusirenorderApplication.class, args);
	}

}