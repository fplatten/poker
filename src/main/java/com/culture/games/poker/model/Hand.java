package com.culture.games.poker.model;

import java.util.LinkedList;
import java.util.List;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;

public class Hand {
	
	LinkedList<PlayingCard> cards = new LinkedList<>();
	private HandType handType;
	
	public Hand(){}
	
	public Hand(HandType handType, LinkedList<PlayingCard> cards){
		this.handType = handType;
		this.cards.addAll(cards);
	}
	
	public Hand(HandType handType, List<PlayingCard> cards){
		this.handType = handType;
		this.cards.addAll(cards);
	}
	
	public LinkedList<PlayingCard> getCards() {
		return cards;
	}
	public void setCards(LinkedList<PlayingCard> cards) {
		this.cards = cards;
	}
	public HandType getHandType() {
		return handType;
	}
	public void setHandType(HandType handType) {
		this.handType = handType;
	}
}
