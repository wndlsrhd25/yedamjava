package com.yedam.java.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StringTest {
	public static void main(String[] args) {
		
		/* 인덱스가 따로 존재하지 않기 때문에 iterator를 사용
		 * map - key&value에 저장, key중복x, value 중복o
		 * key에 같은 값이 들어오면 value에 값을 덮어씀
		 */
	Map<String, Integer> map = new HashMap<String, Integer>();
	
	//put - 값을 집어넣는 역할
	map.put("신윤권", 85);
	map.put("홍길동", 90);
	map.put("동장군", 80);
	
	// entry - 두개의 클래스가 한꺼번에 들어가있다고 생각하면 됨
	System.out.println("총 Entry 수 : "+ map.size());
	
	Integer returnVal = map.put("홍길동",100);
	
	//기존값을 체크해야함, 새로운 값을 집어넣을 순 있지만 기존값을 대체할 수는 없음
	if(returnVal != null) {
		System.out.println("\t기존 값 "+ returnVal);
	}
	System.out.println("\t홍길동 : " + map.get("홍길동"));
	System.out.println();
	
	//set에 넣어서 key값을 하나씩 꺼내서 값을 확인 하는것
	Set<String> KeySet = map.keySet();
	Iterator<String> KeyIterator = KeySet.iterator();
	
	while(KeyIterator.hasNext()) {
		String Key = KeyIterator.next();
		Integer value = map.get(Key);
		System.out.println("\t" + Key + " : "+ value);
	}
	
	System.out.println();
	for(String Key : KeySet) {
		Integer value = map.get(Key);
		System.out.println("\t" + Key + " : "+ value);
	}
	
	System.out.println();
	
	map.remove("홍길동");
	
	//key랑 value 한쌍을 entry라고 하는데 entry를 담을 set을 만든것
	Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
	Iterator<Map.Entry<String,Integer>> entryIterator = entrySet.iterator();
	while(entryIterator.hasNext()) {
		Map.Entry<String, Integer> entry = entryIterator.next();
		
		String Key = entry.getKey();
		Integer value = entry.getValue();
		System.out.println("\t" + Key + " : "+ value);
		
	}
	System.out.println();
	for(Map.Entry<String, Integer> entry : entrySet) {
		String Key = entry.getKey();
		Integer value = entry.getValue();
		System.out.println("\t" + Key + " : "+ value);
		
	}
	}
	
}
