package com.example.helloworld.streamPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPracticeManager {

    public List<Transaction> findAll2011TransactionsManual(List<Transaction> transactionList) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            if (transaction.getYear() == 2011) {
                result.add(transaction);
            }
        }
        return result;
    }

    public List<Transaction> findAll2011TransactionsStream(List<Transaction> transactionList) {
        return transactionList.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .collect(Collectors.toList());
    }
}
