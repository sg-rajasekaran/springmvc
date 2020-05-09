package rnataraj.springframework.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication()
public class SpringmvcApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringmvcApplication.class, args);

        //System.out.println("Beans***");
        //System.out.println(ctx.getBeanDefinitionCount());;
        //System.out.println((ctx.getBeanDefinitionNames()));
    }

}
