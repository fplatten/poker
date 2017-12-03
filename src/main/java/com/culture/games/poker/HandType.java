package com.culture.games.poker;

public enum HandType {
	
	ROYAL_FLUSH("Royal Flush"),
	STRAIGHT_FLUSH("Straight Flush"),
	FOUR_OF_A_KIND("Four-of-a-Kind"),
	FULL_HOUSE("Full House"),
	FLUSH("Flush"),
	STRAIGHT("Straight"),	
	THREE_OF_A_KIND("Three-of-a-Kind"),
	TWO_PAIR("Two Pair"),
	PAIR("One Pair"),
	HIGH_CARD("High Card");

	private String label;
	private HandType(String label) {
		this.label = label;
	}
	public String getLabel() {
		return this.label;
	}

}
