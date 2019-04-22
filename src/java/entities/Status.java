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
@Table(name = "Status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")})
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numStatus")
    private Integer numStatus;
    @Size(max = 100)
    @Column(name = "nomStatus")
    private String nomStatus;
    @OneToMany(mappedBy = "status")
    private List<Evoluer> evoluerList;
    @OneToMany(mappedBy = "statusActuel")
    private List<Demande> demandeList;

    public Status() {
    }

    public Status(Integer numStatus) {
        this.numStatus = numStatus;
    }

    public Integer getNumStatus() {
        return numStatus;
    }

    public void setNumStatus(Integer numStatus) {
        this.numStatus = numStatus;
    }

    public String getNomStatus() {
        return nomStatus;
    }

    public void setNomStatus(String nomStatus) {
        this.nomStatus = nomStatus;
    }

    @XmlTransient
    public List<Evoluer> getEvoluerList() {
        return evoluerList;
    }

    public void setEvoluerList(List<Evoluer> evoluerList) {
        this.evoluerList = evoluerList;
    }

    @XmlTransient
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numStatus != null ? numStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.numStatus == null && other.numStatus != null) || (this.numStatus != null && !this.numStatus.equals(other.numStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Status[ numStatus=" + numStatus + " ]";
    }
    
}
