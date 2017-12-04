package com.culture.games.poker.rules.hands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.cards.Rank;
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

@Rule(order = 8)
public class TwoPairRule {
	
	@Given
	private List<Player> players;

	@Result
	private HandType handType;
	
	private LinkedList<PlayingCard> bestCards = new LinkedList<>();

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
		
		if(twos.size() > 1){
			
			// get top pair and add to best hand
			List<PlayingCard> topPair = RuleHelper.getMaxRank(twos);
			bestCards.addAll(topPair);
			twos.remove(topPair.get(0).getRank());
			
			// get second pair and add to best hand
			List<PlayingCard> secondPair = RuleHelper.getMaxRank(twos);
			bestCards.addAll(secondPair);
			twos.remove(secondPair.get(0).getRank());
			
			//remove two pair from available cards
			cards.removeAll(bestCards);
			
			//get high card to fill out hand
			bestCards.addAll(RuleHelper.findHighCards(cards, 1));
			
			return true;
		}
		
		return false;

	}

	@Then
	public RuleState then() {

		handType = HandType.TWO_PAIR;
		players.get(0).setHand(new Hand(handType,bestCards));
		return RuleState.BREAK;

	}

}
