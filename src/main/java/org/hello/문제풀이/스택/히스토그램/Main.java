package org.hello.문제풀이.스택.히스토그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static int N;
    private static long[] high;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        high = new long[N + 1];

        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            high[i] = value;
        }

        Stack<Integer> stack = new Stack<>();

        long answer = 0L;

        for(int i = 0; i < high.length; i++) {

            long value = high[i];

            if(stack.isEmpty()) {
                stack.add(i);
                continue;
            }

            if(high[stack.peek()] < value) {
                stack.push(i);
                continue;
            }

            while(!stack.isEmpty() && high[stack.peek()] > value) {
                int idx = stack.pop();
                int leftSmallerIdx = stack.isEmpty() ? -1 : stack.peek();
                long width = i - 1 - leftSmallerIdx;
                answer = Math.max(answer, width * high[idx]);
            }

            stack.push(i);
        }

        System.out.println(answer);
    }
}
