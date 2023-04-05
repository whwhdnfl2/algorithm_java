package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G3_20057_마법사상어와토네이도 {
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] map;
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		String[] str;
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int move = 1; // 한 줄에서 움직이는 횟수
		int direc = 0; // 움직이는 방향
		int x = N / 2; // 처음 토네이도 좌표
		int y = N / 2;
		int allCount = N * N; // 총 칸 수
		
		int moveCount = 0;
		for(int i = 0; i < allCount; i++) {
			x += dx[direc];
			y += dy[direc];
			
			blow(x, y, direc);
			
			if(++moveCount == 2) {
				moveCount = 0;
				direc = direc + 1 % 4;
				move++;
			}
		}
	}

	private static void blow(int x, int y, int direc) {
		int send = map[x][y];
		
		int movex = dx[direc];
		int movey = dy[direc];
		
		int ten1 = (int) (send * 0.1);
		int ten2 = (int) (send * 0.1);
		if(movex == 0) {
			if(x + 1 < N && y + movey >= 0 && y + movey < N) {
				map[x + 1][y + movey] += ten1;	
			}
			if(x - 1 >= 0 && y + movey >= 0 && y + movey < N) {
				map[x - 1][y + movey] += ten2;	
			}
		}
	}
}
