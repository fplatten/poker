package com.culture.games.poker;

import java.util.Comparator;
import java.util.LinkedList;

import com.culture.games.poker.cards.PlayingCard;

public class Player {

	// chipStack
	String name;
	LinkedList<PlayingCard> hand = new LinkedList<>();
	Position position;
	PokerGame pokerGame;

	// sessionHistory;

	public Player() {
	}

	public Player(String name, PokerGame pokerGame) {
		this.name = name;
		this.pokerGame = pokerGame;
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

	public PokerGame getPokerGame() {
		return pokerGame;
	}

	public void setPokerGame(PokerGame pokerGame) {
		this.pokerGame = pokerGame;
	}

	public static Comparator<Player> positionComparator = new Comparator<Player>() {
		@Override
		public int compare(Player p1, Player p2) {
			return p1.getPosition().compareTo(p2.getPosition());
		}
	};

}
