package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G3_14890_경사로 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		int t = Integer.parseInt(in.readLine());
		for(int test_case = 1; test_case <= t; test_case++) 
		{
			String[] str = in.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int L = Integer.parseInt(str[1]);
			
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				str = in.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			
			int ans = 0;
			
			for(int i = 0; i < N; i++) //행 검사
			{
				boolean[] isFloor = new boolean[N];
				boolean isLoad = true;
				for(int j = 1; j < N; j++) {
					if(map[i][j - 1] == map[i][j]) continue;
					if(map[i][j - 1] - 1 == map[i][j]) { // 앞 칸이 하나 더 크다
						
						boolean canFloor = true;
						
						for(int k = 0; k < L; k++) {
							if(j + k >= N || (map[i][j] != map[i][j + k]) || isFloor[j + k]) { // 만약 계단을 놓을 칸의 높이들이 다르거나 이미 계단이 설치되어 있다면
								canFloor = false;
								break;
							}
						}
						
						if(canFloor) {
							for(int k = 0; k < L; k++) {
								isFloor[j + k] = true;
							}
						}
						else {
							isLoad = false;
							break; // 다음 행을 본다.
						}
					}
					else if(map[i][j - 1] + 1 == map[i][j]) { // 앞 칸이 하나 더 작다
						
						boolean canFloor = true;
						
						for(int k = 1; k <= L; k++) {		
							if(j - k < 0 || (map[i][j - 1] != map[i][j - k]) || isFloor[j - k]) { // 만약 계단을 놓을 칸의 높이들이 다르거나 이미 계단이 설치되어 있다면
								canFloor = false;
								break;
							}
						}
						
						if(canFloor) { //계단을 놓을 수 있으면 계단 설치
							for(int k = 1; k <= L; k++) {
								isFloor[j - k] = true;
							}
						}
						else { // 계단을 놓을 수 없으면
							isLoad = false;
							break; // 다음 행을 본다.
						}
					}
					else {
						isLoad = false;
						break; // 다음 행을 본다.
					}
				
				}
				if(isLoad) {
					ans++;
				}
			}	
			
			for(int j = 0; j < N; j++) //열 검사
			{
				boolean[] isFloor = new boolean[N];
				boolean isLoad = true;
				for(int i = 1; i < N; i++) {
					if(map[i - 1][j] == map[i][j]) continue;
					if(map[i - 1][j] - 1 == map[i][j]) { // 앞 칸이 하나 더 크다
						
						boolean canFloor = true;
						
						for(int k = 0; k < L; k++) {
							if(i + k >= N || (map[i][j] != map[i + k][j]) || isFloor[i + k]) { // 만약 계단을 놓을 칸의 높이들이 다르거나 이미 계단이 설치되어 있다면
								canFloor = false;
								break;
							}
						}
						
						if(canFloor) {
							for(int k = 0; k < L; k++) {
								isFloor[i + k] = true;
							}
						}
						else {
							isLoad = false;
							break; // 다음 행을 본다.
						}
					}
					else if(map[i - 1][j] + 1 == map[i][j]) { // 앞 칸이 하나 더 작다
						
						boolean canFloor = true;
						
						for(int k = 1; k <= L; k++) {
							if(i - k < 0 || (map[i - 1][j] != map[i - k][j]) || isFloor[i - k]) { // 만약 계단을 놓을 칸의 높이들이 다르거나 이미 계단이 설치되어 있다면
								canFloor = false;
								break;
							}
						}
						
						if(canFloor) { //계단을 놓을 수 있으면 계단 설치
							for(int k = 1; k <= L; k++) {
								isFloor[i - k] = true;
							}
						}
						else { // 계단을 놓을 수 없으면
							isLoad = false;
							break; // 다음 행을 본다.
						}
					}
					else {
						isLoad = false;
						break; // 다음 행을 본다.
					}
				}
				if(isLoad) {
					ans++;
				}
			}
			System.out.println("#" + test_case + " " + ans);
		}

	}
}
