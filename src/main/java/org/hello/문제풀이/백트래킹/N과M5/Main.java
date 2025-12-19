package org.hello.문제풀이.백트래킹.N과M5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);

        permutation(new boolean[N], new int[M], 0);
    }

    private static void permutation(boolean[] visited, int[] value, int depth) {

        if(depth == M) {
            for(int i = 0; i < value.length; i++) {
                System.out.print(value[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                value[depth] = arr[i];
                permutation(visited, value, depth + 1);
                visited[i] = false;
            }
        }
    }
}
