package pgrela.teryt.pojo;

public class StreetBuilder {
    private String voivodship;
    private String district;
    private String municipality;
    private String municipalityType;
    private String teritorialUnitId;
    private String streetId;
    private String category;
    private String name;
    private String secondaryName;

    protected StreetBuilder() {
    }

    public static StreetBuilder builder() {
        return new StreetBuilder();
    }

    public static StreetBuilder someStreet() {
        return builder().withVoivodship("06")
                .withDistrict("55")
                .withMunicipality("32")
                .withMunicipalityType("4")
                .withTeritorialUnitId("2346234")
                .withStreetId("23452423")
                .withCategory("ul.")
                .withName("Mickiewicza")
                .withSecondaryName("Adama");
    }

    public StreetBuilder withVoivodship(String voivodship) {
        this.voivodship = voivodship;
        return this;
    }

    public StreetBuilder withDistrict(String district) {
        this.district = district;
        return this;
    }

    public StreetBuilder withMunicipality(String municipality) {
        this.municipality = municipality;
        return this;
    }

    public StreetBuilder withMunicipalityType(String municipalityType) {
        this.municipalityType = municipalityType;
        return this;
    }

    public StreetBuilder withTeritorialUnitId(String teritorialUnitId) {
        this.teritorialUnitId = teritorialUnitId;
        return this;
    }

    public StreetBuilder withStreetId(String streetId) {
        this.streetId = streetId;
        return this;
    }

    public StreetBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public StreetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StreetBuilder withSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
        return this;
    }

    public Street build() {
        Street street = new Street();
        street.setVoivodship(voivodship);
        street.setDistrict(district);
        street.setMunicipality(municipality);
        street.setMunicipalityType(municipalityType);
        street.setTeritorialUnitId(teritorialUnitId);
        street.setStreetId(streetId);
        street.setCategory(category);
        street.setName(name);
        street.setSecondaryName(secondaryName);
        return street;
    }
}