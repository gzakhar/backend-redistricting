package edu.stonybrook.redistricting.lemonkeredistricting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication(exclude = {BatchAutoConfiguration.class})
@ImportResource("classpath*:/*-context.xml")
public class LemonkeRedistrictingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonkeRedistrictingApplication.class, args);
	}
}
