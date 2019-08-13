package alchemagis.magic;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import alchemagis.util.FileUtil;
import alchemagis.util.RandomUtil;

public final class CardPool {

    private Set<Card> cards;

    public static CardPool loadCardPool(File ...files) {
        return new CardPool(Arrays.stream(files).map(FileUtil::readMtgJsonSetFile).toArray(CardSet[]::new));
    }

    public CardPool(CardSet ...sets) {
        this.cards = Arrays.stream(sets).
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            collect(Collectors.toSet());
    }

    public Set<Card> getCards() {
        return this.cards;
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    public Card getRandomCard() {
        return null;
    }

}
