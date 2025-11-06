package org.hello.문제풀이.스택;

import java.io.*;
import java.util.*;

public class Main {

    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] value = br.readLine().split("");

        int answer = 0;
        int temp = 1;

        for (int i = 0; i < value.length; i++) {
            String cur = value[i];

            if (cur.equals("(")) {
                stack.push('(');
                temp *= 2;
            } else if (cur.equals("[")) {
                stack.push('[');
                temp *= 3;
            }
            else if (cur.equals(")")) {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                }

                if (i > 0 && value[i - 1].equals("(")) { // 올바른 값이라면
                    answer += temp;
                }

                stack.pop();
                temp /= 2;
            }
            else if (cur.equals("]")) {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                }

                if (i > 0 && value[i - 1].equals("[")) {
                    answer += temp;
                }

                stack.pop();
                temp /= 3;
            }
        }

        // 모든 괄호가 닫히지 않았다면 0 처리
        if (!stack.isEmpty()) {
            answer = 0;
        }

        System.out.println(answer);
    }
}
