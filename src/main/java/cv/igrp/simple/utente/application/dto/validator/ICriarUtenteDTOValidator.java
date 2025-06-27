package cv.igrp.simple.utente.application.dto.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CriarUtenteDTOValidator.class)
public @interface ICriarUtenteDTOValidator {

    String message() default "Invalid custom class";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
   