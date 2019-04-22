/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Recu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recu.findAll", query = "SELECT r FROM Recu r")})
public class Recu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numRecu")
    private Integer numRecu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroRecu")
    private int numeroRecu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateVersement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVersement;
    @Size(max = 100)
    @Column(name = "natureRecette")
    private String natureRecette;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montantPaye")
    private int montantPaye;
    @Column(name = "aEteGenere")
    private Boolean aEteGenere;
    @JoinColumn(name = "plageRecus", referencedColumnName = "numPlageRecus")
    @ManyToOne(optional = false)
    private PlageRecus plageRecus;
    @JoinColumn(name = "typeDemande", referencedColumnName = "numTypeDemande")
    @ManyToOne(optional = false)
    private TypeDemande typeDemande;
    @JoinColumn(name = "casDemande", referencedColumnName = "numCasDemande")
    @ManyToOne(optional = false)
    private CasDemande casDemande;
    @JoinColumn(name = "petitionnaire", referencedColumnName = "numPetitionnaire")
    @ManyToOne(optional = false)
    private Petitionnaire petitionnaire;
    @JoinColumn(name = "utilisateurCreateur", referencedColumnName = "numUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur utilisateurCreateur;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "recu")
    private Demande demande;

    public Recu() {
    }

    public Recu(Integer numRecu) {
        this.numRecu = numRecu;
    }

    public Recu(Integer numRecu, int numeroRecu, Date dateVersement, int montantPaye) {
        this.numRecu = numRecu;
        this.numeroRecu = numeroRecu;
        this.dateVersement = dateVersement;
        this.montantPaye = montantPaye;
    }

    public Integer getNumRecu() {
        return numRecu;
    }

    public void setNumRecu(Integer numRecu) {
        this.numRecu = numRecu;
    }

    public int getNumeroRecu() {
        return numeroRecu;
    }

    public void setNumeroRecu(int numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    public String getNatureRecette() {
        return natureRecette;
    }

    public void setNatureRecette(String natureRecette) {
        this.natureRecette = natureRecette;
    }

    public int getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(int montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Boolean getAEteGenere() {
        return aEteGenere;
    }

    public void setAEteGenere(Boolean aEteGenere) {
        this.aEteGenere = aEteGenere;
    }

    public PlageRecus getPlageRecus() {
        return plageRecus;
    }

    public void setPlageRecus(PlageRecus plageRecus) {
        this.plageRecus = plageRecus;
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

    public Petitionnaire getPetitionnaire() {
        return petitionnaire;
    }

    public void setPetitionnaire(Petitionnaire petitionnaire) {
        this.petitionnaire = petitionnaire;
    }

    public Utilisateur getUtilisateurCreateur() {
        return utilisateurCreateur;
    }

    public void setUtilisateurCreateur(Utilisateur utilisateurCreateur) {
        this.utilisateurCreateur = utilisateurCreateur;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numRecu != null ? numRecu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recu)) {
            return false;
        }
        Recu other = (Recu) object;
        if ((this.numRecu == null && other.numRecu != null) || (this.numRecu != null && !this.numRecu.equals(other.numRecu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Recu[ numRecu=" + numRecu + " ]";
    }
    
}
