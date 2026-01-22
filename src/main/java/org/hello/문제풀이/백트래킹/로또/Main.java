package org.hello.문제풀이.백트래킹.로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(input[0] == 0) break;
            int[] value = Arrays.copyOfRange(input, 1, input.length);

            int n = value.length;
            combination(new ArrayList<>(n), 0, n, 6, 0, value);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void combination(List<Integer> comb, int start, int n, int r, int depth, int[] value) {
        if(depth == r) {
            for(int i = 0; i < comb.size(); i++) {
                sb.append(comb.get(i)).append(" ");
            }
            sb.append("\n");

            return;
        }

        for(int i = start; i < n; i++) {
            comb.add(value[i]);
            combination(comb, i + 1, n, r, depth + 1, value);
            comb.remove(comb.size() - 1);
        }
    }
}