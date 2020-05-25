public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Create an Empty array. */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items,0,items,0,other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

    /** Return the index of the first item in the array. */
    public int plusOne(int index){
        return (index+1) % items.length;
    }

    /** Return the index of the last item in the array. */
    public int minusOne(int index){
        return (index -1 + items.length) % items.length;
    }

    /** Resize the array. */
    public void resize(int capacity){
        T[] resize = (T[]) new Object[capacity];
        if(nextLast > nextFirst) {
            System.arraycopy(items, 0, resize, 0, nextLast);
            System.arraycopy(items, nextLast, resize, capacity - (items.length - nextLast), items.length - nextLast);
            items = resize;
            nextFirst = capacity - (size - nextLast) - 1;
        }else{
            System.arraycopy(items, 0, resize, 0, items.length);
            nextLast = items.length;
            items = resize;
            nextFirst = resize.length-1;
        }
    }

    /** Upsize the deque. */
    public  void upsize(){
        resize(size * 2);
    }

    /** Downsize the deque. */
    public void downSize(){
        resize(items.length/2);
    }

    /** Add an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item){
        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(T item){
        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else {
            return false;
        }
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque(){
        int first = plusOne(nextFirst);
        for (int i = first; i != nextLast; i = plusOne(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst(){
        T remove;
        if(size == 0){
            return null;
        }
        int firstIndex = plusOne(nextFirst);
        remove = items[firstIndex];
        nextFirst = firstIndex;
        items[firstIndex] = null;
        size--;
        return remove;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast(){
        T remove;
        if (size == 0){
            return null;
        }
        int lastIndex= minusOne(nextLast);
        remove = items[lastIndex];
        nextLast = lastIndex;
        items[lastIndex] = null;
        size--;
        return remove;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index){
        if (size == 0){
            return null;
        }else{
            return items[index];
        }
    }

    /*
    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.isEmpty();
        a.size();
        for (int i = 0; i < 10; i++){
            a.addFirst(20-i);
        }
        for (int i = 0; i < 5; i++){
            a.addLast(21+i);
        }
        //a.resize(20);
        a.printDeque();
        System.out.println(a.removeFirst());
        System.out.println(a.removeLast());
        //System.out.println(a.get(19));
        ArrayDeque copy = new ArrayDeque(a);
    }
    */
}
