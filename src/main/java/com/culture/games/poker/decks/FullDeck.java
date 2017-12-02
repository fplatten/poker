package com.culture.games.poker.decks;

import java.util.Collections;
import java.util.LinkedList;

import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.cards.Suit;

public class FullDeck implements Deck {
    private LinkedList<PlayingCard> deck = new LinkedList<PlayingCard>();
    private LinkedList<PlayingCard> removedCards = new LinkedList<PlayingCard>();


    public FullDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new PlayingCard(suit, rank));
            }
        }
    }

    public LinkedList<PlayingCard> deal(int num) {
        LinkedList<PlayingCard> dealtCards = new LinkedList<PlayingCard>();
        for (int i = 0; i < num; i++) {
            dealtCards.add(deck.pop());
        }
        removedCards.addAll(dealtCards);
        return dealtCards;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void reshuffle() {
        while (!(removedCards.isEmpty())) {
            deck.add(removedCards.pop());
        }
        shuffle();
    }

	public PlayingCard nextCard() {
		PlayingCard card = deck.pop();
		removedCards.add(card);
		return card;
	}

}