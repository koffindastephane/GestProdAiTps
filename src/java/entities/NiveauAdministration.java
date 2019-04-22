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
@Table(name = "NiveauAdministration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NiveauAdministration.findAll", query = "SELECT n FROM NiveauAdministration n")})
public class NiveauAdministration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numNiveauAdministration")
    private Integer numNiveauAdministration;
    @Size(max = 100)
    @Column(name = "nomNiveauAdministration")
    private String nomNiveauAdministration;
    @OneToMany(mappedBy = "niveauAdministration")
    private List<Groupe> groupeList;

    public NiveauAdministration() {
    }

    public NiveauAdministration(Integer numNiveauAdministration) {
        this.numNiveauAdministration = numNiveauAdministration;
    }

    public Integer getNumNiveauAdministration() {
        return numNiveauAdministration;
    }

    public void setNumNiveauAdministration(Integer numNiveauAdministration) {
        this.numNiveauAdministration = numNiveauAdministration;
    }

    public String getNomNiveauAdministration() {
        return nomNiveauAdministration;
    }

    public void setNomNiveauAdministration(String nomNiveauAdministration) {
        this.nomNiveauAdministration = nomNiveauAdministration;
    }

    @XmlTransient
    public List<Groupe> getGroupeList() {
        return groupeList;
    }

    public void setGroupeList(List<Groupe> groupeList) {
        this.groupeList = groupeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numNiveauAdministration != null ? numNiveauAdministration.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NiveauAdministration)) {
            return false;
        }
        NiveauAdministration other = (NiveauAdministration) object;
        if ((this.numNiveauAdministration == null && other.numNiveauAdministration != null) || (this.numNiveauAdministration != null && !this.numNiveauAdministration.equals(other.numNiveauAdministration))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NiveauAdministration[ numNiveauAdministration=" + numNiveauAdministration + " ]";
    }
    
}
