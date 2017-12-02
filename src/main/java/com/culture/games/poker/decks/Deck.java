package com.culture.games.poker.decks;

import java.util.LinkedList;

import com.culture.games.poker.cards.PlayingCard;

public interface Deck {

    public void shuffle();
    public <E> LinkedList deal(int num);
    public void reshuffle();
    public PlayingCard nextCard();

}