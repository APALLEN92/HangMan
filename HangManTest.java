import java.util.Scanner;

public class HangManTest {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello, would you like to play a game of Hangman? \n ");

        boolean validInput = false;
        do {
            System.out.println(" Please type yes or no below \n ");
            String yesOrNo = sc.nextLine();

            if (yesOrNo.equalsIgnoreCase("yes")) {
                System.out.println(" \nGreat, lets begin \n");
                validInput = true;
            } else if (yesOrNo.equalsIgnoreCase("no")) {
                System.out.println(" Thats to bad, have a good day. ");
                System.exit(0);
            } else {
                System.out.println(" Sorry you have entered an invalid input please try again");
            }
        } while (!validInput);

        String randomWord = randomWord();
        char[] placeholder = createPlaceHolders(randomWord);
        int incorrectGuesses = 0;

        while (incorrectGuesses < 6 && !checkWin(placeholder)) {
            System.out.println(" your incorrect guesses so far are " + incorrectGuesses);

            showGallows(incorrectGuesses);

            System.out.println("word to guess: \n " + String.valueOf(placeholder));
            System.out.println("Please enter your guess. \n");
            char guess = sc.next().charAt(0);

            if (!usersGuess(randomWord, placeholder, guess)) {
                incorrectGuesses++;
            }
        }

        showGallows(incorrectGuesses);

        if (checkWin(placeholder)) {
            System.out.println("Congratulations! You Won!\n ");
            System.out.println("The word was " + randomWord);
        } else {
            System.out.println("Sorry you loose, better luck next time \n");
            System.out.println("The word was " + randomWord);
        }

    }

    public static String[] words = {
            "onomatopoeia", "Elephant", "incomprehensibilities", "xenotransplantation",
            "Education", "Pneumonoultramicroscopic", "Supercalifragilisticexpialidocious"
    };

    public static String[] theGallows = {
            "+---+\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n"
    };

    public static String randomWord() {
        int theWord = (int) (Math.random() * words.length);
        return words[theWord];
    }

    public static char[] createPlaceHolders(String word) {
        char[] placeholders = new char[word.length()];
        for (int x = 0; x < word.length(); x++) {
            placeholders[x] = '_';
        }
        return placeholders;
    }

    public static void showGallows(int gallowImage) {
        System.out.println(theGallows[gallowImage]);
    }

    public static boolean checkWin(char[] placeHolders) {
        for (char check : placeHolders) {
            if (check == '_') {
                return false;
            }
        }

        return true;
    }

    public static boolean usersGuess(String word, char[] placeHolders, char guess) {
        boolean correctGuess = false; // A variable to keep track of whether the guess is correct or not
        for (int x = 0; x < word.length(); x++) { // Loop through each character in the word
            if (word.charAt(x) == guess) { // Check if the current character in the word matches the guessed character
                placeHolders[x] = guess; // If the guess is correct, update the placeholder at position x with the
                                         // guessed character
                correctGuess = true; // Update the correctGuess flag to indicate that the guess was correct
            }
        }
        return correctGuess; // Return whether the guess was correct or not
    }
}
