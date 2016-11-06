package cc.sion567.rest.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new DispatcherServlet(), "/");
//        registration.setAsyncSupported(true);
//        return registration;
//    }
}
