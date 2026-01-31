package org.hello.문제풀이.트리DP.뉴스전하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static List<List<Integer>> tree = new ArrayList<>();
    private static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(i == 0) continue;
            tree.get(parent).add(i);
        }

        time = new int[N + 1]; // 1-based Index

        System.out.println(go(0));
    }

    private static int go(int cur) {
        List<Integer> nodes = new ArrayList<>();

        for(int next: tree.get(cur)) {
            nodes.add(go(next));
        }

        nodes.sort(Collections.reverseOrder());

        int value = 0;

        for(int i = 0; i < nodes.size(); i++) {
            value = Math.max(value, nodes.get(i) + i + 1);
        }

        return time[cur] = value; // 완료 시간
    }
}