public class UnionFind {

    private int[] uf;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        uf = new int[n];
        for (int i = 0; i < n; i++) {
            uf[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= uf.length) {
            throw new IllegalArgumentException("Please enter a number between 0 and " + (uf.length -1));
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -uf[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return uf[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (v1 == v2) {
            return;
        }
        if (find(v1) == find(v2)){
            return;
        }
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        int sizeV1 = sizeOf(v1);
        int sizeV2 = sizeOf(v2);
        if (sizeOf(v1) <= sizeOf(v2)) {
            uf[rootV1] = find(v2);
            uf[rootV2] = -(sizeV1 + sizeV2);
        } else {
            uf[rootV2] = find(v1);
            uf[rootV1] = -(sizeV1 + sizeV2);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        while (uf[vertex] >= 0) {
            vertex = parent(vertex);
        }
        return vertex;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(8);
        uf.union(2, 4);
        uf.union(3, 7);
        uf.union(3, 5);
        uf.union(2, 5);
        System.out.println(uf.connected(2, 3));
        System.out.println(uf.sizeOf(3));
        System.out.println(uf.parent(2));
    }
}
