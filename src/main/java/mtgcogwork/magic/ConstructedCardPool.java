package mtgcogwork.magic;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Function;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multisets;

import mtgcogwork.util.MtgJsonUtil;

public class ConstructedCardPool extends CardPool {

    public static ConstructedCardPool load(Collection<String> setCodes) throws IOException {
        return new ConstructedCardPool(MtgJsonUtil.readMtgJsonSet(setCodes));
    }

    public ConstructedCardPool(Collection<CardSet> sets) {
        super(sets.stream().
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            distinct().
            collect(Multisets.toMultiset(Function.identity(), MagicConstants::getMaxCopies, HashMultiset::create)));
    }

}
