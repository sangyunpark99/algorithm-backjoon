package org.hello.문제풀이.구현.캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int D;
    private static int[][] map;
    private static int[][] cloneMap;
    private static int[] dy = {0, -1, 0, 1}; // 왼쪽 부터
    private static int[] dx = {-1, 0, 1, 0};
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        cloneMap = new int[N + 1][M];
        map = new int[N + 1][M];
        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        deployArcher(0, new int[3], 0);

        System.out.println(answer);
    }

    private static void deployArcher(int start, int[] arr, int depth) {
        if(depth == 3) { // 3명의 궁수가 배치된 경우
            for(int i = 0; i <= N; i++) {
                cloneMap[i] = map[i].clone();
            }

            answer = Math.max(answer,play(arr));
            return;
        }

        for(int i = start; i < M; i++) {
            arr[depth] = i;
            deployArcher(i + 1, arr, depth +  1);
        }
    }

    private static int play(int[] archer) {

        int totalRemoveMonster = 0;
        List<int[]> monsters = new ArrayList<>(3); // 3명의 아처는 3마리의 몬스터

        while(checkMonster()) {
            // 1. 궁수와 가까운 적의 목록 구하기
            for(int i = 0; i < archer.length; i++) {
                int archerY = N;
                int archerX = archer[i];
                // 가장 가까운 적 찾기
                int[] monster = findMonster(archerY, archerX);
                if(monster.length > 0) { // 좌표가 존재하는 경우에만
                    monsters.add(findMonster(archerY, archerX));
                }
            }


            if(!monsters.isEmpty()) {
                for(int i = 0; i < monsters.size(); i++) {
                    int[] monster = monsters.get(i);
                    if(cloneMap[monster[0]][monster[1]] == 1) {
                        cloneMap[monster[0]][monster[1]] = 0; // 제거
                        totalRemoveMonster++;
                    }
                }
                monsters.clear(); // 제거한 몬스터 목록 비우기
            }

            moveMonster(); // 몬스터 맵 아래로 한칸 이동하기
        }

        return totalRemoveMonster;
    }

    private static void moveMonster() {

        List<int[]> monsters = new ArrayList<>();
        // 몬스터 위치 파악
        for(int i = 0; i < N; i++) { // 225
            for(int j = 0; j < M; j++) {
                if(cloneMap[i][j] == 1) {
                    monsters.add(new int[]{i,j});
                }
            }
        }

        // y좌표가 더 큰 것부터
        Collections.sort(monsters, (a,b) -> {
           return b[0] - a[0];
        });

        for(int i = 0; i < monsters.size(); i++) {
            int[] monster = monsters.get(i);
            int y = monster[0];
            int x = monster[1];
            if(y + 1 == N) { // 성을 만나는 경우
                cloneMap[y][x] = 0; // 몬스터 제거
            }else { // 그렇지 않은 경우
                cloneMap[y][x] = 0; // 현재 좌표 제거
                cloneMap[y + 1][x] = 1; // 아래로 한칸 이동
            }
        }
    }

    private static boolean checkMonster() { // 몬스터 존재 여부 확인하기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(cloneMap[i][j] == 1) { // 몬스터가 한마리라도 존재
                    return true;
                }
            }
        }

        return false;
    }

    private static int[] findMonster(int archerY, int archerX) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new int[]{archerY, archerX});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cury = cur[0];
            int curx = cur[1];

            for(int i = 0; i < 4; i++) {
                int ny = cury + dy[i];
                int nx = curx + dx[i];
                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if(!visited[ny][nx]) {
                    int distance = Math.abs(ny - archerY) + Math.abs(nx - archerX);
                    if(distance <= D) { // 사거리 내에서만
                        if(cloneMap[ny][nx] == 1) { // 적 발견시 바로 return
                            return new int[]{ny, nx};
                        }
                        visited[ny][nx] = true;
                        queue.offer(new int[]{ny, nx});
                    }
                }
            }
        }

        return new int[]{};
    }
}