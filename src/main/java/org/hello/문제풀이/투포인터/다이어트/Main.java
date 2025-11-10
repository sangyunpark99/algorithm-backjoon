package org.hello.문제풀이.투포인터.다이어트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int G;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        G = Integer.parseInt(br.readLine());

        int left = 1;
        int right = 2;

        while(left < right) {
            int gap = right * right - left * left;
            if(gap == G) {
                sb.append(right).append("\n");
                right++;
                continue;
            }

            if(gap > G) {
                left++;
                continue;
            }

            if(gap < G) {
                right++;
            }
        }

        if(sb.length() == 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(sb);
    }
}
