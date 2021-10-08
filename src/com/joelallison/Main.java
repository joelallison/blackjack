package com.joelallison;

import java.util.*;

public class Main {

    static String printHand(ArrayList<Character> hand) {
        String output = "";
        for (int i = 0; i < hand.size() - 1; i++) {
            output += (format(hand.get(i)) + ", ");
        }
        output += (format(hand.get(hand.size() - 1)) + ".");

        return output;
    }


    static String format(char input) {
        String value = "";
        switch (input) {
            case '1':
                value = "10";
                break;
            case 'K':
                value = "King";
                break;
            case 'Q':
                value = "Queen";
                break;
            case 'J':
                value = "Jack";
                break;
            case 'A':
                value = "Ace";
                break;
            default:
                value = String.valueOf(input);
        }

        return (value);
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);

        //generate cards
        final char[] values = {'A', '2', '3', '4', '5', '6', '7', '8', '9', '1', 'J', 'Q', 'K'};
        ArrayList<Character> cards = new ArrayList<Character>();
        for (int suit = 0; suit < 4; suit++) {
            for (int i = 0; i < 13; i++) {
                cards.add(values[i]);
            }
        }

        ArrayList<Character> playerHand = new ArrayList<Character>();
        ArrayList<Character> computerHand = new ArrayList<Character>();

        System.out.println("[Blackjack]\n\nThis is your dealer: ʕ·͡ᴥ·ʔ︻┳═一\n\nHe's got a gun.\n\nPlay nice.\n-----------------------------------------");

        int count = 0;
        int playerTotal = 0;
        int playerAceCount = 0;
        int computerTotal = 0;
        int computerAceCount = 0;
        String choice = "";
        //game loop
        boolean playing = true;
        do {count++;

            //handling main gameplay

            //player turn
            int dealtCard = random.nextInt(cards.size());
            System.out.println("Round " + ((count / 2) + (count % 2)));
            playerHand.add(cards.get(dealtCard));
            cards.remove(dealtCard);

            //computer "turn"
            dealtCard = random.nextInt(cards.size());
            computerHand.add(cards.get(dealtCard));
            cards.remove(dealtCard);

            //calc totals
            if ("23456789".contains(Character.toString(playerHand.get(playerHand.size() - 1)))) {
                playerTotal += (int) (playerHand.get(playerHand.size() - 1)) - (int) '0';
            } else if ((playerHand.get(playerHand.size() - 1)) != 'A') {
                playerTotal += 10;
            } else {
                playerTotal += 1;
                playerAceCount += 1;
            }

            if ("23456789".contains(Character.toString(computerHand.get(computerHand.size() - 1)))) {
                computerTotal += (int) (computerHand.get(computerHand.size() - 1)) - (int) '0';
            } else if ((computerHand.get(computerHand.size() - 1)) != 'A') {
                computerTotal += 10;
            } else {
                computerTotal += 1;
                computerAceCount += 1;
            }

            //end of each total round
            System.out.println("Your cards are:" + printHand(playerHand));
            System.out.print("\nThis means that your total is " + playerTotal);
            //handling printing differently based on if player has ace(s)
            if (playerAceCount > 0 && (playerTotal-playerAceCount+(playerAceCount*11) <= 21) ){
                System.out.print(" or " + (playerTotal-playerAceCount+(playerAceCount*11)) + ". Do you want to hit or stand? [h/s]");
            }else{  System.out.print(". Do you want to hit or stand? [h/s]");  }
            choice = userInput.next();
            if (choice.equals("s")){
                playing = false;
            }


        } while (playing);

        System.out.println("Your cards were:" + printHand(playerHand) + "\nThis means your total therefore was " + playerTotal + ".");
        if (playerAceCount > 0 && (playerTotal-playerAceCount+(playerAceCount*11) <= 21) ){
            System.out.print(" or " + (playerTotal-playerAceCount+(playerAceCount*11)) + ".");
        }
        System.out.println("The computer's cards were " + printHand(computerHand) + "\nAnd its total was " + computerTotal + ".");
        if (computerAceCount > 0 && (computerTotal-computerAceCount+(computerAceCount*11) <= 21) ){
            System.out.print(" or " + (computerTotal-computerAceCount+(computerAceCount*11)) + ".");
        }
    }
}
