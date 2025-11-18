package org.hello.문제풀이.시뮬레이션.마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int[] dy = {-1,-1,0,1,1,1,0,-1};
    private static int[] dx = {0,1,1,1,0,-1,-1,-1};
    private static int N;
    private static int M;
    private static int K;
    private static int[][] map;
    private static List<int[]> fires = new ArrayList<>();
    private static HashMap<String, List<int[]>> mapFire = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                mapFire.put(i + "," + j, new ArrayList<>());
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int x = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fires.add(new int[]{y,x,m,s,d});
        }

        for(int i = 0; i < K; i++) { // 이동!
            move();
            checkTwoFire();
        }

        // 남아있는 파이어볼 질량의 합
        int answer = 0;

        for(int i = 0; i < fires.size(); i++) {
            answer += fires.get(i)[2];
        }

        System.out.println(answer);
    }

    // map에 겹치는 fire볼 갯수 기록
    private static void move() {
        for(int i = 0; i < fires.size(); i++) {
            int[] fire = fires.get(i);
            int y = fire[0];
            int x = fire[1];
            int m = fire[2];
            int s = fire[3];
            int d = fire[4];

            int move = s % N;
            int ny = (y + dy[d] * move + N) % N;
            int nx = (x + dx[d] * move + N) % N;
            map[ny][nx]++; // 파이어볼 개수 추가
            mapFire.get(ny + "," + nx).add(new int[]{ny,nx,m,s,d});
        }
        fires.clear();
    }

    private static void checkTwoFire() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                String key = i + "," + j;
                if(map[i][j] == 0) continue;
                if(map[i][j] == 1) { // fire볼 1개
                    fires.add(mapFire.get(key).get(0));
                    mapFire.put(key, new ArrayList<>()); // 초기화
                }else { // 2개 이상
                    splitFire(key);
                    mapFire.put(key, new ArrayList<>());
                }
                map[i][j] = 0;
            }
        }
    }

    private static void splitFire(String key) {
        List<int[]> values = mapFire.get(key);
        int cnt = values.size();
        int d = values.get(0)[4]; // 방향
        boolean checkDir = d % 2 == 0; // 홀수 Or 짝수
        boolean isAllSame = true;
        int y = values.get(0)[0];
        int x = values.get(0)[1];
        int totalM = 0;
        int totalS = 0;
        for(int i = 0; i < values.size(); i++) {
            int[] value = values.get(i);
            int m = value[2];
            int s = value[3];
            int dir = value[4];
            if(dir % 2 == 0 != checkDir) {
                isAllSame = false;
            }
            totalM += m;
            totalS += s;
        }

        int perM = totalM / 5;
        int perS = totalS / cnt;

        if(perM == 0) return;

        if(isAllSame) {
            for(int i = 0; i <= 6; i+=2) {
                fires.add(new int[]{y, x, perM, perS, i});
            }
        }else {
            for(int i = 1; i <= 7; i+=2) {
                fires.add(new int[]{y, x, perM, perS, i});
            }
        }
    }
}
