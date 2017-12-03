package com.culture.games.poker.cards;

import java.util.Comparator;

public enum Rank {
	
	ACE("A",14),
	KING("K",13),
	QUEEN("Q",12),
	JACK("J",11),
	TEN("10",10),
	NINE("9",9),
	EIGHT("8",8),
	SEVEN("7",7),
	SIX("6",6),
	FIVE("5",5),
	FOUR("4",4),
	THREE("3",3),
    TWO("2",2);

    private String value;
    private int number;

    Rank(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public String getValue() {
        return value;
    }
    
    public int getNumber(){
    	return number;
    }
    
    public static Comparator<Rank> rankSorter = new Comparator<Rank>(){

		@Override
		public int compare(Rank rank1, Rank rank2) {
			return rank1.compareTo(rank2);
		}
	};
}