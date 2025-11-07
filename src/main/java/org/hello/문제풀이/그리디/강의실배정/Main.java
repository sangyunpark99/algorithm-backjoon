package org.hello.문제풀이.그리디.강의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 최소의 강의실을 사용해서 모든 수업을 가능하게 한다.
    // 수업이 끝난 직후에 다음 수업을 시작할 수 있다.

    private static int N;
    private static List<int[]> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.add(new int[]{a,b});
        }

        Collections.sort(arr, (a,b) -> { // 정렬 순서를 항상 생각
            if(a[0] == b[0]) {
                return a[1] - b[1]; // 먼저 시작 하는거
            }
            return a[0] - b[0]; // 먼저 끝나는거
        });

        // 끝나는 시간과 시작 시간 같으면 넘어가고 아니면 강의실 추가
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        int answer = 0;
        for(int i = 0; i < N; i++) { // O(N)
            int startTime = arr.get(i)[0];
            int endTime = arr.get(i)[1];

            if(!rooms.isEmpty() && rooms.peek() <= startTime) { // peek를 만족하지 못하면, 어차피 안된다.
                rooms.poll();
            }
            rooms.offer(endTime);
            if(rooms.size() > answer) answer = rooms.size();
        }

        System.out.println(answer);
    }
}
