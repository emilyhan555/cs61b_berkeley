public class UnionFind {
    private int[] array;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > array.length - 1) {
            throw new IllegalArgumentException("It is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = find(v1);
        return -(parent(root));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return array[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (sizeOf(v1) == sizeOf(v2)) {
            array[root2] -= sizeOf(v1);
            array[root1] = root2;
        } else if (sizeOf(v1) < sizeOf(v2)){
            array[root2] -= sizeOf(v1);
            array[root1] = root2;
        } else if (sizeOf(v1) > sizeOf(v2)) {
            array[root1] -= sizeOf(v2);
            array[root2] = root1;

        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int root = vertex;
        while (parent(root) > 0) {
            root = parent(root);
        }
        return root;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(1,2);
        uf.union(3,4);
        uf.union(2,6);
        uf.union(3,6);
        uf.union(7,8);
        System.out.println(uf.connected(5,3));
        System.out.println(uf.sizeOf(2));
        uf.union(8,9);
        uf.union(9,0);
        uf.union(5,8);
        uf.union(2,9);
        System.out.println(uf.find(8));
        System.out.println(uf.sizeOf(8));
        System.out.println(uf.parent(5));
        uf.validate(10);
    }
}
