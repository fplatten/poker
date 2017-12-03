package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.culture.games.poker.HandType;
import com.culture.games.poker.Player;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.rules.RuleHelper;
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

		Optional<List<PlayingCard>> firstThree = RuleHelper.findMatch(cards, 2);
		
		if(firstThree.isPresent()){
			cards.removeAll(firstThree.get());
			
			Optional<List<PlayingCard>> firstTwo = RuleHelper.findMatch(cards, 1);
			
			if(firstTwo.isPresent()){
				return true;
			}
			
		}
		
		
		return false;
		

	}

	@Then
	public RuleState then() {
		
		//TODO: add best five cards to player class

		handType = HandType.FULL_HOUSE;
		return RuleState.BREAK;

	}

}
