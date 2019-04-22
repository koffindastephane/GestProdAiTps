/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numRole")
    private Integer numRole;
    @Size(max = 100)
    @Column(name = "nomRole")
    private String nomRole;
    @OneToMany(mappedBy = "role")
    private List<AvoirRole> avoirRoleList;

    public Role() {
    }

    public Role(Integer numRole) {
        this.numRole = numRole;
    }

    public Integer getNumRole() {
        return numRole;
    }

    public void setNumRole(Integer numRole) {
        this.numRole = numRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    @XmlTransient
    public List<AvoirRole> getAvoirRoleList() {
        return avoirRoleList;
    }

    public void setAvoirRoleList(List<AvoirRole> avoirRoleList) {
        this.avoirRoleList = avoirRoleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numRole != null ? numRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.numRole == null && other.numRole != null) || (this.numRole != null && !this.numRole.equals(other.numRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Role[ numRole=" + numRole + " ]";
    }
    
}
