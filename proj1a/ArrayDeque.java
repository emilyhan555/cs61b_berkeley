public class ArrayDeque<T>{
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

    /** change the index once add an item or delete an item. */

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item){
        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        if (nextFirst == 0){
            nextFirst = items.length -1;
        }else{
            nextFirst = nextFirst -1;
        }
        size += 1;
    }

    /** Add an item of type T to the back of the deque. */
    public void addLast(T item){
        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextLast] = item;
        if(nextLast == items.length-1){
            nextLast = 0;
        }else {
            nextLast = nextLast + 1;
        }
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else {
            return false;
        }
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    public void printDeque(){
        if (nextLast-nextFirst==1) {
            for (int i = nextLast; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextFirst+1; i++){
                System.out.print(items[i] + " ");
            }
        } else if (nextFirst-nextLast== items.length -1) {
            for (int i = 0; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
        } else if (nextFirst - nextLast >= 1 || (nextFirst==nextLast && nextFirst!= 0 && nextFirst != items.length -1)) {
            for (int i = nextFirst + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(items[i] + " ");
            }
        } else if (nextLast - nextFirst > 1){
            for (int i = nextFirst+1; i < nextLast; i++){
                System.out.print(items[i] + " ");
            }
        } else if (nextFirst == nextLast) {
            if (nextFirst == 0) {
                for (int i = nextFirst + 1; i < items.length; i++) {
                    System.out.print(items[i] + " ");
                }
            } else if (nextFirst == items.length-1) {
                for (int i = 0; i < nextLast; i++) {
                    System.out.print(items[i] + " ");
                }
            }
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst(){
        T remove;
        T[] temp = (T[]) new Object[items.length - 1];
        if(size == 0){
            return null;
        }else{
            if(nextLast-nextFirst==1 && nextLast== items.length-1){
                remove = items[nextFirst+1];
                System.arraycopy(items, 0, temp, 0, nextFirst+1);
                nextLast=0;
            }else if (nextLast - nextFirst > 1 && nextFirst==0){
                remove = items[nextFirst+1];
                temp[0] = null;
                System.arraycopy(items, nextFirst+2, temp, nextFirst+1, nextLast - (nextFirst+1)-1);
                nextLast--;
            }else if(nextFirst-nextLast== items.length-1){
                remove = items[nextLast];
                System.arraycopy(items, nextLast+1, temp, 0, items.length-1);
                nextFirst--;
            }else if(nextFirst== items.length-1 && nextFirst-nextLast!= items.length-1){
                    remove = items[0];
                    System.arraycopy(items, 1,temp,0, items.length-1);
                    nextFirst--;
                    nextLast--;
            }else{
                    remove = items[nextFirst+1];
                    System.arraycopy(items, 0, temp, 0, nextFirst+1);
                    System.arraycopy(items, nextFirst+2,temp,nextFirst+1, items.length-(nextFirst+1)-1);
            }
        }
        items = temp;
        size--;
        return remove;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast(){
        T remove;
        T[] temp = (T[]) new Object[items.length - 1];
        if (size == 0){
            return null;
        }else{
            if(nextFirst==0 && nextLast==1){
                remove = items[0];
                System.arraycopy(items,1,temp,0,items.length-1);
                nextFirst= items.length-1-1;
                nextLast=0;
            }else if(nextFirst==0 && nextLast>1){
                remove = items[nextLast-1];
                System.arraycopy(items,0, temp, 0,nextLast-1);
                nextLast--;
            }else if(nextLast==0 && nextFirst == items.length-1){
                remove = items[items.length-1];
                System.arraycopy(items, 0, temp, 0, items.length-1);
                nextFirst--;
            }else if(nextFirst==nextLast && nextFirst==0){
                remove = items[items.length-1];
                System.arraycopy(items,0,temp,0,items.length-1);
            }else{
                remove = items[nextLast - 1];
                if(nextLast-nextFirst<=1 || nextLast<nextFirst){
                    System.arraycopy(items, 0, temp, 0, nextLast - 1);
                    System.arraycopy(items, nextLast, temp, nextLast - 1, items.length - nextLast);
                }
                else{
                    System.arraycopy(items,0,temp,0,nextLast-1);
                }
                nextFirst--;
                nextLast--;
            }
        }
        items = temp;
        size--;
        return remove;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index){
        if (size == 0){
            return null;
        }else{
            return items[index];
        }
    }

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

}
