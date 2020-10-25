
/*
ID: helena.6
LANG: JAVA
TASK: mootube
*/

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class mootube {
	static int n, q;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		LinkedList<Edge>[] edges = new LinkedList[n];
		for(int i = 0; i < n; i++)
			edges[i] = new LinkedList<Edge>();
		
		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			int p = Integer.parseInt(st.nextToken())-1;
			int q = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken());
			edges[p].add(new Edge(q, r));
			edges[q].add(new Edge(p, r));
		}
		
		for(int query = 0; query < q; query++) {
			st = new StringTokenizer(f.readLine());
			int threshold = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken())-1;
			int ret = 0;
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(start);
			boolean[] seen = new boolean[n];
			seen[start] = true;
			while(!queue.isEmpty()) {
				int curr = queue.removeFirst();
				for(Edge e: edges[curr]) {
					if(!seen[e.getDest()] && e.getWeight() >= threshold) {
						seen[e.getDest()] = true;
						queue.add(e.getDest());
						ret++;
					}
				}
			}
			out.println(ret);
		}
		
		out.close();
	}
}

class Edge {
	private int dest;
	private int weight;
	
	public Edge(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}
	
	public int getDest() {
		return dest;
	}

	public int getWeight() {
		return weight;
	}
}
