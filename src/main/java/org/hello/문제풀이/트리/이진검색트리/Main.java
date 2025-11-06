package org.hello.문제풀이.트리.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> pre = new ArrayList<>();
    private static int idx = 0;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while((line = br.readLine()) != null && !line.isEmpty()) {
            pre.add(Integer.parseInt(line));
        }

        postOrder(Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(sb.toString());
    }

    private static void postOrder(long min, long max) {
        if(idx >= pre.size()) return;

        int val = pre.get(idx); // 현재 값 - 50
        if(val < min || val > max) return; // min 보다 작고, max 보다 크면 해당 노드 x

        idx++; // idx 증가
        postOrder(min, val - 1L); // 왼쪽 - 더 작은 값 탐색
        postOrder(val + 1L, max); // 오른쪽 - 더 큰 값 탐색
        sb.append(val).append("\n");
    }
}
