package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;

import com.culture.games.poker.HandType;
import com.culture.games.poker.Player;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.rules.RuleHelper;
import com.culture.games.poker.utils.PokerConstants;
import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule(order = 10)
public class HighCardRule {
	
	@Given
	private List<Player> players;

	@Result
	private HandType handType;

	@When
	public boolean when() {
		
		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(players.get(0).getHoleCards());
		cards.addAll(players.get(0).getPokerGame().getBoard());
		players.get(0).getBestHand().addAll(RuleHelper.findHighCards(cards, PokerConstants.HAND_SIZE));
		return true;

	}

	@Then
	public RuleState then() {
		
		//TODO: add best five cards to player class

		handType = HandType.HIGH_CARD;
		players.get(0).setHandType(handType);
		return RuleState.BREAK;

	}

}
