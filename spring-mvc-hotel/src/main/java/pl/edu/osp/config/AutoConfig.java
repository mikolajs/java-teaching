package pl.edu.osp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("pl.edu.osp.impl")
@Import(Config.class)
public class AutoConfig {

}
