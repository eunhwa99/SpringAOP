package hello.aop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan
public class AopApplication {

	private static ApplicationContext context;
	public static void main(String[] args) {

		context = SpringApplication.run(AopApplication.class, args);
		printBeanList();
	}
	public static void printBeanList() {		String[] beans = context.getBeanDefinitionNames();
		for(String bean: beans)	{			System.out.println("Bean Definition Name: " + bean);		}	}

}
