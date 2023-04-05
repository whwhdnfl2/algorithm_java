package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_G3_20058_마법사상어와파이어스톰 {
	
	static int countIce = 0;
	
	static int[][] map;
	
	static int mapSize; // 전체 맵 사이즈
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str[] = in.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int Q = Integer.parseInt(str[1]);
		
		mapSize = (int)Math.pow(2, N);
		
		map = new int[mapSize][mapSize];
		
		for(int i = 0; i < mapSize; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				countIce += map[i][j];
			}
		}
		
		str = in.readLine().split(" ");
		for(int i = 0; i < Q; i++) {
			int L = Integer.parseInt(str[i]);
			
			spin(L);
			
			int[][] tempMap = new int[mapSize][mapSize];
			for(int j = 0; j < mapSize; j++) {
				tempMap[j] = map[j].clone();
			}
			
			for(int l = 0; l < mapSize; l++) {
				for(int c = 0; c < mapSize; c++) {
					
					if(map[l][c] <= 0) continue;
					
					int near = 0;
					
					for(int a = 0; a < 4; a++) {
						int nx = l + dx[a];
						int ny = c + dy[a];
						if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize) continue;
						if(map[nx][ny] >= 1) near++;
					}
					if(near < 3) {
						tempMap[l][c] --;
						countIce--;
					}
				}
			}
			map = tempMap;
		}
		
		visited = new boolean[mapSize][mapSize];
		
		int biggestIce = 0;
		
		for(int l = 0; l < mapSize; l++) {
			for(int c = 0; c < mapSize; c++) {
				if(!visited[l][c] && map[l][c] != 0) {
					int tempIce = bfs(l, c);
					if(tempIce > biggestIce) biggestIce = tempIce; 
				}
			}
		}
		
		System.out.println(countIce);
		System.out.println(biggestIce);

	}

	private static int bfs(int x, int y) {
		int sum = 1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize) continue;
				if(map[nx][ny] > 0 && !visited[nx][ny]) {
					queue.offer(new int[] {nx, ny});
					sum++;
					visited[nx][ny] = true;
				}
			}
		}
		return sum;
	}

	private static void spin(int L) {
		int size = (int)Math.pow(2, L); // 나누는 사이즈
		
		
		for(int i = 0; i < mapSize; i += size) {
			for(int j = 0; j < mapSize; j += size) {
				int[][] temp = new int[size][size];
				
				for(int r = i; r < i + size; r++) {
					for(int c = j; c < j + size; c++) {
						temp[r - i][c - j] = map[r][c];
					}					
				}
				
				int[][] temp2 = new int[size][size];
				
				for(int l = 0; l < size; l++) {
					for(int c = 0; c < size; c++) {
						temp2[l][c] = temp[size - c - 1][l];
					}
				}
				
				for(int l = i; l < i + size; l++) {
					for(int c = j; c < j + size; c++) {
						map[l][c] = temp2[l - i][c - j];
					}					
				}
				
			}
		}
	}
}
