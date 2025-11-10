package org.hello.문제풀이.투포인터.겹치는건싫어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int[] arr;
    private static HashMap<Integer, Integer> cnts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        cnts.put(arr[left], 1);
        int len = 1;
        int right = 1;
        int answer = 1;

        while(left < right && right < N) { // O(N)
            int value = arr[right];
            // 원소 추가
            if(!cnts.containsKey(value)) { // 처음 만난 경우
                cnts.put(value, 1); // 값 추가
                len++; // 길이 추가
            }else {
                int cnt = cnts.get(value);
                int next = cnt + 1;
                if(next > K) { // K개보다 많이 포함된 경우
                    while(cnts.get(value) >= K) { // 더 작게 만들어야 한다.
                        cnts.put(arr[left], cnts.get(arr[left]) - 1); // 제거하고
                        left++;
                        len--; // 길이 줄이기
                    }
                    cnts.put(arr[right], cnts.get(arr[right]) + 1);
                    len++;
                } else { // K개보다 많이 포함되지 않은 겨웅
                    cnts.put(value, next);
                    len++;
                }
            }

            answer = Math.max(answer, len);
            right++; // 오른쪽 확장
        }

        System.out.println(answer);
    }
}