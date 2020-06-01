package mainApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controllers","services"})
public class MainAPP {
	public static void main(String[] args) {
		SpringApplication.run(MainAPP.class, args);
	}
}
