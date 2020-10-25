import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class cardgame {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		int n = Integer.parseInt(f.readLine());
		ArrayList<Integer> elsie = new ArrayList<Integer>();
		HashSet<Integer> set = new HashSet<Integer>();
		
		for (int i = 0; i < n; i++) {
			int card = Integer.parseInt(f.readLine());
			elsie.add(card);
			set.add(card);
		}
		
		ArrayList<Integer> bessie = new ArrayList<Integer>();
		for (int i = 1; i <= 2 * n; i++) {
			if (!set.contains(i))
				bessie.add(i);
		}

		ArrayList<Integer> higherElsie = new ArrayList<Integer>();
		ArrayList<Integer> lowerElsie = new ArrayList<Integer>();
		for (int i = 0; i < n / 2; i++)
			higherElsie.add(elsie.get(i));
		for (int i = n / 2; i < n; i++)
			lowerElsie.add(elsie.get(i));
		Collections.sort(higherElsie);
		Collections.sort(lowerElsie);
		Collections.reverse(lowerElsie);

		ArrayList<Integer> higherBessie = new ArrayList<Integer>();
		ArrayList<Integer> lowerBessie = new ArrayList<Integer>();
		for (int i = n / 2; i < n; i++)
			higherBessie.add(bessie.get(i));
		for (int i = 0; i < n / 2; i++)
			lowerBessie.add(bessie.get(i));
		Collections.reverse(lowerBessie);

		int ans = 0;
		while (!higherBessie.isEmpty()) {
			if (higherBessie.get(0) > higherElsie.get(0)) {
				ans++;
				higherElsie.remove(0);
			}
			
			higherBessie.remove(0);
		}
		
		while (!lowerBessie.isEmpty()) {
			if (lowerBessie.get(0) < lowerElsie.get(0)) {
				ans++;
				lowerElsie.remove(0);
			}
			
			lowerBessie.remove(0);
		}

		//System.out.println(ans);
		out.println(ans);
		out.close();
	}
}