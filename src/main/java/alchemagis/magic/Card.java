package alchemagis.magic;

import java.lang.NumberFormatException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Card {

    private final String name;
    private final List<String> colors;
    private final String manaCost;
    private final int convertedManaCost;
    private final List<String> supertypes;
    private final List<String> types;
    private final List<String> subtypes;
    private int power;
    private int toughness;
    private final String text;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Card(
        @JsonProperty("name") String name,
        @JsonProperty("colors") String[] colors,
        @JsonProperty("manaCost") String manaCost,
        @JsonProperty("convertedManaCost") double convertedManaCost,
        @JsonProperty("supertypes") String[] supertypes,
        @JsonProperty("types") String[] types,
        @JsonProperty("subtypes") String[] subtypes,
        @JsonProperty("power") String power,
        @JsonProperty("toughness") String toughness,
        @JsonProperty("text") String text
    ) {
        this.name = name;
        this.colors = List.of(colors);
        this.manaCost = manaCost;
        this.convertedManaCost = (int)convertedManaCost;
        this.supertypes = List.of(supertypes);
        this.types = List.of(types);
        this.subtypes = List.of(subtypes);
        try {
            this.power = Integer.parseInt(power);
        } catch (NumberFormatException e) {
            this.power = 0;
        }
        try {
            this.toughness = Integer.parseInt(toughness);
        } catch (NumberFormatException e) {
            this.toughness = 0;
        }
        this.text = (text == null) ? "" : text;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getColors() {
        return this.colors;
    }

    public String getManaCost() {
        return this.manaCost;
    }

    public int getConvertedManaCost() {
        return this.convertedManaCost;
    }

    public List<String> getSupertypes() {
        return this.supertypes;
    }

    public List<String> getTypes() {
        return this.types;
    }

    public List<String> getSubtypes() {
        return this.subtypes;
    }

    public int getPower() {
        return this.power;
    }

    public int getToughness() {
        return this.toughness;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Card))
            return false;
        Card other = (Card) o;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
