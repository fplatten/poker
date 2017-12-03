package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.culture.games.poker.HandType;
import com.culture.games.poker.Player;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.rules.RuleHelper;
import com.culture.games.poker.utils.PokerConstants;
import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule(order = 6)
public class StraightRule {
	
	@Given
	private List<Player> players;
	
	@Result
	private HandType handType;
	
	@When
	public boolean when(){
		
		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(players.get(0).getHoleCards());
		cards.addAll(players.get(0).getPokerGame().getBoard());
		
		Map<Rank,List<PlayingCard>> ranks = cards.stream().collect(Collectors.groupingBy(PlayingCard::getRank,Collectors.toList()));
		Map<Rank, List<PlayingCard>> straight = RuleHelper.getStraight(ranks);
		
		if(straight.size() == PokerConstants.HAND_SIZE){
			players.get(0).getBestHand().addAll(RuleHelper.findHighCards(straight, PokerConstants.HAND_SIZE));
			return true;
		}
		
		return false;
	}
	
	@Then
	public RuleState then(){
		
		handType = HandType.STRAIGHT;
		players.get(0).setHandType(handType);
		return RuleState.BREAK;
		
	}

}
