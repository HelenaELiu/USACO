
/*
ID: helena.6
LANG: JAVA
TASK: reststops
*/

import java.io.*;
import java.util.StringTokenizer;

public class reststops {
	static int l, n, rf, rb;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		rf = Integer.parseInt(st.nextToken());
		rb = Integer.parseInt(st.nextToken());
		
		RestStop[] rs = new RestStop[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			rs[i] = new RestStop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		boolean[] maxes = new boolean[n];
		
		int max = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (rs[i].tasty > max) {
				maxes[i] = true;
				max = rs[i].tasty;
			}
		}
		
		long totalTasty = 0;
		long tf = 0;
		long tb = 0;
		int prevLoc = 0;
		
		for (int i = 0; i < n; i++) {
			if (maxes[i]) {
				tf += ((rs[i].loc - prevLoc) * (long) rf);
				tb += ((rs[i].loc - prevLoc) * (long) rb);
				totalTasty += (tf - tb) * (long) rs[i].tasty;
				tb = tf;
				prevLoc = rs[i].loc;
			}
		}
		
		out.println(totalTasty);
		out.close();
	}
}

class RestStop {
	public int loc, tasty;

	public RestStop(int loc, int tasty) {
	    this.loc = loc;
	    this.tasty = tasty;
	}

}