package eu.sii.pl.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Service {
    
    @Id
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private User servicePerson;
    @ManyToOne
    private User salesPerson;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public User getServicePerson() {
        return servicePerson;
    }
    public void setServicePerson(User servicePerson) {
        this.servicePerson = servicePerson;
    }
    public User getSalesPerson() {
        return salesPerson;
    }
    public void setSalesPerson(User salesPerson) {
        this.salesPerson = salesPerson;
    }
    
}
