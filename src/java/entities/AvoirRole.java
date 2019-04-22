/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "AvoirRole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvoirRole.findAll", query = "SELECT a FROM AvoirRole a")})
public class AvoirRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numAvoirRole")
    private Integer numAvoirRole;
    @JoinColumn(name = "groupe", referencedColumnName = "numGroupe")
    @ManyToOne
    private Groupe groupe;
    @JoinColumn(name = "role", referencedColumnName = "numRole")
    @ManyToOne
    private Role role;

    public AvoirRole() {
    }

    public AvoirRole(Integer numAvoirRole) {
        this.numAvoirRole = numAvoirRole;
    }

    public Integer getNumAvoirRole() {
        return numAvoirRole;
    }

    public void setNumAvoirRole(Integer numAvoirRole) {
        this.numAvoirRole = numAvoirRole;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numAvoirRole != null ? numAvoirRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvoirRole)) {
            return false;
        }
        AvoirRole other = (AvoirRole) object;
        if ((this.numAvoirRole == null && other.numAvoirRole != null) || (this.numAvoirRole != null && !this.numAvoirRole.equals(other.numAvoirRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AvoirRole[ numAvoirRole=" + numAvoirRole + " ]";
    }
    
}
