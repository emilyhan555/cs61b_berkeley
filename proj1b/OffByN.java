public class OffByN implements CharacterComparator {
    public int NItem;

    public OffByN(int N){
        NItem = N;
    }

    public boolean equalChars(char x, char y){
        if (Math.abs(x-y) == NItem){
            return true;
        }
        return false;
    }
}