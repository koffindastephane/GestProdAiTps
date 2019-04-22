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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Varier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Varier.findAll", query = "SELECT v FROM Varier v")})
public class Varier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numVarier")
    private Integer numVarier;
    @Column(name = "dates")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dates;
    @Column(name = "tarif")
    private Integer tarif;
    @Column(name = "dureeValidite")
    private Integer dureeValidite;
    @Size(max = 3)
    @Column(name = "symbole")
    private String symbole;
    @Size(max = 50)
    @Column(name = "adresseIP")
    private String adresseIP;
    @JoinColumn(name = "typeDemande", referencedColumnName = "numTypeDemande")
    @ManyToOne
    private TypeDemande typeDemande;
    @JoinColumn(name = "utilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateur;

    public Varier() {
    }

    public Varier(Integer numVarier) {
        this.numVarier = numVarier;
    }

    public Integer getNumVarier() {
        return numVarier;
    }

    public void setNumVarier(Integer numVarier) {
        this.numVarier = numVarier;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    public Integer getDureeValidite() {
        return dureeValidite;
    }

    public void setDureeValidite(Integer dureeValidite) {
        this.dureeValidite = dureeValidite;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numVarier != null ? numVarier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Varier)) {
            return false;
        }
        Varier other = (Varier) object;
        if ((this.numVarier == null && other.numVarier != null) || (this.numVarier != null && !this.numVarier.equals(other.numVarier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Varier[ numVarier=" + numVarier + " ]";
    }
    
}
