package DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_G1_1194_달이차오른다가자 {
	
	/*
	 * 비트마스킹을 이용한 3차원 visited를 사용하여 풀 수 있다.
	 * 해당 열쇠를 가지고 있을 때의 visited를 만들어서 사용하면 되는 것.
	 */
	
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
		int key;
		int cnt;
		
		public user(int x, int y, int key, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.key = key;
			this.cnt = cnt;
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
		int[] dy = {0, -1, 0, 1};
		
		Queue<user> queue = new ArrayDeque<>();
		int[][][] visited = new int[N][M][64];
		visited[startx][starty][0] = 1;
		queue.offer(new user(startx, starty, 0, 0));
		while(!queue.isEmpty()) {
			user tempUser = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nx = tempUser.x + dx[i];
				int ny = tempUser.y + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][tempUser.key] == 1 || map[nx][ny] == '#') continue;
				if(map[nx][ny] == '1') {
					ans = tempUser.cnt + 1;
					return;
				}
				if(map[nx][ny] == '.' || map[nx][ny] == '0') {
					visited[nx][ny][tempUser.key] = 1;
					queue.offer(new user(nx, ny, tempUser.key, tempUser.cnt + 1));
				}
				if(map[nx][ny] == 'a' || map[nx][ny] == 'b' || map[nx][ny] == 'c' || map[nx][ny] == 'd' || map[nx][ny] == 'e' || map[nx][ny] == 'f') {
					int now =  tempUser.key | (1 << (map[nx][ny] - 'a'));
					visited[nx][ny][now] = 1;
					queue.offer(new user(nx, ny, now, tempUser.cnt + 1));
				}
				if(map[nx][ny] == 'A' || map[nx][ny] == 'B' || map[nx][ny] == 'C' || map[nx][ny] == 'D' || map[nx][ny] == 'E' || map[nx][ny] == 'F') {
					if((tempUser.key & (1 << (map[nx][ny] - 'a'))) >= 1) {
						visited[nx][ny][tempUser.key] = 1;
						queue.offer(new user(nx, ny, tempUser.key, tempUser.cnt + 1));
					} 
				}
			}
		}
		return;
	}
}
