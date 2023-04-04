package DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_G4_2636_치즈 {
	
	static int N, M;
	
	static int map[][];
	
	static int cheeseCount;
	static int beforeCheeseCount;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = in.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] == 1) cheeseCount ++;
			}
		}
		
		beforeCheeseCount = cheeseCount;
		int count = 1;
		while(bfs()) {
			count ++;
		}
		System.out.println(count);
		System.out.println(beforeCheeseCount);
	}

	private static boolean bfs() {
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		int[][] newMap = cloneMap();
		boolean[][] visited = new boolean[N][M];
		
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;
		newMap[0][0] = 2;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(!visited[nx][ny] && newMap[nx][ny] == 0) {
					queue.offer(new int[] {nx, ny});
					newMap[nx][ny] = 2;
					visited[nx][ny] = true;
				}
			}
		}
		
		for(int i = 1; i < N - 1; i++) {
			for(int j = 1; j < M - 1; j++) {
				if(map[i][j] == 1) {
					for(int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(newMap[nx][ny] == 2) {
							map[i][j] = 0;
							cheeseCount--;
							break;
						}
					}
				}
			}
		}
		
		if(cheeseCount <= 0) {
			return false;
		}
		else {
			beforeCheeseCount = cheeseCount;
			return true;
		}
	}

	private static int[][] cloneMap() {
		int[][] newMap = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			newMap[i] = map[i].clone();
		}
		return newMap;
	}
}
