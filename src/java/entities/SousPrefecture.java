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
@Table(name = "SousPrefecture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousPrefecture.findAll", query = "SELECT s FROM SousPrefecture s")})
public class SousPrefecture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "numSousPrefecture")
    private String numSousPrefecture;
    @Size(max = 100)
    @Column(name = "nomSousPrefecture")
    private String nomSousPrefecture;
    @OneToMany(mappedBy = "sousPrefecture")
    private List<Commune> communeList;

    public SousPrefecture() {
    }

    public SousPrefecture(String numSousPrefecture) {
        this.numSousPrefecture = numSousPrefecture;
    }

    public String getNumSousPrefecture() {
        return numSousPrefecture;
    }

    public void setNumSousPrefecture(String numSousPrefecture) {
        this.numSousPrefecture = numSousPrefecture;
    }

    public String getNomSousPrefecture() {
        return nomSousPrefecture;
    }

    public void setNomSousPrefecture(String nomSousPrefecture) {
        this.nomSousPrefecture = nomSousPrefecture;
    }

    @XmlTransient
    public List<Commune> getCommuneList() {
        return communeList;
    }

    public void setCommuneList(List<Commune> communeList) {
        this.communeList = communeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numSousPrefecture != null ? numSousPrefecture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SousPrefecture)) {
            return false;
        }
        SousPrefecture other = (SousPrefecture) object;
        if ((this.numSousPrefecture == null && other.numSousPrefecture != null) || (this.numSousPrefecture != null && !this.numSousPrefecture.equals(other.numSousPrefecture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SousPrefecture[ numSousPrefecture=" + numSousPrefecture + " ]";
    }
    
}
