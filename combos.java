import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class combos {
	static int max = 301; //N * length + 1 = 20 * 15 + 1

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("combos.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combos.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int total = 1;
		int[][] arr = new int[max][3];
		int[] points = new int[max];		

		for (int i = 0; i < n; i++) {
			String str = f.readLine();
			int idx = 0;
			
			for (int j = 0; j < str.length(); j++) {
				int m = (int) (str.charAt(j) - 'A');
				if (arr[idx][m] == 0) {
					arr[idx][m] = total;
					total++;
				}
				
				idx = arr[idx][m];
			}

			points[idx]++;
			//System.out.println(idx);
		}
		
		//for (int i = 0; i < max; i++)
		//	System.out.print(points[i] + " ");
		//System.out.println();

		Queue<Integer> queue = new LinkedList<Integer>();
		int[] works = new int[max];
		for (int i = 0; i < 3; i++) {
			if (arr[0][i] != 0)
				queue.add(arr[0][i]);
		}

		while (!queue.isEmpty()) {
			int u = queue.poll();

			for (int i = 0; i < 3; i++) {
				if (arr[u][i] == 0) {
					arr[u][i] = arr[works[u]][i];
				} else {
					works[arr[u][i]] = arr[works[u]][i];
					queue.add(arr[u][i]);
				}
			}

			points[u] += points[works[u]];
		}

		int[][] dp = new int[k + 1][max];
		for (int i = 0; i <= k; i++)
			Arrays.fill(dp[i], -Integer.MAX_VALUE);
		for (int i = 0; i <= k; i++)
			dp[i][0] = 0;

		int ans = 0;
		//System.out.println(total);

		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < total; j++) {
				for (int m = 0; m < 3; m++) {
					int child = arr[j][m];
					if (child != 0)
						dp[i][child] = Math.max(dp[i][child], dp[i - 1][j] + points[child]);
				}
			}
		}
		
		/*for (int i = 0; i <= k; i++) {
			for (int j = 0; j < total; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}*/

		for (int i = 0; i < total; i++)
			ans = Math.max(ans, dp[k][i]);

		out.println(ans);
		out.close();
	}
}