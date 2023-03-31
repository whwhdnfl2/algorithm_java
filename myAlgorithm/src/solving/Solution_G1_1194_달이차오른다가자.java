package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_G1_1194_달이차오른다가자 {
	
	static class key{
		int x;
		int y;
		public key(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class user{
		int x;
		int y;
		key[] keys;
		int cnt;
		int[][] visited;
		
		public user(int x, int y, key[] keys, int cnt, int[][] visited) {
			super();
			this.x = x;
			this.y = y;
			this.keys = keys;
			this.cnt = cnt;
			this.visited = visited;
		}
	}
	
	
	static int ans = -1;
	static int N, M;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str[] = in.readLine().split(" ");
		
		
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		int startx = 0;
		int starty = 0;
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String str1 = in.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str1.charAt(j);
				if(map[i][j] == '0') {
					startx = i;
					starty = j;
				}
			}
		}
		
		bfs(startx, starty);
		System.out.println(ans);
	}

	private static void bfs(int startx, int starty) {
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		Queue<user> queue = new ArrayDeque<>();
		int[][] visited = new int[N][M];
		visited[startx][starty] = 1;
		queue.offer(new user(startx, starty, new key[6], 0, visited));
		while(!queue.isEmpty()) {
			user tempUser = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = tempUser.x + dx[i];
				int ny = tempUser.y + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || tempUser.visited[nx][ny] == 1) continue;
				if(map[nx][ny] == '.' || map[nx][ny] == '0') {
					key[] newKeys = tempUser.keys.clone();
					int[][] newVisited = visitedClone(tempUser.visited);
					newVisited[nx][ny] ++;
					queue.offer(new user(nx, ny, newKeys, tempUser.cnt + 1, newVisited));
				}
				if(map[nx][ny] == '1') {
					ans = tempUser.cnt + 1;
					return;
				}
				if(map[nx][ny] == 'a' || map[nx][ny] == 'b' || map[nx][ny] == 'c' || map[nx][ny] == 'd' || map[nx][ny] == 'e' || map[nx][ny] == 'f') {
					if(tempUser.keys[map[nx][ny] - 'a'] != null) {
						key[] newKeys = tempUser.keys.clone();
						int[][] newVisited = visitedClone(tempUser.visited);
						newVisited[nx][ny] ++;
						queue.offer(new user(nx, ny, newKeys, tempUser.cnt + 1, newVisited));
						continue;
					}
					key[] newKeys = tempUser.keys.clone();
					newKeys[map[nx][ny] - 'a'] = new key(nx, ny);
					int[][] newVisited = new int[N][M];
					newVisited[nx][ny] ++;
					queue.offer(new user(nx, ny, newKeys, tempUser.cnt + 1, newVisited));
				}
				if(map[nx][ny] == 'A' || map[nx][ny] == 'B' || map[nx][ny] == 'C' || map[nx][ny] == 'D' || map[nx][ny] == 'E' || map[nx][ny] == 'F') {
					if(tempUser.keys[map[nx][ny] - 'A'] != null) {
						key[] newKeys = tempUser.keys.clone();
						int[][] newVisited = visitedClone(tempUser.visited);
						for(int a = 0; a < 6; a++) {
							if(newKeys[a] == null) continue;
							newVisited[newKeys[a].x][newKeys[a].y] = 1;
						}
						queue.offer(new user(nx, ny, newKeys, tempUser.cnt + 1, newVisited));
					}else {
					}
				}
			}
		}
		return;
	}

	private static int[][] visitedClone(int[][] visited) {
		int[][] newVisited = new int[N][M];
		for(int i = 0; i < N; i++) {
			newVisited[i] = visited[i].clone();
		}
		return newVisited;
	}
}
