package com.joelallison;

import java.util.*;

public class Main {

    static String format(String input){
        String suit = new String();
        switch(input.charAt(0)){
            case 'c':
                suit = "Clubs";
                break;
            case 'd':
                suit = "Diamonds";
                break;
            case 'h':
                suit = "Hearts";
                break;
            case 's':
                suit = "Spades";
                break;
        }

        String value = "";
        if(input.charAt(2) == '1'){
            value = "10";
        }else{
            value = String.valueOf(input.charAt(2));
        }

        System.out.println(value + suit);
        System.out.println(suit);

        return (value + " of " + suit);
    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);

        //generate cards
        final char[] suits = {'c','d','h','s'};
        final String[] values = {"A","2","3","4","5","6","7","8","9","1","J","Q","K"};
        ArrayList<String> cards = new ArrayList<String>();
        for (int suit = 0; suit < 4; suit++) {
            for (int i = 0; i < 13; i++) {
                cards.add("o" + suits[suit] + values[i]);
            }
        }

        ArrayList<String> playerHand = new ArrayList<String>();
        ArrayList<String> computerHand = new ArrayList<String>();

        System.out.println("Blackjack");

        int count = 0;
        //game loop
        boolean playing = false;
        do{
            count++;
            int dealtCard = random.nextInt(cards.size());
            if(count % 2 == 1){
                //player turn
                System.out.println("Round " + count/2);
                playerHand.add(cards.get(dealtCard));

                System.out.println(cards.get(dealtCard));

                for (String card:playerHand){
                    System.out.println(format(card));
                }
            }else{
                //computer "turn"
                computerHand.add(cards.get(dealtCard));
            }cards.remove(dealtCard);
        }while (playing);
    }
}
