import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class guard {
	static int n, h;
	static Cow[] cows;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("guard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("guard.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		cows = new Cow[n];
		
		for (int i = 0; i < n; i++) { 
			st = new StringTokenizer(f.readLine());
			int height = Integer.parseInt(st.nextToken()); 
			int weight = Integer.parseInt(st.nextToken());
			int strength = Integer.parseInt(st.nextToken());
			cows[i] = new Cow(height, weight, strength);
		}
		Arrays.sort(cows);
		
		//for (Cow c: cows)
		//	System.out.println(c.height + " " + c.weight + " " + c.strength);
		
		int size = (int) Math.pow(2, n);
		int[] dp = new int[size]; //dp[cows used] = max safety factor
		for (int i = 0; i < n; i++)
			dp[(int) Math.pow(2, i)] = cows[n - i - 1].strength;
		
		//for (int i = 0; i < size; i++)
		//	System.out.println(i + " " + dp[i]);
		
		for (int i = 1; i < size; i++) { //build up
			int num = i;
			for (int j = 0; j < n; j++) {
				if ((num & 1) == 0) {
					int digit = (int) Math.pow(2, j);
					dp[i + digit] = Math.max(dp[i + digit], Math.min(dp[i] - cows[n - j - 1].weight, cows[n - j - 1].strength));
				}

				num = num >> 1;
			}
		}
		
		//for (int i = 0; i < size; i++)
		//	System.out.println(i + " " + dp[i]);
		
		int ans = 0;
		for (int i = 0; i < size; i++) {
			if (tallEnough(i)) {
				ans = Math.max(ans, dp[i]);
			}
		}
		
		if (ans == 0)
			out.println("Mark is too tall");
		else
			out.println(ans);
		out.close();
	}
	
	private static boolean tallEnough(int num) {
		int height = 0;
		
		for (int i = 0; i < n; i++) {
			if ((num & 1) == 1) {
				height += cows[n - i - 1].height;
				if (height >= h)
					return true;
			}
			
			num = num >> 1;
		}

		return false;
	}
}

class Cow implements Comparable<Cow> {
	int height, weight, strength;
	
	public Cow(int height, int weight, int strength) {
		this.height = height;
		this.weight = weight;
		this.strength = strength;
	}

	@Override
	public int compareTo(Cow other) {
		if (this.strength == other.strength)
			return this.weight - other.weight;
		return this.strength - other.strength;
	}
}