public class Palindrome {
    /** Given a String, wordToDeque return a Deque where
     * the characters appear in the same order as in the string. */
    public Deque<Character> wordToDeque(String word){
        char c;
        Deque<Character> d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++){
            c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    /** Return if string word is palindrome or not. */
    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        while (d.size()>1) {
            if (d.removeFirst() == d.removeLast()) {
                continue;
            }
            return false;
        }
        return true;
    }

    /** Overload isPalindrome. */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        while (deque.size() > 1) {
            if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                continue;
            }
            return false;
        }
        return true;
    }

}