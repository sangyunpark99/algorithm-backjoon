package org.hello.문제풀이.스택.도키도간식드리미;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<Integer> stack = new Stack<>();
        int w = 1;

        for(int x : arr) {
            while(!stack.isEmpty() && stack.peek() == w) {
                stack.pop();
                w++;
            }

            if(x == w) {
                w++;
            }else {
                stack.push(x);
            }
        }

        while(!stack.isEmpty() && stack.peek() == w) {
            stack.pop();
            w++;
        }

        System.out.println(w == N + 1 ? "Nice" : "Sad");
    }
}