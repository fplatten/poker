package com.culture.games.poker.model;

import java.util.Comparator;
import java.util.LinkedList;

import com.culture.games.poker.HandType;
import com.culture.games.poker.PokerGame;
import com.culture.games.poker.Position;
import com.culture.games.poker.cards.PlayingCard;

public class Player {

	// chipStack
	String name;
	LinkedList<PlayingCard> holeCards = new LinkedList<>();
	//LinkedList<PlayingCard> bestHand = new LinkedList<>();
	Hand hand;
	Position position;
	PokerGame pokerGame;
	private HandType handType;

	// sessionHistory;

	public Player() {
	}

	public Player(String name, PokerGame pokerGame) {
		this.name = name;
		this.pokerGame = pokerGame;
	}

	public void addCardToHand(PlayingCard card) {
		holeCards.add(card);
	}

	public LinkedList<PlayingCard> getHoleCards() {
		return holeCards;
	}

	public void setHoleCards(LinkedList<PlayingCard> holeCards) {
		this.holeCards = holeCards;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
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

	public HandType getHandType() {
		return handType;
	}

	public void setHandType(HandType handType) {
		this.handType = handType;
	}

	public static Comparator<Player> positionComparator = new Comparator<Player>() {
		@Override
		public int compare(Player p1, Player p2) {
			return p1.getPosition().compareTo(p2.getPosition());
		}
	};
	
	public static Comparator<Player> handComparator = new Comparator<Player>() {
		@Override
		public int compare(Player p1, Player p2) {
			return p1.getHand().compareTo(p2.getHand());
		}
	};

}
