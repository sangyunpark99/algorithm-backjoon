package org.hello.문제풀이.시뮬레이션.이차원배열과연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 수의 등장 횟수가 커지는 순, 이런것이 여러가지면 수가 커지는 순으로
    // 배열 A에 정렬된 결과를 넣을때, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저

    private static int r;
    private static int c;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[3][3];
        for(int i = 0; i < 3; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int t = 0;

        while(t <= 100) {
            if(get(arr, r - 1, c - 1) == k) {
               System.out.println(t);
               return;
            }

            if(arr.length >= arr[0].length) arr = R(arr);
            else arr = C(arr);
            t++;
        }

        System.out.println(-1);
    }

    private static int get(int[][] arr, int i, int j) {
        // idx 넘어가는 경우도 생각해야 한다.
        if(i < 0 || i >= arr.length) return 0;
        if(j < 0 || j >= arr[0].length) return 0;
        return arr[i][j];
    }

    private static int[][] R(int[][] arr) {
        int R = arr.length;
        int C = arr[0].length;
        List<int[]> rows = new ArrayList<>();
        int newC = 0;

        for(int i = 0; i < R; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            for(int j = 0; j < C; j++) {
                int v = arr[i][j];
                if(v == 0) continue;
                freq.put(v, freq.getOrDefault(v,0) + 1);
            }

            List<int[]> pairs = new ArrayList<>();
            for(Map.Entry<Integer, Integer> e : freq.entrySet()) {
                pairs.add(new int[]{e.getKey(), e.getValue()});
            }

            Collections.sort(pairs, (a, b) -> {
                if(a[1] == b[1]) {
                    return a[0] - b[0];
                }

                return a[1] - b[1];
            });

            int m = Math.min(50, pairs.size());
            int[] row = new int[Math.min(100, m * 2)]; // 100을 넘어가는 경우
            for(int k = 0, idx = 0; k < m && idx < 100; k++) {
                row[idx++] = pairs.get(k)[0];
                row[idx++] = pairs.get(k)[1];
            }

            rows.add(row);
            newC = Math.max(newC, row.length);
        }

        newC = Math.min(newC, 100); // 최대 100으로 제한 두기
        int[][] B = new int[R][newC];
        for(int i = 0; i < R; i++) {
            int[] row = rows.get(i);
            System.arraycopy(row, 0, B[i], 0, Math.min(row.length, newC)); // 배열 복사
        }

        return B;
    }

    private static int[][] C(int[][] arr) {
        arr = transpose(arr);
        arr = R(arr);
        return transpose(arr);
    }

    private static int[][] transpose(int[][] arr) { // 배열 회전
        int R = arr.length;
        int C = arr[0].length;
        int[][] T = new int[C][R];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                T[j][i] = arr[i][j];
            }
        }

        return T;
    }
}
