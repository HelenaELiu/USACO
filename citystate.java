
/*
ID: helena.6
LANG: JAVA
TASK: citystate
*/

import java.io.*;
import java.util.*;

public class citystate {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("citystate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		n = Integer.parseInt(f.readLine());
		
		Map<String, Long> map = new HashMap<String, Long>(); //long because 200,000^2 is too great for an int
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			String city = st.nextToken().substring(0, 2);
			String state = st.nextToken();
			
			if(!city.equals(state)) { //if its equal, then they will be from same state. must be from different states
				String code = city + state;
				if(!map.containsKey(code))
					map.put(code, 0L); 
				map.put(code, map.get(code) + 1);
			}
		}
		
		long count = 0;
		for(String key: map.keySet()) {
			String otherKey = key.substring(2) + key.substring(0, 2);
			if(map.containsKey(otherKey)) {
				count += map.get(key) * map.get(otherKey);
			}
		}
		
		out.println(count / 2); //account for double counting
		out.close();
	}
}
