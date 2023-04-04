package DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_G5_17070_파이프옮기기1 {

	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		String str[];
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
				
		int[][] visited = new int[N][N];
		visited[0][1] = 1;
		

		//bfs
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 1, 0}); // 0은 가로, 1은 세로, 2는 대각
		while(!queue.isEmpty()) {
			
			int[] dx = {0, 1, 1};
			int[] dy = {1, 0, 1};
			
			int[] temp = queue.poll();
//			if(temp[0] == N - 1 && temp[1] == N - 1) {
//				ans++;
//				continue;
//			}
			if(temp[2] == 0) {
				for(int i = 0; i < 3; i++) {
					if(i == 1) continue;
					int nx = temp[0] + dx[i];
					int ny = temp[1] + dy[i];
					if(i != 2) {
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) continue;
						
						if(visited[nx][ny] == 0) {
							visited[nx][ny] = visited[temp[0]][temp[1]];
							queue.offer(new int[] {nx, ny, i});
						}
						else {
							visited[nx][ny] = visited[temp[0]][temp[1]] + visited[nx][ny];
						}
					}
					else {
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1) continue;
						if(visited[nx][ny] == 0) {
							visited[nx][ny] = visited[temp[0]][temp[1]];
							queue.offer(new int[] {nx, ny, i});
						}
						else {
							visited[nx][ny] = visited[temp[0]][temp[1]] + visited[nx][ny];
						}
					}

				}
			}else if(temp[2] == 1) {
				for(int i = 0; i < 3; i++) {
					if(i == 0) continue;
					int nx = temp[0] + dx[i];
					int ny = temp[1] + dy[i];
					if(i != 2) {
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) continue;
						if(visited[nx][ny] == 0) {
							visited[nx][ny] = visited[temp[0]][temp[1]];
							queue.offer(new int[] {nx, ny, i});
						}
						else {
							visited[nx][ny] = visited[temp[0]][temp[1]] + visited[nx][ny];
						}
					}
					else {
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1) continue;
						if(visited[nx][ny] == 0) {
							visited[nx][ny] = visited[temp[0]][temp[1]];
							queue.offer(new int[] {nx, ny, i});
						}
						else {
							visited[nx][ny] = visited[temp[0]][temp[1]] + visited[nx][ny];
						}
					}
				}
			}else {
				for(int i = 0; i < 3; i++) {
					int nx = temp[0] + dx[i];
					int ny = temp[1] + dy[i];
					if(i != 2) {
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) continue;
						if(visited[nx][ny] == 0) {
							visited[nx][ny] = visited[temp[0]][temp[1]];
							queue.offer(new int[] {nx, ny, i});
						}
						else {
							visited[nx][ny] = visited[temp[0]][temp[1]] + visited[nx][ny];
						}
					}
					else {
						if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1 || map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1) continue;
						if(visited[nx][ny] == 0) {
							visited[nx][ny] = visited[temp[0]][temp[1]];
							queue.offer(new int[] {nx, ny, i});
						}
						else {
							visited[nx][ny] = visited[temp[0]][temp[1]] + visited[nx][ny];
						}
					}
				}
			}
		}
		System.out.println(visited[N - 1][N - 1]);
	}
}