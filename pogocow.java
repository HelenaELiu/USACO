import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pogocow {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("Y.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Y.out")));
		int n = Integer.parseInt(f.readLine());
		Target[] targets = new Target[n];
		StringTokenizer st;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			int xi = Integer.parseInt(st.nextToken());
			int pi = Integer.parseInt(st.nextToken());
			targets[i] = new Target(xi, pi);
		}
		Arrays.sort(targets); //change this to collections if TLE
		
		//for (Target t: targets)
		//	System.out.println(t.x + " " + t.val);
		
		int ans = -1;
		int[][] dp = new int[n][n]; //curr idx + prev idx
		
		for (int i = 0; i < n; i++) {
			dp[i][i] = targets[i].val;
			ans = Math.max(ans, targets[i].val);
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				int jumpSize = targets[i].x - targets[j].x;
				for (int k = 0; k <= j; k++) {
					if (targets[j].x - targets[k].x <= jumpSize) {
						dp[i][j] = Math.max(dp[i][j], dp[j][k] + targets[i].val);
						ans = Math.max(ans, dp[i][j]);
						//System.out.println(ans);
					}
				}
			}
		}
		
		int[][] dp2 = new int[n][n]; //curr idx + prev idx
		
		for (int i = 0; i < n; i++) {
			dp2[i][i] = targets[i].val;
			ans = Math.max(ans, targets[i].val);
		}
		
		for (int i = n - 2; i >= 0; i--) {
			for (int j = n - 1; j > i; j--) {
				int jumpSize = targets[j].x - targets[i].x;
				for (int k = n - 1; k >= j; k--) {
					if (targets[k].x - targets[j].x <= jumpSize) {
						dp2[i][j] = Math.max(dp2[i][j], dp2[j][k] + targets[i].val);
						ans = Math.max(ans, dp2[i][j]);
						//System.out.println(ans); 
					}
				}
			}
		}
		
		out.println(ans);
		out.close();
	}
}

class Target implements Comparable<Target> {
	int x, val;
	
	public Target(int x, int val) {
		this.x = x;
		this.val = val;
	}

	@Override
	public int compareTo(Target other) {
		return this.x - other.x;
	}
}