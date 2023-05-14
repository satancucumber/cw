package LETI.cw;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"LETI.cw.controller"})
@EntityScan("LETI.cw.models")
@EnableJpaRepositories("LETI.cw.repo")
public class CwApplication {
	public static final Logger log = Logger.getLogger("ShopAdminLog");
	public static void main(String[] args) {
		SpringApplication.run(CwApplication.class, args);
	}

}
