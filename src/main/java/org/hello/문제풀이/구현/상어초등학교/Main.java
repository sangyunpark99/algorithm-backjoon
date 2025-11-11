package org.hello.문제풀이.구현.상어초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static List<Set<Integer>> prefer = new ArrayList<>();
    private static int[] sequence;
    private static HashMap<Integer, int[]> sitPosition = new HashMap<>();
    private static int[][] sitMap;
    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N * N; i++) {
            prefer.add(new HashSet<>());
        }

        sequence = new int[N * N];
        int idx = 0;
        for(int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            sequence[idx++] = number;
            for(int j = 0; j < 4; j++) {
                prefer.get(number).add(Integer.parseInt(st.nextToken())); // 좋아하는 학생 추가
            }
        }

        sitMap = new int[N][N];

        for(int i = 0; i < sequence.length; i++) {
            int number = sequence[i];
            sit(number); // 앉히기
        }

        int answer = 0;

        for(int i = 1; i <= N*N; i++) {
            int preferSize = getPrefer(i);
            if(preferSize == 0) {
                answer += 0;
            }else if(preferSize == 1) {
                answer += 1;
            }else if(preferSize == 2) {
                answer += 10;
            }else if(preferSize == 3) {
                answer += 100;
            }else {
                answer += 1000;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(sitMap[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(answer);
    }

    private static void sit(int number) {
        int[] blankSit = findBlankSit(number);
        sitMap[blankSit[0]][blankSit[1]] = number; // 앉히기
        sitPosition.put(number, new int[]{blankSit[0], blankSit[1]});
    }

    private static int getPrefer(int number) {
        int[] cur = sitPosition.get(number);
        int y = cur[0];
        int x = cur[1];

        int preferSize = 0;
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            int value = sitMap[ny][nx];
            if(prefer.get(number).contains(value)) {
                preferSize++;
            }
        }

        return preferSize;
    }

    private static int[] findBlankSit(int number) {

        List<int[]> blank = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int blankSize = 0;
                int preferSize = 0;
                if(sitMap[i][j] != 0) continue;
                for(int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    if(sitMap[ny][nx] != 0) {
                        if(prefer.get(number).contains(sitMap[ny][nx])) {
                            preferSize++;
                        }
                    }else {
                        blankSize++;
                    }
                }
                blank.add(new int[]{i, j, preferSize, blankSize});
            }
        }

        Collections.sort(blank,(a,b) -> {
            if(a[2] == b[2]) { // 선호하는 사람이 같은 경우
                if(b[3] == a[3]) { // 빈칸의 갯수가 같은 경우
                    if(a[0] == b[0]) { // g
                        return a[1] - b[1]; // 열 정렬
                    }
                    return a[0] - b[0]; // 행 정렬
                }
                return b[3] - a[3];
            }
            return b[2] - a[2];
        });

        return blank.get(0); // 제일 우선순위가 높은 자리 제공
    }
}
