
/*
ID: helena.6
LANG: JAVA
TASK: homework
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class homework {
	static int n;
	static int[] scores;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("homework.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		
		n = Integer.parseInt(f.readLine());
		scores = new int[n];
		int sum = 0;
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
			sum += scores[i];
		}
				
		int[] mins = new int[n]; //at index i is the smallest score from i to n
		int min = n-1; //index of smallest
		mins[n-1] = scores[n-1];
		
		for (int i = n - 2; i >= 0; i--) { //store the smallest from back to front
			if (scores[i] < scores[min]) {
				mins[i] = scores[i];
				min = i;
			} else {
				mins[i] = scores[min];
			}
		}
		
		double maxScore = -1.0; //largest score
		List<Integer> maxKs = new ArrayList<Integer>();
		sum -= scores[0]; //cow is always eating at least one problem
		
		for (int i = 1; i <= n - 2; i++) { // calculate from (inclusive) 1 onward, 2 onward, 3 onward, etc.
			double score = (double) (sum - mins[i])/(n - i - 1);

			if (score >= maxScore) {
				if (score > maxScore)
					maxKs.clear();
				maxScore = score;
				maxKs.add(i);
			}
			sum -= scores[i];
		}
		
		for (Integer i: maxKs)
			out.println(i);
		
		out.close();
	}
}
