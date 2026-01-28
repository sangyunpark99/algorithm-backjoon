package org.hello.문제풀이.라인스위핑.수상택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static List<int[]> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) { // O(N)
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(to < from) {
                lines.add(new int[]{to, from});
            }
        }

        if(lines.isEmpty()) {
            System.out.println(M);
            return;
        }

        Collections.sort(lines, (a,b) -> { // O(NlogN)
            return a[0] - b[0];
        });

        int startLine = lines.get(0)[0];
        int endLine = lines.get(0)[1];

        long totalLength = 0;

        for(int i = 1; i < lines.size(); i++) { // O(N)
            int[] nextLine = lines.get(i);
            int nextStartLine = nextLine[0];
            int nextEndLine = nextLine[1];

            if(endLine < nextStartLine) { // 구간이 겹치지 않는 경우
                totalLength += Math.abs(endLine - startLine);
                startLine = nextStartLine;
                endLine = nextEndLine;
            }else { // 구간이 겹치는 경우
                endLine = Math.max(endLine,nextEndLine);
            }
        }

        totalLength += endLine - startLine;

        long answer = M + 2 * totalLength;

        System.out.println(answer);
    }
}