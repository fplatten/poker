package com.culture.games.poker.tests;

import java.util.LinkedList;

import org.junit.Test;

import com.culture.games.poker.HandType;
import com.culture.games.poker.Player;
import com.culture.games.poker.PokerGame;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.cards.Suit;
import com.deliveredtechnologies.rulebook.Fact;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

public class FlushRuleTest {
	
	@Test
	public void testFlushRule(){
		
		LinkedList<PlayingCard> testBoard = new LinkedList<>();
		testBoard.add(new PlayingCard(Suit.SPADES,Rank.NINE));
		testBoard.add(new PlayingCard(Suit.SPADES,Rank.TWO));
		testBoard.add(new PlayingCard(Suit.CLUBS,Rank.FIVE));
		testBoard.add(new PlayingCard(Suit.HEARTS,Rank.SEVEN));
		testBoard.add(new PlayingCard(Suit.SPADES,Rank.TEN));
		
		PokerGame pokerGame = new PokerGame();
		pokerGame.getBoard().addAll(testBoard);
		
		Player player = new Player("player1",pokerGame);
		
		player.addCardToHand(new PlayingCard(Suit.SPADES,Rank.QUEEN));
		player.addCardToHand(new PlayingCard(Suit.SPADES,Rank.JACK));
		
		
		RuleBookRunner cardHandRules = new RuleBookRunner("com.culture.games.poker.rules.hands");
		NameValueReferableMap<Player> facts = new FactMap<>();
		facts.put(new Fact<>(player));
		
		cardHandRules.run(facts);
		
		cardHandRules.getResult().ifPresent(foo -> {
			if(foo.getValue() instanceof HandType){
				HandType playerHand = (HandType) foo.getValue();
				if(playerHand.equals(HandType.FLUSH)){
					System.out.println("You have a flush");
				}
			}
		});
	}

}
