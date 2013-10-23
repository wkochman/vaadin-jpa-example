package eu.sii.pl.domain.model;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class UserConverter implements Converter<String, User> {

    @Override
    public User convertToModel(String value, Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToPresentation(User value, Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
        return value.toString();
    }

    @Override
    public Class<User> getModelType() {
        return User.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }

}
