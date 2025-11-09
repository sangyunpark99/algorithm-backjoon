package org.hello.문제풀이.우선순위큐.가운데를말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> lq = new PriorityQueue<>(Collections.reverseOrder()); // top : 최대
        PriorityQueue<Integer> rq = new PriorityQueue<>(); // top: 최소

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) { // O(N)
            int value = Integer.parseInt(br.readLine());
            if(lq.size() == rq.size()) {
                lq.offer(value);
            }else {
                rq.offer(value);
            }

            while(!lq.isEmpty() && !rq.isEmpty() && lq.peek() > rq.peek()) {
                int lqValue = lq.poll();
                int rqValue = rq.poll();
                lq.offer(rqValue);
                rq.offer(lqValue);
            }

            sb.append(lq.peek()).append("\n");
        }

        // 중간ㅂ
        System.out.println(sb);
    }
}
