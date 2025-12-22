package org.hello.문제풀이.우선순위큐.N번째큰수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(N * N, Collections.reverseOrder());

        for(int i = 0; i < N; i++) { // O(nlogn)
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < arr.length; j++) {
                pq.offer(arr[j]);
            }
        }

        while(N > 1) { // O(1)
            pq.poll();
            N--;
        }

        System.out.println(pq.peek());
    }
}
