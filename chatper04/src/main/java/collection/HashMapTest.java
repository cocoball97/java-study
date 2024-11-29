package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		Map<String, Integer> m = new HashMap<>();
		
		m.put("one", 1); // auto boxing
		m.put("two", 2);
		m.put("three", 3);
		
		int i = m.get("one"); // auto unboxing
		int j = m.get(new String("two"));
		System.out.println(i+":"+j);
		
		// 값을 덮어씀
		m.put("three", 3333);
		System.out.println(m.get("three"));
		
		// 순회
		Set<String> s = m.keySet();
		for(String key : s) {
			System.out.println(m.get(key));
		}
	}

}
