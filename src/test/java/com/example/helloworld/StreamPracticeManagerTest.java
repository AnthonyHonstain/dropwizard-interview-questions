package com.example.helloworld;

import com.example.helloworld.streamPractice.StreamPracticeManager;
import com.example.helloworld.streamPractice.Trader;
import com.example.helloworld.streamPractice.Transaction;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StreamPracticeManagerTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300, "Dollar"),
            new Transaction(raoul, 2012, 1000, "Pound"),
            new Transaction(raoul, 2011, 400, "Pound"),
            new Transaction(mario, 2012, 710, "Frank"),
            new Transaction(mario, 2012, 700, "Frank"),
            new Transaction(alan, 2012, 950, "Dollar")
    );

    @Test
    public void findAll2011TransactionsTest() {
        StreamPracticeManager streamPracticeManager = new StreamPracticeManager();

        List<Transaction> expected = Arrays.asList(
                new Transaction(brian, 2011, 300, "Dollar"),
                new Transaction(raoul, 2011, 400, "Pound")
        );

        List<Transaction> resultManual = streamPracticeManager.findAll2011TransactionsManual(transactions);
        assertThat(resultManual, equalTo(expected));

        List<Transaction> resultStream = streamPracticeManager.findAll2011TransactionsStream(transactions);
        assertThat(resultStream, equalTo(expected));
    }

    @Test
    public void groupByCurrencyTest() {
        StreamPracticeManager streamPracticeManager = new StreamPracticeManager();

        Map<String, List<Transaction>> expected = ImmutableMap.of(
                "Dollar", ImmutableList.of(
                        new Transaction(brian, 2011, 300, "Dollar"),
                        new Transaction(alan, 2012, 950, "Dollar")
                ),
                "Pound", ImmutableList.of(
                        new Transaction(raoul, 2012, 1000, "Pound"),
                        new Transaction(raoul, 2011, 400, "Pound")
                ),
                "Frank", ImmutableList.of(
                        new Transaction(mario, 2012, 710, "Frank"),
                        new Transaction(mario, 2012, 700, "Frank")
                )
        );

        Map<String, List<Transaction>> resultManual = streamPracticeManager.groupTransactionManual(transactions);
        assertThat(resultManual, equalTo(expected));

        Map<String, List<Transaction>> resultStream = streamPracticeManager.groupTransactionStream(transactions);
        assertThat(resultStream, equalTo(expected));
    }
}
