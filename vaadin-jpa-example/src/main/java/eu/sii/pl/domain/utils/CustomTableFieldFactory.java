package eu.sii.pl.domain.utils;

import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

import eu.sii.pl.domain.model.User;
import eu.sii.pl.domain.model.UserConverter;

public class CustomTableFieldFactory extends DefaultFieldFactory {
    @Override
    public Field createField(Container container, Object itemId, Object propertyId, Component uiContext) {
        Field f = null;
        if (User.class.isAssignableFrom(container.getContainerProperty(itemId, propertyId).getType())) {
            f = new TextField();
            ((TextField) f).setConverter(new UserConverter());
            f.setValue(container.getContainerProperty(itemId, propertyId).toString());
            f.setReadOnly(true);
        } else {
            f = super.createField(container, itemId, propertyId, uiContext);
            if ("id".equals(propertyId)) {
                f.setReadOnly(true);
            }
        }
        return f;
    }

}
