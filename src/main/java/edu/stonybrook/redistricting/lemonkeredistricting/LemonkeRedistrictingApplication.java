package edu.stonybrook.redistricting.lemonkeredistricting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LemonkeRedistrictingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonkeRedistrictingApplication.class, args);
	}

}
