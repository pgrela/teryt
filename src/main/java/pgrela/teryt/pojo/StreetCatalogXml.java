package pgrela.teryt.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Collection;

public class StreetCatalogXml {
    private Collection<Street> streets = new ArrayList<>();

    @XmlElement(name = "row")
    public Collection<Street> getStreets() {
        return streets;
    }
}
