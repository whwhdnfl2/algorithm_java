package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_G3_20057_마법사상어와토네이도 {
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] map;
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		int allSend = 0;
		
		String[] str;
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				allSend += map[i][j];
			}
		}
		
		int move = 1; // 한 줄에서 움직이는 횟수
		int direc = 0; // 움직이는 방향
		int x = N / 2; // 처음 토네이도 좌표
		int y = N / 2;
		int allCount = N * N; // 총 칸 수
		int count = 1; // 현재 움직인 횟수
		
		int moveCount = 0;
		while(count < allCount) {
			for(int i = 0; i < move; i++) {
				x += dx[direc];
				y += dy[direc];
				
				blow(x, y, direc);
//				map[x][y] = count;
//				System.out.println();
//				for(int j = 0; j < N; j++) {
//					System.out.println(Arrays.toString(map[j]));
//				}

				count++;
				if(count == allCount) {
					break;
				}
			}
			direc = (direc + 1) % 4;
			if(++moveCount == 2) {
				moveCount = 0;
				move++;
			}
		}
//		System.out.println();
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		int remainSend = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				remainSend += map[i][j];
			}
		}
		System.out.println(allSend - remainSend);
	}

	private static void blow(int x, int y, int direc) {
		int send = map[x][y];
		
		int movex = dx[direc];
		int movey = dy[direc];
		
		int ten = (int) (send * 0.1);
		int five = (int) (send * 0.05);
		int one = (int) (send * 0.01);
		int seven = (int) (send * 0.07);
		int two = (int) (send * 0.02);

		
		if(direc % 2 == 0) { // 좌 우
			//10
			if(x + 1 < N && y + movey >= 0 && y + movey < N) {
				map[x + 1][y + movey] += ten;
			}
			if(x - 1 >= 0 && y + movey >= 0 && y + movey < N) {
				map[x - 1][y + movey] += ten;
			}
			
			//5
			if(y + movey * 2 >= 0 && y + movey * 2 < N) {
				map[x][y + movey * 2] += five;
			}
			
			//7
			if(x + 1 < N) {
				map[x + 1][y] += seven;
			}
			if(x - 1 >= 0) {
				map[x - 1][y] += seven;
			}
			
			//2
			if(x + 2 < N) {
				map[x + 2][y] += two;
			}
			if(x - 2 >= 0) {
				map[x - 2][y] += two;
			}
			
			//1
			if(x + 1 < N && y - movey >= 0 && y - movey < N) {
				map[x + 1][y - movey] += one;
			}
			if(x - 1 >= 0 && y - movey >= 0 &&y - movey < N) {
				map[x - 1][y - movey] += one;
			}
		}else { //상 하
			//10
			if(x + movex < N && x + movex >= 0 && y + 1 < N) {
				map[x + movex][y + 1] += ten;
			}
			if(x + movex < N && x + movex >= 0 && y - 1 >= 0) {
				map[x + movex][y - 1] += ten;
			}
			
			//5
			if(x + movex * 2 >= 0 && x + movex * 2 < N) {
				map[x + movex * 2][y] += five;
			}
			
			//7
			if(y + 1 < N) {
				map[x][y + 1] += seven;
			}
			if(y - 1 >= 0) {
				map[x][y - 1] += seven;
			}
			
			//2
			if(y + 2 < N) {
				map[x][y + 2] += two;
			}
			if(y - 2 >= 0) {
				map[x][y - 2] += two;
			}
			
			//1
			if(x - movex < N && x - movex >= 0 && y + 1 < N) {
				map[x - movex][y + 1] += one;
			}
			if(x - movex < N && x - movex >= 0 && y - 1 >= 0) {
				map[x - movex][y - 1] += one;
			}
		}
		if(x + movex < N && x + movex >= 0 && y + movey >= 0 && y + movey < N) {
			map[x + movex][y + movey] += send - (ten * 2 + five + one * 2 + two * 2 + seven * 2);
		}
		map[x][y] = 0;
	}
}
