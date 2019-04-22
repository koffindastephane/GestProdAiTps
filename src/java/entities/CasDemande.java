/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "CasDemande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasDemande.findAll", query = "SELECT c FROM CasDemande c")})
public class CasDemande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numCasDemande")
    private Integer numCasDemande;
    @Size(max = 100)
    @Column(name = "nomCasDemande")
    private String nomCasDemande;
    @OneToMany(mappedBy = "casDemande")
    private List<DocumentNecessaire> documentNecessaireList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casDemande")
    private List<Recu> recuList;
    @OneToMany(mappedBy = "casDemande")
    private List<Correspondre> correspondreList;
    @OneToMany(mappedBy = "casDemande")
    private List<MotiverDemande> motiverDemandeList;

    public CasDemande() {
    }

    public CasDemande(Integer numCasDemande) {
        this.numCasDemande = numCasDemande;
    }

    public Integer getNumCasDemande() {
        return numCasDemande;
    }

    public void setNumCasDemande(Integer numCasDemande) {
        this.numCasDemande = numCasDemande;
    }

    public String getNomCasDemande() {
        return nomCasDemande;
    }

    public void setNomCasDemande(String nomCasDemande) {
        this.nomCasDemande = nomCasDemande;
    }

    @XmlTransient
    public List<DocumentNecessaire> getDocumentNecessaireList() {
        return documentNecessaireList;
    }

    public void setDocumentNecessaireList(List<DocumentNecessaire> documentNecessaireList) {
        this.documentNecessaireList = documentNecessaireList;
    }

    @XmlTransient
    public List<Recu> getRecuList() {
        return recuList;
    }

    public void setRecuList(List<Recu> recuList) {
        this.recuList = recuList;
    }

    @XmlTransient
    public List<Correspondre> getCorrespondreList() {
        return correspondreList;
    }

    public void setCorrespondreList(List<Correspondre> correspondreList) {
        this.correspondreList = correspondreList;
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
        hash += (numCasDemande != null ? numCasDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasDemande)) {
            return false;
        }
        CasDemande other = (CasDemande) object;
        if ((this.numCasDemande == null && other.numCasDemande != null) || (this.numCasDemande != null && !this.numCasDemande.equals(other.numCasDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CasDemande[ numCasDemande=" + numCasDemande + " ]";
    }
    
}
