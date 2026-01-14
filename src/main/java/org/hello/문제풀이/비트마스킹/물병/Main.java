package org.hello.문제풀이.비트마스킹.물병;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int M;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int n = M;
        int answer = 0;
        while(Integer.bitCount(n) > K) {
            int lsb = n & -n;
            answer += lsb;
            n += lsb;
        }

        System.out.println(answer);
    }
}
