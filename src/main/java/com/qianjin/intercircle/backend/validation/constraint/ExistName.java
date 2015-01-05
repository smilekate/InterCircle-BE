package com.qianjin.intercircle.backend.validation.constraint;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.qianjin.intercircle.backend.common.Utilities;

@Constraint(validatedBy = { ExistName.ExistNameValidator.class })
@Target(value = { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistName {

	String message() default "{Constants.Errors.EXISTNAME_MSGKEY}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	public class ExistNameValidator implements
			ConstraintValidator<ExistName, Serializable> {

		@Autowired
		private HibernateTemplate hibernateTemplate;

		@Override
		public void initialize(ExistName arg0) {

		}

		@Override
		public boolean isValid(Serializable target,
				ConstraintValidatorContext context) {
			Class<?> entityClass = target.getClass();

			String nameValue = (String) Utilities.getValue("name", entityClass);

			List<?> nameExists = hibernateTemplate.find("from "
					+ entityClass.getSimpleName() + " as r where r.name = '"
					+ nameValue.replace("'", "''") + "'");

			if (nameExists == null || nameExists.size() == 0) {
				return true;
			}

			return false;
		}

	}
}
