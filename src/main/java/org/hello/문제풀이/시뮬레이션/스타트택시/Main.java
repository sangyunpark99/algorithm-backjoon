package org.hello.문제풀이.시뮬레이션.스타트택시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int[][] map;
    private static int initFire = 0;
    private static List<Client> clients = new ArrayList<>();
    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    private static class Taxi {
        int y;
        int x;
        int fire;

        public Taxi(int y, int x, int fire) {
            this.y = y;
            this.x = x;
            this.fire = fire;
        }
    }

    private static class Client {
        int y;
        int x;
        int targetY;
        int targetX;

        public Client(int y, int x, int targetY, int targetX) {
            this.y = y;
            this.x = x;
            this.targetY = targetY;
            this.targetX = targetX;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initFire = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        int taxiY = Integer.parseInt(st.nextToken()) - 1;
        int taxiX = Integer.parseInt(st.nextToken()) - 1;

        Taxi taxi = new Taxi(taxiY, taxiX, initFire);

        for(int i = 0; i < M; i++) { // 승객
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int targetY = Integer.parseInt(st.nextToken()) - 1;
            int targetX = Integer.parseInt(st.nextToken()) - 1;

            Client client = new Client(y, x, targetY, targetX);
            clients.add(client);
        }

        while(true) { // 손님을 전부 태웠거나 택시 연료가 전부 떨어진 경우

            if(clients.isEmpty()) { // 손님을 전부 태운 경우
                break;
            }

            boolean check = findShortestClient(taxi);
            if(!check) { // 안되는 경우
                System.out.println(-1);
                return;
            }
        }

        System.out.println(taxi.fire);
    }

    private static boolean findShortestClient(Taxi taxi) { // 가장 가까운 고객 찾기
        HashMap<String, Client> clientPosition = new HashMap<>();
        for(int i = 0; i < clients.size(); i++) { // 고객 존재 위치 파악 여부용
            Client client = clients.get(i);
            int y = client.y;
            int x = client.x;
            clientPosition.put(y + "," + x, client);
        }

        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {taxi.y, taxi.x, 0}); // 택시 위치, 이동 거리
        visited[taxi.y][taxi.x] = true; // 출발 지점

        int taxiMoveCnt = 0;
        List<Client> sameDistanceClient = new ArrayList<>();
        int shortDistance = 0;
        boolean firstMeet = false;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int moveCnt = cur[2];

            String position = y + "," + x;
            if(clientPosition.containsKey(position)) {
                if(!firstMeet) {
                    shortDistance = moveCnt;
                    firstMeet = true;
                } else {
                    if(shortDistance != moveCnt) {
                        break;
                    }
                }

                taxiMoveCnt = moveCnt;
                Client shortestClient = clientPosition.get(position);
                sameDistanceClient.add(shortestClient);
            }

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
                if(map[ny][nx] == 1) continue;
                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx, moveCnt + 1});
            }
        }

        if(taxi.fire < taxiMoveCnt || sameDistanceClient.isEmpty()) { // 태울 손님이 없는 경우(벽에 막히거나 없는 경우)
            return false;
        }

        Collections.sort(sameDistanceClient, (a,b) -> {
           if(a.y == b.y) {
               return a.x - b.x;
           }

           return a.y - b.y;
        });

        Client shortestClient = sameDistanceClient.get(0);

        taxi.y = shortestClient.y;
        taxi.x = shortestClient.x;
        taxi.fire -= taxiMoveCnt;

        // 가장 거리가 가까운 손님 목적지에 데려다 주기
        queue = new ArrayDeque<>();
        queue.offer(new int[]{taxi.y, taxi.x, 0});
        visited = new boolean[N][N];
        visited[taxi.y][taxi.x] = true;
        int targetY = shortestClient.targetY;
        int targetX = shortestClient.targetX;

        boolean reached = false;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int moveCnt = cur[2];

            if(y == targetY && x == targetX) {
                taxiMoveCnt = moveCnt;
                reached = true;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx]) continue;
                if(map[ny][nx] == 1) continue;
                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx, moveCnt + 1});
            }
        }

        if(!reached) {
            return false;
        }

        if(taxi.fire < taxiMoveCnt) {
            return false;
        }

        taxi.fire += taxiMoveCnt;
        taxi.y = targetY;
        taxi.x = targetX;

        // 고객 제거
        clients.remove(shortestClient);

        return true;
    }
}
