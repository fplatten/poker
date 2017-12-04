package com.culture.games.poker.rules.hands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
import com.culture.games.poker.model.Player;
import com.culture.games.poker.rules.RuleHelper;
import com.culture.games.poker.utils.PokerConstants;
import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule(order = 4)
public class FullHouseRule {
	
	@Given
	private List<Player> players;

	@Result
	private HandType handType;

	@When
	public boolean when() {

		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(players.get(0).getHoleCards());
		cards.addAll(players.get(0).getPokerGame().getBoard());

		Optional<List<PlayingCard>> threeMatch = RuleHelper.findMatch(cards, PokerConstants.PAIR);
		
		Map<Rank,List<PlayingCard>> threes = new HashMap<>();
		Map<Rank,List<PlayingCard>> twos = new HashMap<>();
		
		while(threeMatch.isPresent()){
			threes.put(threeMatch.get().get(0).getRank(), threeMatch.get());
			threeMatch.get().forEach(t  -> cards.remove(t));
			threeMatch = RuleHelper.findMatch(cards, PokerConstants.PAIR);			
		}
		
		if(threes.size() > 0){
			threes.forEach((k,v) -> cards.removeAll(v));
			
			Optional<List<PlayingCard>> twoMatch = RuleHelper.findMatch(cards, 1);
			
			while(twoMatch.isPresent()){
				twos.put(twoMatch.get().get(0).getRank(), twoMatch.get());
				twoMatch.get().forEach(t  -> cards.remove(t));
				twoMatch = RuleHelper.findMatch(cards, 1);
			}
			
			if(twos.size() > 0 || threes.size() > 2){
				List<PlayingCard> topThree = RuleHelper.getMaxRank(threes);
				if(threes.size() > 1){
					threes.remove(topThree.get(0).getRank());
					threes.forEach((k,v) -> twos.put(k, v));
				}
				List<PlayingCard> topTwo = RuleHelper.getMaxRank(twos);
				
				players.get(0).getBestHand().addAll(topThree);
				players.get(0).getBestHand().addAll(RuleHelper.findHighCards(topTwo, 2));
				
				return true;
			}
			
		}
		
		return false;

	}

	@Then
	public RuleState then() {

		handType = HandType.FULL_HOUSE;
		players.get(0).setHandType(handType);
		return RuleState.BREAK;

	}

}
