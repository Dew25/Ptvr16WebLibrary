/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.secure;

import entity.Role;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Melnikov
 */
@Entity
public class Permision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private PermisionName permisionName;
    @OneToOne
    private Role role;
    

    public Permision() {
    }

    public Permision(PermisionName permisionName, Role role) {
        this.permisionName = permisionName;
        this.role = role;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermisionName getPermisionName() {
        return permisionName;
    }

    public void setPermisionName(PermisionName permisionName) {
        this.permisionName = permisionName;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.permisionName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permision other = (Permision) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.permisionName, other.permisionName)) {
            return false;
        }
       
        return true;
    }

    @Override
    public String toString() {
        return "Permision{" 
                + "id=" + id 
                + ", permisionName=" + permisionName.getName()
                + '}';
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    
}
