package org.hello.문제풀이.트리DP.우수마을;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] people;
    private static List<List<Integer>> tree = new ArrayList<>();
    private static int[][] dp;;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        people = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dp = new int[N + 1][2]; // 우수 마을 선정 or 선정 x

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, visited);

        int maxValue = Math.max(dp[1][0], dp[1][1]);
        System.out.println(maxValue);
    }

    private static void dfs(int cur, boolean[] visited) {

        dp[cur][0] = 0;
        dp[cur][1] = people[cur];

        for(int number: tree.get(cur)) {
            if(!visited[number]) {
                visited[number] = true;
                dfs(number, visited);

                dp[cur][0] = dp[cur][0] + Math.max(dp[number][0], dp[number][1]);
                dp[cur][1] = dp[cur][1] + dp[number][0];
            }
        }
    }
}
