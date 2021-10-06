package com.joelallison;

import java.util.*;

public class Main {

    static void printHand(ArrayList<Character> hand){
        for (int i = 0; i < hand.size()-1; i++) {
            System.out.print(format(hand.get(i)) + ", ");
        }System.out.print(format(hand.get(hand.size()-1)) + ".");
    }

    static String format(char input){
        String value = "";
        switch(input){
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
        final char[] values = {'A','2','3','4','5','6','7','8','9','1','J','Q','K'};
        ArrayList<Character> cards = new ArrayList<Character>();
        for (int suit = 0; suit < 4; suit++) {
            for (int i = 0; i < 13; i++) {
                cards.add(values[i]);
            }
        }

        ArrayList<Character> playerHand = new ArrayList<Character>();
        ArrayList<Character> computerHand = new ArrayList<Character>();

        System.out.println("Blackjack");

        int count = 0;
        int playerTotal = 0;
        int playerAceCount = 0;
        int computerTotal = 0;
        int computerAceCount = 0;
        String choice = "";
        //game loop
        boolean playing = true;
        do{
            count++;
            //calc the totals
            for (char card:playerHand) {
                if("23456789".contains(Character.toString(card))){
                    playerTotal += Character.getNumericValue(card);
                }else if(card != 'A'){
                    playerTotal += 10;
                }else{
                    playerTotal += 1;
                    playerAceCount += 1;
                }
            }
            for (char card:computerHand) {
                if("23456789".contains(Character.toString(card))){
                    computerTotal += Character.getNumericValue(card);
                }else if(card != 'A'){
                    computerTotal += 10;
                }else{
                    computerTotal += 1;
                    computerAceCount += 1;
                }
            }
            int dealtCard = random.nextInt(cards.size());
            if(count % 2 == 1){
                //player turn
                System.out.println("Round " + ((count/2) + (count%2)));
                playerHand.add(cards.get(dealtCard));

                System.out.println(cards.get(dealtCard));

                System.out.println(playerHand);

                printHand(playerHand);
            }else{
                //computer "turn"
                computerHand.add(cards.get(dealtCard));

                //end of each total round
                System.out.println("Your cards are:");
                printHand(playerHand);
                System.out.println("\nThis means that your total is " + playerTotal + "\nWould you like to hit or stand? [h/s]");
                choice = userInput.next();
            }cards.remove(dealtCard);
        }while (playing);
    }
}
