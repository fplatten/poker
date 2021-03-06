package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
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

@Rule(order = 7)
public class ThreeOfAKindRule {
	
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

		Optional<List<PlayingCard>> threeMatch = RuleHelper.findMatch(cards, PokerConstants.PAIR);
		
		if(threeMatch.isPresent()){
			bestCards.addAll(threeMatch.get());
			cards.removeAll(threeMatch.get());
			bestCards.addAll(RuleHelper.findHighCards(cards, PokerConstants.PAIR));
			return true;
		}		
		
		return false;

	}

	@Then
	public RuleState then() {

		handType = HandType.THREE_OF_A_KIND;
		players.get(0).setHand(new Hand(handType,bestCards));
		return RuleState.BREAK;

	}

}
