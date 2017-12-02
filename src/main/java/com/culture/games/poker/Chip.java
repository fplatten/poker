package com.culture.games.poker;

public enum Chip {
	
	WHITE(1),
	YELLOW_2(2),
	RED(5),
	BLUE(10),
	GREY(20),
	GREEN(25),
	ORANGE(50),
	BLACK(100),
	PINK(250),
	PURPLE(500),
	YELLOW_1K(1000),
	LIGHT_BLUE(2000),
	BROWN(5000);
	
	private int value;
	
	Chip(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
