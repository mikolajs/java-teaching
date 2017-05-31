package pl.edu.osp;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import pl.edu.osp.web.MainController;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

   public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
	while(true){
		try { 
			Thread.sleep(10000);
			System.out.println("app started");
		} catch (InterruptedException e) {
			System.out.println("exeption" + e.toString());
		}
	}
   }

}
