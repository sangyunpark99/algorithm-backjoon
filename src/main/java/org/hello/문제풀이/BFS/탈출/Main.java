package org.hello.문제풀이.BFS.탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int R;
    private static int C;
    private static char[][] map;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};
    private static int[][] water;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new int[R][C]; // 물이 차오르는 턴

        for(int i = 0; i < R; i++) {
            Arrays.fill(water[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<int[]> waterStart = new  ArrayList<>();

        int animalY = 0;
        int animalX = 0;

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == '*') {
                    waterStart.add(new int[]{i, j});
                }

                if(map[i][j] == 'S') {
                    animalY = i;
                    animalX = j;
                }
            }
        }

        waterFull(waterStart);
        int result = go(animalY, animalX);

        if(result == 0) {
            System.out.println("KAKTUS");
            return;
        }

        System.out.println(result);
    }

    private static void waterFull(List<int[]> waterStart) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        for(int i = 0; i < waterStart.size(); i++) {
            int waterY = waterStart.get(i)[0];
            int waterX = waterStart.get(i)[1];
            visited[waterY][waterX] = true;
            queue.offer(new int[]{waterY, waterX, 0});
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curY = cur[0];
            int curX = cur[1];
            int curTurn = cur[2];
            for(int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];

                if(ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) continue;
                if(map[ny][nx] == 'D' || map[ny][nx] == 'X' || map[ny][nx] == 'S') continue;
                visited[ny][nx] = true;
                water[ny][nx] = curTurn + 1; // 다음 턴
                queue.offer(new int[]{ny, nx, curTurn + 1});
            }
        }
    }

    private static int go(int animalY, int animalX) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[animalY][animalX] = true;
        queue.offer(new int[]{animalY, animalX, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curY = cur[0];
            int curX = cur[1];
            int curTurn = cur[2];
            for(int i = 0; i < 4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                int nextTurn = curTurn + 1;
                if(ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) continue;
                if(map[ny][nx] == 'D') { // 도착 지점인경우
                    return nextTurn;
                }
                if(map[ny][nx] == '.') {
                    if(water[ny][nx] > nextTurn) { // 물 확장 턴 보다 작은 경우
                        visited[ny][nx] = true;
                        queue.offer(new int[]{ny, nx, nextTurn});
                    }
                }
            }
        }

        return 0;
    }
}
