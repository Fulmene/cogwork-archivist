package alchemagis.magic.quality;

import java.util.Collections;
import java.util.List;

public class KeywordQuality extends MagicCardQualityType {

    private final List<String> keywords;

    public KeywordQuality(List<String> keywords) {
        this.keywords = Collections.unmodifiableList(keywords);
    }

    public boolean contains(String keyword) {
        return this.keywords.contains(keyword);
    }

    public <T> T accept(MagicCardQualityType.Visitor<T> visitor) {
        return visitor.visitKeyword(this);
    }

}
