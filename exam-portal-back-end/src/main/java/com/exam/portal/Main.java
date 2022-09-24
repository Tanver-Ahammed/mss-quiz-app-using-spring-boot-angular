package com.exam.portal;

import java.util.*;

class Pair implements Comparable<Pair> {
    String key;
    long value;

    public Pair(String key, long value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Pair pair) {
        if ((this.key.compareTo(pair.key)) > 0)
            return 1;
        else if ((this.key.compareTo(pair.key)) < 0)
            return -1;
        return 0;
    }

}

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Pair pair = new Pair(scanner.next(), scanner.nextLong());
            pairs.add(pair);
        }

        Collections.sort(pairs);

        System.out.println(pairs);


        List<Integer> integers = new ArrayList<>(List.of(5, 5, 56, 56, 65, 65, 65, 65, 6, 3, 32, 3, 65));

        integers.sort(Comparator.reverseOrder());


    }

}
