package org.hello.문제풀이.투포인터.과일탕후루;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    private static int N;
    private static int[] fruit;
    private static HashMap<Integer, Integer> cnts = new HashMap<>();
    private static HashSet<Integer> kind = new HashSet<>();
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        fruit = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;
        int left = 0;

        for(int right = 0; right < N; right++) {
            int value = fruit[right];
            cnts.put(value, cnts.getOrDefault(value, 0) + 1);
            kind.add(value);

            while(kind.size() > 2) {
                int leftValue = fruit[left++];
                int cnt = cnts.get(leftValue);
                if(cnt - 1 == 0) {
                    kind.remove(leftValue);
                    cnts.remove(leftValue);
                }else {
                    cnts.put(leftValue, cnt - 1);
                }
            }

            answer = Math.max(answer, right - left + 1);
        }

        System.out.println(answer);
    }
}
