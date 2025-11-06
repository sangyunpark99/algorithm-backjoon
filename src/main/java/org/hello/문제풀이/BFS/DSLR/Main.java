package org.hello.문제풀이.BFS.DSLR;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간 초과
// 메모리 초과
// 문자열 사용 x, 숫자 사용
public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        // a에서 b로 변환
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(solve(a, b)).append("\n");
        }

        System.out.println(sb);
    }

    private static String solve(int start, int target) {
        boolean[] visited = new boolean[10000];
        int[] prev = new int[10000]; // 이전 값
        char[] how = new char[10000]; // 방법
        Arrays.fill(prev, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            if(cur == target) break;

            int d = (cur * 2) % 10000;
            if(!visited[d]) {
                visited[d] = true;
                prev[d] = cur;
                how[d] = 'D';
                q.add(d);
            }

            int s = cur == 0 ? 9999 : cur - 1;
            if(!visited[s]) {
                visited[s] = true;
                prev[s] = cur;
                how[s] = 'S';
                q.add(s);
            }

            int l = (cur % 1000) * 10 + cur / 1000; // 왼쪽으로 이동
            if(!visited[l]) {
                visited[l] = true;
                prev[l] = cur;
                how[l] = 'L';
                q.add(l);
            }

            int r = (cur / 10) + (cur % 10) * 1000; // 오른쪽으로 이동
            if(!visited[r]) {
                visited[r] = true;
                prev[r] = cur;
                how[r] = 'R';
                q.add(r);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int cur = target; cur != start; cur = prev[cur]) { // 역추적
            sb.append(how[cur]);
        }

        return sb.reverse().toString();
    }
}