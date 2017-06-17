package com.example.helloworld.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TwoDimensionalArray {
    private List<List<Integer>> twoDimensionalArray;

    public TwoDimensionalArray() {
        // Jackson deserialization
    }

    public TwoDimensionalArray(List<List<Integer>> twoDimensionalArray) {
        this.twoDimensionalArray = twoDimensionalArray;
    }

    @JsonProperty
    public List<List<Integer>> getTwoDimensionalArray() {
        return twoDimensionalArray;
    }
}
