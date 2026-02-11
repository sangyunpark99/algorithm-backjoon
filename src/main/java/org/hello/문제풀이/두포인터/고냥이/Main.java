package org.hello.문제풀이.두포인터.고냥이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    // 연속된 문자열 -> 구간 -> 투 포인터?
    // 인식할 수 있는 최대 문자열 길이

    private static int N;
    private static String[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        values = br.readLine().split("");

        // 알파벳 종류를 최대 N개를 가지면서 연속된 문자열

        // 알파벳 종류가 부족한 경우, 오른쪽 이동
        // 알파벳 종류가 넘어간 경우, 왼쪽으로 이동

        int left = 0;
        int right = 0;

        HashMap<String,Integer> cnt = new HashMap<>();
        HashSet<String> kind = new HashSet<>();

        int answer = 0;
        int gap = 0;

        while(right < values.length) {

            String value = values[right];

            if(kind.size() < N) { // 유형이 부족한 경우
                cnt.put(value, cnt.getOrDefault(value,0) + 1);
                kind.add(value);
                gap++;
                right++;

            }else { // 유형이 전부 존재하는 경우
                if(!cnt.containsKey(value)) { // 새로운 유형인 경우
                    if(cnt.get(values[left]) - 1 == 0) { // 1개밖에 존재하지 않는 경우
                        cnt.remove(values[left]); // 제거
                        kind.remove(values[left]);
                    }else{ // 1개 이상 존재하는 경우
                        cnt.put(values[left], cnt.get(values[left]) - 1); // 카운팅 1 감소
                    }
                    gap--;
                    left++;
                }else { // 새로운 유형이 아닌 경우
                    gap++;
                    right++;
                    cnt.put(value, cnt.get(value) + 1); // 카운팅 1 증가
                }
            }

            answer = Math.max(answer, gap);
        }

        System.out.println(answer);
    }
}