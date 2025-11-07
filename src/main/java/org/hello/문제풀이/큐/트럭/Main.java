package org.hello.문제풀이.큐.트럭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int W;
    private static int L;
    private static int[] trucks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        trucks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Queue<Integer> bridge = new ArrayDeque<>(W);
        for(int i = 0; i < W; i++) bridge.offer(0);
        int idx = 0;
        int time = 0;
        int weight = 0;
        while(idx < N) {
            time++;
            weight -= bridge.poll();
            int next = trucks[idx];
            if(weight + next <= L) {
                bridge.offer(next);
                weight += next;
                idx++;
            }else {
                bridge.offer(0);
            }
        }

        System.out.println(time + W); // 마지막에 트럭이 지나가는 경우
    }
}