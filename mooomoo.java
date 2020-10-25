import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mooomoo {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mooomoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int inf = 1000000;
		
		int[] breeds = new int[b]; 
		for (int i = 0; i < b; i++)
			breeds[i] = Integer.parseInt(f.readLine());
		
		int[] carryOver = new int[n]; //volume + carryover
		for (int i = 0; i < n; i++)
			carryOver[i] = Integer.parseInt(f.readLine());
		
		int[] volume = new int[n]; //just volume
		volume[0] = carryOver[0];
		int max = volume[0];
		
		for (int i = 1; i < n; i++) {
			volume[i] = carryOver[i] - Math.max(0, carryOver[i - 1] - 1);
			max = Math.max(max, volume[i]);
		}
		
		//for (int i: volume)
		//	System.out.println(i);
		
		int[] knapsack = new int[100001];
		Arrays.fill(knapsack, inf);
		knapsack[0] = 0;
		
		for (int i = 0; i < b; i++) {
			for (int j = breeds[i]; j <= max; j++)
				knapsack[j] = Math.min(knapsack[j], knapsack[j - breeds[i]] + 1);
		}
		
		//for (int i = 0; i <= 17; i++) 
		//	System.out.println(knapsack[i]);
		
		int numCows = 0;
		for (int i = 0; i < n; i++) {
			if (knapsack[volume[i]] == inf) {
				numCows = -1;
				break;
			} else
				numCows += knapsack[volume[i]];
		}
		
		out.println(numCows);
		out.close();
	}
}