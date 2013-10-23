package eu.sii.pl.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class Service {
    
    @TableGenerator(name = "S_Gen", table = "ID_GEN_S", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "S_Gen")
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
