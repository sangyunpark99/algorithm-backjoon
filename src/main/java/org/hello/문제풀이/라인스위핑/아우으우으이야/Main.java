package org.hello.문제풀이.라인스위핑.아우으우으이야;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<long[]> lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        lines = new ArrayList<>(N);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            lines.add(new long[]{a,b});
        }

        long start = lines.get(0)[0];
        long end = lines.get(0)[1];
        long answer = 0;

        for(int i = 1; i < N; i++) { // O(n)
            long nextStart = lines.get(i)[0];
            long nextEnd = lines.get(i)[1];

            if(end < nextStart) {
                answer += end - start;
                start = nextStart;
                end = nextEnd;
            }else {
                if(nextEnd > end) {
                    end = nextEnd;
                }
            }
        }

        System.out.println(answer + end - start);
    }
}
