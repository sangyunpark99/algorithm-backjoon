package org.hello.문제풀이.라인스위핑.개구리점프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int Q;
    private static List<int[]> woods = new ArrayList<>();
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] value = new int[]{input[0], input[1], input[2], i + 1};
            woods.add(value); // 번호순
        }

        woods.sort((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }

            return a[0] - b[0];
        });

        arr = new int[N + 1]; // 1-based

        int num = woods.get(0)[3];
        int end = woods.get(0)[1];
        int groupId = 1;
        arr[num] = groupId;

        for (int i = 1; i < N; i++) { // O(N)
            int[] next = woods.get(i);
            int nextStart = next[0];
            int nextEnd = next[1];
            int number = next[3];

            if (nextStart <= end) {
                arr[number] = groupId;
                end = Math.max(end, nextEnd); // 끝의 길이가 더 긴것으로 갱신
            } else {
                groupId++;
                arr[number] = groupId;
                end = nextEnd;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) { // O(Q)
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (arr[a] == arr[b]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        // O(200,000)

        System.out.println(sb);
        br.close();
    }
}
