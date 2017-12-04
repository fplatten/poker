package com.culture.games.poker;

import java.util.LinkedList;
import java.util.stream.IntStream;

import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.decks.FullDeck;
import com.culture.games.poker.model.Player;
import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

public class PokerGame {
	
	public static final int HOLD_LIMIT = 2;
	public static final int FLOP = 3;
	private FullDeck deck = new FullDeck();
	LinkedList<Player> players = new LinkedList<>();
	LinkedList<PlayingCard> board = new LinkedList<>();
    LinkedList<PlayingCard> burners = new LinkedList<>();

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
		
		PokerGame pokerGame = new PokerGame();
		pokerGame.simulate();
		
		
    }

    public void simulate() {
    	
    	Player player1 = new Player("player1", this);
    	Player player2 = new Player("player2", this);
    	Player player3 = new Player("player3", this);
    	Player player4 = new Player("player4", this);
    	Player player5 = new Player("player5", this);
    	Player player6 = new Player("player6", this);
    	
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	players.add(player4);
    	players.add(player5);
    	players.add(player6);
    	
    	
    	player1.setPosition(Position.BUTTON);
    	player2.setPosition(Position.SMALL_BLIND);
    	player3.setPosition(Position.BIG_BLIND);
    	player4.setPosition(Position.UTG);
    	player5.setPosition(Position.HIGH_JACK);
    	player6.setPosition(Position.CUTTOFF);
    	
        deck = new FullDeck();
        deck.shuffle();
        
        
        players.sort( Player.positionComparator );
        
        IntStream.range(0, HOLD_LIMIT).forEach($ -> players.forEach(player -> player.addCardToHand(deck.nextCard())));
        
        players.forEach(player -> {
        	System.out.print(player.getName() + ": ");
        	player.getHoleCards().forEach(card -> System.out.print(card + " "));
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
        
        System.out.println();
        
        players.forEach(player -> {
        	
        	RuleBookRunner cardHandRules = new RuleBookRunner("com.culture.games.poker.rules.hands");
    		NameValueReferableMap<Player> facts = new FactMap<>();
    		facts.put(new Fact<>(player));
    		cardHandRules.run(facts);
    		
    		cardHandRules.getResult().ifPresent(foo -> {
    			if(foo.getValue() instanceof HandType){
    				HandType playerHand = (HandType) foo.getValue();
    				
    				System.out.print(player.getName() + " hand is " + playerHand + " ");
    				player.getHand().getCards().forEach(card -> System.out.print(card.toString() + " "));
    				
    			}
    		});
    		
    		System.out.print("\n");
        	
        });
        
        players.sort(Player.handComparator);
        
        System.out.println("### SORT HANDS ACCORDING TO RANK");
        
        players.forEach(player -> {
        	
        	System.out.print(player.getName() + " hand is " + player.getHand().getHandType() + " ");
			player.getHand().getCards().forEach(card -> System.out.print(card.toString() + " "));
			System.out.print("\n");
        	
        });
        
        
        
    }

	public LinkedList<PlayingCard> getBoard() {
		return board;
	}

	public void setBoard(LinkedList<PlayingCard> board) {
		this.board = board;
	}
    
    

}
