package org.hello.문제풀이.백트래킹.부분수열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int S;
    private static int[] values;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0, 0);

        if(S == 0) { // 공집합은 제거해야 한다.
            answer--;
        }

        System.out.println(answer);
    }

    private static void dfs(int cur,int sum) {

        if(cur == N) {
            if(sum == S) {
                answer++;
            }
            return;
        }

        dfs(cur + 1, sum + values[cur]);
        dfs(cur + 1, sum);
    }
}
