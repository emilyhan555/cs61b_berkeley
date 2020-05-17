public class OffByN implements CharacterComparator {
    public int NItem;

    /** Constructor: create objects whose equalChars method return true for characters that are off by N. */
    public OffByN(int N){
        NItem = N;
    }

    /** If the given x & y off by NItem, then return true. */
    public boolean equalChars(char x, char y){
        if (Math.abs(x-y) == NItem){
            return true;
        }
        return false;
    }
}