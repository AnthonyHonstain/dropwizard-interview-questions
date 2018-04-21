package com.example.helloworld.resources;

import com.example.helloworld.core.Saying;
import com.example.helloworld.core.TwoDimensionalArray;
import com.example.helloworld.permutations.Permutate;
import com.example.helloworld.permutations.PermutateV2;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
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

    @POST
    @Timed
    @Path("/iteration")
    public void iterations(@QueryParam("input") String input) {
        // Working through different styles of nested for loops,
        // attempting to visualize the differences.
        for (int i = input.length() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                LOG.debug("{}MINE ({},{}) {}",
                        StringUtils.leftPad(" ", input.length() - i),
                        j,
                        i,
                        input.substring(j, i+1));
            }
        }
        LOG.debug("");
        for (int i = input.length(); i > 0; i--) {
            for (int j = 0; j <= input.length() - i; j++) {
                // We stop inner loop at the difference between size and i.
                LOG.debug("{}BOOK ({},{}) {} j <= input.length()->{} - i->{}",
                        StringUtils.leftPad(" ", input.length() - i),
                        j,
                        i+j-1,
                        input.substring(j, i+j),
                        input.length(),
                        i);
            }
        }
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
    @Path("/uniqueCheckV2/{input}")
    public String uniqueCheckV2(@PathParam("input") String input){
        LOG.debug("uniqueCheckV2:{}", input);
        for (int i = 0; i < input.length() - 1; i++) {
            for (int j = i + 1; j < input.length(); j++) {
                LOG.debug("uniqueCheckV2: {} {} {} {}", i, input.charAt(i), j, input.charAt(j));
                if (input.charAt(i) == input.charAt(j)) {
                    return "NotUnique";
                }
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

    @GET
    @Timed
    @Path("/permutationCompareV2")
    public String permutationCompareV2(@QueryParam("string1") String string1, @QueryParam("string2") String string2) {
        HashMap<Character, MutablePair<Integer,Integer>> freq = new HashMap<>();
        for (char char1 : string1.toCharArray()) {
            if (freq.containsKey(char1)) {
                freq.get(char1).left += 1;
            }
            else {
                freq.put(char1, new MutablePair(1, 0));
            }
        }
        for (char char2 : string2.toCharArray()) {
            if (freq.containsKey(char2)) {
                freq.get(char2).right += 1;
            }
            else {
                return "False";
            }
        }
        for (Pair<Integer,Integer> value : freq.values()) {
            if (value.getLeft() != value.getRight()) {
                return "False";
            }
        }
        return "True";
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
