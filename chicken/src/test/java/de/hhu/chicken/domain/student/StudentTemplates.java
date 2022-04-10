package de.hhu.chicken.domain.student;

import java.time.LocalDate;
import java.time.LocalTime;

public class StudentTemplates {

  static void fuegeUrlaubsterminHinzu(Urlaubstermin urlaubstermin, Student student,
                                      boolean istKlausurtag) {
    student.fuegeUrlaubsterminHinzu(urlaubstermin.getDatum(),
        urlaubstermin.getVon(),
        urlaubstermin.getBis(),
        istKlausurtag,
        LocalTime.of(9, 30),
        LocalTime.of(13, 30));
  }

  static Urlaubstermin urlaubsterminTemplate(int stundeVon, int minVon, int stundeBis, int minBis) {
    return new Urlaubstermin(LocalDate.of(2022, 2, 15),
        LocalTime.of(stundeVon, minVon),
        LocalTime.of(stundeBis, minBis));
  }

  static Student studentMitMehrerenUrlaubsterminen() {
    Student student = new Student(14529531L, "jensbendisposto");

    Urlaubstermin urlaubstermin = urlaubsterminTemplate(9, 30, 10, 30);
    Urlaubstermin urlaubstermin2 = urlaubsterminTemplate(11, 0, 12, 0);
    Urlaubstermin urlaubstermin3 = urlaubsterminTemplate(12, 30, 13, 0);

    fuegeUrlaubsterminHinzu(urlaubstermin, student, true);
    fuegeUrlaubsterminHinzu(urlaubstermin2, student, true);
    fuegeUrlaubsterminHinzu(urlaubstermin3, student, true);

    return student;
  }

  static Student studentMitEinemUrlaubstermin(int stundeVon,
                                              int minVon, int stundeBis, int minBis) {
    Student student = new Student(14529531L, "jensbendisposto");

    Urlaubstermin urlaubstermin = urlaubsterminTemplate(stundeVon, minVon, stundeBis, minBis);

    fuegeUrlaubsterminHinzu(urlaubstermin, student, true);

    return student;
  }
}
