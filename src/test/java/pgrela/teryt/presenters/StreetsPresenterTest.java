package pgrela.teryt.presenters;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import pgrela.teryt.pojo.Street;
import pgrela.teryt.pojo.StreetBuilder;
import pgrela.teryt.pojo.Town;
import pgrela.teryt.pojo.TownBuilder;

public class StreetsPresenterTest {

    private StreetsPresenter streetsPresenter = new StreetsPresenter();

    @Test
    public void shouldFormatStreets() {
        // given
        Town town = TownBuilder.someTown().withName("Poznań").build();
        List<Street> streets = Arrays.asList(
                StreetBuilder.someStreet()
                        .withCategory("ul.")
                        .withName("Słowackiego")
                        .withSecondaryName("Juliusza")
                        .build(),
                StreetBuilder.someStreet()
                        .withCategory("plac")
                        .withName("Konstytucji 3 maja")
                        .withSecondaryName("")
                        .build()
        );

        // when
        String presentedText = streetsPresenter.presentAsString(streets, town);

        // then
        Assertions.assertThat(presentedText).isEqualTo(
                "Ulice w miejscowości Poznań:\n"
                        + "\tplac Konstytucji 3 maja \n"
                        + "\tul. Słowackiego Juliusza\n");
    }
}