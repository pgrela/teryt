package pgrela.teryt.pojo;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Collection;

public class TeritorialUnitCatalogXml {
    private Collection<TeritorialUnit> teritorialUnits = new ArrayList<>();

    @XmlElement(name = "row")
    public Collection<TeritorialUnit> getTeritorialUnits() {
        return teritorialUnits;
    }
}
