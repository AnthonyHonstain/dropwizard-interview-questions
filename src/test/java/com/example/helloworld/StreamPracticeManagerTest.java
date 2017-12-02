package com.example.helloworld;

import com.example.helloworld.streamPractice.StreamPracticeManager;
import com.example.helloworld.streamPractice.Trader;
import com.example.helloworld.streamPractice.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StreamPracticeManagerTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void findAll2011TransactionsTest() {
        StreamPracticeManager streamPracticeManager = new StreamPracticeManager();
        List<Transaction> resultManual = streamPracticeManager.findAll2011TransactionsManual(transactions);
        List<Transaction> resultStream = streamPracticeManager.findAll2011TransactionsStream(transactions);

        List<Transaction> expected = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400)
        );
        //assertThat(expected, is(equalTo(result)));
        assertThat(expected, equalTo(resultManual));

        assertThat(expected, equalTo(resultStream));
    }
}
