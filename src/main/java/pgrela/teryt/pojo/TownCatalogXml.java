package pgrela.teryt.pojo;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;

public class TownCatalogXml {
    private Collection<Town> streets = new ArrayList<>();

    @XmlElement(name = "row")
    public Collection<Town> getTowns() {
        return streets;
    }
}
