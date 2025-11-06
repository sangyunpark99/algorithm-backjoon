package org.hello.문제풀이.두포인터.화문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) { // 30
            String value = br.readLine();
            System.out.println(checkRemoveOne(value));
        }
    }

    private static boolean check(char[] s, int l, int r) {
        while(l < r) {
            if(s[l] != s[r]) return false;
            l++;
            r--;
        }

        return true;
    }

    private static int checkRemoveOne(String value) {
        char[] s =  value.toCharArray();
        int l = 0;
        int r = s.length - 1;

        while(l < r && s[l] == s[r]) { // 같을때가지 좌표 이동
            l++;
            r--;
        }

        if(l >= r) return 0;
        if(check(s, l + 1, r) || check(s, l, r - 1)) return 1; // 같지 않은 경우 하나씩 제거
        return 2;
    }
}