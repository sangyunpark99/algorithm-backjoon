package org.hello.문제풀이.분할정복.색종이만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[][] map;
    private static int black = 0;
    private static int white = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        divideConquer(0, N - 1, 0, N - 1);

        System.out.println(white);
        System.out.println(black);
    }

    private static void divideConquer(int a1, int a2, int b1, int b2) {

        if(a1 == a2 && b1 == b2) { // 동일한 칸인 경우
            if(map[a1][b1] == 0) {
                white++;
            }else {
                black++;
            }

            return;
        }

        int value = check(a1, a2, b1, b2);
        if(value == 1) {
            black++;
        }else if(value == 0) {
            white++;
        }else { // 색이 섞여있는 경우
            divideConquer(a1, (a1 + a2) / 2, b1, (b1 + b2) / 2);
            divideConquer((a1 + a2)/ 2 + 1, a2, b1, (b1 + b2) / 2);
            divideConquer(a1, (a1 + a2) / 2, (b1 + b2) / 2 + 1, b2);
            divideConquer((a1 + a2) / 2 + 1, a2, (b1 + b2) / 2 + 1, b2);
        }
    }

    private static int check(int a1, int a2, int b1, int b2) {

        int color = map[a1][b1];

        for(int i = a1; i <= a2; i++) {
            for(int j = b1; j <= b2; j++) {
                if(map[i][j] != color) {
                    return -1;
                }
            }
        }

        return color;
    }
}
