/*
You have been given a "word search" puzzle, which consists of a rectangular grid of letters,
in which several words are hidden. Each word may begin anywhere in the puzzle, and may be
oriented in any straight line horizontally, vertically, or diagonally. However, the words
must all go down, right, or down-right. (see examples)

You are given a String[], grid, indicating the letters in the grid to be searched.
Character j of element i of grid is the letter at row i, column j.
You are also given a String[], wordList, indicating the words to be found in the grid.
You are to return a String[] indicating the locations of each word within the grid.

The return value should have the same number of elements as wordList.
Each element of wordList corresponds to the element of the return value with the same index.

Each element of the return value should be formatted as "row col" (quotes added for clarity),
where row is the 0-based row in which the first letter of the word is found, and col is the
0-based column in which the first letter of the word is found. If the same word can be found
more than once, the location in the lowest-indexed row should be returned.
If there is still a tie, return the location with the lowest-indexed column.
If a word cannot be found in the grid, return an empty string for that element.
 */
public class WordSearch {
    public static void main(String[] args) {
        //String[] grid = {"TEST", "GOAT", "BOAT"};
        //String[] wordlist = {"GOAT", "BOAT", "TEST"};

        //String[] grid = {"SXXX", "XQXM", "XXLA", "XXXR"};
        //String[] wordlist = {"SQL", "RAM"};

        String[] grid = {"EASYTOFINDEAGSRVHOTCJYG",
                "FLVENKDHCESOXXXXFAGJKEO",
                "YHEDYNAIRQGIZECGXQLKDBI",
                "DEIJFKABAQSIHSNDLOMYJIN",
                "CKXINIMMNGRNSNRGIWQLWOG",
                "VOFQDROQGCWDKOUYRAFUCDO",
                "PFLXWTYKOITSURQJGEGSPGG"};
        String [] wordlist = {"EASYTOFIND", "DIAG", "GOING", "THISISTOOLONGTOFITINTHISPUZZLE"};

        String[] location = findWords(grid, wordlist);

        for (String s : location) {
            System.out.println("RESULT " + s);
        }
    }
    public static String[] findWords(String[] grid, String[] wordlist) {
        String[] locations = new String[wordlist.length];
        for (int w = 0; w < wordlist.length; w++) {
            locations[w] = findWord(wordlist[w], grid);
        }
        return locations;
    }
    public static String findWord(String wordToFind, String[] grid) {
        // Loop through grid rows
        for (int i = 0; i < grid.length; i++) {
            // Loop through grid columns
            for (int j = 0; j < grid[i].length(); j++) {
                if (wordToFind.charAt(0) == grid[i].charAt(j)) {
                    if (isWordFound(wordToFind, 1, grid, i, j)) {
                        return i + " " + j;
                    }
                }
            }
        }
        return "";
    }
    public static boolean isWordFound(String wordToFind, int charIndex, String[] grid, int row, int col) {
        // Base case: whole word is found
        if (wordToFind.length() == charIndex) {
            return true;
        }
        boolean wordFound = false;
        // Check right
        if (grid[row].length() - 1 > col) {
            if (grid[row].charAt(col + 1) == wordToFind.charAt(charIndex))
                wordFound = isWordFound(wordToFind, charIndex + 1, grid, row, col + 1);
        }
        // Check down
        if (!wordFound && grid.length - 1 > row) {
            if (grid[row + 1].charAt(col) == wordToFind.charAt(charIndex))
                wordFound = isWordFound(wordToFind, charIndex + 1, grid, row + 1, col);
        }
        // Check down-right
        if (!wordFound && grid.length - 1 > row && grid[row].length() - 1 > col) {
            if (grid[row + 1].charAt(col + 1) == wordToFind.charAt(charIndex))
                wordFound = isWordFound(wordToFind, charIndex + 1, grid, row + 1, col + 1);
        }
        return wordFound;
    }
}