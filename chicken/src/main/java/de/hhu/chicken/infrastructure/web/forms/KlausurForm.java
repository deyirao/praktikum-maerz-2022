package de.hhu.chicken.infrastructure.web.forms;

import de.hhu.chicken.domain.klausur.Klausur;
import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsValidDatum;
import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsValidId;
import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsValidOnlineKlausurZeitraum;
import de.hhu.chicken.infrastructure.web.forms.stereotypes.IsValidPraesenzKlausurZeitraum;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class KlausurForm {

  @NotEmpty
  private String fach;

  @NotNull
  @IsValidDatum
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate datum;

  @NotNull
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime von;

  @NotNull
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime bis;

  private Boolean isPraesenz;

  @NotNull
  @IsValidId
  private Long veranstaltungsId;

  @IsValidPraesenzKlausurZeitraum
  private List<LocalTime> praesenzKlausurZeitraum;

  @IsValidOnlineKlausurZeitraum
  private List<LocalTime> onlineKlausurZeitraum;

  public KlausurForm(String fach, LocalDate datum, LocalTime von, LocalTime bis,
                     Boolean isPraesenz, Long veranstaltungsId) {
    this.fach = fach;
    this.datum = datum;
    this.von = von;
    this.bis = bis;
    this.isPraesenz = isPraesenz;
    this.veranstaltungsId = veranstaltungsId;

    if (von == null || bis == null) {
      return;
    }

    if (isPraesenz) {
      this.praesenzKlausurZeitraum = List.of(von, bis);
      return;
    }
    this.onlineKlausurZeitraum = List.of(von, bis);
  }

  public Klausur toKlausur() {
    return new Klausur(null,
        fach,
        datum,
        von,
        bis,
        isPraesenz,
        veranstaltungsId);
  }

  public List<LocalTime> getPraesenzKlausurZeitraum() {
    if (praesenzKlausurZeitraum == null) {
      return List.of();
    }
    return List.copyOf(praesenzKlausurZeitraum);
  }

  public List<LocalTime> getOnlineKlausurZeitraum() {
    if (onlineKlausurZeitraum == null) {
      return List.of();
    }
    return List.copyOf(onlineKlausurZeitraum);
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

  public String getFach() {
    return fach;
  }

  public Boolean isPraesenz() {
    return isPraesenz;
  }

  public Long getVeranstaltungsId() {
    return veranstaltungsId;
  }
}