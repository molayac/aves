package co.com.ias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@EnableAutoConfiguration
public class AvesApplication {

	public static void main(String[] args) {
		try {
			/*Create Directory By default*/
			Files.createDirectories(Paths.get("db"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(AvesApplication.class, args);

	}
}
