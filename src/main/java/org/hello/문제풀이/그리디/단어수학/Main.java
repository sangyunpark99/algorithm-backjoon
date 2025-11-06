package org.hello.문제풀이.그리디.단어수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static HashMap<String, Integer> digit = new HashMap<>();
    private static HashMap<String, Integer> match = new  HashMap<>();
    private static List<String> values = new ArrayList<>();
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < 26; i++) {
            digit.put(String.valueOf((char) ('A' + i)), 0);
        }

        // 자릿수 숫자 지정하기
        for(int i = 0; i < N; i++) {
            String value = br.readLine();
            values.add(value);
            char[] arr = value.toCharArray();
            int len = arr.length;
            for(int j = 0; j < len; j++) { // 알파벳 돌면서
                char c = arr[j];
                int s = digit.get(String.valueOf(c)); // 이전 가중치
                int weight = (int) Math.pow(10, len - j - 1); // 자릿수 만큼 가중치 주기
                digit.put(String.valueOf(c), s + weight);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(digit.entrySet());
        Collections.sort(list, (a,b) -> {
            return b.getValue() - a.getValue();
        }); // 자릿수 순서대로 정렬

        int max = 9;
        for(int i = 0; i < list.size(); i++) {
            String key = list.get(i).getKey();
            match.put(key, Math.max(max--,0));
        }

        for(int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            answer += convert(value);
        }

        System.out.println(answer);
    }

    private static int convert(String value) {

        int len = value.length();
        int sum = 0;
        int idx = 0;
        for(char c : value.toCharArray()) {
            sum += match.get(String.valueOf(c)) * (int) Math.pow(10, len - idx - 1);
            idx++;
        }

        return sum;
    }
}
