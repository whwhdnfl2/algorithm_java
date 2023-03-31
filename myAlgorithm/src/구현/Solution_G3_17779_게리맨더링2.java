package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_G3_17779_게리맨더링2 {
	
	static int N;
	
	static int map[][];
	
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String str[] = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dfs(i, j);
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int x, int y) {
		for(int d1 = 1; d1 < N; d1++) {
			for(int d2 = 1; d2 < N; d2++) {
				if(x + d1 + d2 <= N - 1 && 0 <= y - d1 && y + d2 <= N - 1) {
					int[][] newMap = new int[N][N];
					//5 경계선
					for(int i = 0; i <= d1; i++) {
						newMap[x + i][y - i] = 5;
					}
					for(int i = 0; i <= d2; i++) {
						newMap[x + i][y + i] = 5;
					}
					for(int i = 0; i <= d2; i++) {
						newMap[x + d1 + i][y - d1 + i] = 5;
					}
					for(int i = 0; i <= d1; i++) {
						newMap[x + d2 + i][y + d2 - i] = 5;
					}
					
					//5 채우기
					if(d1 == 1 && d2 == 1) {
						newMap[x + 1][y] = 5;
					}
					else if(d1 != 1 && d2 == 1) {
						for(int i = 0; i < d1; i++) {
							newMap[x + i + 1][y - i] = 5;
						}
						
					}else if(d1 == 1 && d2 != 1) {
						for(int i = 0; i < d2; i++) {
							newMap[x + i + 1][y + i] = 5;
						}
					}else {
						insert(newMap, x + 1, y);
					}
					
					for(int i = 0; i < x + d1; i++) {
						for(int j = 0; j <= y; j++) { // 1구역구
							if(newMap[i][j] == 0) {
								newMap[i][j] = 1;
							}
						}
					}
					for(int i = 0; i <= x + d2; i++) { //2 구역구
						for(int j = y + 1; j < N; j++) {
							if(newMap[i][j] == 0) {
								newMap[i][j] = 2;
							}
						}
					}
					for(int i = x + d1; i < N; i++) { //3
						for(int j = 0; j < y-d1+d2; j++) {
							if(newMap[i][j] == 0) {
								newMap[i][j] = 3;
							}
						}
					}
					for(int i = x + d2 + 1; i < N; i++) {//4
						for(int j = y-d1+d2; j < N; j++) {
							if(newMap[i][j] == 0) {
								newMap[i][j] = 4;
							}
						}
					}
													
					int count[] = new int[5];
					int max = Integer.MIN_VALUE;
					int min = Integer.MAX_VALUE;
					
					for(int i = 0; i < N; i++) {
						for(int j = 0; j < N; j++) {
							if(newMap[i][j] == 1) count[0] += map[i][j];
							if(newMap[i][j] == 2) count[1] += map[i][j];
							if(newMap[i][j] == 3) count[2] += map[i][j];
							if(newMap[i][j] == 4) count[3] += map[i][j];
							if(newMap[i][j] == 5) count[4] += map[i][j];
						}
					}
					for(int i = 0; i < 5; i++) {
						if(count[i] > max) max = count[i];
						if(count[i] < min) min = count[i];
					}
					if(max - min < ans) ans = max - min; 
				}
			}
		}
	}

	private static void insert(int[][] newMap, int x, int y) {
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		newMap[x][y] = 5;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if(newMap[nx][ny] == 0) {
					queue.offer(new int[] {nx, ny});
					newMap[nx][ny] = 5;
				}
			}
		}
	}
}
