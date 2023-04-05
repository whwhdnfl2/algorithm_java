package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_G4_15683_감시 {
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static class cctv{
		int x;
		int y;
		int what;
		public cctv(int x, int y, int what) {
			super();
			this.x = x;
			this.y = y;
			this.what = what;
		}
	}
	
	static List<cctv> cctvList;
	

	static int N, M;
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str[] = in.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		cctvList = new ArrayList<>();
		int map[][] = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new cctv(i, j, map[i][j]));
				}
			}
		}
		
		combination(0, map);
		System.out.println(answer);
	}

	private static void combination(int cnt, int[][] newMap) {
		if(cnt == cctvList.size()) {
			
			int ans = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(newMap[i][j] == 0) {
						ans++;
					}
				}
			}
			if(ans < answer) answer = ans;
			return;
		}

		cctv now = cctvList.get(cnt);
		if(now.what == 1) {
			for(int i = 0; i < 4; i++) {
				int[][] tempMap = clone(newMap);
				fill1(tempMap, now.x, now.y, i);
				combination(cnt + 1, tempMap);
			}
		}
		if(now.what == 2) {
			for(int i = 0; i < 2; i++) {
				int[][] tempMap = clone(newMap);
				fill2(tempMap, now.x, now.y, i);
				combination(cnt + 1, tempMap);
			}
		}
		if(now.what == 3) {
			for(int i = 0; i < 4; i++) {
				int[][] tempMap = clone(newMap);
				fill3(tempMap, now.x, now.y, i);
				combination(cnt + 1, tempMap);
			}
		}
		if(now.what == 4) {
			for(int i = 0; i < 4; i++) {
				int[][] tempMap = clone(newMap);
				fill4(tempMap, now.x, now.y, i);
				combination(cnt + 1, tempMap);
			}
		}
		if(now.what == 5) {
			int[][] tempMap = clone(newMap);
			fill5(tempMap, now.x, now.y);
			combination(cnt + 1, tempMap);
		}
		

		
	}
	
	private static void fill5(int[][] tempMap, int x, int y) {
		int nx1 = x;
		int ny1 = y;
		int nx2 = x;
		int ny2 = y;
		int nx3 = x;
		int ny3 = y;
		int nx4 = x;
		int ny4 = y;
		
		while(true) {
			nx1 = nx1 + dx[0];
			ny1 = ny1 + dy[0];
			if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) break;
			if(tempMap[nx1][ny1] == 6) break;
			tempMap[nx1][ny1] = 10;
		}
		while(true) {
			nx2 = nx2 + dx[1];
			ny2 = ny2 + dy[1];
			if(nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) break;
			if(tempMap[nx2][ny2] == 6) break;
			tempMap[nx2][ny2] = 10;
		}
		while(true) {
			nx3 = nx3 + dx[2];
			ny3 = ny3 + dy[2];
			if(nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= M) break;
			if(tempMap[nx3][ny3] == 6) break;
			tempMap[nx3][ny3] = 10;
		}
		while(true) {
			nx4 = nx4 + dx[3];
			ny4 = ny4 + dy[3];
			if(nx4 < 0 || nx4 >= N || ny4 < 0 || ny4 >= M) break;
			if(tempMap[nx4][ny4] == 6) break;
			tempMap[nx4][ny4] = 10;
		}
		
	}

	private static void fill4(int[][] tempMap, int x, int y, int i) {
		int nx1 = x;
		int ny1 = y;
		int nx2 = x;
		int ny2 = y;
		int nx3 = x;
		int ny3 = y;
		
		while(true) {
			nx1 = nx1 + dx[i];
			ny1 = ny1 + dy[i];
			if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) break;
			if(tempMap[nx1][ny1] == 6) break;
			tempMap[nx1][ny1] = 10;
		}
		
		while(true) {
			int temp = i + 1;
			if(temp == 4) {
				temp = 0;
			}
			nx2 = nx2 + dx[temp];
			ny2 = ny2 + dy[temp];
			if(nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) break;
			if(tempMap[nx2][ny2] == 6) break;
			tempMap[nx2][ny2] = 10;
		}
		while(true) {
			int temp = i - 1;
			if(temp == -1) {
				temp = 3;
			}
			nx3 = nx3 + dx[temp];
			ny3 = ny3 + dy[temp];
			if(nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= M) break;
			if(tempMap[nx3][ny3] == 6) break;
			tempMap[nx3][ny3] = 10;
		}
	}

	private static void fill3(int[][] tempMap, int x, int y, int i) {
		int nx1 = x;
		int ny1 = y;
		int nx2 = x;
		int ny2 = y;
		
		while(true) {
			nx1 = nx1 + dx[i];
			ny1 = ny1 + dy[i];
			if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) break;
			if(tempMap[nx1][ny1] == 6) break;
			tempMap[nx1][ny1] = 10;
		}
		
		while(true) {
			int temp = i + 1;
			if(temp == 4) {
				temp = 0;
			}
			nx2 = nx2 + dx[temp];
			ny2 = ny2 + dy[temp];
			if(nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) break;
			if(tempMap[nx2][ny2] == 6) break;
			tempMap[nx2][ny2] = 10;
		}
	}

	private static void fill2(int[][] tempMap, int x, int y, int i) {
		
		int nx1 = x;
		int ny1 = y;
		int nx2 = x;
		int ny2 = y;
		
		while(true) {
			nx1 = nx1 + dx[i];
			ny1 = ny1 + dy[i];
			if(nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) break;
			if(tempMap[nx1][ny1] == 6) break;
			tempMap[nx1][ny1] = 10;
		}
		
		while(true) {
			int temp = i + 2;
			nx2 = nx2 + dx[temp];
			ny2 = ny2 + dy[temp];
			if(nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) break;
			if(tempMap[nx2][ny2] == 6) break;
			tempMap[nx2][ny2] = 10;
		}
		
		
	}

	private static void fill1(int[][] tempMap, int x, int y, int i) {
		while(true) {
			x = x + dx[i];
			y = y + dy[i];
			if(x < 0 || x >= N || y < 0 || y >= M) break;
			if(tempMap[x][y] == 6) break;
			tempMap[x][y] = 10;
		}
	}

	private static int[][] clone(int[][] newMap) {
		int[][] tempMap = new int[N][M];
		for(int i = 0; i < N; i++) {
			tempMap[i] = newMap[i].clone();
		}
		return tempMap;
	}
}
