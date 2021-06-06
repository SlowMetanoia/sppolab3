package logger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.company.*;

@Configuration
@ComponentScan("com.company")
@ComponentScan("logger")
public class ApplicationConfig {
	
}
