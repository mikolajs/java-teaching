package pl.edu.osp;

//import java.util.HashMap;
//import java.util.Map;

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
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        //Map<String, Object> prop = new HashMap<String, Object>();
        //prop.put("local.server.port", "9090");
        //app.setDefaultProperties(prop);
        app.run(args);
        int i = 0;
	while(true){
		try { 
			i++;
			Thread.sleep(100000);
			System.out.println("app started " + i);
		} catch (InterruptedException e) {
			System.out.println("exeption" + e.toString());
		}
	}
   }

}
