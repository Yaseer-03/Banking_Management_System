package com.example.BankingManagementSystem.CustomClasses;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountNumberGenerator {
    private static final String[] PREFIXES = {"1", "2", "3", "5", "6", "56", "66", "53", "63", "57", "67"};
    private static final int ACCOUNT_NUMBER_LENGTH = 11;
    private static Set<String> existingAccountNumbers = new HashSet<>(); // Simulate database check

    public static String generateUniqueAccountNumber() {
        Random random = new Random();
        String accountNumber;

        do {
            // Step 1: Choose a random prefix
            String prefix = PREFIXES[random.nextInt(PREFIXES.length)];

            // Step 2: Generate remaining digits using Java 8 IntStream
            int remainingLength = ACCOUNT_NUMBER_LENGTH - prefix.length();
            String remainingDigits = IntStream.range(0, remainingLength)
                    .mapToObj(i -> String.valueOf(random.nextInt(10))) // Generate random digit (0-9)
                    .collect(Collectors.joining());

            // Combine prefix and remaining digits
            accountNumber = prefix + remainingDigits;
        } while (existingAccountNumbers.contains(accountNumber)); // Ensure uniqueness

        // Add the generated account number to the set of existing account numbers
        existingAccountNumbers.add(accountNumber);

        return accountNumber;
    }
}

