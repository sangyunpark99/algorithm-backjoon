package org.hello.문제풀이.냅색.양팔저울;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[] weights;
    private static boolean[] possible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int sum = 0;
        for(int weight : weights) {
            sum += weight;
        }
        possible = new boolean[sum + 1];
        possible[0] = true; // 차이가 0인경우

        for(int weight: weights) {
            List<Integer> diff = new ArrayList<>();
            for(int i = 0; i <= sum; i++) {
                if(possible[i]) diff.add(i);
            }

            for(int d: diff) { // 차이 값을 기준으로
                int d1 = Math.abs(d - weight); // 주어진 무게를 왼쪽 저울에 놓는 경우
                int d2 = d + weight; // 주어진 무게를 오른쪽에 놓는 경우
                possible[d1] = true;
                possible[d2] = true;
            }
        }

        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < k; i++) {
            int value = Integer.parseInt(st.nextToken());
            if(value > sum) {
                sb.append("N");
            }else {
                sb.append(possible[value] ? "Y" : "N");
            }

            sb.append(" ");
        }

        System.out.println(sb);
    }
}
