package com.potatoes.bloodrecovery.interfaces.rest.validation;

import com.potatoes.bloodrecovery.interfaces.rest.constants.ValidationErrorMessage;
import com.potatoes.bloodrecovery.interfaces.rest.constants.ValidationErrorType;
import com.potatoes.bloodrecovery.interfaces.rest.dto.DirectedBloodDonationReqDto;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DirectedBloodDonationReqValidation.Validator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DirectedBloodDonationReqValidation {
    String message() default "잘못된 파라미터입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<DirectedBloodDonationReqValidation, DirectedBloodDonationReqDto> {
        private static final String REPLACE_FIRST_STRING = "{0}";

        @Override
        public void initialize(DirectedBloodDonationReqValidation constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(DirectedBloodDonationReqDto value, ConstraintValidatorContext context) {
            return isRequestIdValid(value.getRequestId(), context);
        }

        private boolean isRequestIdValid(Long requestId, ConstraintValidatorContext context) {
            boolean result = requestId != null;
            if (!result)
                addConstraintViolationException(context, getErrorMessageReplace("requestId", ValidationErrorType.BLANK));

            return result;
        }

        private String getErrorMessageReplace(String errorFieldName, ValidationErrorType exceptionType) {
            switch (exceptionType) {
                case EMPTY:
                    return ValidationErrorMessage.EMPTY_PARAMETER.replace(REPLACE_FIRST_STRING, errorFieldName);
                default:
                    return StringUtils.EMPTY;

            }
        }

        private void addConstraintViolationException(ConstraintValidatorContext context, String errorMessage) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
        }
    }
}
