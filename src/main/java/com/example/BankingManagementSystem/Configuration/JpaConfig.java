package com.example.BankingManagementSystem.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.FilterType;
import com.example.BankingManagementSystem.ReactiveRepository.ReactiveUserRepo;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.BankingManagementSystem.Repository",  // JPA repositories
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ReactiveUserRepo.class) // Exclude reactive
)
public class JpaConfig {
}