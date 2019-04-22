/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "DemandeEnCoursDUtilisation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DemandeEnCoursDUtilisation.findAll", query = "SELECT d FROM DemandeEnCoursDUtilisation d")})
public class DemandeEnCoursDUtilisation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "numDemandeEnCoursDUtilisation")
    private String numDemandeEnCoursDUtilisation;
    @Column(name = "dateDebutUtilisation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebutUtilisation;
    @JoinColumn(name = "utilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateur;
    @JoinColumn(name = "connexion", referencedColumnName = "numConnexion")
    @ManyToOne
    private Connexion connexion;
    @JoinColumn(name = "typeDemande", referencedColumnName = "numTypeDemande")
    @ManyToOne(optional = false)
    private TypeDemande typeDemande;

    public DemandeEnCoursDUtilisation() {
    }

    public DemandeEnCoursDUtilisation(String numDemandeEnCoursDUtilisation) {
        this.numDemandeEnCoursDUtilisation = numDemandeEnCoursDUtilisation;
    }

    public String getNumDemandeEnCoursDUtilisation() {
        return numDemandeEnCoursDUtilisation;
    }

    public void setNumDemandeEnCoursDUtilisation(String numDemandeEnCoursDUtilisation) {
        this.numDemandeEnCoursDUtilisation = numDemandeEnCoursDUtilisation;
    }

    public Date getDateDebutUtilisation() {
        return dateDebutUtilisation;
    }

    public void setDateDebutUtilisation(Date dateDebutUtilisation) {
        this.dateDebutUtilisation = dateDebutUtilisation;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDemandeEnCoursDUtilisation != null ? numDemandeEnCoursDUtilisation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeEnCoursDUtilisation)) {
            return false;
        }
        DemandeEnCoursDUtilisation other = (DemandeEnCoursDUtilisation) object;
        if ((this.numDemandeEnCoursDUtilisation == null && other.numDemandeEnCoursDUtilisation != null) || (this.numDemandeEnCoursDUtilisation != null && !this.numDemandeEnCoursDUtilisation.equals(other.numDemandeEnCoursDUtilisation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DemandeEnCoursDUtilisation[ numDemandeEnCoursDUtilisation=" + numDemandeEnCoursDUtilisation + " ]";
    }
    
}
