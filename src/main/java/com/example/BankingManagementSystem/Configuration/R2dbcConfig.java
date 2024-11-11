package com.example.BankingManagementSystem.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@Configuration
@EnableR2dbcRepositories(
    basePackages = "com.example.BankingManagementSystem.ReactiveRepository"  // Reactive repositories
)
public class R2dbcConfig {
}