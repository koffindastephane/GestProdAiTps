/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "TypeDemande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeDemande.findAll", query = "SELECT t FROM TypeDemande t")})
public class TypeDemande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numTypeDemande")
    private Integer numTypeDemande;
    @Size(max = 100)
    @Column(name = "nomTypeDemande")
    private String nomTypeDemande;
    @Column(name = "dateEntreeEnVigueur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEntreeEnVigueur;
    @Column(name = "tarifActuel")
    private Integer tarifActuel;
    @Column(name = "dureeValiditeActuel")
    private Integer dureeValiditeActuel;
    @Size(max = 3)
    @Column(name = "symboleActuel")
    private String symboleActuel;
    @OneToMany(mappedBy = "typeDemande")
    private List<DocumentNecessaire> documentNecessaireList;
    @OneToMany(mappedBy = "typeDemande")
    private List<Varier> varierList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeDemande")
    private List<Recu> recuList;
    @OneToMany(mappedBy = "typeDemande")
    private List<Correspondre> correspondreList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeDemande")
    private List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList;

    public TypeDemande() {
    }

    public TypeDemande(Integer numTypeDemande) {
        this.numTypeDemande = numTypeDemande;
    }

    public Integer getNumTypeDemande() {
        return numTypeDemande;
    }

    public void setNumTypeDemande(Integer numTypeDemande) {
        this.numTypeDemande = numTypeDemande;
    }

    public String getNomTypeDemande() {
        return nomTypeDemande;
    }

    public void setNomTypeDemande(String nomTypeDemande) {
        this.nomTypeDemande = nomTypeDemande;
    }

    public Date getDateEntreeEnVigueur() {
        return dateEntreeEnVigueur;
    }

    public void setDateEntreeEnVigueur(Date dateEntreeEnVigueur) {
        this.dateEntreeEnVigueur = dateEntreeEnVigueur;
    }

    public Integer getTarifActuel() {
        return tarifActuel;
    }

    public void setTarifActuel(Integer tarifActuel) {
        this.tarifActuel = tarifActuel;
    }

    public Integer getDureeValiditeActuel() {
        return dureeValiditeActuel;
    }

    public void setDureeValiditeActuel(Integer dureeValiditeActuel) {
        this.dureeValiditeActuel = dureeValiditeActuel;
    }

    public String getSymboleActuel() {
        return symboleActuel;
    }

    public void setSymboleActuel(String symboleActuel) {
        this.symboleActuel = symboleActuel;
    }

    @XmlTransient
    public List<DocumentNecessaire> getDocumentNecessaireList() {
        return documentNecessaireList;
    }

    public void setDocumentNecessaireList(List<DocumentNecessaire> documentNecessaireList) {
        this.documentNecessaireList = documentNecessaireList;
    }

    @XmlTransient
    public List<Varier> getVarierList() {
        return varierList;
    }

    public void setVarierList(List<Varier> varierList) {
        this.varierList = varierList;
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
    public List<DemandeEnCoursDUtilisation> getDemandeEnCoursDUtilisationList() {
        return demandeEnCoursDUtilisationList;
    }

    public void setDemandeEnCoursDUtilisationList(List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList) {
        this.demandeEnCoursDUtilisationList = demandeEnCoursDUtilisationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numTypeDemande != null ? numTypeDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeDemande)) {
            return false;
        }
        TypeDemande other = (TypeDemande) object;
        if ((this.numTypeDemande == null && other.numTypeDemande != null) || (this.numTypeDemande != null && !this.numTypeDemande.equals(other.numTypeDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeDemande[ numTypeDemande=" + numTypeDemande + " ]";
    }
    
}
