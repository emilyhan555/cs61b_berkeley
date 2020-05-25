public class LinkedListDeque<T> implements Deque<T>{
    /** Nested class IntNode     */
    private class TNode{
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T i, TNode p,TNode n){
            item = i;
            prev = p;
            next = n;
        }
    }

    /** class LinkedListDeque has 2 variables. */
    private TNode sentinel;
    private int size;

    /** Create an empty list. */
    public LinkedListDeque(){
        sentinel = new TNode((T) "any", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Constructor
     *  Create a list with a first item
     */
    public LinkedListDeque(T item){
        sentinel = new TNode((T) "any", null, null);
        sentinel.next = new TNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
    }

    /** Create an entirely new LinkedListDeque with the exact same items as other. */
    public LinkedListDeque(LinkedListDeque other){
        TNode p = other.sentinel.next;
        sentinel = new TNode((T) "any", null, null);
        sentinel.next = new TNode(p.item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
        p = p.next;
        for(int i = 1; i < other.size(); i++) {
            addLast(p.item);
            p = p.next;
        }
    }

    /** Add an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item){
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(T item){
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
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
        TNode p = sentinel.next;
        for (int i = size; i > 0; i--){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }else{
            T remove = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return remove;
        }

    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }else{
            T remove = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return remove;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index){
        if (size == 0 || index > size - 1){
            return null;
        }else{
            TNode p = sentinel;
                for(int i = 0; i <= index; i++){
                    p = p.next;
                }
                return p.item;
        }
    }

    /** Get method. Recursively!
     */


    /*public static void main(String[] args){

        LinkedListDeque<Integer> Empty = new LinkedListDeque<>();
        System.out.println(Empty.isEmpty());
        System.out.println(Empty.removeFirst());
        System.out.println(Empty.removeLast());
        System.out.println(Empty.get(0));


        LinkedListDeque<String> L = new LinkedListDeque<>("cat");
        L.addFirst("dog");
        L.addLast("bird");
        L.addLast("deer");
        System.out.println(L.isEmpty());
        System.out.println(L.size());
        L.printDeque();
        //System.out.println(L.removeFirst());
        //System.out.println(L.removeLast());
        System.out.println(L.get(10));
        System.out.println(L.get(2));
        System.out.println(L.get(3));

        LinkedListDeque copy = new LinkedListDeque(L);
    }
    */

}
