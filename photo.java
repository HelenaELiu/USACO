import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class photo {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
		final int n = Integer.parseInt(f.readLine());
		final int[] p1 = new int[n];
		final int[] p2 = new int[n];
		final int[] p3 = new int[n];
		final int[] p4 = new int[n];
		final int[] p5 = new int[n];
		ArrayList<Integer> ans = new ArrayList<Integer>();
		final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		final int[][] arr = new int[n][5]; //keeps the index of each number
		
		for (int i = 0; i < n; i++) {
			p1[i] = Integer.parseInt(f.readLine().trim());
			ans.add(p1[i]);
			map.put(p1[i], i);
			arr[i][0] = i;
		}
		
		for (int i = 0; i < n; i++) {
			p2[i] = Integer.parseInt(f.readLine().trim());
			arr[map.get(p2[i])][1] = i;
		}
		
		for (int i = 0; i < n; i++) {
			p3[i] = Integer.parseInt(f.readLine().trim());
			arr[map.get(p3[i])][2] = i;
		}

		for (int i = 0; i < n; i++) {
			p4[i] = Integer.parseInt(f.readLine().trim());
			arr[map.get(p4[i])][3] = i;
		}
		
		for (int i = 0; i < n; i++) {
			p5[i] = Integer.parseInt(f.readLine().trim());
			arr[map.get(p5[i])][4] = i;
		}
		
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < 5; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		} */

		Collections.sort(ans, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				int xid = map.get(x);
				int yid = map.get(y);
				int freqX = 0; //how many times x comes first
				int freqY = 0; //how many times y comes first
				
				for (int i = 0; i < 5; i++) {
					if (arr[xid][i] < arr[yid][i])
						freqX++;
					else
						freqY++;
				}
				
				return freqY - freqX;
			}
		});
		
		for (Integer i: ans)
			out.println(i);
		out.close();
	}
}