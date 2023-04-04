package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G5_9205_맥주마시면서걸어가기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for(int test_case = 0; test_case < t; test_case++) 
		{
			int N = Integer.parseInt(in.readLine()); // 편의점 개수
			int[][] graph = new int[N + 2][N + 2];
			
			String str[] = in.readLine().split(" ");
			int sangx = Integer.parseInt(str[0]);
			int sangy = Integer.parseInt(str[1]);
			
			int[][] pyun = new int[N + 2][2];
			for(int i = 0; i < N; i++) {
				str = in.readLine().split(" ");
				pyun[i][0] = Integer.parseInt(str[0]);
				pyun[i][1] = Integer.parseInt(str[1]);
			}
			pyun[N][0] = sangx;
			pyun[N][1] = sangy;
			
			str = in.readLine().split(" ");
			
			pyun[N + 1][0] = Integer.parseInt(str[0]);
			pyun[N + 1][1] = Integer.parseInt(str[1]);
		
			//바로 갈 수 있는거 초기화
			for(int i = 0; i < N + 2; i++) {
				for(int j = 0; j < N + 2; j++){
					if(i == j) continue;
//					if(Math.abs(pyun[i][0] - pyun[j][0]) + Math.abs(pyun[i][1] - pyun[j][1]) <= 1000) graph[i][j] = 1;

				}
			}
			
			for(int k = 0; k < N + 2; k++) {
				for(int i = 0; i < N + 2; i++) {
					if(i == k) continue;
					for(int j = 0; j < N + 2; j++){
						if(i == j) continue;
						if(graph[i][k] == 1 && graph[k][j] == 1) graph[i][j] = 1;
					}
				}
			}
			
			if(graph[N][N + 1] == 1) {
				System.out.println("happy");
			}
			else {
				System.out.println("sad");
			}
		}
	}
}
