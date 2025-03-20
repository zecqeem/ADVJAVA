package com.example.try1;

import com.example.try1.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import com.example.try1.service.ProductParserService;

@SpringBootApplication
public class Try1Application {

	public static void main(String[] args) {
		SpringApplication.run(Try1Application.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationContext ctx) {
		return args -> {
			System.out.println("Приложение запущено!");

			// Вызываем CurrencyService
			CurrencyService currencyService = ctx.getBean(CurrencyService.class);
			String api = currencyService.getUsdRate();
			System.out.println("Курс доллара: " + api);

			// Запускаем парсер
			ProductParserService parserService = ctx.getBean(ProductParserService.class);
			System.out.println("Запускаем парсер товаров...");
			parserService.printProducts();
		};
	}
}