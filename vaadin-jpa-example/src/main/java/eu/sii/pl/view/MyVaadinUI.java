package eu.sii.pl.view;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.sii.pl.domain.model.Service;
import eu.sii.pl.domain.model.User;
import eu.sii.pl.domain.utils.DBUtils;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        final JPAContainer<User> container = DBUtils.getUsersContainer();
        final JPAContainer<Service> sContainer = DBUtils.getServiceContainer();
        Table table = new Table(null, sContainer);
        table.setSelectable(true);
        Button button = new Button("Populate");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                DBUtils.populate();
                sContainer.refresh();
                Notification.show("Data populated", Notification.Type.TRAY_NOTIFICATION);
            }
        });
        layout.addComponent(button);
        layout.addComponent(table);
    }

}
