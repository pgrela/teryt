package pgrela.teryt.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlElement;

public class Town {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String voivodeship;
    private String district;
    private String municipality;
    private String category;
    private String name;
    private String type;
    private LocalDate validOn;

    public String getVoivodeship() {
        return voivodeship;
    }

    @XmlElement(name = "WOJ")
    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
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

    public String getCategory() {
        return category;
    }

    @XmlElement(name = "RODZ_GMI")
    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "NAZWA")
    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalName() {
        return type;
    }

    @XmlElement(name = "NAZDOD")
    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getValidOn() {
        return validOn;
    }

    @XmlElement(name = "STAN_NA")
    public void setValidOn(String validOn) {
        this.validOn = LocalDate.parse(validOn, DATE_TIME_FORMATTER);
    }
}
