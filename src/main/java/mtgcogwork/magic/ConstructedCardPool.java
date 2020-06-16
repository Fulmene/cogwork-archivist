package mtgcogwork.magic;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multisets;

import mtgcogwork.util.MtgJsonUtil;

public class ConstructedCardPool extends CardPool {

    public static final int MAX_COPIES = 4;

    public static ConstructedCardPool load(Collection<String> setCodes) throws IOException {
        return new ConstructedCardPool(MtgJsonUtil.readMtgJsonSet(setCodes));
    }

    public ConstructedCardPool(Collection<CardSet> sets) {
        super(sets.stream().
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            distinct().
            collect(Multisets.toMultiset(Function.identity(), ConstructedCardPool::getMaxCopies, HashMultiset::create)));
    }

    private static final boolean canHaveAnyNumberOf(Card card) {
        return (card.getSupertypes().contains("basic") && card.getTypes().contains("land")) ||
            card.getText().contains("A deck can have any number of cards named " + card.getName());
    }

    private static final int getMaxCopies(Card card) {
        if (canHaveAnyNumberOf(card))
            return Integer.MAX_VALUE;
        else
            return MAX_COPIES;
    }

}
