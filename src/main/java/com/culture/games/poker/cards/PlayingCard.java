package com.culture.games.poker.cards;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;


public class PlayingCard{
    private Suit suit;
    private Rank rank;


    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    

    public Suit getSuit() {
		return suit;
	}



	public void setSuit(Suit suit) {
		this.suit = suit;
	}



	public Rank getRank() {
		return rank;
	}



	public void setRank(Rank rank) {
		this.rank = rank;
	}



	@Override public String toString() {
        try {
			return (this.rank.getValue() + new String(this.suit.getValue().getBytes(), "UTF8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        return (this.rank.getValue() + this.suit.getValue());
    }
	
	public static Comparator<PlayingCard> rankSorter = new Comparator<PlayingCard>(){

		@Override
		public int compare(PlayingCard playingCard1, PlayingCard playingCard2) {
			return playingCard1.getRank().compareTo(playingCard2.getRank());
		}
	};
}