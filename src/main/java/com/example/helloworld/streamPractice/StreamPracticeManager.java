package com.example.helloworld.streamPractice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

    public Map<String, List<Transaction>> groupTransactionManual(List<Transaction> transactionList) {
        Map<String, List<Transaction>> currencyMap = new HashMap<>();

        for (Transaction transaction: transactionList) {
            String currency = transaction.getCurrency();
            if (!currencyMap.containsKey(currency)) {
                currencyMap.put(currency, new ArrayList<>());
            }
            currencyMap.get(currency).add(transaction);
        }
        return currencyMap;
    }

    public Map<String, List<Transaction>> groupTransactionStream(List<Transaction> transactionList) {
        return transactionList.stream()
                .collect(groupingBy(Transaction::getCurrency));
    }
}
