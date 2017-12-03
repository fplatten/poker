package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.culture.games.poker.HandType;
import com.culture.games.poker.Player;
import com.culture.games.poker.cards.PlayingCard;
import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule(order = 3)
public class FourOfAKindRule {

	@Given
	private List<Player> players;

	@Result
	private HandType handType;

	@When
	public boolean when() {

		LinkedList<PlayingCard> cards = new LinkedList<>();
		cards.addAll(players.get(0).getHoleCards());
		cards.addAll(players.get(0).getPokerGame().getBoard());

		return cards.stream()
				.collect(Collectors.groupingBy(PlayingCard::getRank, Collectors.toList()))
				.values().stream().anyMatch(p -> p.size() == 4);

	}

	@Then
	public RuleState then() {
		
		//TODO: add best five cards to player class

		handType = HandType.FOUR_OF_A_KIND;
		return RuleState.BREAK;

	}

}
