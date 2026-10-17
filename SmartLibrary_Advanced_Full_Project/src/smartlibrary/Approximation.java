package smartlibrary;

public class Approximation {
	public static boolean[] vertexCover2Approx(boolean[][] e) {
		int n = e.length;
		boolean[] c = new boolean[n];
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (e[i][j] && !c[i] && !c[j]) {
					c[i] = true;
					c[j] = true;
				}
		return c;
	}
}