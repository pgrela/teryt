package pgrela.teryt;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pgrela.teryt.exceptions.TerytException;
import pgrela.teryt.pojo.TeritorialUnit;

public class Teryt {
    private static final Logger LOG = LogManager.getLogger(Teryt.class);
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);
        TerytDatabase terytDatabase = context.getBean(TerytDatabase.class);

        Scanner consoleInput = new Scanner(System.in);
        System.out.println("Aby wyjść wpisz \"exit\"");
        printHintLine();
        String query;
        while (!"exit".equals(query = consoleInput.nextLine())) {
            try {
                System.out.println(terytDatabase.query(query));
            } catch (TerytException e) {
                LOG.warn(e);
            }
            printHintLine();
        }
    }

    private static void printHintLine() {
        System.out.print("Wpisz kod TERYT gminy lub miejscowości: ");
    }

    static String getTerytId(TeritorialUnit teritorialUnit) {
        return teritorialUnit.getVoivodeship() + teritorialUnit.getDistrict() + teritorialUnit.getMunicipality() +
                teritorialUnit.getCategory();
    }
}
