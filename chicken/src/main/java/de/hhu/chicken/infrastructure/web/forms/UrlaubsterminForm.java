package de.hhu.chicken.infrastructure.web.forms;

import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsValidDatum;
import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsValidUrlaubsterminZeitraum;
import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsVielfacheVon15Minuten;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class UrlaubsterminForm {

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @IsValidDatum
  @NotNull
  private LocalDate datum;

  @DateTimeFormat(pattern = "HH:mm")
  @IsVielfacheVon15Minuten
  @NotNull
  private LocalTime von;

  @DateTimeFormat(pattern = "HH:mm")
  @IsVielfacheVon15Minuten
  @NotNull
  private LocalTime bis;

  @IsValidUrlaubsterminZeitraum
  private List<LocalTime> urlaubsterminZeitraum;

  public UrlaubsterminForm(LocalDate datum, LocalTime von, LocalTime bis) {
    this.datum = datum;
    this.von = von;
    this.bis = bis;

    if (von == null || bis == null) {
      return;
    }

    this.urlaubsterminZeitraum = List.of(von, bis);
  }

  public List<LocalTime> getUrlaubsterminZeitraum() {
    if (urlaubsterminZeitraum == null) {
      return List.of();
    }
    return List.copyOf(urlaubsterminZeitraum);
  }

  public LocalDate getDatum() {
    return datum;
  }

  public LocalTime getVon() {
    return von;
  }

  public LocalTime getBis() {
    return bis;
  }
}
