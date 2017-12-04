package com.culture.games.poker.rules.hands;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.culture.games.poker.HandType;
import com.culture.games.poker.cards.PlayingCard;
import com.culture.games.poker.model.Player;
import com.culture.games.poker.rules.RuleHelper;
import com.culture.games.poker.utils.PokerConstants;
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
		
		Optional<List<PlayingCard>> fourMatch =cards.stream()
		.collect(Collectors.groupingBy(PlayingCard::getRank, Collectors.toList()))
		.values().stream().filter(p -> p.size() == 4).findAny();
		
		if(fourMatch.isPresent()){
			players.get(0).getBestHand().addAll(fourMatch.get());
			cards.removeAll(fourMatch.get());
			players.get(0).getBestHand().addAll(RuleHelper.findHighCards(cards, 1));
			return true;
		}
		
		return false;

	}

	@Then
	public RuleState then() {

		handType = HandType.FOUR_OF_A_KIND;
		players.get(0).setHandType(handType);
		return RuleState.BREAK;

	}

}
