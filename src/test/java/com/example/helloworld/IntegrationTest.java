package com.example.helloworld;

import com.example.helloworld.core.TwoDimensionalArray;
import com.google.common.base.Optional;

import com.example.helloworld.core.Saying;
import com.google.common.collect.ImmutableList;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("hello-world.yml");

    @ClassRule
    public static final DropwizardAppRule<HelloWorldConfiguration> RULE =
            new DropwizardAppRule<>(HelloWorldApplication.class, CONFIG_PATH);

    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void testHelloWorld() {
        final Optional<String> name = Optional.of("TestName");
        final Saying saying = client.target("http://localhost:" + RULE.getLocalPort() + "/hello-world")
                .queryParam("name", name.get())
                .request()
                .get(Saying.class);
        assertEquals("Hello, " + name.get() + "!", saying.getContent());
    }

    @Test
    public void testPermutationCompare() {
        assertEquals(Boolean.TRUE.toString(), permutationCompareRestCall("foo", "oof"));
        assertEquals(Boolean.TRUE.toString(), permutationCompareRestCall("a", "a"));
        assertEquals(Boolean.FALSE.toString(), permutationCompareRestCall("a", "aa"));
        assertEquals(Boolean.FALSE.toString(), permutationCompareRestCall("aa", "a"));
        assertEquals(Boolean.TRUE.toString(), permutationCompareRestCall("bbaaa", "ababa"));
        assertEquals(Boolean.FALSE.toString(), permutationCompareRestCall("ab", "cab"));
    }

    private String permutationCompareRestCall(final String string1, final String string2) {
        final String result = client.target("http://localhost:" + RULE.getLocalPort() + "/hello-world/permutationCompare")
                .queryParam("string1", string1)
                .queryParam("string2", string2)
                .request()
                .get(String.class);
        return result;
    }

    @Test
    public void testUniqueCheck() {
        assertEquals("Unique", uniqueCheckHelper("a"));
        assertEquals("Unique", uniqueCheckHelper("ab"));
        assertEquals("a NotUnique 2", uniqueCheckHelper("aa"));
        assertEquals("a NotUnique 2", uniqueCheckHelper("abca"));
        assertEquals("a NotUnique 3", uniqueCheckHelper("abcadea"));

    }

    private String uniqueCheckHelper(String input) {
        final String result = client.target(
                "http://localhost:" + RULE.getLocalPort() + "/hello-world/uniqueCheck/" + input
        ).request().get(String.class);
        return result;
    }

    @Test
    public void testTraverseCounterClockWise() {
        List<List<Integer>> testArray = new ArrayList();
        testArray.add(ImmutableList.of(1, 2, 3));
        testArray.add(ImmutableList.of(4, 5, 6));
        testArray.add(ImmutableList.of(7, 8, 9));

        TwoDimensionalArray testInput = new TwoDimensionalArray(testArray);
        final List<Integer> result = client.target(
                "http://localhost:" + RULE.getLocalPort() + "/hello-world/traverseCounterClockWise/"
        ).request().post(Entity.json(testInput), List.class);

        assertEquals(ImmutableList.of(1,4,7,8,9,6,3,2,5), result);
    }
}