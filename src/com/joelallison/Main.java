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
        switch (input) {
            case '1':
                return ("10");
            case 'K':
                return("King");
            case 'Q':
                return("Queen");
            case 'J':
                return("Jack");
            case 'A':
                return ("Ace");
            default:
                return(String.valueOf(input));
        }
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

        System.out.println("\uD83C\uDCA1  [Blackjack]  ʕ·ᴥ·͡ʔ \n\nThis is your dealer: ʕ·͡ᴥ·ʔ︻┳═一\n\nHe's got a gun.\n\nPlay nice.\n-----------------------------------------");

        int round = 0;
        int playerTotal = 0;
        int playerAceCount = 0;
        int computerTotal = 0;
        int computerAceCount = 0;
        String choice;
        int specialRound;
        //game loop
        boolean playing = true;
        boolean playerIn = true;
        boolean computerIn = true;
        do {round++;

            if(round == 1){
                specialRound = 2;
            }else{
                specialRound = 1;
            }

            System.out.println("\n---> Round " + round + "<---\n");

            //handling main gameplay
            //player turn

            if(playerIn){
                for (int i = 0; i < specialRound; i++) {
                    int dealtCard = random.nextInt(cards.size());
                    playerHand.add(cards.get(dealtCard));
                    cards.remove(dealtCard);
                }
                //calc playerTotal
                for (int i = 0; i < specialRound; i++) {
                    if ("23456789".contains(Character.toString(playerHand.get(playerHand.size() - specialRound + i)))) {
                        playerTotal += (int) (playerHand.get(playerHand.size() - specialRound + i)) - (int) '0';
                    } else if ((playerHand.get(playerHand.size() - specialRound + i)) != 'A') {
                        playerTotal += 10;
                    } else {
                        playerTotal += 1;
                        playerAceCount += 1;
                    }
                }
            }

            //computer "turn"
            if (computerIn){
               for (int i = 0; i < specialRound; i++) {
                  if(computerTotal-computerAceCount+(computerAceCount*11) < 17){
                        if (specialRound == 1){System.out.println("The dealer hits.");}
                        int dealtCard = random.nextInt(cards.size());
                        computerHand.add(cards.get(dealtCard));
                        cards.remove(dealtCard);
                    }else{
                        System.out.println("The dealer stands. Its total is " + (computerTotal-computerAceCount+(computerAceCount*11)));
                        computerIn = false;
                    }
                }
               //calc computerTotal
               for (int i = 0; i < specialRound; i++) {
                    if ("23456789".contains(Character.toString(computerHand.get(computerHand.size() - specialRound + i)))) {
                        computerTotal += (int) (computerHand.get(computerHand.size() - specialRound + i)) - (int) '0';
                    } else if ((computerHand.get(computerHand.size() - specialRound + i)) != 'A') {
                        computerTotal += 10;
                    } else {
                        computerTotal += 1;
                        computerAceCount += 1;
                    }
                }
            }

            if (specialRound == 2){
                System.out.println("You've both drawn 2 cards (as per the rules)");
            }

            //end of each total round
            if (playerIn){
               System.out.println("Your cards are: " + printHand(playerHand));
              System.out.print("\nThis means that your total is " + playerTotal);
               //handling printing differently based on if player has ace(s)
               if (playerAceCount > 0 && (playerTotal-playerAceCount+(playerAceCount*11) <= 21) ){
                   System.out.print(" or " + (playerTotal-playerAceCount+(playerAceCount*11)) + ". Do you want to hit or stand? [h/s]");
               }else if (playerTotal <= 21) {
                   System.out.print(". Do you want to hit or stand? [h/s]");
                    choice = userInput.next();
                    if (choice.equals("s")){
                        playerIn = false;
                }
                }
                else{
                    System.out.print(" You went bust!");
                    playerIn = false;
                    computerIn = false;
                }
            }
            if(playerIn == false && computerIn == false){playing = false;}


        } while (playing);

        System.out.println("\nYour cards were: " + printHand(playerHand) + "\nThis means your total therefore was " + playerTotal + ".");
        if (playerAceCount > 0 && (playerTotal-playerAceCount+(playerAceCount*11) <= 21) ){
            System.out.print(" or " + (playerTotal-playerAceCount+(playerAceCount*11)) + ".");
        }
        System.out.println("\nThe computer's cards were " + printHand(computerHand) + " This means its total was " + computerTotal + ".");
        if (computerAceCount > 0 && (computerTotal-computerAceCount+(computerAceCount*11) <= 21) ){
            System.out.print(" or " + (computerTotal-computerAceCount+(computerAceCount*11)) + ".");
        }

        //printing who won
        if(playerTotal <= 21 && computerTotal <= 21 && (21-playerTotal) < (21-computerTotal)){
            System.out.println("\nYou won!");
        }else if(playerTotal > 21 && computerTotal <= 21){
            System.out.println("\nBetter luck next time...");
        }else if(computerTotal > 21){
            System.out.println("\nDealer went bust! You won!");
        }
    }
}
