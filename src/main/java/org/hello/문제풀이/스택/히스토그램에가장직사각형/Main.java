package org.hello.문제풀이.스택.히스토그램에가장직사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        // 가장 넓이가 큰 직사각형
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = input[0];
            if(n == 0) {
                break;
            }
            int[] arr = Arrays.copyOfRange(input, 1, input.length);

            long maxValue = 0;
            stack.clear();

            for(int i = 0; i <= arr.length; i++) {
                long curHeight = i == arr.length ? 0 : arr[i];
                while(!stack.isEmpty() && arr[stack.peek()] > curHeight) {
                    long height = arr[stack.pop()];
                    long width;

                    if(stack.isEmpty()) {
                        width = i;
                    }else {
                        width = i - 1 - stack.peek();
                    }

                    maxValue = Math.max(maxValue, height * width);
                }
                stack.push(i);
            }

            System.out.println(maxValue);
        }
    }
}