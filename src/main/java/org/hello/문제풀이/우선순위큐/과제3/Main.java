package org.hello.문제풀이.우선순위큐.과제3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static HashMap<Integer,List<Integer>> tasks = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= 1000; i++) {
            tasks.put(i, new ArrayList<>());
        }

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            tasks.get(day).add(score);
        }

        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int day = 1000; day >= 0; day--) { // 그 당일에 어떤거 할지 정한다.
            List<Integer> task = tasks.get(day);
            if(!pq.isEmpty()) {
                answer += pq.poll();
            }

            for(int i = 0; i < task.size(); i++) {
                pq.offer(task.get(i));
            }
        }

        System.out.println(answer);
    }
}