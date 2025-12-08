package org.hello.문제풀이.그리디.크게만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;

    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

        int k = K;
        for(int i = 0; i < arr.length; i++) {
            while(k > 0 && !stack.isEmpty() && stack.peek() < arr[i]) {
                stack.pop();
                k--;
            }
            stack.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();

        while(k > 0) {
            stack.pop();
            k--;
        }

        for(int number: stack) {
            sb.append(number);
        }

        System.out.println(sb.substring(0, N - K));
    }
}
