
/*
ID: helena.6
LANG: JAVA
TASK: lifeguards
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lifeguards {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		
		n = Integer.parseInt(f.readLine());
		Interval[] timeIntervals = new Interval[2*n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			timeIntervals[2*i] = new Interval(Integer.parseInt(st.nextToken()), i);
			timeIntervals[2*i+1] = new Interval(Integer.parseInt(st.nextToken()), i);
		}

		Arrays.sort(timeIntervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval x, Interval y) {
				return x.getTime() - y.getTime();
			}
		});
		
		TreeSet<Integer> set = new TreeSet<Integer>(); //treeset to keep intervals sorted
		int fullTime = 0; //total amount of time covered by all cows together, none fired
		int[] alone = new int[n]; //amount of time they cover that isn't covered by anyone else
		int prev = 0;
		
		for (Interval i: timeIntervals) {
			if (set.size() == 1)
				alone[set.first()] += (i.getTime() - prev);
			if (!set.isEmpty())
				fullTime += i.getTime() - prev;
			
			if (set.contains(i.getIndex())) //start and end have been handled. remove it
				set.remove(i.getIndex());
			else 
				set.add(i.getIndex());
			
			prev = i.getTime();
		}
		
		int maxTime = 0;
		for (int i: alone)
			maxTime = Math.max(maxTime, fullTime - i);
		
		out.println(maxTime);
		out.close();
	}
}

class Interval {
	private int time;
	private int index;

	public Interval(int time, int index) {
		this.time = time;
		this.index = index;
	}

	public int getTime() {
		return time;
	}

	public int getIndex() {
		return index;
	}
}