package pgrela.teryt.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teryt")
public class TeritorialUnitRootXml {
    private TeritorialUnitCatalogXml catalog;

    @XmlElement(name = "catalog")
    public TeritorialUnitCatalogXml getCatalog() {
        return catalog;
    }

    public void setCatalog(TeritorialUnitCatalogXml catalog) {
        this.catalog = catalog;
    }
}
