package org.hello.문제풀이.DP.돌게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static boolean[] game;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        game = new boolean[N + 1]; // 돌이 i개 남았을 때, 현재 차례인 사람이 이길 수 있으면 true, 질 수밖에 없으면 false

        game[1] = true; // 상근 승
        if(N >= 2) game[2] = false; // 창영 승
        if(N >= 3) game[3] = true; // 상근 승

        // 상대가 i - 1개 돌일 때 지는가 or 상대가 i - 3개 돌일 때 지는가
        for(int i = 4; i <= N; i++) {
            game[i] = !game[i - 1] || !game[i - 3];
        }

        System.out.println(game[N] ? "SK" : "CY");
    }
}
