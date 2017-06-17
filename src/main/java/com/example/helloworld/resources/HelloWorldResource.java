package com.example.helloworld.resources;

import com.example.helloworld.core.Saying;
import com.example.helloworld.core.TwoDimensionalArray;
import com.example.helloworld.permutations.Permutate;
import com.example.helloworld.permutations.PermutateV2;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldResource.class);

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Timed
    @Path("/perms")
    public List<String> calcPermutation(@QueryParam("input") String input) {
        Permutate permutate = new Permutate();
        return permutate.calcuatePermutations(input);
    }

    @GET
    @Timed
    @Path("/permsV2")
    public List<String> calcPermutationV2(@QueryParam("input") String input) {
        PermutateV2 permutate = new PermutateV2();
        return permutate.calcuatePermutations(input);
    }

    @GET
    @Timed
    @Path("/uniqueCheck/{input}")
    public String uniqueCheck(@PathParam("input") String input){
        LOG.debug("uniqueCheck:{}", input);
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (char inputChar: input.toCharArray()) {
            LOG.debug("uniqueCheck inputChar:{}", inputChar);
            charMap.computeIfAbsent(inputChar, character -> charMap.put(character, 0));
            charMap.computeIfPresent(inputChar, (character, value) -> value+1);
        }
        for (Character k: charMap.keySet()) {
            LOG.debug("uniqueCheck key:{} value:{}", k, charMap.get(k));
            if (charMap.get(k) > 1) {
                return k + " NotUnique " + charMap.get(k);
            }
        }
        return "Unique";
    }

    @GET
    @Timed
    @Path("/permutationCompare")
    public String permutationCompare(@QueryParam("string1") String string1, @QueryParam("string2") String string2) {
        Map<Character, PermValue> freq = new HashMap<>();
        for (Character key : string1.toCharArray()) {
            PermValue value = freq.getOrDefault(key, new PermValue(0, 0));
            value.setString1Value(value.getString1Value() + 1);
            freq.put(key, value);
        }
        for (Character key : string2.toCharArray()) {
            PermValue value = freq.getOrDefault(key, new PermValue(0, 0));
            value.setString2Value(value.getString2Value() + 1);
            freq.put(key, value);
        }
        for (Character key : freq.keySet()) {
            PermValue value = freq.get(key);
            if (value.getString1Value() != value.getString2Value()) {
                return Boolean.FALSE.toString();
            }
        }
        return Boolean.TRUE.toString();
    }

    @POST
    @Timed
    @Path("/traverseCounterClockWise")
    public List<Integer> traverseCounterClockWise(TwoDimensionalArray input) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> raw = input.getTwoDimensionalArray();

        if (raw.size() == 0 || raw.get(0).size() == 0) {
            return result;
        }

        int left = 0;
        int right = raw.get(0).size() - 1;
        int top = 0;
        int bottom = raw.size() - 1;

        while(left <= right && top <= bottom) {

            printDown(top, bottom, left, raw, result);
            left++;
            if (left > right) {break;}

            printRight(left, right, bottom, raw, result);
            bottom--;
            if (top > bottom) { break; }

            printUp(bottom, top, right, raw, result);
            right--;
            if (left > right) { break; }

            printLeft(right, left, top, raw, result);
            top++;
            if (top > bottom) { break; }
        }

        return result;
    }

    private void printDown(int startY, int finishY, int staticX, List<List<Integer>> raw, List<Integer> result) {
        for(int i = startY; i <= finishY; i++) {
            result.add(raw.get(i).get(staticX));
        }
    }

    private void printRight(int startX, int finishX, int staticY, List<List<Integer>> raw, List<Integer> result) {
        for(int i = startX; i <= finishX; i++) {
            result.add(raw.get(staticY).get(i));
        }
    }

    private void printUp(int startY, int finishY, int staticX, List<List<Integer>> raw, List<Integer> result) {
        for(int i = startY; i >= finishY; i--) {
            result.add(raw.get(i).get(staticX));
        }
    }

    private void printLeft(int startX, int finishX, int staticY, List<List<Integer>> raw, List<Integer> result) {
        for(int i = startX; i >= finishX; i--) {
            result.add(raw.get(staticY).get(i));
        }
    }

    public class PermValue {
        private Integer string1Value;
        private Integer string2Value;
        public PermValue(final Integer string1Value, final Integer string2Value) {
            this.string1Value = string1Value;
            this.string2Value = string2Value;
        }

        public Integer getString1Value() {
            return string1Value;
        }

        public void setString1Value(Integer string1Value) {
            this.string1Value = string1Value;
        }

        public Integer getString2Value() {
            return string2Value;
        }

        public void setString2Value(Integer string2Value) {
            this.string2Value = string2Value;
        }
    }
}
