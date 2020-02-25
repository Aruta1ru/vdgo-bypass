package com.vdgo.bypass.execvdgo;

import com.vdgo.bypass.execvdgo.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ExecvdgoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExecvdgoApplication.class, args);
	}
}
