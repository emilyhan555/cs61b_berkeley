import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("aaaaab"));
    }

    @Test
    public void testisPalindromeOffByOne(){
        OffByOne offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("abcab",offByOne));
        assertFalse(palindrome.isPalindrome("noon", offByOne));
    }
}     //Uncomment this class once you've created your Palindrome class. */