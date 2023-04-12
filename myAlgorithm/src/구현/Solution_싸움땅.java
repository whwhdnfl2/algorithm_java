package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_싸움땅 {
	
	/*
	 * 해당 칸에 존재하는 총들
	 * 플레이어가 가진 포인트
	 * 플레이어가 가진 총
	 * 플레이어의 방향
	 */
	
	static int N;
	static int[] dx = {-1, 0, 1, 0}; // 상우하좌
	static int[] dy = {0, 1, 0, -1};
	
	static class player{
		int x;
		int y;
		int direc; // 방향
		int point; // 보유한 포인트
		int gun; // 가진 총의 공격력
		int base; //기본 능력치
		public player(int x, int y, int direc, int point, int gun, int base) {
			super();
			this.x = x;
			this.y = y;
			this.direc = direc;
			this.point = point;
			this.gun = gun;
			this.base = base;
		}
	}
	
	static class kan{
		PriorityQueue<Integer> guns;
		int person;

		public kan(PriorityQueue<Integer> guns) {
			super();
			this.guns = guns;
			this.person = -1; // 일단 사람이 없는 것
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str[] = in.readLine().split(" ");
		N = Integer.parseInt(str[0]); // 격자 크기
		int M = Integer.parseInt(str[1]); // 플레이어의 수
		int K = Integer.parseInt(str[2]); // 라운드의 수
		
		kan[][] map = new kan[N][N]; //맵 (각 칸에 떨어져 있는 총들을 저장 중이다.)
		for(int i = 0; i < N; i++) {
			map[i] = new kan[N];
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new kan(new PriorityQueue<Integer>());
			}
		}
		
		//각 칸에 총을 넣어준다.
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int gun = Integer.parseInt(str[j]);
				if(gun != 0) map[i][j].guns.add(gun);
			}
		}
		
		
		player[] players = new player[M];
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			players[i] = new player( 
					Integer.parseInt(str[0]) - 1, 
					Integer.parseInt(str[1]) - 1, 
					Integer.parseInt(str[2]), 0, 0, 
					Integer.parseInt(str[3])
			);
			map[Integer.parseInt(str[0]) - 1][Integer.parseInt(str[1]) - 1].person = i; // 각 칸에다가 플레이어를 넣어준다.
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j].guns.size() +  " ");
//			}
//			System.out.println();
//		}
//		
		
		//라운드 시작.
		for(int round = 0; round < K; round++) // 각 라운드 진행
		{
			
			
			for(int num = 0; num < M; num++) { // 0번 플레이어부터 한 칸씩 움직인다.
				
				player nowPlayer = players[num];
				int nx = nowPlayer.x + dx[nowPlayer.direc];
				int ny = nowPlayer.y + dy[nowPlayer.direc];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) { // 범위를 벗어나면
//					System.out.println(nowPlayer.direc);
//					System.out.println(nx + " " + ny);
					nowPlayer.direc = (nowPlayer.direc + 2) % 4; //방향 전환
					nx = nx + dx[nowPlayer.direc] * 2; // 방향 전환 후에 반대로 2배만큼 가줘야 제대로 이동한 것.
					ny = ny + dy[nowPlayer.direc] * 2;
//					System.out.println(nowPlayer.direc);
//					System.out.println(nx + " " + ny);
				}

				
				if(map[nx][ny].person != -1) { //이동 후에 그 칸에 플레이어가 있으면
					//싸움 시작
					player fightPlayer = players[map[nx][ny].person]; // 싸울 플레이어 꺼냄.
					int fightPlayerstat = fightPlayer.base + fightPlayer.gun;
					int mystat = nowPlayer.base + nowPlayer.gun;
					if(fightPlayerstat == mystat) { //공격력 합이 같다면 기본 스탯이 강한 놈이 이김.
						if(nowPlayer.base > fightPlayer.base) {
							//진 플레이어가 총을 버린다.
							map[nx][ny].guns.add(fightPlayer.gun);
							fightPlayer.gun = 0;
							move(fightPlayer, map); // 진 플레이어가 움직임.
							// 이긴 플레이어가 총을 줍는다.
							getGun(map, nowPlayer, nx, ny);
						}else {
							map[nx][ny].guns.add(nowPlayer.gun);
							nowPlayer.gun = 0;
							move(nowPlayer, map);
							// 이긴 플레이어가 총을 줍는다.
							getGun(map, fightPlayer, nx, ny);
						}
						
					}else if(fightPlayerstat > mystat){
						map[nx][ny].guns.add(nowPlayer.gun);
						nowPlayer.gun = 0;
						fightPlayer.point += fightPlayerstat - mystat;
						move(nowPlayer, map);
						// 이긴 플레이어가 총을 줍는다.
						getGun(map, fightPlayer, nx, ny);
						
					}else {
						map[nx][ny].guns.add(fightPlayer.gun);
						fightPlayer.gun = 0;
						nowPlayer.point += mystat - fightPlayerstat;
						move(fightPlayer, map);
						// 이긴 플레이어가 총을 줍는다.
						getGun(map, nowPlayer, nx, ny);
					}
					
					
				}else { //없으면
					map[nowPlayer.x][nowPlayer.y].person = 0;
					map[nx][ny].person = num; // 해당 칸에 플레이어를 추가함.
					nowPlayer.x = nx; // 플레이어 이동 완료 시킴.
					nowPlayer.y = ny;
					
					// 총 줍기 시전
					getGun(map, nowPlayer, nx, ny);
				}
				
				
			}
			
			
		}
		for(int i = 0; i < M; i++) {
			System.out.print(players[i].point + " ");
		}

		
	}

	private static void getGun(kan[][] map, player nowPlayer, int x, int y) {
		int gun = map[x][y].guns.poll();
		if(gun > nowPlayer.gun) { // 현재 플레이어가 가진 총보다 강하다면
			map[x][y].guns.add(nowPlayer.gun); //플레이어가 들고 있던 총을 넣어준다.
			nowPlayer.gun = gun; // 총 바꿔줌.
		}else {// 강하지 않다면
			map[x][y].guns.add(gun);// 총 다시 넣어줌.
		}
	}

	private static void move(player nowPlayer, kan[][] map) {
		while(true) {
//			System.out.println("1");
//			System.out.println(N);
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < N; j++) {
//					System.out.print(map[i][j].guns.size() +  " ");
//				}
//				System.out.println();
//			}



			int nx = nowPlayer.x + dx[nowPlayer.direc];
			int ny = nowPlayer.y + dy[nowPlayer.direc];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny].person != -1) { // 범위를 벗어나거나 플레이어가 있으면
				nowPlayer.direc = (nowPlayer.direc + 1) % 4; // 오른쪽 90도 전환
				continue;
			}
			if(map[nx][ny].guns.size() != 0) { // 움직인 칸이 빈칸이면
				nowPlayer.x = nx; // 플레이어 이동 완료
				nowPlayer.y = ny;
				nowPlayer.gun = map[nx][ny].guns.poll();
				break;
			}
			if(map[nx][ny].person == -1){
				System.out.println("asdasd");
				nowPlayer.x = nx; // 플레이어 이동 완료
				nowPlayer.y = ny;
				break;
			}
		}

	}
}
