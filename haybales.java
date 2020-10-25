
/*
ID: helena.6
LANG: JAVA
TASK: haybales
*/

import java.io.*;
import java.util.*;

public class haybales {
	static int n, q;
	static int[] haybales;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		haybales = new int[n];

		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			int h = Integer.parseInt(st.nextToken());
			haybales[i] = h;
		}

		Arrays.sort(haybales);
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int lowIndex = binarySearch(a - 1);
			int highIndex = binarySearch(b);
			out.println(highIndex - lowIndex);
		}

		out.close();
	}

	private static int binarySearch(int target) {
		if (haybales[0] > target)
			return 0;
		
		int start = 0;
		int end = n - 1;
		
		while (start != end) {
			int middle = (start + end + 1) / 2;
			
			if (haybales[middle] <= target)
				start = middle;
			else
				end = middle - 1;
		}
		
		return start + 1;
	}
}
