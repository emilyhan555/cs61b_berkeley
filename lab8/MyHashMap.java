import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize;
    private double loadFactor;
    private int size;
    private ArrayList<Entry> array;

    private class Entry {
        private K key;
        private V value;
        private Entry next;

        private Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private Entry get(K key) {
            Entry l = this;
            if (l == null) {return null;}
            while (l != null) {
                if (l.key.equals(key)) {
                    return l;
                } else {
                    l = l.next;
                }
            }
            return null;
        }
    }

    public MyHashMap() {
        initialSize = 16;
        loadFactor = 0.75;
        size = 0;
        array = new ArrayList<>(initialSize);
        array.addAll(Collections.nCopies(initialSize, null));
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        loadFactor = 0.75;
        size = 0;
        array = new ArrayList<>(initialSize);
        array.addAll(Collections.nCopies(initialSize, null));
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        size = 0;
        array = new ArrayList<>(initialSize);
        array.addAll(Collections.nCopies(initialSize, null));
    }

    // Resize the bucket number if actual load factor exceeds the set loadFactor.
    public void resize() {
        initialSize = initialSize * 2;
    }

    @Override
    public void clear() {
        array = new ArrayList<>(initialSize);
        array.addAll(Collections.nCopies(initialSize, null));
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = (0x7fffffff & key.hashCode()) % initialSize;
        Entry list = array.get(index);
        if (list == null) {
            return false;
        }
        return list.get(key) != null;
    }

    @Override
    public V get(K key) {
        int index = (0x7fffffff & key.hashCode()) % initialSize;
        Entry list = array.get(index);
        if (list == null) return null;
        return list.get(key).value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int index = (0x7fffffff & key.hashCode()) % initialSize;
        Entry list = array.get(index);
        if (list == null) {
            list = new Entry(key, value, null);
            array.set(index, list);
        } else {
            while (list.next != null) {
                if (list.key != key) {
                    list = list.next;
                } else {
                    list.value = value;
                    return;
                }
            }
            if (list.key == key) {
                list.value = value;
                return;
            }
            list.next = new Entry(key, value, null);
        }
        size += 1;
        double threshold = (double) size / initialSize;

        if (threshold > loadFactor) {
            Set<K> s = keySet();
            Map<K, V> m = keyMap();
            resize();
            array = new ArrayList<>(initialSize);
            array.addAll(Collections.nCopies(initialSize, null));
            for (K k : s) {
                V val = m.get(k);
                index = (0x7fffffff & k.hashCode()) % initialSize;
                list = array.get(index);
                if (list == null) {
                    list = new Entry(k, val, null);
                    array.set(index, list);
                } else {
                    list.next = new Entry(k, val, null);
                }
            }
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < array.size(); i++) {
            Entry l = array.get(i);
            while (l != null) {
                keys.add(l.key);
                l = l.next;
            }
        }
        return keys;
    }

    public Map<K, V> keyMap() {
        Map<K, V> maps = new HashMap<>();
        for (int i = 0; i < array.size(); i++) {
            Entry l = array.get(i);
            while (l != null) {
                maps.put(l.key, l.value);
                l = l.next;
            }
        }
        return maps;
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
        return new HashIter();
    }

    private class HashIter implements Iterator<K> {
        private int item;
        private int arrayPos;
        private Entry list;

        public HashIter() {
            item = 0;
            arrayPos = 0;
            list = array.get(arrayPos);
        }

        @Override
        public boolean hasNext() {
            return item < size;
        }

        @Override
        public K next() {
            while (list == null) {
                arrayPos += 1;
                list = array.get(arrayPos);
            }
            K returnItem = list.key;
            item += 1;
            list = list.next;
            return returnItem;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, String> myHashmap = new MyHashMap<>();
        myHashmap.put("apple", "72");
        myHashmap.put("pear", "3");
        myHashmap.put("apple","62");
        System.out.println(myHashmap.containsKey("apple"));
        System.out.println(myHashmap.get("pear"));
    }

}
