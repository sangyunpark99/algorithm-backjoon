package org.hello.문제풀이.스택.스택2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    private static Stack<Integer> stack = new Stack<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int[] orders = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int len = orders.length;
            if(len == 2) {
                stack.push(orders[1]);
                continue;
            }

            int order = orders[0];

            if(order == 2) {
                System.out.println(stack.isEmpty() ? -1 : stack.pop());
            }else if(order == 3) {
                System.out.println(stack.size());
            }else if(order == 4) {
                System.out.println(stack.isEmpty() ? 1 : 0);
            }else {
                System.out.println(stack.isEmpty() ? -1 : stack.peek());
            }
        }
    }
}
