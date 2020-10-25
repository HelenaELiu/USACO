import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class fencedin {
	static int numRows, numCols, v;
	static int[] vert, horiz, parent, size;
	static ArrayList<Edge> edges;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		numCols = Integer.parseInt(st.nextToken()) + 1;
		numRows = Integer.parseInt(st.nextToken()) + 1;
		v = numRows * numCols;
		
		vert = new int[numCols];
		for (int i = 1; i < numCols; i++)
			vert[i] = Integer.parseInt(f.readLine());
		Arrays.sort(vert);
		for (int i = 0; i < numCols - 1; i++)
			vert[i] = vert[i + 1] - vert[i];
		vert[numCols - 1] = a - vert[numCols - 1];
		
		horiz = new int[numRows];
		for (int i = 1; i < numRows; i++)
			horiz[i] = Integer.parseInt(f.readLine());
		Arrays.sort(horiz);
		for (int i = 0; i < numRows - 1; i++)
			horiz[i] = horiz[i + 1] - horiz[i];
		horiz[numRows - 1] = b - horiz[numRows - 1];

		edges = new ArrayList<Edge>();
		getEdges();
		Collections.sort(edges);
		
		//for (Edge e : edges)
		//	System.out.println(e.src + " " + e.dest + " " + e.weight);
		
		long ans = kruskal();
		System.out.println(ans);
		out.println(ans);
		
		out.close();
	}

	private static void getEdges() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				for (int k = 0; k < 2; k++) {
					int row = i + dx[k];
					int col = j + dy[k];
					if (row >= 0 && row < numRows && col >= 0 && col < numCols) {// inbounds
						if (j == col) { //case up or down
							edges.add(new Edge(i * numCols + j, row * numCols + col, vert[col]));
						} else if (i == row) { //case left or right
							edges.add(new Edge(i * numCols + j, row * numCols + col, horiz[row]));
						}					
					}
				}
			}
		}
	}

	private static long kruskal() {
		long sum = 0;

		parent = new int[v];
		for (int i = 0; i < v; i++)
			parent[i] = i;

		size = new int[v];
		Arrays.fill(size, 1);

		for (int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			//System.out.println(recurParent(e.src) + " " + recurParent(e.dest));
			
			if (recurParent(e.src) != recurParent(e.dest)) {
				//System.out.println(size[parent[e.src]] + " " + size[parent[e.dest]]);
				union(e.src, e.dest);
				recurParent(e.src);
				recurParent(e.dest);
				//System.out.println(e.weight);
				sum += (long) e.weight;

				if (size[parent[e.src]] == v || size[parent[e.dest]] == v) {
					break;
				}
			}
		}
		
		//for (int i: parent)
		//	System.out.print(i + " ");
		//System.out.println();

		return sum;
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

class Edge implements Comparable<Edge> {
	int src, dest, weight; 

	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
}