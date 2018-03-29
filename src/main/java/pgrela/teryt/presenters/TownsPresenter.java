package pgrela.teryt.presenters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pgrela.teryt.pojo.TeritorialUnit;
import pgrela.teryt.pojo.Town;

@Component
public class TownsPresenter {
    public String presentAsString(List<Town> towns, TeritorialUnit teritorialUnit) {
        return towns.stream()
                .map(this::toLineDescription)
                .collect(
                        Collectors.joining("\n", String.format("Miejscowości w gminie %s:\n", teritorialUnit.getName()),
                                "\n"));
    }

    private String toLineDescription(Town town) {
        return town.getName();
    }
}
