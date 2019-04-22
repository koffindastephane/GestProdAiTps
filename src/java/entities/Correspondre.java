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
@Table(name = "Correspondre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Correspondre.findAll", query = "SELECT c FROM Correspondre c")})
public class Correspondre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numCorrespondre")
    private Integer numCorrespondre;
    @Column(name = "nbJoursPourRdv")
    private Integer nbJoursPourRdv;
    @JoinColumn(name = "typeDemande", referencedColumnName = "numTypeDemande")
    @ManyToOne
    private TypeDemande typeDemande;
    @JoinColumn(name = "casDemande", referencedColumnName = "numCasDemande")
    @ManyToOne
    private CasDemande casDemande;

    public Correspondre() {
    }

    public Correspondre(Integer numCorrespondre) {
        this.numCorrespondre = numCorrespondre;
    }

    public Integer getNumCorrespondre() {
        return numCorrespondre;
    }

    public void setNumCorrespondre(Integer numCorrespondre) {
        this.numCorrespondre = numCorrespondre;
    }

    public Integer getNbJoursPourRdv() {
        return nbJoursPourRdv;
    }

    public void setNbJoursPourRdv(Integer nbJoursPourRdv) {
        this.nbJoursPourRdv = nbJoursPourRdv;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public CasDemande getCasDemande() {
        return casDemande;
    }

    public void setCasDemande(CasDemande casDemande) {
        this.casDemande = casDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCorrespondre != null ? numCorrespondre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Correspondre)) {
            return false;
        }
        Correspondre other = (Correspondre) object;
        if ((this.numCorrespondre == null && other.numCorrespondre != null) || (this.numCorrespondre != null && !this.numCorrespondre.equals(other.numCorrespondre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Correspondre[ numCorrespondre=" + numCorrespondre + " ]";
    }
    
}
