package com.culture.games.poker.rules.hands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

@Rule(order = 9)
public class PairRule {
	
	@Given
	private List<Player> players;

	@Result
	private HandType handType;

	@When
	public boolean when() {

		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(players.get(0).getHoleCards());
		cards.addAll(players.get(0).getPokerGame().getBoard());
		
		Optional<List<PlayingCard>> twoMatch = RuleHelper.findMatch(cards, 1);
		Map<Rank,List<PlayingCard>> twos = new HashMap<>();
		
		while(twoMatch.isPresent()){
			twos.put(twoMatch.get().get(0).getRank(), twoMatch.get());
			twoMatch.get().forEach(t  -> cards.remove(t));
			twoMatch = RuleHelper.findMatch(cards, 1);			
		}
		
		if(twos.size() == 1){
			
			List<PlayingCard> topPair = RuleHelper.getMaxRank(twos);
			players.get(0).getBestHand().addAll(topPair);
			topPair.forEach(t -> cards.remove(t));
			
			//get high card to fill out hand
			players.get(0).getBestHand().addAll(RuleHelper.findHighCards(cards, PokerConstants.THREE_OF_A_KIND));
			
			return true;
		}
		
		return false;

	}

	@Then
	public RuleState then() {
		
		//TODO: add best five cards to player class

		handType = HandType.PAIR;
		players.get(0).setHandType(handType);
		return RuleState.BREAK;

	}

}
