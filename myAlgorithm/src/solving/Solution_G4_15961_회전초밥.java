package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_G4_15961_회전초밥 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str[] = in.readLine().split(" ");
		
		int N = Integer.parseInt(str[0]);
		int d = Integer.parseInt(str[1]);
		int k = Integer.parseInt(str[2]);
		int c = Integer.parseInt(str[3]);
		
		int[] belt = new int[N];
		int[] susi = new int[d + 1];
		for(int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(in.readLine());
		}
		
		int all = 1;
		susi[c] ++;
		for(int i = 0; i < k; i++) {
			if(susi[belt[i]] == 0) {
				all++;
			}
			susi[belt[i]]++;
		}
		int max = all;
		
		for(int i = 0; i < N; i++) {
			if(--susi[belt[i]] == 0) {
				all--;
			}
			
			int temp = i + k;
			if(temp >= N) {
				temp -= N;
			}
			if(++susi[belt[temp]] == 1) {
				all++;
			}
			if(all > max) {
				max = all;
			}
//			System.out.println(Arrays.toString(susi));
		}
		System.out.println(max);
	}
}
