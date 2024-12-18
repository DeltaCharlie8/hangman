import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> targetWords = new ArrayList<>();
    private static ArrayList<String> chosenWords = new ArrayList<String>();
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C:\\Users\\debbi\\OneDrive\\Documents\\GOLDSMITHS\\Year 2\\Extended Java\\Hangman\\Hangman\\wordlist.txt"));
        while(in.hasNext()){
            targetWords.add(in.next());
        }

        //picks and hides the target word
        String targetWord = getWord();
        String storedWord = targetWord;
        char[] hiddenWord = storedWord.toCharArray();
        System.out.println("The hidden word is " + Arrays.toString(hiddenWord));
        for(int i = 0; i < storedWord.length(); i++){
            hiddenWord[i] = '-';
        }

        int guesses = 7;
        boolean solved = false;
        while(guesses > 0 && solved != true){
            //takes the user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a letter: ");
            String input = scanner.next();

            //adds the chosen char to the chosenWords list
            chosenWords.add(input);

            //searches for the input in the target word
            for(int i = guesses; i > 0; i--){
                for(int j = 0; j < targetWord.length(); j++){
                    if(targetWord.charAt(j) == input.charAt(0)){
                        hiddenWord[j] = input.charAt(0);

                    }
                }
            }
            guesses --;
            System.out.println("You have " + guesses + " guess left");
            System.out.println(hiddenWord);
            System.out.println("");
            System.out.println("Selected letters: " + chosenWords);
            System.out.println("");
            String wordSolved = new String(hiddenWord);

            //output for win or lose
            if(targetWord.equals(wordSolved)){
                System.out.println("CONGRATULATIONS you won!");
                solved = true;
            } else if (guesses == 0) {
                System.out.println("YOU LOSE, you ran out of guesses!");
            }
        }
    }

    public static String getWord(){
        Random r = new Random();
        String word = targetWords.get(r.nextInt(targetWords.size()));
        System.out.println("The target word is: " + word);
        return word;
    }
}
