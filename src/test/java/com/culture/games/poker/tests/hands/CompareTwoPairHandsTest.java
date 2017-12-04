package com.culture.games.poker.tests.hands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.cards.Suit;
import com.culture.games.poker.model.Hand;

public class CompareTwoPairHandsTest {
	
	@Test
	public void testHandCompareSame(){
		
		LinkedList<PlayingCard> cards1 = new LinkedList<>();
		cards1.add(new PlayingCard(Suit.DIAMONDS,Rank.JACK));
		cards1.add(new PlayingCard(Suit.SPADES,Rank.JACK));
		cards1.add(new PlayingCard(Suit.DIAMONDS,Rank.SEVEN));
		cards1.add(new PlayingCard(Suit.CLUBS,Rank.SEVEN));
		cards1.add(new PlayingCard(Suit.CLUBS,Rank.ACE));
		
		LinkedList<PlayingCard> cards2 = new LinkedList<>();
		cards2.add(new PlayingCard(Suit.DIAMONDS,Rank.JACK));
		cards2.add(new PlayingCard(Suit.SPADES,Rank.JACK));
		cards2.add(new PlayingCard(Suit.DIAMONDS,Rank.SEVEN));
		cards2.add(new PlayingCard(Suit.CLUBS,Rank.SEVEN));
		cards2.add(new PlayingCard(Suit.CLUBS,Rank.ACE));
		
		Hand hand1 = new Hand(HandType.TWO_PAIR,cards1);
		Hand hand2 = new Hand(HandType.TWO_PAIR,cards2);
		
		int comp = hand1.compareTo(hand2);
		
		assertEquals(0, comp);
		
	}
	
	@Test
	public void testHandCompareDiff(){
		
		LinkedList<PlayingCard> cards1 = new LinkedList<>();
		cards1.add(new PlayingCard(Suit.SPADES,Rank.NINE));
		cards1.add(new PlayingCard(Suit.SPADES,Rank.KING));
		cards1.add(new PlayingCard(Suit.CLUBS,Rank.FOUR));
		cards1.add(new PlayingCard(Suit.HEARTS,Rank.SEVEN));
		cards1.add(new PlayingCard(Suit.DIAMONDS,Rank.THREE));
		
		LinkedList<PlayingCard> cards2 = new LinkedList<>();
		cards2.add(new PlayingCard(Suit.SPADES,Rank.ACE));
		cards2.add(new PlayingCard(Suit.SPADES,Rank.KING));
		cards2.add(new PlayingCard(Suit.CLUBS,Rank.FIVE));
		cards2.add(new PlayingCard(Suit.HEARTS,Rank.SEVEN));
		cards2.add(new PlayingCard(Suit.DIAMONDS,Rank.THREE));
		
		Hand hand1 = new Hand(HandType.HIGH_CARD,cards1);
		Hand hand2 = new Hand(HandType.HIGH_CARD,cards2);
		
		int comp = hand1.compareTo(hand2);
		
		List<Hand> hands = new ArrayList<>();
		hands.add(hand1);
		hands.add(hand2);
		
		hands.sort(Hand.handComparator);
		
		hands.forEach(t -> 
		{t.getCards().forEach(card -> System.out.print(card.toString() + " "));
		System.out.print("\n");});
		
		assertEquals(1, comp);
		
	}
	

}
