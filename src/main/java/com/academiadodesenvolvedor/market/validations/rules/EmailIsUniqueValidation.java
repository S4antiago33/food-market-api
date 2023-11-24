package com.academiadodesenvolvedor.market.validations.rules;

import com.academiadodesenvolvedor.market.validations.EmailIsUnique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmailIsUniqueValidation implements ConstraintValidator<EmailIsUnique, Object> {
    private String field;
    private String message;
    private Class<?> entity;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(EmailIsUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entity = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String entity = this.getEntityClass();
        String sql = "SELECT e FROM "+entity+" e WHERE e."+this.field+" = :value";
        List<?> emailList = this.entityManager.createQuery(sql,this.entity)
                .setParameter("value", value)
                .getResultList();
        return emailList.isEmpty();
    }

    private String getEntityClass(){
        String entity = this.entity.toString();

        if(entity.startsWith("class") || entity.startsWith("Class")){
            return entity.split(" ")[1];
        }

        return entity;
    }
}
