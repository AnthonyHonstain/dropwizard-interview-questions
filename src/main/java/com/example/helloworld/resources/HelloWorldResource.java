package com.example.helloworld.resources;

import com.example.helloworld.core.Saying;
import com.example.helloworld.permutations.Permutate;
import com.example.helloworld.permutations.PermutateV2;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

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
}