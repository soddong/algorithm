import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Swea0714_01 {

    static int n, m, c;     // n 격자크기, m 승객수, c 배터리충전량
    static int[][] board;   // 0은 도로, 1은 벽
    static int cnt;

    static Car carInfo;
    static Person[] persons;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Car {
        int x; int y;
        int battery;

        Car(int x, int y, int battery) {
            this.x = x;
            this.y = y;
            this.battery = battery;
        }

        @Override
        public String toString() {
            return "현위치 : [" + x + " " + y + "]"
                    + " - 배터리 용량 : " + battery;
        }
    }

    static class Person implements Comparable<Person>{
        int src_x, src_y;
        int dst_x, dst_y;
        int dist_between_car;

        Person(int src_x, int src_y, int dst_x, int dst_y) {
            this.src_x = src_x; this.src_y = src_y;
            this.dst_x = dst_x; this.dst_y = dst_y;
            this.dist_between_car = -1;
        }
        Person(int src_x, int src_y, int dst_x, int dst_y, int dist_between_car) {
            this.src_x = src_x; this.src_y = src_y;
            this.dst_x = dst_x; this.dst_y = dst_y;
            this.dist_between_car = dist_between_car;
        }

        @Override
        public String toString() {
            return "출발점 : [" + src_x + " " + src_y + "]"
                    + " - 도착점 : [" + dst_x + " " + dst_y + "]"
                    + " - 거리 : " + dist_between_car;
        }

        @Override
        public int compareTo(Person o) {
            // 거리 오름차순 -> row 오름차순 -> col 오름차순
            if (this.dist_between_car != o.dist_between_car) {
                return this.dist_between_car - o.dist_between_car;
            }
            if (this.src_x != o.src_x) {
                return this.src_x - o.src_x;
            }
            return this.src_y - o.src_y;
        }
    }

    public static int start() {
        while (cnt < m) {

            // 1. 택시 기준 사람들 거리 업데이트 => O(n^2)
            updateDistance();

            // 2. 이동 후 배터리 업데이트
            // 2-1. 시작점 까지 이동 및 배터리 소모 => O(m)
            int num = getShortestNumber();
            Person person = persons[num];
            carInfo.battery -= person.dist_between_car;

            // 2-2. 승객 승차전 가능여부 확인 => O(n^2)
            int dist = getDistance(person);
            if (dist > carInfo.battery) {
                return -1;
            }

            // 2-3. 승객 하자 (배터리 충전, 위치 업데이트, 인원 배제)
            carInfo.battery += dist;
            carInfo.x = person.dst_x;
            carInfo.y = person.dst_y;

            persons[num] = null;
            cnt++;
        }

        return carInfo.battery;
    }

    private static int getDistance(Person person) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] checked = new boolean[n][n];
        q.add(new int[] {person.src_x, person.src_y, 0});
        checked[person.src_x][person.src_y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0] == person.dst_x && now[1] == person.dst_y) {
                return now[2];
            }

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (!isRange(nx, ny) || board[nx][ny] == 1 || checked[nx][ny]) {
                    continue;
                }

                q.add(new int[] {nx, ny, now[2]+1});
                checked[nx][ny] = true;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static int getShortestNumber() {
        int num = -1;
        boolean first = true;
        for (int i = 1; i <= m; i++) {
            if (persons[i] == null)
                continue;
            if (first) {
                first = false;
                num = i;
            }
            if (persons[i].dist_between_car > persons[num].dist_between_car) {
                continue;
            } else if (persons[i].dist_between_car < persons[num].dist_between_car) {
                num = i;
                continue;
            }

            if (persons[i].src_x > persons[num].src_x) {
                continue;
            } else if (persons[i].src_x < persons[num].src_x) {
                num = i;
                continue;
            }

            if (persons[i].src_y < persons[num].src_y) {
                num = i;
            }
        }
        return num;
    }

    private static void updateDistance() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] checked = new boolean[n][n];
        q.add(new int[] {carInfo.x, carInfo.y, 0});
        checked[carInfo.x][carInfo.y] = true;
        boolean[] checked_person = new boolean[m+1];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int p_num = board[now[0]][now[1]];

            if (p_num < 0) {
                Person person = persons[-p_num];
                if (person != null) {
                    person.dist_between_car = now[2];
                    persons[-p_num] = person;
                    checked_person[-p_num] = true;
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (!isRange(nx, ny) || board[nx][ny] == 1 || checked[nx][ny]) {
                    continue;
                }

                q.add(new int[] {nx, ny, now[2]+1});
                checked[nx][ny] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            if (checked_person[i] || persons[i] == null) {
                continue;
            }
            persons[i].dist_between_car = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        // 도로 정보
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int init_x = Integer.parseInt(st.nextToken());
        int init_y = Integer.parseInt(st.nextToken());
        carInfo = new Car(init_x-1, init_y-1, c);

        // 승객 정보
        persons = new Person[m+1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            persons[i] = new Person(sx-1, sy-1, dx-1, dy-1);
            board[sx-1][sy-1] = -i;
        }

        System.out.println(start());
    }

    static boolean isRange(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }
}
