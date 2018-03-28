package pgrela.teryt.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SIMC")
public class TownRootXml {
    private TownCatalogXml catalog;

    @XmlElement(name = "catalog")
    public TownCatalogXml getCatalog() {
        return catalog;
    }

    public void setCatalog(TownCatalogXml catalog) {
        this.catalog = catalog;
    }
}
