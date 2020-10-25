import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class route {
	static int n, m, r;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("route.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("route.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		int[] leftVal = new int[n];
		for (int i = 0; i < n; i++) {
			int val = Integer.parseInt(f.readLine());
			leftVal[i] = val;
		}
		
		int[] rightVal = new int[m];
		for (int i = 0; i < m; i++) {
			int val = Integer.parseInt(f.readLine());
			rightVal[i] = val;
		}
		
		Edge5[] edges = new Edge5[r];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(f.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			edges[i] = new Edge5(left, right);
		}
		Arrays.sort(edges);
		
		int[] leftdp = new int[n];
		int[] rightdp = new int[m];
		
		for (int i = 0; i < n; i++)
			leftdp[i] = leftVal[i];
		
		for (int i = 0; i < m; i++)
			rightdp[i] = rightVal[i];
		
		for (int i = 0; i < r; i++) {
			Edge5 e = edges[i];
			int leftOrig = leftdp[e.left];
			int rightOrig = rightdp[e.right];
			leftdp[e.left] = Math.max(leftdp[e.left], rightOrig + leftVal[e.left]);
			rightdp[e.right] = Math.max(rightdp[e.right], leftOrig + rightVal[e.right]);
		}
		
		int ans = 0;
		
		for (int i = 0; i < n; i++)
			ans = Math.max(ans, leftdp[i]);
		
		for (int i = 0; i < m; i++)
			ans = Math.max(ans, rightdp[i]);
		
		out.println(ans);
		out.close();
	}
}

class Edge5 implements Comparable<Edge5> {
	int left, right;

	public Edge5(int left, int right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int compareTo(Edge5 other) {
		if (this.left == other.left)
			return this.right - other.right;
		return this.left - other.left;
	}
}