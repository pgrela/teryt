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

        String query = "80203022";
        //query = "0936032";
        Scanner consoleInput = new Scanner(System.in);
        while (!"exit".equals(query = consoleInput.nextLine())) {
            try {
                System.out.println(terytDatabase.query(query));
            } catch (TerytException e) {
                LOG.warn(e);
            }
        }
    }

    static String getTerytId(TeritorialUnit teritorialUnit) {
        return teritorialUnit.getVoivodeship() + teritorialUnit.getDistrict() + teritorialUnit.getMunicipality() +
                teritorialUnit.getCategory();
    }
}
