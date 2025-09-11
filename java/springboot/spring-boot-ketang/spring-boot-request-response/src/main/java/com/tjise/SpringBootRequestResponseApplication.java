package com.tjise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootRequestResponseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRequestResponseApplication.class, args);
//		ApplicationContext context = SpringApplication.run(SpringBootRequestResponseApplication.class, args);
//		String[] beanNames = context.getBeanDefinitionNames();
//		Arrays.sort(beanNames); // 排序，方便查看
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}
	}

}
