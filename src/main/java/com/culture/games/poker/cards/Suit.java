package com.culture.games.poker.cards;

public enum Suit {
	
    SPADES("\u2660"),
    HEARTS("\u2665"),
    CLUBS("\u2663"),
    DIAMONDS("\u2666");

    private String value;
    Suit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}