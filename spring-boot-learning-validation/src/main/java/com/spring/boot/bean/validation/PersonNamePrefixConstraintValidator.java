package com.spring.boot.bean.validation;

import com.spring.boot.bean.validation.constraints.PersonNamePrefix;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author lianp
 * @date 2019/1/3 17:30
 * @since
 **/
public class PersonNamePrefixConstraintValidator implements ConstraintValidator<PersonNamePrefix, String> {

    private String prefix;

    @Override
    public void initialize( PersonNamePrefix constraintAnnotation ) {
        this.prefix = constraintAnnotation.prefix();
    }

    @Override
    public boolean isValid( String value, ConstraintValidatorContext context ) {

        if ( StringUtils.isEmpty( value ) ) {
            return false;
        }
        if ( !value.startsWith( prefix ) ) {
            return false;
        }
        return true;
    }
}
