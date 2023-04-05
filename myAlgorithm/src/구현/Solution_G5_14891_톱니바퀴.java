package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G5_14891_톱니바퀴 {
	
	static int[][] top;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		top = new int[4][8];
		String str[];
		for(int i = 0; i < 4; i++) {
			str = in.readLine().split("");
			for(int j = 0; j < 8; j++) {
				top[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int N = Integer.parseInt(in.readLine());
		for(int i = 0; i < N; i++) 
		{
			str = in.readLine().split(" ");
			int num = Integer.parseInt(str[0]);
			int direc = Integer.parseInt(str[1]);
			spin(num - 1, direc);

		}
		int ans = 0;
		
		if(top[0][0] == 1) {
			ans++;
		}
		if(top[1][0] == 1) {
			ans += 2;
		}
		if(top[2][0] == 1) {
			ans += 4;
		}
		if(top[3][0] == 1) {
			ans += 8;
		}
		System.out.println(ans);
	}

	private static void spin(int num, int direc) {
		int[] direction = new int[4];
		direction[num] = direc; // 1: 시계방향, -1: 반시계 방향
		
		int num_plus = num + 1;
		int num_minus = num - 1;
		
		while(true) {
			boolean plus = false;
			boolean minus = false;
			
			if(num_plus < 4) {
				plus = true;
				if(top[num_plus - 1][2] != top[num_plus][6]) {
					if(direction[num_plus - 1] == -1) {
						direction[num_plus] = 1;
					}else if(direction[num_plus - 1] == 1){
						direction[num_plus] = -1;
					}
				}
			}
			if(num_minus >= 0) {
				minus = true;
				if(top[num_minus + 1][6] != top[num_minus][2]) {
					if(direction[num_minus + 1] == -1) {
						direction[num_minus] = 1;
					}else if(direction[num_minus + 1] == 1){
						direction[num_minus] = -1;
					}
				}
			}
			
			
			if(!plus && !minus) {
				break;
			}
			num_plus++;
			num_minus--;
		}
		
		for(int i = 0; i < 4; i++) {
			if(direction[i] == 1) spin_index1(top[i]);
			else if(direction[i] == -1) spin_index2(top[i]);
		}
		
	}

	private static void spin_index1(int[] is) {//시계 방향 회전 1
		int temp = is[7];
		for(int i = 7; i >= 1; i--) {
			is[i] = is[i - 1];
		}
		is[0] = temp;
	}
	
	private static void spin_index2(int[] is) {//반시계 방향 회전 -1
		int temp = is[0];
		for(int i = 0; i < 7; i++) {
			is[i] = is[i + 1];
		}
		is[7] = temp;
	}
}
