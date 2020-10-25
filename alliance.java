import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class alliance {
	static int n, m;
	static long mod = 1000000007L;
	static ArrayList<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("alliance.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("alliance.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(f.readLine());
			int ui = Integer.parseInt(st.nextToken()) - 1;
			int vi = Integer.parseInt(st.nextToken()) - 1;
			adj[ui].add(vi);
			adj[vi].add(ui);
		}

		long ans = cc();
		out.println(ans);
		out.close();
	}

	private static long cc() {
		boolean[] visited = new boolean[n];
		long ans = 1;

		for (int i = 0; i < n; i++) {
			if (!visited[i]) { // new component
				int numNodes = 0;
				int numEdges = 0;
				Queue<Integer> q = new LinkedList<Integer>();
				q.add(i);

				while (!q.isEmpty()) {
					int u = q.poll();

					if (!visited[u]) {
						visited[u] = true;
						numNodes++;

						for (int v : adj[u]) {
							if (!visited[v]) {
								numEdges++;
								q.add(v);
							}
						}
					}
				}
				
				if (numEdges == numNodes - 1) {
					ans = (long) ((ans % mod) * (numNodes % mod)) % mod;
				} else if (numEdges == numNodes) {
					ans = (long) ((ans % mod) * 2) % mod;
				} else {
					ans = 0;
					break;
				}
					
			}
		}
		
		return ans;
	}
}