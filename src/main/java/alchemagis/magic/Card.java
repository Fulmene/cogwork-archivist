package alchemagis.magic;

import java.util.ArrayList;
import java.util.Collections;
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
        this.colors = createColorList(colors);
        this.manaCost = manaCost;
        this.convertedManaCost = (int)convertedManaCost;
        this.supertypes = createTypeList(supertypes);
        this.types = createTypeList(types);
        this.subtypes = createTypeList(subtypes);
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

    private static final List<String> createColorList(String[] colors) {
        List<String> colorList = new ArrayList<>();
        for (String c : colors) {
            switch (c) {
                case "W" : colorList.add("white"); break;
                case "U" : colorList.add("blue"); break;
                case "B" : colorList.add("black"); break;
                case "R" : colorList.add("red"); break;
                case "G" : colorList.add("green"); break;
                default: throw new IllegalArgumentException("Unknown color symbol " + c);
            }
        }
        return Collections.unmodifiableList(colorList);
    }

    private static final List<String> createTypeList(String[] types) {
        List<String> typeList = new ArrayList<>();
        for (String t : types)
            typeList.add(t.toLowerCase());
        return Collections.unmodifiableList(typeList);
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
        return this.name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return this.name.toLowerCase().hashCode();
    }

}
