package smartlibrary;

public class NetworkFlow {
	public static int maxFlow(int[][] cap, int s, int t) {
		int n = cap.length;
		int[][] r = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				r[i][j] = cap[i][j];
		int flow = 0;
		int[] p = new int[n];
		while (bfs(r, s, t, p)) {
			int f = Integer.MAX_VALUE;
			for (int v = t; v != s; v = p[v])
				f = Math.min(f, r[p[v]][v]);
			for (int v = t; v != s; v = p[v]) {
				r[p[v]][v] -= f;
				r[v][p[v]] += f;
			}
			flow += f;
		}
		return flow;
	}

	static boolean bfs(int[][] r, int s, int t, int[] p) {
		int n = r.length;
		boolean[] seen = new boolean[n];
		int[] q = new int[n];
		int h = 0, z = 0;
		q[z++] = s;
		seen[s] = true;
		while (h < z) {
			int u = q[h++];
			for (int v = 0; v < n; v++)
				if (!seen[v] && r[u][v] > 0) {
					seen[v] = true;
					p[v] = u;
					q[z++] = v;
					if (v == t)
						return true;
				}
		}
		return false;
	}
}