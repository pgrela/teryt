package pgrela.teryt.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Street {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String voivodship;
    private String district;
    private String municipality;
    private String municipalityType;
    private String teritorialUnitId;
    private String streetId;
    private String category;
    private String name;
    private String secondaryName;
    private LocalDate validOn;

    public String getVoivodship() {
        return voivodship;
    }

    @XmlElement(name = "WOJ")
    public void setVoivodship(String voivodship) {
        this.voivodship = voivodship;
    }

    public String getDistrict() {
        return district;
    }

    @XmlElement(name = "POW")
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMunicipality() {
        return municipality;
    }

    @XmlElement(name = "GMI")
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getMunicipalityType() {
        return municipalityType;
    }

    @XmlElement(name = "RODZ_GMI")
    public void setMunicipalityType(String municipalityType) {
        this.municipalityType = municipalityType;
    }

    public String getTeritorialUnitId() {
        return teritorialUnitId;
    }

    @XmlElement(name = "SYM")
    public void setTeritorialUnitId(String teritorialUnitId) {
        this.teritorialUnitId = teritorialUnitId;
    }

    public String getStreetId() {
        return streetId;
    }

    @XmlElement(name = "SYM_UL")
    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public String getCategory() {
        return category;
    }

    @XmlElement(name = "CECHA")
    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "NAZWA_1")
    public void setName(String name) {
        this.name = name;
    }

    public String getSecondaryName() {
        return secondaryName;
    }

    @XmlElement(name = "NAZWA_2")
    public void setSecondaryName(String secondaryName) {
        this.secondaryName = secondaryName;
    }

    public LocalDate getValidOn() {
        return validOn;
    }

    @XmlElement(name = "STAN_NA")
    public void setValidOn(String validOn) {
        this.validOn = LocalDate.parse(validOn, DATE_TIME_FORMATTER);
    }
}
