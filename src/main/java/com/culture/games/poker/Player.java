package com.culture.games.poker;

import java.util.LinkedList;

import com.culture.games.poker.cards.PlayingCard;

public class Player {

	// chipStack
	String name;
	LinkedList<PlayingCard> hand = new LinkedList<>();
	Position position;

	// sessionHistory;

	public Player() {
	}

	public Player(String name, Position position) {
		this.name = name;
		this.position = position;
	}

	public LinkedList<PlayingCard> getHand() {
		return hand;
	}

	public void setHand(LinkedList<PlayingCard> hand) {
		this.hand = hand;
	}

	public void addCardToHand(PlayingCard card) {
		hand.add(card);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
}
