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
@Table(name = "Etat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etat.findAll", query = "SELECT e FROM Etat e")})
public class Etat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numEtat")
    private Integer numEtat;
    @Size(max = 100)
    @Column(name = "nomEtat")
    private String nomEtat;
    @OneToMany(mappedBy = "etat")
    private List<DocumentFourni> documentFourniList;

    public Etat() {
    }

    public Etat(Integer numEtat) {
        this.numEtat = numEtat;
    }

    public Integer getNumEtat() {
        return numEtat;
    }

    public void setNumEtat(Integer numEtat) {
        this.numEtat = numEtat;
    }

    public String getNomEtat() {
        return nomEtat;
    }

    public void setNomEtat(String nomEtat) {
        this.nomEtat = nomEtat;
    }

    @XmlTransient
    public List<DocumentFourni> getDocumentFourniList() {
        return documentFourniList;
    }

    public void setDocumentFourniList(List<DocumentFourni> documentFourniList) {
        this.documentFourniList = documentFourniList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numEtat != null ? numEtat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etat)) {
            return false;
        }
        Etat other = (Etat) object;
        if ((this.numEtat == null && other.numEtat != null) || (this.numEtat != null && !this.numEtat.equals(other.numEtat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etat[ numEtat=" + numEtat + " ]";
    }
    
}
