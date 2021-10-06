package com.joelallison;

import java.util.*;

public class Main {

    String format(String input){
        String suit = new String();
        switch(input.charAt(1)){
            case 'c':
                suit = "♣";
                break;
            case 'd':
                suit = "♦";
                break;
            case 'h':
                suit = "♥";
                break;
            case 's':
                suit = "♠";
                break;
        }

        String value = "";
        if(input.charAt(3) == '1'){
            value = "10";
        }else{
            value = String.valueOf(input.charAt(3));
        }

        return suit + value;
    }

    public static void main(String[] args) {
        Random random = new Random();

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

        for(String card : cards){
            System.out.println(card);
        }


        System.out.println("Blackjack");

        int count = 0;
        //game loop
        boolean playing = false;
        do{
            count++;
            int dealtCard = random.nextInt(ArrayList.size());
            if(count % 2 == 0){
                System.out.println("Round " + count/2);
                playerHand.add(cards.get(dealtCard));
            }else{
                computerHand.add(cards.get(dealtCard));
            }cards.remove(dealtCard);
        }while (playing);
    }
}
