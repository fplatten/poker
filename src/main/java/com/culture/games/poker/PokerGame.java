package com.culture.games.poker;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.IntStream;

import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.decks.FullDeck;

public class PokerGame {
	
	public static final int HOLD_LIMIT = 2;
	public static final int FLOP = 3;

	public static void main(String args[]) {
		
		//players
		//main pot
		//side pots
		//ante
		//small blind
		//big blind
		//deck
		//button
		//board cards
		//flop
		//turn
		//river
		//discards
		
		
		
        simulate();
    }

    public static void simulate() {
    	
    	LinkedList<Player> players = new LinkedList<>();
    	
    	players.add(new Player("player1", Position.BUTTON));
    	players.add(new Player("player2", Position.SMALL_BLIND));
    	players.add(new Player("player3", Position.BIG_BLIND));
    	players.add(new Player("player4", Position.UTG));
    	players.add(new Player("player5", Position.HIGH_JACK));
    	players.add(new Player("player6", Position.CUTTOFF));
    	
        FullDeck deck = new FullDeck();
        deck.shuffle();
        LinkedList<PlayingCard> board = new LinkedList<>();
        LinkedList<PlayingCard> burners = new LinkedList<>();
        
        players.sort( new Comparator<Player>(){
			@Override
			public int compare(Player p1, Player p2) {
				return p1.getPosition().compareTo(p2.getPosition());
			}} );
        
        IntStream.range(0, HOLD_LIMIT).forEach($ -> players.forEach(player -> player.addCardToHand(deck.nextCard())));
        
        players.forEach(player -> {
        	System.out.print(player.getName() + ": ");
        	player.getHand().forEach(card -> System.out.print(card + " "));
        	System.out.println();
        });
        
        //flop
        burners.add(deck.nextCard());
        board.addAll(deck.deal(3));
        
        //turn
        burners.add(deck.nextCard());
        board.add(deck.nextCard());
        
        //river
        burners.add(deck.nextCard());
        board.add(deck.nextCard());
        
        System.out.print("board: ");
        
        board.forEach(card -> System.out.print(card.toString() + " "));
        
        
    }

}
