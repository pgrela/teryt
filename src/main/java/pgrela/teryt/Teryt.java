package pgrela.teryt;

import pgrela.teryt.pojo.StreetRootXml;
import pgrela.teryt.pojo.TeritorialUnit;
import pgrela.teryt.pojo.TeritorialUnitRootXml;
import pgrela.teryt.presenters.TeritorialUnitPresenter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

public class Teryt {
    public static void main(String[] args) {
        String query = "02";
        query = new Scanner(System.in).nextLine();
        String streetsUrl = "http://eteryt.stat.gov.pl/eTeryt/rejestr_teryt/udostepnianie_danych/baza_teryt/uzytkownicy_indywidualni/pobieranie/pliki_pelne.aspx?contrast=default";
        String teritorialUnitsUrl = "/TERC_Urzedowy_2018-03-27.zip";
        try {
            File file1 = new File(Teryt.class.getClass().getResource(teritorialUnitsUrl).toURI());
            ZipFile zipFile = new ZipFile(file1);
            InputStream zipFileInputStream = zipFile.getInputStream(zipFile.getEntry("TERC_Urzedowy_2018-03-27.xml"));
            JAXBContext jaxbContext = JAXBContext.newInstance(TeritorialUnitRootXml.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TeritorialUnitRootXml customer = (TeritorialUnitRootXml) jaxbUnmarshaller.unmarshal(zipFileInputStream);
            System.out.println(customer.getCatalog().getTeritorialUnits().iterator().next().getName());

            Map<String, TeritorialUnit> teritorialUnitMap = customer.getCatalog().getTeritorialUnits().stream().collect(Collectors.toMap(Teryt::getTerytId, teritorialUnit -> teritorialUnit));
            System.out.println(new TeritorialUnitPresenter().presentAsString(teritorialUnitMap.get(query)));

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (true) return;
        try {

            File file = new File("C:\\Users\\Patryk\\IdeaProjects\\teryt\\src\\main\\resources\\ULIC_Adresowy_2018-03-27.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(StreetRootXml.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StreetRootXml customer = (StreetRootXml) jaxbUnmarshaller.unmarshal(file);
            System.out.println(customer.getCatalog().getStreets().iterator().next().getName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static String getTerytId(TeritorialUnit teritorialUnit) {
        return teritorialUnit.getVoivodeship()  + teritorialUnit.getDistrict() + teritorialUnit.getMunicipality()
                +teritorialUnit.getCategory();
    }
}
