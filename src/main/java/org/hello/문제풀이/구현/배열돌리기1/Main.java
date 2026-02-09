package org.hello.문제풀이.구현.배열돌리기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int R;

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        rotate();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void rotate() {
        int layers = Math.min(N, M) / 2;

        for(int k = 0; k < layers; k++) {
            int top = k;
            int left = k;
            int bottom = N - 1 - k;
            int right = M - 1 - k;

            ArrayList<Integer> list = new ArrayList<>();

            // 배열 테두리 추출
            for(int y = top; y <= bottom; y++) {
                list.add(map[y][left]);
            }

            for(int x = left + 1; x <= right; x++) {
                list.add(map[bottom][x]);
            }

            for(int y = bottom - 1; y >= top; y--) {
                list.add(map[y][right]);
            }

            for(int x = right - 1; x >= left + 1; x--) {
                list.add(map[top][x]);
            }

            int len = list.size();
            int shift = R % len;

            int idx = 0;

            // 값 옮기기
            for (int y = top; y <= bottom; y++){
                map[y][left] = list.get((idx++ - shift + len) % len);
            }

            for (int x = left + 1; x <= right; x++){
                map[bottom][x] = list.get((idx++ - shift + len) % len);
            }

            for (int y = bottom - 1; y >= top; y--){
                map[y][right] = list.get((idx++ - shift + len) % len);
            }

            for (int x = right - 1; x >= left + 1; x--){
                map[top][x] = list.get((idx++ - shift + len) % len);
            }
        }
    }
}
