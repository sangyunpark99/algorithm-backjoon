package org.hello.문제풀이.냅색.호텔;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int C;
    private static int N;
    private static List<int[]> cities = new ArrayList<>();
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            cities.add(new int[]{number, weight});
        }

        Collections.sort(cities, (a, b) -> {
            return a[1] - b[1];
        });

        int maxPerson = cities.stream().mapToInt(a -> a[1]).max().getAsInt();

        dp = new int[C + maxPerson + 1]; // n명 만드는데 드는 최소 비용

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 각 도시에서 뽑을 수 있는 인원수 별 비용을 추가
        for(int[] city: cities) {
            int cost = city[0];
            int person = city[1]; // 1명
            for(int i = person; i <= C + maxPerson; i++) { // C명 까지
                if(dp[i - person] != Integer.MAX_VALUE) { // 오버 플로우 방지
                    dp[i] = Math.min(dp[i], dp[i - person] + cost);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = C; i <= C + maxPerson; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}