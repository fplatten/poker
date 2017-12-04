package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.cards.Suit;
import com.culture.games.poker.model.Hand;
import com.culture.games.poker.model.Player;
import com.culture.games.poker.rules.RuleHelper;
import com.culture.games.poker.utils.PokerConstants;
import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule(order=1)
public class RoyalFlushRule {
	
	
	@Given
	private List<Player> players;
	
	@Result
	private HandType handType;
	
	private LinkedList<PlayingCard> bestCards = new LinkedList<>();
	
	@When
	public boolean when(){
		
		
		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(players.get(0).getHoleCards());
		cards.addAll(players.get(0).getPokerGame().getBoard());
		
		Map<Suit,List<PlayingCard>> suits = cards.stream().collect(Collectors.groupingBy(PlayingCard::getSuit,Collectors.toList()));
		Map<Rank,List<PlayingCard>> ranks = cards.stream().collect(Collectors.groupingBy(PlayingCard::getRank,Collectors.toList()));
		
		
		List<PlayingCard> flush = RuleHelper.getFlush(suits);
		Map<Rank,List<PlayingCard>> foo = RuleHelper.getStraight(ranks);
		
		if(flush.size() == PokerConstants.HAND_SIZE){
			LinkedList<PlayingCard> straight = RuleHelper.getStraight(flush);
			if(straight.size() == PokerConstants.HAND_SIZE 
					&& straight.getFirst().getRank().equals(Rank.ACE)
					&& straight.getLast().getRank().equals(Rank.TEN)){
				bestCards.addAll(RuleHelper.findHighCards(straight, PokerConstants.HAND_SIZE));
				return true;
			}
		}
		
		return false;
	}
	
	@Then
	public RuleState then(){
		
		handType = HandType.ROYAL_FLUSH;
		players.get(0).setHand(new Hand(handType,bestCards));
		return RuleState.BREAK;
		
	}

}
