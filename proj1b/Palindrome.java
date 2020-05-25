public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++){
            char ithChar = word.charAt(i);
            deque.addLast(ithChar);
        }
        return deque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = this.wordToDeque(word);
        while (d.size() > 1){
            char l = d.removeLast();
            char f = d.removeFirst();
            if (l != f) {
                return false;
            }
        }
        return true;
    }

    /** Overloading isPalindrome method. */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = this.wordToDeque(word);
        while(d.size() > 1){
            char first = d.removeFirst();
            char last = d.removeLast();
            if (!cc.equalChars(first, last)){
                return false;
            }
        }
        return true;
    }
}
