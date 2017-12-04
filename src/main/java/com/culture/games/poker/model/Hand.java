package com.culture.games.poker.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.rules.RuleHelper;

public class Hand implements Comparable<Hand>{
	
	LinkedList<PlayingCard> cards = new LinkedList<>();
	private HandType handType;
	
	public Hand(){}
	
	public Hand(HandType handType, LinkedList<PlayingCard> cards){
		this.handType = handType;
		this.cards.addAll(cards);
	}
	
	public Hand(HandType handType, List<PlayingCard> cards){
		this.handType = handType;
		this.cards.addAll(cards);
	}
	
	public LinkedList<PlayingCard> getCards() {
		return cards;
	}
	public void setCards(LinkedList<PlayingCard> cards) {
		this.cards = cards;
	}
	public HandType getHandType() {
		return handType;
	}
	public void setHandType(HandType handType) {
		this.handType = handType;
	}

	@Override
	public int compareTo(Hand hand) {
		
		int comp = this.handType.compareTo(hand.getHandType());
		
		if(comp != 0 ){
			return comp;
		}
		
		return differentiate(this, hand);
	}
	
	public static Comparator<Hand> handComparator = new Comparator<Hand>() {
		@Override
		public int compare(Hand h1, Hand h2) {
			return h1.compareTo(h2);
		}
	};
	
	// all these methods are to compare the same hands with each other
	
	public int differentiate(Hand hand1, Hand hand2){
		
		if(HandType.ROYAL_FLUSH.equals(hand1.getHandType()) && HandType.ROYAL_FLUSH.equals(hand2.getHandType())){
			return 0;
		}
		else if(HandType.STRAIGHT_FLUSH.equals(hand1.getHandType()) && HandType.STRAIGHT_FLUSH.equals(hand2.getHandType())){
			return compareStraightFlush( hand1, hand2);
		}
		else if(HandType.FOUR_OF_A_KIND.equals(hand1.getHandType()) && HandType.FOUR_OF_A_KIND.equals(hand2.getHandType())){
			return compareFourOfAKind( hand1, hand2);
		}
		else if(HandType.FULL_HOUSE.equals(hand1.getHandType()) && HandType.FULL_HOUSE.equals(hand2.getHandType())){
			return compareFullHouse( hand1, hand2);
		}
		else if(HandType.FLUSH.equals(hand1.getHandType()) && HandType.FLUSH.equals(hand2.getHandType())){
			return compareFlush( hand1, hand2);
		}
		else if(HandType.STRAIGHT.equals(hand1.getHandType()) && HandType.STRAIGHT.equals(hand2.getHandType())){
			return compareStraight( hand1, hand2);
		}
		else if(HandType.THREE_OF_A_KIND.equals(hand1.getHandType()) && HandType.THREE_OF_A_KIND.equals(hand2.getHandType())){
			return compareThreeOfAKind( hand1, hand2);
		}
		else if(HandType.TWO_PAIR.equals(hand1.getHandType()) && HandType.TWO_PAIR.equals(hand2.getHandType())){
			return compareTwoPair( hand1, hand2);
		}
		else if(HandType.PAIR.equals(hand1.getHandType()) && HandType.PAIR.equals(hand2.getHandType())){
			return comparePair( hand1, hand2);
		}
		else if(HandType.HIGH_CARD.equals(hand1.getHandType()) && HandType.HIGH_CARD.equals(hand2.getHandType())){
			return compareHighCard( hand1, hand2);
		}
		
		
		return 0;
	}

	public int compareStraightFlush(Hand hand1, Hand hand2){
		
		boolean hand1HasAce = hand1.cards.stream().anyMatch(p -> Rank.ACE.equals(p.getRank()));
		boolean hand1HasFive = hand1.cards.stream().anyMatch(p -> Rank.FIVE.equals(p.getRank()));
		
		boolean hand2HasAce = hand2.cards.stream().anyMatch(p -> Rank.ACE.equals(p.getRank()));
		boolean hand2HasFive = hand2.cards.stream().anyMatch(p -> Rank.FIVE.equals(p.getRank()));
		
		if(hand1HasAce && hand1HasFive){
			
			if(hand2HasAce && hand2HasFive){
				return 0;
			}
			else{
				return 1;
			}
		}
		else{
			
			if(hand2HasAce && hand2HasFive){
				return -1;
			}
		}
		
		return compareHighCard( hand1, hand2);
	}
	
	public int compareFourOfAKind(Hand hand1, Hand hand2){
		
		Optional<List<PlayingCard>> hand1Match = RuleHelper.findMatch(hand1.getCards(), 3);
		Optional<List<PlayingCard>> hand2Match = RuleHelper.findMatch(hand2.getCards(), 3);
		
		int comp = hand1Match.get().get(0).getRank().compareTo(hand2Match.get().get(0).getRank());
		
		if(comp == 0){
			
			Optional<PlayingCard> card1 = hand1.getCards().stream().filter(p -> !p.getRank().equals(hand1Match.get().get(0).getRank())).findFirst();
			Optional<PlayingCard> card2 = hand2.getCards().stream().filter(p -> !p.getRank().equals(hand2Match.get().get(0).getRank())).findFirst();
			
			return card1.get().getRank().compareTo(card2.get().getRank());
			
		}
		
		return comp;
	}
	
	public int compareFlush(Hand hand1, Hand hand2){
		
		return compareHighCard( hand1, hand2);
	}
	
	public int compareStraight(Hand hand1, Hand hand2){
		
		return compareStraightFlush( hand1, hand2);
	}
	
