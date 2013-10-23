package eu.sii.pl.view;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Like;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.sii.pl.domain.model.Service;
import eu.sii.pl.domain.model.User;
import eu.sii.pl.domain.utils.CustomTableFieldFactory;
import eu.sii.pl.domain.utils.DBUtils;
import eu.sii.pl.domain.utils.EnhancedFieldGroupFieldFactory;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
    final VerticalLayout layout = new VerticalLayout();
    final FieldGroup fg = new FieldGroup();
    final JPAContainer<User> container = DBUtils.getUsersContainer();
    final JPAContainer<Service> sContainer = DBUtils.getServiceContainer();
    Table table = null;
    TabSheet tabs = null;

    @Override
    protected void init(VaadinRequest request) {
        initTabs();
        setContent(tabs);
        
        Button button = new Button("Populate");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                DBUtils.populate();
                sContainer.refresh();
                Notification.show("Data populated", Notification.Type.TRAY_NOTIFICATION);
            }
        });

        final TextField tf = new TextField();
        tf.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                sContainer.removeAllContainerFilters();
                Like like = new Like("name", tf.getValue());
                sContainer.addContainerFilter(like);
            }
        });
        
        layout.addComponent(button);
        layout.addComponent(tf);
        layout.addComponent(initTable());
        layout.addComponent(initTableEdit());
        layout.addComponent(initFields(new BeanItem<Service>(new Service())));
    }
    
    private CheckBox initTableEdit(){
        final CheckBox tableEdit = new CheckBox();
        tableEdit.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                table.setEditable(tableEdit.getValue());
            }
        });
       
        return tableEdit;
    }
    
    private void initTabs(){
        tabs = new TabSheet();
        tabs.setSizeFull();
        
        layout.setMargin(true);
        tabs.addTab(layout, "GridJpa");
        
    }
    
    private FormLayout initFields(Item item) {
        final FormLayout fl = new FormLayout();
        fg.setItemDataSource(item);
        fg.setFieldFactory(new EnhancedFieldGroupFieldFactory());
        fl.addComponent(fg.buildAndBind("Name", "name"));
        fl.addComponent(fg.buildAndBind("Start Date", "startDate", DateField.class));
        fl.addComponent(fg.buildAndBind("End Date", "endDate", DateField.class));
        fl.addComponent(new Button("OK", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fg.commit();
                    sContainer.commit();
                } catch (CommitException e) {
                    e.printStackTrace();
                }

            }

        }));
        return fl;
    }

    private Table initTable() {
        table = new Table(null, sContainer);
        table.setSelectable(true);
        table.setImmediate(true);
        table.setSizeFull();
        table.setColumnCollapsingAllowed(true);
        table.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Object contactId = table.getValue();
                if (contactId != null) {
                    fg.setItemDataSource(table.getItem(contactId));
                }

            }
        });
        table.setTableFieldFactory(new CustomTableFieldFactory());
        return table;
    }

}
