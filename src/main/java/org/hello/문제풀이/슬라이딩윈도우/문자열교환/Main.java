package org.hello.문제풀이.슬라이딩윈도우.문자열교환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        int aCnt = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a') aCnt++;
        }

        if(aCnt <= 1) {
            System.out.println(0);
            return;
        }

        String doubled = s + s;
        char[] arr = doubled.toCharArray();

        int bCnt = 0;
        for(int i = 0; i < aCnt; i++) {
            if(arr[i] == 'b') bCnt++;
        }

        int answer = bCnt;

        for(int start = 1; start < n; start++) {
            int end = start + aCnt - 1;

            if(arr[start - 1] == 'b') bCnt--;
            if(arr[end] == 'b') bCnt++;

            answer = Math.min(answer, bCnt);
        }

        System.out.println(answer);
    }
}
