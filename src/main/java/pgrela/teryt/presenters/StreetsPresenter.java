package pgrela.teryt.presenters;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import pgrela.teryt.pojo.Street;
import pgrela.teryt.pojo.Town;

@Component
public class StreetsPresenter {
    public String presentAsString(List<Street> streets) {
        return streets.stream()
                .sorted(Comparator.comparing(Street::getName))
                .map(this::toLineDescription)
                .collect(Collectors.joining("\n"));
    }

    private String toLineDescription(Street street) {
        return String.format("%s %s", street.getCategory(), street.getName());
    }
}
