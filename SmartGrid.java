
import java.util.*;

class Edge {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class DSU {
    int[] parent;

    DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        parent[find(a)] = find(b);
    }
}

public class SmartGrid {

    static void BFS(List<List<int[]>> g, int start) {
        boolean[] vis = new boolean[g.size()];
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        vis[start] = true;

        System.out.print("BFS: ");

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int[] nei : g.get(node)) {
                if (!vis[nei[0]]) {
                    vis[nei[0]] = true;
                    q.add(nei[0]);
                }
            }
        }
        System.out.println();
    }

    static void DFS(List<List<int[]>> g, int node, boolean[] vis) {
        vis[node] = true;
        System.out.print(node + " ");

        for (int[] nei : g.get(node)) {
            if (!vis[nei[0]])
                DFS(g, nei[0], vis);
        }
    }

    public static void main(String[] args) {

        int n = 4;
        List<List<int[]>> g = new ArrayList<>();

        for (int i = 0; i < n; i++)
            g.add(new ArrayList<>());

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        for (Edge e : edges) {
            g.get(e.u).add(new int[]{e.v, e.w});
            g.get(e.v).add(new int[]{e.u, e.w});
        }

        BFS(g, 0);

        boolean[] vis = new boolean[n];
        System.out.print("DFS: ");
        DFS(g, 0, vis);
        System.out.println();

        Collections.sort(edges, Comparator.comparingInt(e -> e.w));

        DSU dsu = new DSU(n);
        int cost = 0;

        System.out.println("MST (Kruskal):");

        for (Edge e : edges) {
            if (dsu.find(e.u) != dsu.find(e.v)) {
                dsu.union(e.u, e.v);
                cost += e.w;
                System.out.println(e.u + "-" + e.v + " : " + e.w);
            }
        }

        System.out.println("Total Cost = " + cost);
    }
}
