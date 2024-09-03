/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Persistence.Enums.FilterType;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author soyky
 */
public class BuilderFilter {

    private CriteriaBuilder builder;
    private From<?, ?> entity;
    private String search;

    public BuilderFilter(CriteriaBuilder builder, From<?, ?> entity, String search) {
        this.builder = builder;
        this.entity = entity;
        this.search = search;
    }
    
    public Predicate createFilter(FilterType filterType){
          switch (filterType) {
        case PRICE:
            return priceFilter();
        case SCHEDULING:
            return AfterSchedulingFilter();
        case DENTIST:
            return dentistFilter();
        case PATIENTS:
            return patientFilter();
        case PREDETERMINED:
            return predeterminedFilter();
        default:
            throw new IllegalArgumentException("Invalid filter type: " + filterType);
    }
    }

    private Predicate predeterminedFilter() {
        return builder.equal(builder.literal(1), 1);
    }

    private Predicate patientFilter() {
        Pattern pattern = Pattern.compile("^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\\\s]+$");
        Matcher matcher = pattern.matcher(search);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid format");
        }
        return builder.equal(entity.get("firstName"), search);
    }

    private Predicate dentistFilter() {
        Pattern pattern = Pattern.compile("^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\\\s]+$");
        Matcher matcher = pattern.matcher(search);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid format");
        }
        return builder.equal(entity.get("firstName"), search);
    }

    private Predicate priceFilter() {
        Pattern pricePattern = Pattern.compile("^\\d+-\\d+$");
        Matcher matcher = pricePattern.matcher(search);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid format");
        }
        List<Integer> priceRange = ExctractAndConvertDate.extractDate(search, "-");
        return builder.between(entity.get("price"), priceRange.get(0), priceRange.get(1));
    }

    private Predicate AfterSchedulingFilter() {
        Pattern schedulingPattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}$");
        Matcher matcher = schedulingPattern.matcher(search);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid format");
        }
        List<Integer> dateParts = ExctractAndConvertDate.extractDate(search, "-");
        Calendar scheduling = ExctractAndConvertDate.convertToDate(dateParts);
        return builder.greaterThanOrEqualTo(entity.get("scheduling"), scheduling);
        
    }

}