	public int compareFullHouse(Hand hand1, Hand hand2){
		
		Optional<List<PlayingCard>> hand1Match = RuleHelper.findMatch(hand1.getCards(), 2);
		Optional<List<PlayingCard>> hand2Match = RuleHelper.findMatch(hand2.getCards(), 2);
		
		int comp = hand1Match.get().get(0).getRank().compareTo(hand2Match.get().get(0).getRank());
		
		if(comp == 0){
			
			List<PlayingCard> highCards1 = hand1.getCards().stream().filter(p -> !p.getRank().equals(hand1Match.get().get(0).getRank())).collect(Collectors.toList());
			List<PlayingCard> highCards2 = hand2.getCards().stream().filter(p -> !p.getRank().equals(hand2Match.get().get(0).getRank())).collect(Collectors.toList());
			
			return compareRankLists(highCards1, highCards2);
			
		}
		
		return comp;
	}
	
	public int compareThreeOfAKind(Hand hand1, Hand hand2){
		
		Optional<List<PlayingCard>> hand1Match = RuleHelper.findMatch(hand1.getCards(), 2);
		Optional<List<PlayingCard>> hand2Match = RuleHelper.findMatch(hand2.getCards(), 2);
		
		int comp = hand1Match.get().get(0).getRank().compareTo(hand2Match.get().get(0).getRank());
		
		if(comp == 0){
			
			List<PlayingCard> highCards1 = hand1.getCards().stream().filter(p -> !p.getRank().equals(hand1Match.get().get(0).getRank())).collect(Collectors.toList());
			List<PlayingCard> highCards2 = hand2.getCards().stream().filter(p -> !p.getRank().equals(hand2Match.get().get(0).getRank())).collect(Collectors.toList());
			
			return compareRankLists(highCards1, highCards2);
			
		}
		
		return comp;
	}
	
	public int compareTwoPair(Hand hand1, Hand hand2){
		
		Map<Rank,List<PlayingCard>> ranks1 = hand1.getCards().stream().collect(Collectors.groupingBy(PlayingCard::getRank,Collectors.toList()));
		Map<Rank,List<PlayingCard>> ranks2 = hand2.getCards().stream().collect(Collectors.groupingBy(PlayingCard::getRank,Collectors.toList()));
		
		Map<Rank,List<PlayingCard>> pairs1 = new HashMap<>();
		Map<Rank,List<PlayingCard>> pairs2 = new HashMap<>();
		
		ranks1.forEach((k,v) -> {if(v.size() > 1) pairs1.put(k, v);});
		ranks2.forEach((k,v) -> {if(v.size() > 1) pairs2.put(k, v);});
		
		SortedSet<Rank> keys1 = new TreeSet<>(pairs1.keySet());
		SortedSet<Rank> keys2 = new TreeSet<>(pairs2.keySet());
		
		int comp = 0;
		
		comp = keys1.first().compareTo(keys2.first());
		if(comp == 0 ){
			comp = keys1.last().compareTo(keys2.last());
		}
		else{
			return comp;
		}
		
		if(comp == 0){
			
			pairs1.clear();
			pairs2.clear();
			
			ranks1.forEach((k,v) -> {if(v.size() == 1) pairs1.put(k, v);});
			ranks2.forEach((k,v) -> {if(v.size() == 1) pairs2.put(k, v);});
			
			keys1 = new TreeSet<>(pairs1.keySet());
			keys2 = new TreeSet<>(pairs2.keySet());
			
			return keys1.first().compareTo(keys2.first());
			
		}
		
		return comp;
	}
	
	public int comparePair(Hand hand1, Hand hand2){
		
		Optional<List<PlayingCard>> hand1Match = RuleHelper.findMatch(hand1.getCards(), 1);
		Optional<List<PlayingCard>> hand2Match = RuleHelper.findMatch(hand2.getCards(), 1);
		
		int comp = hand1Match.get().get(0).getRank().compareTo(hand2Match.get().get(0).getRank());
		
		if(comp == 0){
			
			List<PlayingCard> highCards1 = hand1.getCards().stream().filter(p -> !p.getRank().equals(hand1Match.get().get(0).getRank())).collect(Collectors.toList());
			List<PlayingCard> highCards2 = hand2.getCards().stream().filter(p -> !p.getRank().equals(hand2Match.get().get(0).getRank())).collect(Collectors.toList());
			
			return compareRankLists(highCards1, highCards2);
			
		}
		
		return comp;
	}
	
	public int compareHighCard(Hand hand1, Hand hand2){
		
		hand1.getCards().sort(PlayingCard.rankSorter);
		hand2.getCards().sort(PlayingCard.rankSorter);
		
		int comp = 0;
		
		return compareRankLists(hand1.getCards(),hand2.getCards());
	}
	
	public int compareRankLists(List<PlayingCard> list1, List<PlayingCard> list2){
		
		list1.sort(PlayingCard.rankSorter);
		list2.sort(PlayingCard.rankSorter);
		
		int comp = 0;
		
		for(int i = 0; i < list1.size(); ++i){
			comp = list1.get(i).getRank().compareTo(list2.get(i).getRank());
			if(comp != 0){
				return comp;
			}
		}
		
		return comp;
	}
	
	public int compareRankLists(LinkedList<PlayingCard> list1, LinkedList<PlayingCard> list2){
		
		list1.sort(PlayingCard.rankSorter);
		list2.sort(PlayingCard.rankSorter);
		
		int comp = 0;
		
		for(int i = 0; i < list1.size(); ++i){
			comp = list1.get(i).getRank().compareTo(list2.get(i).getRank());
			if(comp != 0){
				return comp;
			}
		}
		
		return comp;
	}
}
