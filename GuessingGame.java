 import java.util.Random;
 import java.util.Scanner;


public class GuessingGame {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);5
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 6;

        while (true) {
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have 6 attempts to guess it.");

            for (int i = 0; i < maxAttempts; i++) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You found the number in " + attempts + " attempts.");
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("Game over! The number was " + numberToGuess + ".");
                System.out.println("You used " + attempts + " attempts.");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }

            System.out.println();
        }
    }
    
}
