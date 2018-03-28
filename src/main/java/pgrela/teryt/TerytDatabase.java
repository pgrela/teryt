package pgrela.teryt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pgrela.teryt.exceptions.TerytException;
import pgrela.teryt.pojo.Street;
import pgrela.teryt.pojo.StreetRootXml;
import pgrela.teryt.pojo.TeritorialUnit;
import pgrela.teryt.pojo.TeritorialUnitRootXml;
import pgrela.teryt.pojo.Town;
import pgrela.teryt.pojo.TownRootXml;
import pgrela.teryt.presenters.StreetsPresenter;
import pgrela.teryt.presenters.TownsPresenter;

@Component
public class TerytDatabase {
    private static final Logger LOG = LogManager.getLogger(TerytDatabase.class);
    private final String streetsZipFile;
    private final String streetsFile;
    private final String territorialUnitsZipFile;
    private final String territorialUnitsFile;
    private final String townsZipFile;
    private final String townsFile;
    private Map<String, TeritorialUnit> teritorialUnitMap;
    private Map<String, List<Street>> streetsByTerritorialIdMap;
    private Map<String, List<Town>> townsByTerritorialIdMap;
    private TownsPresenter townsPresenter;
    private StreetsPresenter streetsPresenter;

    @Autowired
    public TerytDatabase(
            @Value("${teryt.territorialUnitsZipFile:/TERC_Urzedowy_2018-03-27.zip}") String territorialUnitsZipFile,
            @Value("${teryt.territorialUnitsFile:TERC_Urzedowy_2018-03-27.xml}") String territorialUnitsFile,
            @Value("${teryt.streetsZipFile:/ULIC_Adresowy_2018-03-27.zip}") String streetsZipFile,
            @Value("${teryt.streetsFile:ULIC_Adresowy_2018-03-27.xml}") String streetsFile,
            @Value("${teryt.townsZipFile:/SIMC_Adresowy_2018-03-28.zip}") String townsZipFile,
            @Value("${teryt.townsFile:SIMC_Adresowy_2018-03-28.xml}") String townsFile,
            TownsPresenter townsPresenter,
            StreetsPresenter streetsPresenter) {
        this.territorialUnitsZipFile = territorialUnitsZipFile;
        this.territorialUnitsFile = territorialUnitsFile;
        this.streetsZipFile = streetsZipFile;
        this.streetsFile = streetsFile;
        this.townsZipFile = townsZipFile;
        this.townsFile = townsFile;
        this.townsPresenter = townsPresenter;
        this.streetsPresenter = streetsPresenter;
    }

    @PostConstruct
    void loadData() throws URISyntaxException, IOException, JAXBException {
        loadTerritorialUnits();
        loadTowns();
        loadStreets();
    }

    private void loadTowns() throws URISyntaxException, IOException, JAXBException {
        LOG.info("Loading towns started.");
        ZipFile zipFile = getZipFile(townsZipFile);
        InputStream zipFileInputStream = getFileFromZip(zipFile, townsFile);
        TownRootXml townRootXml = parseXml(zipFileInputStream, TownRootXml.class);

        mapTowns(townRootXml);

        LOG.info(String.format("Loading towns finished. Loaded %d units.", townRootXml.getCatalog().getTowns().size()));
    }

    private void mapTowns(TownRootXml townRootXml) {
        townsByTerritorialIdMap = townRootXml.getCatalog().getTowns()
                .stream()
                .collect(Collectors.groupingBy(this::toMunicipalityId));
    }

    private String toMunicipalityId(Town town) {
        return town.getVoivodeship() + town.getDistrict() + town.getMunicipality() + town.getCategory();
    }

    private void loadTerritorialUnits() throws URISyntaxException, IOException, JAXBException {
        LOG.info("Loading territorial units started.");
        ZipFile zipFile = getZipFile(territorialUnitsZipFile);
        InputStream zipFileInputStream = getFileFromZip(zipFile, territorialUnitsFile);
        TeritorialUnitRootXml teritorialUnitRootXml = parseXml(zipFileInputStream, TeritorialUnitRootXml.class);

        mapTerritorialUnits(teritorialUnitRootXml);

        LOG.info(String.format("Loading territorial units finished. Loaded %d units.", teritorialUnitMap.size()));
    }

    private void loadStreets() throws URISyntaxException, IOException, JAXBException {
        LOG.info("Loading streets started.");
        ZipFile zipFile = getZipFile(streetsZipFile);
        InputStream zipFileInputStream = getFileFromZip(zipFile, streetsFile);
        StreetRootXml streetRootXml = parseXml(zipFileInputStream, StreetRootXml.class);

        mapStreets(streetRootXml);

        LOG.info(String.format("Loading streets finished. Loaded %d streets.",
                streetRootXml.getCatalog().getStreets().size()));
    }

    private void mapStreets(StreetRootXml streetRootXml) {
        streetsByTerritorialIdMap = streetRootXml.getCatalog()
                .getStreets()
                .stream()
                .collect(Collectors.groupingBy(Street::getTeritorialUnitId));
    }

    private void mapTerritorialUnits(TeritorialUnitRootXml teritorialUnitRootXml) {
        teritorialUnitMap = teritorialUnitRootXml.getCatalog()
                .getTeritorialUnits()
                .stream()
                .filter(unit -> unit.getMunicipality()!=null)
                .collect(Collectors.toMap(Teryt::getTerytId, teritorialUnit -> teritorialUnit));
    }

    private <RESULT_TYPE> RESULT_TYPE parseXml(InputStream zipFileInputStream, Class<RESULT_TYPE> clazz)
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (RESULT_TYPE) jaxbUnmarshaller.unmarshal(zipFileInputStream);
    }

    private InputStream getFileFromZip(ZipFile zipFile, String file) throws IOException {
        return zipFile.getInputStream(zipFile.getEntry(file));
    }

    private ZipFile getZipFile(String zipFile) throws URISyntaxException, IOException {
        File territorialUnitFile = new File(Teryt.class.getClass().getResource(zipFile).toURI());
        return new ZipFile(territorialUnitFile);
    }

    public String query(String identifier) {
        if (isMunicipalityIdentifier(identifier)) {
            return townsPresenter.presentAsString(townsByTerritorialIdMap.get(identifier));
        } else if (isCityIdentifier(identifier)) {
            return streetsPresenter.presentAsString(streetsByTerritorialIdMap.get(identifier));
        }
        throw new TerytException(String.format("Identifier %s is not valid", identifier));
    }

    private boolean isMunicipalityIdentifier(String identifier) {
        return townsByTerritorialIdMap.containsKey(identifier);
    }

    public boolean isCityIdentifier(String identifier) {
        return streetsByTerritorialIdMap.containsKey(identifier);
    }
}
