package org.hello.문제풀이.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};
    private static int N;
    private static int[][] map;

    // 상어의 위치
    private static int sharkY = 0;
    private static int sharkX = 0;
    private static int sharkSize = 2; // 아기 상어의 크기 2
    private static int sharkEatFish = 0;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < N; i++) {
            boolean isFind = false;
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 9) {
                    sharkY = i;
                    sharkX = j;
                    map[i][j] = 0;
                    isFind = true;
                    break;
                }
            }
            if(isFind) {
                break;
            }
        }

        while(true) {
            if(!go()) {
                break;
            }
        }

        System.out.println(answer);

    }

    public static boolean go() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        visited[sharkY][sharkX] = true;
        queue.offer(new int[] {sharkY,  sharkX, 0}); // 이동 거리
        List<int[]> fishes = new ArrayList<>(); // 먹을 수 있는 물고기 후보

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cury = cur[0];
            int curx = cur[1];
            int dist = cur[2];

            for(int i = 0; i < 4; i++) { // 인접한
                int ny = cury + dy[i];
                int nx = curx + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
                if(map[ny][nx] > sharkSize) continue;

                visited[ny][nx] = true;

                if(map[ny][nx] > 0 && map[ny][nx] < sharkSize) { // 먹을 수 있는 물고기
                    fishes.add(new int[]{ny, nx, dist + 1});
                }

                // 다음칸 이동
                queue.offer(new int[] {ny, nx, dist + 1}); // 현재 지점을 기준으로의 거리
            }
        }

        if(fishes.isEmpty()) return false; // 물고기가 더 존재하지 않는 경우

        Collections.sort(fishes, (a, b) -> {
            if(a[2] == b[2]) { // 같은 거리
                if(a[0] == b[0]) { // y좌표 동일한 경우
                    return a[1] - b[1]; // x좌표 작은 순
                }
                return a[0] - b[0]; // y좌표 작은순
            }
            return a[2] - b[2]; // 거리 짧은 순
        });

        // 물고기는 한 턴에 한마리
        int[] fish = fishes.get(0);

        int fishy = fish[0];
        int fishx = fish[1];
        int dist = fish[2];

        if(++sharkEatFish == sharkSize) {
            sharkSize++;
            sharkEatFish = 0; // 먹은 후 초기화
        }

        answer += dist; // 거리 더하기
        map[fishy][fishx] = 0; // 물고기 박멸
        sharkY = fishy; // 상어 y좌표 이동
        sharkX = fishx; // 상어 x좌표 이동

        return true;
    }
}
