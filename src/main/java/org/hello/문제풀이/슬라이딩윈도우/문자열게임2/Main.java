package org.hello.문제풀이.슬라이딩윈도우.문자열게임2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());

            List<Integer>[] pos = new ArrayList[26];
            for(int i = 0; i < 26; i++) {
                pos[i] = new ArrayList<>();
            }

            for(int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                pos[c - 'a'].add(i);
            }

            int minLen = Integer.MAX_VALUE;
            int maxLen = -1;

            for(int c = 0; c < 26; c++) {
                List<Integer> list = pos[c];
                if(list.size() < k) continue;

                for(int i = 0; i <= list.size() - k; i++) { // k개를 제외한 원소 중
                    int start = list.get(i);
                    int end = list.get(i + k - 1);
                    int len = end - start + 1;

                    if(len < minLen) minLen = len;
                    if(len > maxLen) maxLen = len;
                }
            }

            if(maxLen == -1) {
                sb.append("-1\n");
            }else {
                sb.append(minLen).append(" ").append(maxLen).append("\n");
            }
        }

        System.out.println(sb);
    }
}
