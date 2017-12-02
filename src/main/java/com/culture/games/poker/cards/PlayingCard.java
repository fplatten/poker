package com.culture.games.poker.cards;

import java.io.UnsupportedEncodingException;


public class PlayingCard {
    private Suit suit;
    private Rank rank;


    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override public String toString() {
        try {
			return (this.rank.getValue() + new String(this.suit.getValue().getBytes(), "UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return (this.rank.getValue() + this.suit.getValue());
    }
}