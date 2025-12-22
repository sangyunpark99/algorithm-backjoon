package org.hello.문제풀이.우선순위큐.카드합체놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int i = 0; i < arr.length; i++) {
            pq.offer((long) arr[i]);
        }

        while(M > 0) {
            long a = pq.poll();
            long b = pq.poll();

            long value = a + b;
            pq.offer(value);
            pq.offer(value);
            M--;
        }

        long answer = 0;

        while(!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
