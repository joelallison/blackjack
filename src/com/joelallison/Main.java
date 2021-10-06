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
        //game loop
        boolean playing = true;
        do{
            count++;
            int dealtCard = random.nextInt(cards.size());
            if(count % 2 == 0){
                //player turn
                System.out.println("Round " + count/2);
                playerHand.add(cards.get(dealtCard));

                System.out.println(cards.get(dealtCard));

                System.out.println(playerHand);

                printHand(playerHand);
            }else{
                //computer "turn"
                computerHand.add(cards.get(dealtCard));
            }cards.remove(dealtCard);
        }while (playing);
    }
}
