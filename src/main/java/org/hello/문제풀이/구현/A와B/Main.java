package org.hello.문제풀이.구현.A와B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String S;
    private static String T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        while(T.length() > S.length()) {
            char lastValue = T.charAt(T.length() - 1);
            if(lastValue == 'A') {
                T.deleteCharAt(T.length() - 1);
            }else {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }

        System.out.println(T.toString().equals(S) ? 1 : 0);
    }
}
