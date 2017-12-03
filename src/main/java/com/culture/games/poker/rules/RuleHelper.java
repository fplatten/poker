package com.culture.games.poker.rules;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.cards.Suit;
import com.culture.games.poker.utils.PokerConstants;

public class RuleHelper {

	public static LinkedList<PlayingCard> getFlush(Map<Suit, List<PlayingCard>> suits) {

		LinkedList<PlayingCard> flush = new LinkedList<>();

		suits.forEach((k, v) -> {

			if (v.size() > 4) {
				flush.addAll(v);
			}
		});

		return flush;
	}

	public static Map<Rank, List<PlayingCard>> getRankMatches(Map<Rank, List<PlayingCard>> ranks) {

		Map<Rank, List<PlayingCard>> matches = new HashMap<>();

		ranks.forEach((k, v) -> {

			if (v.size() > 1) {
				matches.put(k, v);
			}
		});

		return matches;

	}

	public static Map<Rank, List<PlayingCard>> getStraight(Map<Rank, List<PlayingCard>> ranks) {

		Map<Rank, List<PlayingCard>> straight = new HashMap<>();
		Stack<Rank> stack = new Stack<>();
		SortedSet<Rank> keys = new TreeSet<>(ranks.keySet());

		boolean hasAce = false;

		if (keys.first().equals(Rank.ACE)) {
			hasAce = true;
		}

		keys.forEach(t -> {
			

			if (stack.isEmpty()) {
				stack.push(t);
			} else {
				
				Rank lastRank = stack.peek();

				if (lastRank.getNumber() - t.getNumber() == 1) {
					stack.push(t);
				} else if (lastRank.getNumber() - t.getNumber() > 1 && straight.size() < PokerConstants.HAND_SIZE) {
					stack.empty();
				}
			}
		}

		);

		if (stack.size() == 4 && keys.last().equals(Rank.TWO) && hasAce) {
			stack.push(keys.first());
		}

		if (stack.size() == PokerConstants.HAND_SIZE) {
			stack.forEach(t -> straight.put(t, ranks.get(t)));
		}

		return straight;

	}

	public static LinkedList<PlayingCard> getStraight(List<PlayingCard> list) {

		LinkedList<PlayingCard> straight = new LinkedList<>();
		Stack<PlayingCard> stack = new Stack<>();

		list.sort(PlayingCard.rankSorter);

		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(list);

		boolean hasAce = false;

		if (cards.getFirst().getRank().equals(Rank.ACE)) {
			hasAce = true;
		}
		

		cards.forEach(card -> {

			

			if (stack.isEmpty()) {
				stack.push(card);
			} 
			else {
				
				PlayingCard lastCard = stack.peek();

				if (lastCard.getRank().getNumber() - card.getRank().getNumber() == 1) {
					stack.push(card);
				} else if (lastCard.getRank().getNumber() - card.getRank().getNumber() > 1 && straight.size() < 5) {
					stack.empty();
				}
			}
		}

		);
		
		cards.forEach(card -> System.out.println(card.toString()));

		if (stack.size() == 4 && cards.getLast().getRank().equals(Rank.TWO) && hasAce) {
			stack.push(cards.getFirst());
		}

		if (stack.size() == 5) {
			stack.forEach(t -> straight.add(t));
		}

		return straight;

	}

}
