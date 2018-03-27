package pgrela.teryt.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ULIC")
public class StreetRootXml {
    private StreetCatalogXml catalog;

    @XmlElement(name = "catalog")
    public StreetCatalogXml getCatalog() {
        return catalog;
    }

    public void setCatalog(StreetCatalogXml catalog) {
        this.catalog = catalog;
    }
}
