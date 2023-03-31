package solving;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class UserSolution {

	int mstrcmp(char[] a, char[] b) {
		int i;
		for (i = 0; a[i] != '\0'; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return a[i] - b[i];
	}

	int mstrncmp(char[] a, char[] b, int len) {
		for (int i = 0; i < len; i++) {
			if (a[i] != b[i])
				return a[i] - b[i];
		}
		return 0;
	}
	
	
	int mstrlen(char[] a) {
		int len = 0;

		while (a[len] != '\0')
			len++;

		return len;
	}

	void mstrcpy(char[] dest, char[] src) {
		int i = 0;
		while (src[i] != '\0') {
			dest[i] = src[i];
			i++;
		}
		dest[i] = src[i];
	}

	void mstrncpy(char[] dest, char[] src, int len) {
		for (int i = 0; i < len; i++) {
			dest[i] = src[i];
		}
		dest[len] = '\0';
	}
	
	static class correctWord{
		Set<Integer> set = new HashSet<>();
		char[] word;

		public correctWord(char[] word, int mId) {
			super();
			this.word = word;
			set.add(mId);
		}
	}
	
	Map<String, List<correctWord>> db;
	
	int[] timeStamp;
	
	char[][] userWord;
	
	void init(int n) {
		db = new HashMap<String, List<correctWord>>();
		
		timeStamp = new int[n + 1];
		
		userWord = new char[n + 1][11]; // 오타 넣는 배열
	}
	
	int search(int mId, int searchTimestamp, char[] searchWord, char[][] niceWord) {
		//입력 받은 단어가 db에 있는지 체크한다.
		List<correctWord> list = db.get(String.valueOf(searchWord).split("\0")[0].trim());
		
		if(timeStamp[mId] == 0) { // 해당 사용자가 처음 단어를 넣음.(timeStamp 시작)
			timeStamp[mId] = searchTimestamp;
			mstrcpy(userWord[mId], searchWord);
		}else { // 이미 단어가 있다.
			if(searchTimestamp - timeStamp[mId] > 10) {// 이전 단어를 넣은지 10초가 넘었으므로 이전에 넣은 단어는 잘 넣었거나 오타 검사를 했다.
				timeStamp[mId] = searchTimestamp;
				userWord[mId] = new char[11]; // 들어가 있는 단어 초기화
				mstrcpy(userWord[mId], searchWord);
			}else { // 10초 이내에 들어옴. 즉 이전에 들어온 단어는 오타고 다시 들어온 단어는 정타이다.
				// 체크하는 logic 추가
				boolean isInsert = check(userWord[mId], searchWord);
								
				if(isInsert) { // 삭제, 치환, 추가 중 하나임.
					
					if(db.containsKey(String.valueOf(userWord[mId]).split("\0")[0].trim())) { // 해당 오타가 이미 db에 존재한다면
						boolean is = false; // 리스트에 추가해야하나?
						for(int i = 0; i < db.get(String.valueOf(userWord[mId]).split("\0")[0].trim()).size(); i++) { //key 값으로 리스틀 가져온다.
							
							correctWord now = db.get(String.valueOf(userWord[mId]).split("\0")[0].trim()).get(i);
							
							if(now.word == searchWord) { // 리스트에서 같은거 찾는다.
								now.set.add(mId);
								is = true;
								break;
							}
						}
						if(!is) {
							db.get(String.valueOf(userWord[mId]).split("\0")[0].trim()).add(new correctWord(searchWord, mId));
						}
						
						
					}else { // 해당 오타가 db에 존재하지 않는다면
						List<correctWord> tempList = new ArrayList<correctWord>();
						tempList.add(new correctWord(searchWord, mId));						
						db.put(String.valueOf(userWord[mId]).split("\0")[0].trim(), tempList);
					}
				}		
			}
		}
		if(list != null) {
			int a = 0;
			
			for(int i = 0; i < list.size(); i++) {	
				if(list.get(i).set.size() >= 3) {
					niceWord[a++] = list.get(i).word;
				}	
			}
			if(a > 0) {
				return a;
			}
		}
		return 0;
	}
	
	

	boolean check(char[] wrongWord, char[] rightWord) { //오타 여부 검사
		
		int wronglen = mstrlen(wrongWord);
		int correctlen = mstrlen(rightWord);
		
		if(wronglen == correctlen) { //치환인지 비교
			
			int count = 0; //틀린 횟수
			
			for(int i = 0; i < wronglen; i++) {
				if(rightWord[i] != wrongWord[i]) {
					if(++count > 1) {
						return false;
					}
				}
			}
			if(count == 0) {
				return false;
			}
			return true;
			
		}else if(correctlen == wronglen + 1){ // 삭제인지 비교
			
			int change = 100;
			
			for(int i = 0; i < wronglen; i++) {
				if(rightWord[i] != wrongWord[i]) {
					change = i;
				}
			}
			
			for(int i = change; i < wronglen; i++) {
				if(rightWord[i + 1] != wrongWord[i]) {
					return false;
				}
			}
			
			return true;
			
			
		}else if(correctlen == wronglen - 1){ //추가인지 비교
			int change = 100;
			
			for(int i = 0; i < correctlen; i++) {
				if(wrongWord[i] != rightWord[i]) {
					change = i;
				}
			}
			
			for(int i = change; i < correctlen; i++) {
				if(wrongWord[i + 1] != rightWord[i]) {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
