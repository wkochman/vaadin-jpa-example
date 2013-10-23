package eu.sii.pl.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="users")
public class User {
    @TableGenerator(name = "U_Gen", table = "ID_GEN_U", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "U_Gen")
    private Long id;
    private String name;
    private String lastName;
    private Date birthday;
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
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Override
    public String toString() {
        return new StringBuilder().append(name).append(" ").append(lastName).toString();
    }
    
}
