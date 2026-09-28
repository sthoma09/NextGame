

import java.util.Scanner;
import java.util.Random;

public class NextGame {
	
	private int[] answer = new int[5];
    private Random rand = new Random();

    // Keep the five lowest turn counts
    private String[] highNames = new String[5];
    private int[] highTurns = new int[5];
    private int scoreCount = 0;

    public NextGame() {
        // Start with 1, 2, 3, 4, 5
        for (int i = 0; i < 5; i++) {
            answer[i] = i + 1;
        }

        // Mix the numbers up
        for (int i = 0; i < 5; i++) {
            int spot = rand.nextInt(5);
            int temp = answer[i];
            answer[i] = answer[spot];
            answer[spot] = temp;
        }
    }

    public void play() {
        Scanner input = new Scanner(System.in);
        int[] guess = new int[5];
        int turns = 0;
        boolean quit = false;
        boolean win = false;
        
        System.out.print("Enter your name");       
        String name = input.next();

        System.out.println("Game: Who's Next");
        System.out.println("Guess the sequence of 5 numbers from 1 to 5.");
        System.out.println("Enter 0 at any time to give up.");
        System.out.println("GOOD LUCK!!!");

        while (!quit && !win) {
            turns++;
            int correct = 0;
            System.out.println("== Turn " + turns + " ==");
            
            String entry = input.next();

            if (entry.equals("0")) {

                quit = true;

            } else {

                while (entry.length() != 5) {

                    System.out.print("Please enter exactly 5 digits, or 0 to quit: ");

                    entry = input.next();

                    if (entry.equals("0")) {

                        quit = true;

                        break;

                    }

                }

 

                if (!quit) {

                    for (int i = 0; i < 5; i++) {

                        guess[i] = entry.charAt(i) - '0';

                        if (guess[i] < 1 || guess[i] > 5) {

                            System.out.println("Use only digits 1 through 5.");

                            quit = true;

                            break;

                        }

                    }

                }

            }

 

            if (!quit) {

                for (int i = 0; i < 5; i++)

                    if (guess[i] == answer[i]) correct++;

 

                System.out.println("You have " + correct + " numbers correct.");

                if (correct == 5) {

                    win = true;

                    System.out.println("You guessed the sequence in " + turns + " turns!");

                    addHighScore(name, turns);

                }

            }

        }

 

        if (quit) System.out.println("You gave up, " + name + ".");

        System.out.println("Game Number Sequence");

        System.out.println("---------------------");

        for (int i = 0; i < 5; i++) System.out.print("| " + answer[i] + " ");

        System.out.println("|");

        System.out.println("---------------------");

        showHighScores();

    }

 

    private void addHighScore(String name, int turns) {

        int place = 0;

        while (place < scoreCount && highTurns[place] <= turns) place++;

        if (place < 5) {

            int last = scoreCount;

            if (last > 4) last = 4;

            for (int i = last; i > place; i--) {

                highNames[i] = highNames[i - 1];

                highTurns[i] = highTurns[i - 1];

            }

            highNames[place] = name;

            highTurns[place] = turns;

            if (scoreCount < 5) scoreCount++;

        }

    }

 

    private void showHighScores() {

        System.out.println("TOP 5 HIGH SCORES");

        System.out.println("-----------------");

        if (scoreCount == 0) {

            System.out.println("No scores yet. Only completed wins are recorded.");

        } else {

            for (int i = 0; i < scoreCount; i++)

                System.out.println((i + 1) + ". " + highNames[i] + " - " + highTurns[i] + " turns");

        }

    }

}