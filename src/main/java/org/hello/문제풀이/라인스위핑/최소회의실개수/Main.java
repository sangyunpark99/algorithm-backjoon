package org.hello.문제풀이.라인스위핑.최소회의실개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static List<int[]> timeLines = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            timeLines.add(new int[]{start, end});
        }

        Collections.sort(timeLines, (a,b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[1] - b[1];
        });

        int cnt = 1;
        pq.offer(new int[]{timeLines.get(0)[0], timeLines.get(0)[1]}); // 회의실 방 추가
        int idx = 1;
        while(idx < N) { // O(NlogN)
            int[] time = timeLines.get(idx++);
            if(time[0] >= pq.peek()[1]) { // 시작 시간 >= 끝나는 시간
                pq.poll();
                pq.offer(new int[]{time[0], time[1]}); // 시간 갱신
            }else { // 시작 시간 < 끝나는 시간
                cnt++; // 회의실 갯수 추가
                pq.offer(new int[]{time[0], time[1]});
            }
        }

        System.out.println(cnt);
    }
}
