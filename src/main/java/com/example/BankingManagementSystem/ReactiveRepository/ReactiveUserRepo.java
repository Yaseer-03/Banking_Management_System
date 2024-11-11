package com.example.BankingManagementSystem.ReactiveRepository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.example.BankingManagementSystem.Model.User;
import reactor.core.publisher.Flux;

public interface ReactiveUserRepo extends ReactiveCrudRepository<User, Long> {
    Flux<User> findAll(); // Define other custom methods if needed
}
