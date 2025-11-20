package org.hello.문제풀이.백트래킹.가르침;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int K;
    private static String[] words;
    private static boolean[] visited = new boolean[26];
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 일단 anta tica는 무조건 배워야 함
        words = new String[N];

        if(K < 5) {
            System.out.println(0);
            return;
        }

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            words[i] = s.substring(4, s.length() - 4);
        }

        visited['a' - 'a'] = true; // 인덱스로 관리
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        dfs(0,0);

        System.out.println(answer);
    }

    private static void dfs(int start, int depth) {
        if(depth == K - 5) {
            int value = count();
            answer = Math.max(answer, value);
        }

        for(int i = start; i < 26; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static int count() {
        int cnt = 0;

        outer:
        for(String word : words) {
            for(char c : word.toCharArray()) {
                if(!visited[c - 'a']) {
                    continue outer;
                }
            }

            cnt++;
        }

        return cnt;
    }
}