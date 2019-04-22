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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Connexion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connexion.findAll", query = "SELECT c FROM Connexion c")})
public class Connexion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numConnexion")
    private Integer numConnexion;
    @Size(max = 50)
    @Column(name = "adresseIP")
    private String adresseIP;
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Column(name = "dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Column(name = "estTermineeNormalement")
    private Integer estTermineeNormalement;
    @Column(name = "estActive")
    private Boolean estActive;
    @JoinColumn(name = "utilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "connexion")
    private List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList;

    public Connexion() {
    }

    public Connexion(Integer numConnexion) {
        this.numConnexion = numConnexion;
    }

    public Integer getNumConnexion() {
        return numConnexion;
    }

    public void setNumConnexion(Integer numConnexion) {
        this.numConnexion = numConnexion;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getEstTermineeNormalement() {
        return estTermineeNormalement;
    }

    public void setEstTermineeNormalement(Integer estTermineeNormalement) {
        this.estTermineeNormalement = estTermineeNormalement;
    }

    public Boolean getEstActive() {
        return estActive;
    }

    public void setEstActive(Boolean estActive) {
        this.estActive = estActive;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
        hash += (numConnexion != null ? numConnexion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connexion)) {
            return false;
        }
        Connexion other = (Connexion) object;
        if ((this.numConnexion == null && other.numConnexion != null) || (this.numConnexion != null && !this.numConnexion.equals(other.numConnexion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Connexion[ numConnexion=" + numConnexion + " ]";
    }
    
}
