package pgrela.teryt.pojo;

public class TownBuilder {
    private String voivodeship;
    private String district;
    private String municipality;
    private String category;
    private String name;
    private String type;
    private String townIdentifier;

    protected TownBuilder() {
    }

    public static TownBuilder builder() {
        return new TownBuilder();
    }

    public static TownBuilder someTown() {
        return builder().withVoivodeship("02")
                .withDistrict("03")
                .withMunicipality("11")
                .withCategory("1")
                .withName("Poznań")
                .withType("5")
                .withTownIdentifier("0324764");
    }

    public TownBuilder withVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
        return this;
    }

    public TownBuilder withDistrict(String district) {
        this.district = district;
        return this;
    }

    public TownBuilder withMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public TownBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public TownBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TownBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public TownBuilder withTownIdentifier(String townIdentifier) {
        this.townIdentifier = townIdentifier;
        return this;
    }

    public Town build() {
        Town town = new Town();
        town.setVoivodeship(voivodeship);
        town.setDistrict(district);
        town.setMunicipality(municipality);
        town.setCategory(category);
        town.setName(name);
        town.setType(type);
        town.setTownIdentifier(townIdentifier);
        return town;
    }
}