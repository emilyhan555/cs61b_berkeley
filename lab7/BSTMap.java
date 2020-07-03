import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left, right;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(key, root);
    }

    public V get(K key, Node T) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (T == null) {
            return null;
        }
        if (key.compareTo(T.key) < 0) {
            return get(key, T.left);
        }
        if (key.compareTo(T.key) > 0) {
            return get(key, T.right);
        }
        else return T.value;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node T) {
        if (T == null) {return 0;}
        else return T.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    public Node put(K key, V value, Node T) {
        if (T == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(T.key) < 0) {
            T.left = put(key, value, T.left);
        } else if (key.compareTo(T.key) > 0) {
            T.right = put(key, value, T.right);
        } else {
            T.value = value;
        }
        T.size = size(T.left) + size(T.right) + 1;
        return T;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        for (int i = 0; i < root.size; i++) {
            System.out.println(i + ": " + select(i) + ": " + get(select(i)));
        }
    }

    // Return the key of a given rank.
    public K select(int rank) {
        if (rank < 0 || rank > size()) {
            throw new IllegalArgumentException();
        }
        return select(rank, root);
    }

    public K select(int rank, Node T) {
        if (T == null) {return null;}
        int leftsize = size(T.left);
        if (leftsize > rank) {
            return select(rank, T.left);
        } else if (leftsize < rank) {
            return select(rank - leftsize - 1, T.right);
        } else {
            return T.key;
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("Cat", 2);
        bst.put("Dog", 5);
        bst.put("Dinasour", 1);
        bst.put("Ant", 7);
        bst.put("Mat", 4);
        bst.printInOrder();
        System.out.println(bst.size());
        System.out.println(bst.get("Dog"));
    }
}
