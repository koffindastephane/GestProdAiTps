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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Commune")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commune.findAll", query = "SELECT c FROM Commune c")})
public class Commune implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "numCommune")
    private String numCommune;
    @Size(max = 100)
    @Column(name = "nomCommune")
    private String nomCommune;
    @JoinColumn(name = "sousPrefecture", referencedColumnName = "numSousPrefecture")
    @ManyToOne
    private SousPrefecture sousPrefecture;
    @OneToMany(mappedBy = "commune")
    private List<Centre> centreList;

    public Commune() {
    }

    public Commune(String numCommune) {
        this.numCommune = numCommune;
    }

    public String getNumCommune() {
        return numCommune;
    }

    public void setNumCommune(String numCommune) {
        this.numCommune = numCommune;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public SousPrefecture getSousPrefecture() {
        return sousPrefecture;
    }

    public void setSousPrefecture(SousPrefecture sousPrefecture) {
        this.sousPrefecture = sousPrefecture;
    }

    @XmlTransient
    public List<Centre> getCentreList() {
        return centreList;
    }

    public void setCentreList(List<Centre> centreList) {
        this.centreList = centreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCommune != null ? numCommune.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commune)) {
            return false;
        }
        Commune other = (Commune) object;
        if ((this.numCommune == null && other.numCommune != null) || (this.numCommune != null && !this.numCommune.equals(other.numCommune))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Commune[ numCommune=" + numCommune + " ]";
    }
    
}
