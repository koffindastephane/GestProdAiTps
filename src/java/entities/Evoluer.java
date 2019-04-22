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
@Table(name = "Evoluer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evoluer.findAll", query = "SELECT e FROM Evoluer e")})
public class Evoluer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numEvoluer")
    private Integer numEvoluer;
    @Column(name = "dates")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dates;
    @Size(max = 50)
    @Column(name = "adresseIP")
    private String adresseIP;
    @JoinColumn(name = "demande", referencedColumnName = "numDemande")
    @ManyToOne
    private Demande demande;
    @JoinColumn(name = "status", referencedColumnName = "numStatus")
    @ManyToOne
    private Status status;
    @JoinColumn(name = "utilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateur;

    public Evoluer() {
    }

    public Evoluer(Integer numEvoluer) {
        this.numEvoluer = numEvoluer;
    }

    public Integer getNumEvoluer() {
        return numEvoluer;
    }

    public void setNumEvoluer(Integer numEvoluer) {
        this.numEvoluer = numEvoluer;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        hash += (numEvoluer != null ? numEvoluer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evoluer)) {
            return false;
        }
        Evoluer other = (Evoluer) object;
        if ((this.numEvoluer == null && other.numEvoluer != null) || (this.numEvoluer != null && !this.numEvoluer.equals(other.numEvoluer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evoluer[ numEvoluer=" + numEvoluer + " ]";
    }
    
}
