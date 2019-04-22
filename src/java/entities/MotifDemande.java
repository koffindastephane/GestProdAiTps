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
@Table(name = "MotifDemande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotifDemande.findAll", query = "SELECT m FROM MotifDemande m")})
public class MotifDemande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numMotifDemande")
    private Integer numMotifDemande;
    @Size(max = 100)
    @Column(name = "nomMotifDemande")
    private String nomMotifDemande;
    @OneToMany(mappedBy = "motifDemande")
    private List<MotiverDemande> motiverDemandeList;

    public MotifDemande() {
    }

    public MotifDemande(Integer numMotifDemande) {
        this.numMotifDemande = numMotifDemande;
    }

    public Integer getNumMotifDemande() {
        return numMotifDemande;
    }

    public void setNumMotifDemande(Integer numMotifDemande) {
        this.numMotifDemande = numMotifDemande;
    }

    public String getNomMotifDemande() {
        return nomMotifDemande;
    }

    public void setNomMotifDemande(String nomMotifDemande) {
        this.nomMotifDemande = nomMotifDemande;
    }

    @XmlTransient
    public List<MotiverDemande> getMotiverDemandeList() {
        return motiverDemandeList;
    }

    public void setMotiverDemandeList(List<MotiverDemande> motiverDemandeList) {
        this.motiverDemandeList = motiverDemandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numMotifDemande != null ? numMotifDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MotifDemande)) {
            return false;
        }
        MotifDemande other = (MotifDemande) object;
        if ((this.numMotifDemande == null && other.numMotifDemande != null) || (this.numMotifDemande != null && !this.numMotifDemande.equals(other.numMotifDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MotifDemande[ numMotifDemande=" + numMotifDemande + " ]";
    }
    
}
