package org.hello.문제풀이.비트마스킹.막대기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        int cnt = 0;
        while(X > 0) {
            if((X & 1) == 1) cnt++;
            X >>= 1;
        }

        System.out.println(cnt);
    }
}
