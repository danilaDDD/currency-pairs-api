package com.jfund.currencypairsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyPairsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyPairsServiceApplication.class, args);
	}
}
