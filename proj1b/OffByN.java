public class OffByN implements CharacterComparator {
    private int N;

    /** Create a constructor which takes an integer. */
    public OffByN(int n){
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y){
        if (Math.abs(x-y) == N){
            return true;
        }
        return false;
    }
}
