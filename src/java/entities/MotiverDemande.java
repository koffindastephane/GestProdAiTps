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
@Table(name = "MotiverDemande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotiverDemande.findAll", query = "SELECT m FROM MotiverDemande m")})
public class MotiverDemande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numMotiverDemande")
    private Integer numMotiverDemande;
    @JoinColumn(name = "casDemande", referencedColumnName = "numCasDemande")
    @ManyToOne
    private CasDemande casDemande;
    @JoinColumn(name = "motifDemande", referencedColumnName = "numMotifDemande")
    @ManyToOne
    private MotifDemande motifDemande;

    public MotiverDemande() {
    }

    public MotiverDemande(Integer numMotiverDemande) {
        this.numMotiverDemande = numMotiverDemande;
    }

    public Integer getNumMotiverDemande() {
        return numMotiverDemande;
    }

    public void setNumMotiverDemande(Integer numMotiverDemande) {
        this.numMotiverDemande = numMotiverDemande;
    }

    public CasDemande getCasDemande() {
        return casDemande;
    }

    public void setCasDemande(CasDemande casDemande) {
        this.casDemande = casDemande;
    }

    public MotifDemande getMotifDemande() {
        return motifDemande;
    }

    public void setMotifDemande(MotifDemande motifDemande) {
        this.motifDemande = motifDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numMotiverDemande != null ? numMotiverDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotiverDemande)) {
            return false;
        }
        MotiverDemande other = (MotiverDemande) object;
        if ((this.numMotiverDemande == null && other.numMotiverDemande != null) || (this.numMotiverDemande != null && !this.numMotiverDemande.equals(other.numMotiverDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MotiverDemande[ numMotiverDemande=" + numMotiverDemande + " ]";
    }
    
}
