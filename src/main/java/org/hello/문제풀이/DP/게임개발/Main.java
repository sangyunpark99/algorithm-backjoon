package org.hello.문제풀이.DP.게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int N;
    private static int[] costs;
    private static List<int[]> build = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        costs = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            build.add(new int[]{});
        }

        for(int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cost = arr[0];
            costs[i + 1] = cost;
            int[] bfBuild = Arrays.copyOfRange(arr, 1, arr.length - 1);
            build.set(i + 1, bfBuild);
        }

        
    }
}
