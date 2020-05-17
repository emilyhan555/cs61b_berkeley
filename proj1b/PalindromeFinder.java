/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("C:/Users/emily/cs61b/cs61b_berkeley/library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        OffByOne offbyone = new OffByOne();
        //OffByN offbyN = new OffByN(5);

        /* Find all palindromes of length 4 or more.
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }
        */

        /** Find all off-by-one palindromes of length 4 or more. */
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offbyN)){
                System.out.println(word);
            }
        }
    }
}