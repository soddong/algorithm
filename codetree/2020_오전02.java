import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 시간 복잡도 (3^16) * 16
public class Swea0713_01 {

    static final boolean ENABLE_DEBUG = false;

    static final int NONE = -1;
    static final int CATCHER = 0;

    static final int N = 4;
    static int[][] board = new int[N][N];
    static Horse[] horses = new Horse[N*N + 1];

    //  ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int answer = 0;

    static class Horse {
        int x; int y; int dir;
        boolean alive;

        Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.alive = true;
        }
        Horse(int x, int y, int dir, boolean alive) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.alive = alive;
        }

        Horse getHorse() {
            return new Horse(x, y, dir, alive);
        }

        @Override
        public String toString() {
            return "[x: " + x + " y: " + y + " dir: " + dir + " isAlive: " + alive + " ]";
        }
    }

    static void move(int x, int y, int point) {
        answer = Integer.max(point, answer);

        // 도둑말 이동
        for(int i = 1; i <= N*N; i++) {
            int d;
            for (d = horses[i].dir; d < horses[i].dir+8; d++) {
                int nd = d % 8;
                if (!horses[i].alive)
                    continue;
                int nx = horses[i].x + dx[nd];
                int ny = horses[i].y + dy[nd];
                if (!isRange(nx, ny))
                    continue;

                if(board[nx][ny] == CATCHER)
                    continue;

                if (board[nx][ny] == NONE) { // 빈칸이면 현재 말만 위치 업데이트
                    board[nx][ny] = board[horses[i].x][horses[i].y];
                    board[horses[i].x][horses[i].y] = NONE;
                    horses[i].x = nx;
                    horses[i].y = ny;
                } else { // 다른 도둑말이 있으면 위치 바꾸기
                    int xx = horses[i].x; int yy = horses[i].y;
                    int tmp = board[horses[i].x][horses[i].y];
                    board[horses[i].x][horses[i].y] = board[nx][ny];
                    board[nx][ny] = tmp;

                    horses[i].x = nx;
                    horses[i].y = ny;
                    horses[board[xx][yy]].x = xx;
                    horses[board[xx][yy]].y = yy;
                }
                horses[i].dir = nd;
                break;
            }
        }

        // 이전 말 상태 저장
        Horse[] horses_copy = deepcopy_horse(horses);
        int[][] board_copy = deepcopy_board(board);

        // 술래말 이동 (시간복잡도 : 3^(N*N))
        int nx = x; int ny = y;
        int d = horses[CATCHER].dir;
        for (int m = 1; m <= 3; m++) { // 움직이는 횟수

            nx += dx[d];
            ny += dy[d];
            if (!isRange(nx, ny))
                break;
            if (board[nx][ny] <= CATCHER)
                continue;

            int h_num = board[nx][ny];

            // 방향 바꿔
            horses[CATCHER].dir = horses[h_num].dir;

            // 꿀꺽
            horses[h_num].alive = false;
            board[nx][ny] = CATCHER;
            board[x][y] = NONE;
            move(nx, ny, point + h_num);

            horses = deepcopy_horse(horses_copy);
            board = deepcopy_board(board_copy);
        }
    }

    private static Horse[] deepcopy_horse(Horse[] horses) {
        Horse[] copys = new Horse[N*N+1];
        for (int i = 0; i <= N*N; i++) {
            copys[i] = horses[i].getHorse();
        }
        return copys;
    }

    private static int[][] deepcopy_board(int[][] board) {
        int[][] copys = new int[N][N];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copys[i][j] = board[i][j];
            }
        }
        return copys;
    }

    static boolean isRange(int x, int y ) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                horses[board[i][j]] = new Horse(i, j, Integer.parseInt(st.nextToken())-1);
            }
        }

        if (board[0][0] == 0) {
            System.out.println(0);
            return;
        }

        // 처음 도둑말 잡기 (0, 0)
        horses[CATCHER] = new Horse(0, 0, horses[board[0][0]].dir);
        horses[board[0][0]].alive = false;
        int init_point = board[0][0];
        board[0][0] = CATCHER;

        move(0, 0, init_point);

        System.out.println(answer);
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printHorses() {
        for (int i = 0; i < N*N + 1; i++) {
            System.out.println(horses[i]);
        }
    }


}
