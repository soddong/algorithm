import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int[][] checked;
    static Deque<int[]> q = new ArrayDeque<>();
    static int h; static int w;
    static boolean goInitFlag;
    static HashSet<Integer> keySet;
    static int[] dx = {-1, 1, 0, 0}; static int[] dy = {0, 0, -1, 1};
    static final int SMALL_BIG_DIFF = 32;
    static int result;
    // . 빈공간
    // * 벽
    // $ 문서
    // 알파벳 대문자 문
    // 알파벳 소문자 열쇠
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        for (int tc = 0; tc < T; tc++) {
        
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            
            board = new int[h][w];
            checked = new int[h][w];
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < input.length(); j++) {
                	board[i][j] = input.charAt(j);
                }
            }
            
            keySet = new HashSet<>();
            String input = br.readLine();
            if (input.charAt(0) != '0') {
            	for (int i = 0; i < input.length(); i++) {
            		keySet.add((int)input.charAt(i));
				}
            }
            
            result = 0;
            start();       
            System.out.println(result);
        }
        
    }

    private static void start() {
        // 1. 처음 입장할 수 있는 길 찾기 -> 순회
        // 2. 문 있으면 열쇠 있는지 확인 (map으로)
        // 2. 열쇠를 주우면 반복
        
    	int round = 1;
    	do {
    		goInitFlag = false;
    		checked = new int[h][w];
            // 1. 처음 입장할 수 있는 길 찾기 -> 순회
            initPushIfPossible();
            while (!q.isEmpty()) {
            	int[] now = q.pop();
            	// 이동하면서 마주치는 애들에 따라 처리
        		for (int d = 0; d < 4; d++) {
    				int[] next = {now[0] + dx[d], now[1] + dy[d]};
    				if (next[0] < 0 || next[0] >= h || next[1] < 0 || next[1] >= w) continue;
    				boolean ismove = moveAndDecide(next);
    				int val =  checked[next[0]][next[1]];
    				if (ismove == true && val == 0) {
    					++checked[next[0]][next[1]];
    					q.add(new int[] {next[0], next[1]});
    				}
    			}
            }	
            round++;
    	} while (goInitFlag);
    	// 3. 열쇠 주우면 반복
    }

    private static boolean moveAndDecide(int[] next) {
    	if (board[next[0]][next[1]] == '.') {
    		// 빈 공간
    		return true;
    		
    	} else if (board[next[0]][next[1]] == '*') {
    		// 벽
    		return false;
    		
     	} else if (board[next[0]][next[1]] >= 'A' && board[next[0]][next[1]] <= 'Z') {
     		// 문
     		if (keySet.contains(board[next[0]][next[1]] + SMALL_BIG_DIFF)) { // 키가 있으면
     			board[next[0]][next[1]] = '.';
     			return true;
     		}
     		return false;
     	} else if (board[next[0]][next[1]] >= 'a' && board[next[0]][next[1]] <= 'z') {
     		keySet.add(board[next[0]][next[1]]);
     		board[next[0]][next[1]] = '.';
     		goInitFlag = true;
     		return true;
     		
     	} else if (board[next[0]][next[1]] == '$') {
     		result++;
     		board[next[0]][next[1]] = '.';
     		return true;
     	}
    	
    	return false;
		
	}

	private static void initPushIfPossible() {
		
        for (int i = 0; i < w; i++) {
        	int[][] nowArr = {
        			{0, i}, {h-1, i}
        	};
        	for (int[] now : nowArr) {
                if (moveAndDecide(now)) {
                    q.add(now);
                    checked[now[0]][now[1]]++;
                }
        	}
        }
        
        for (int i = 0; i < h; i++) {
        	int[][] nowArr = {
        			{i, 0}, {i, w-1}
        	};
        	
        	for (int[] now : nowArr) {
                if (moveAndDecide(now)) {
                    q.add(new int[]{now[0], now[1]});
                    checked[now[0]][now[1]]++;
                }
        	}
        	
		}
    }
}
