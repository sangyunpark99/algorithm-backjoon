package org.hello.문제풀이.우선순위큐.파일합치기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            long[] values = Arrays.stream(br.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();
            PriorityQueue<Long> pq = new PriorityQueue<>(K);

            for(long value : values) {
                pq.offer(value);
            }

            long answer = 0L;

            while(pq.size() > 1) { // final 직전까지
                long first = pq.poll();
                long second = pq.poll();
                long sum = first + second;

                answer += sum;
                pq.offer(sum);
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}