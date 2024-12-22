package com.jfund.currencypairsservice.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Import;

@Configuration
@Profile({"local", "dev", "test"})
@Import({DataSourceAutoConfiguration.class, LiquibaseAutoConfiguration.class})
public class LiquibaseConfig {
}
