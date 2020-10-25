
/*
ID: helena.6
LANG: JAVA
TASK: shuffle
*/

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class shuffle {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

		n = Integer.parseInt(f.readLine());

		int[] start = new int[n]; // zero based
		int[] dest = new int[n]; 
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			dest[i] = Integer.parseInt(st.nextToken()) - 1;
			start[dest[i]]++;
		}
		
		int pos = n;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		for(int i = 0; i < n; i++) {
			if(start[i] == 0) {
				queue.add(i);
				pos--;
			}
		}
		
		while(!queue.isEmpty()) {
			int curr = queue.removeFirst();
			start[dest[curr]]--;
			if(start[dest[curr]] == 0) {
				queue.add(dest[curr]);
				pos--;
			}
		}
		
		out.println(pos);
		out.close();
	}
}
