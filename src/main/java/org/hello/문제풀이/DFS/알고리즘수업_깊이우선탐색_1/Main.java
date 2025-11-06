package org.hello.문제풀이.DFS.알고리즘수업_깊이우선탐색_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int R;

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] cnts;

    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            graph.add(new  ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        cnts = new int[N + 1];
        cnts[R] = count;

        dfs(R);

        for(int i = 1; i <= N; i++) {
            System.out.println(cnts[i]);
        }
    }

    private static void dfs(int number) {
        for(int next : graph.get(number)) {
            if(cnts[next] == 0) { // 다음으로 이동할 그래프
                count++;
                cnts[next] = count;
                dfs(next);
            }
        }
    }
}
