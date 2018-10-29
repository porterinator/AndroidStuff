package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

        public static Integer[] flatt(Object[] inputArray) throws IllegalArgumentException {

            if (inputArray == null) return null;

            List<Integer> flat = new ArrayList<>();

            for (Object el : inputArray) {
                if (el instanceof Integer) {
                    flat.add((Integer) el);
                } else if (el instanceof Object[]) {
                    flat.addAll(Arrays.asList(flatt((Object[]) el)));
                } else {
                    throw new IllegalArgumentException("Input must be an array of Integers or nested arrays of Integers");
                }
            }
            return flat.toArray(new Integer[flat.size()]);
        }


    public static void main(String[] args) {
	// write your code here
    }
}
