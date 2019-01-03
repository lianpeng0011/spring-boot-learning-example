package com.spring.boot.bean.validation.constraints;

import com.spring.boot.bean.validation.PersonNamePrefixConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD } )
@Retention( RUNTIME )
@Documented
@Constraint( validatedBy = { PersonNamePrefixConstraintValidator.class } )
public @interface PersonNamePrefix {

    String message() default "{king.PersonNamePrefix.massage}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String prefix() default "lp-";
}
