package pgrela.teryt.presenters;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pgrela.teryt.pojo.Street;
import pgrela.teryt.pojo.Town;

@Component
public class StreetsPresenter {

    static final String FIRST_LINE = "Ulice w miejscowości %s:\n";

    public String presentAsString(List<Street> streets, Town town) {
        return streets.stream()
                .sorted(Comparator.comparing(Street::getName))
                .map(this::toLineDescription)
                .collect(Collectors.joining("\n", String.format(FIRST_LINE, town.getName()), "\n"));
    }

    private String toLineDescription(Street street) {
        return String.format("\t%s %s %s", street.getCategory(), street.getName(), street.getSecondaryName());
    }
}
