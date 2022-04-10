package de.hhu.chicken.infrastructure.web.forms.stereotypes;

import de.hhu.chicken.infrastructure.web.forms.verification.DatumVerifizierer;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DatumVerifizierer.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidDatum {

  String message() default "Das Datum der Klausur liegt außerhalb des Praktikumszeitraums!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
