import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class closing {
	static int[] parent, size;
	static ArrayList<Edge2> edges;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] adj = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		
		/*for (int i = 0; i < n; i++) {
			System.out.print(i + ": ");
			for (Integer j: adj[i])
				System.out.print(j + " ");
			System.out.println();
		}*/
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(f.readLine()) - 1;
		
		boolean[] open = new boolean[n];
		open[arr[n - 1]] = true;
		String[] output = new String[n];
		output[n - 1] = "YES";
		int count = 1;
		
		parent = new int[n];
		for (int i = 0; i < n; i++)
			parent[i] = i;

		size = new int[n];
		Arrays.fill(size, 1);
		
		for (int i = n - 2; i >= 0; i--) {
			int barn = arr[i];
			open[barn] = true;
			count++;
			boolean connects = false;
			
			for (Integer barn2: adj[barn]) {
				if (open[barn2] && recurParent(barn) != recurParent(barn2)) {
					union(barn, barn2);
					
					if (count == Math.max(size[parent[barn]], size[parent[barn2]]))
						connects = true;
				}
			}
			
			if (connects)
				output[i] = "YES";
			else
				output[i] = "NO";
		}
		
		for (String s: output)
			out.println(s);
		out.close();
	}
	
	private static void union(int src, int dest) {
		if (size[parent[src]] > size[parent[dest]]) {
			size[parent[src]] += size[parent[dest]];
			parent[parent[dest]] = parent[src]; 
		} else {
			size[parent[dest]] += size[parent[src]];
			parent[parent[src]] = parent[dest];
		}
	}
	
	private static int recurParent(int node) {
		if (parent[node] == node)
			return node;

		parent[node] = recurParent(parent[node]);
		return parent[node];
	}
}

class Edge2 implements Comparable<Edge2> {
	int src, dest, weight;

	public Edge2(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge2 other) {
		return this.weight - other.weight;
	}
}