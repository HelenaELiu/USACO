import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class gpsduel {
	static int n, m;

	@SuppressWarnings("unchecked") 
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("gpsduel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gpsduel.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		ArrayList<Vertex>[] adj1 = new ArrayList[n]; 
		for (int i = 0; i < n; i++)
			adj1[i] = new ArrayList<>(); 

		ArrayList<Vertex>[] adj2 = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj2[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) { 
			st = new StringTokenizer(f.readLine());
			int ai = Integer.parseInt(st.nextToken()) - 1;
			int bi = Integer.parseInt(st.nextToken()) - 1;
			int pi = Integer.parseInt(st.nextToken());
			int qi = Integer.parseInt(st.nextToken());
			adj1[bi].add(new Vertex(ai, pi)); // reversing edges
			adj2[bi].add(new Vertex(ai, qi)); // reversing edges
		}

		int[] dist1 = dijkstra(adj1, n - 1);
		int[] dist2 = dijkstra(adj2, n - 1);

		ArrayList<Vertex>[] adj3 = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj3[i] = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < adj1[i].size(); j++) {
				int count = 0;
				Vertex v = adj1[i].get(j);
				Vertex v2 = adj2[i].get(j);
				int dest = v.id;
				int weight1 = v.weight;
				int weight2 = v2.weight;

				if (dist1[dest] - dist1[i] != weight1)
					count++;
				if (dist2[dest] - dist2[i] != weight2)
					count++;
				adj3[dest].add(new Vertex(i, count));
			}
		}

		int[] dist3 = dijkstra(adj3, 0);
		out.println(dist3[n - 1]);
		out.close();
	}

	public static int[] dijkstra(ArrayList<Vertex>[] adj, int src) {
		boolean[] visited = new boolean[n];
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Vertex> q = new PriorityQueue<>(n);
		q.add(new Vertex(src, 0));
		dist[src] = 0;

		while (!q.isEmpty()) {
			int u = q.poll().id;
			if (!visited[u]) {
				visited[u] = true;

				for (Vertex v : adj[u]) { // for all edges from u -> v
					if (!visited[v.id]) {
						if (dist[u] != Integer.MAX_VALUE && dist[u] + v.weight < dist[v.id]) {
							dist[v.id] = dist[u] + v.weight;
							q.add(new Vertex(v.id, dist[v.id]));
						}
					}
				}
			}
		}

		return dist;
	}
}

class Vertex implements Comparable<Vertex> {
	int id, weight;

	public Vertex(int id, int weight) {
		this.id = id;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex v) {
		return Integer.compare(weight, v.weight);
	}
}