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
@Table(name = "TitreIdentite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TitreIdentite.findAll", query = "SELECT t FROM TitreIdentite t")})
public class TitreIdentite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numTitreIdentite")
    private Integer numTitreIdentite;
    @Size(max = 100)
    @Column(name = "numeroTitreIdentite")
    private String numeroTitreIdentite;
    @Column(name = "dateEtablissementTitreIdentite")
    @Temporal(TemporalType.DATE)
    private Date dateEtablissementTitreIdentite;
    @Column(name = "dateExpirationTitreIdentite")
    @Temporal(TemporalType.DATE)
    private Date dateExpirationTitreIdentite;
    @Column(name = "dateRetraitTitreIdentite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetraitTitreIdentite;
    @Column(name = "aEteGenere")
    private Boolean aEteGenere;
    @Column(name = "mailFinProductionAEteEnvoyeAvecSucces")
    private Boolean mailFinProductionAEteEnvoyeAvecSucces;
    @JoinColumn(name = "demande", referencedColumnName = "numDemande")
    @ManyToOne
    private Demande demande;

    public TitreIdentite() {
    }

    public TitreIdentite(Integer numTitreIdentite) {
        this.numTitreIdentite = numTitreIdentite;
    }

    public Integer getNumTitreIdentite() {
        return numTitreIdentite;
    }

    public void setNumTitreIdentite(Integer numTitreIdentite) {
        this.numTitreIdentite = numTitreIdentite;
    }

    public String getNumeroTitreIdentite() {
        return numeroTitreIdentite;
    }

    public void setNumeroTitreIdentite(String numeroTitreIdentite) {
        this.numeroTitreIdentite = numeroTitreIdentite;
    }

    public Date getDateEtablissementTitreIdentite() {
        return dateEtablissementTitreIdentite;
    }

    public void setDateEtablissementTitreIdentite(Date dateEtablissementTitreIdentite) {
        this.dateEtablissementTitreIdentite = dateEtablissementTitreIdentite;
    }

    public Date getDateExpirationTitreIdentite() {
        return dateExpirationTitreIdentite;
    }

    public void setDateExpirationTitreIdentite(Date dateExpirationTitreIdentite) {
        this.dateExpirationTitreIdentite = dateExpirationTitreIdentite;
    }

    public Date getDateRetraitTitreIdentite() {
        return dateRetraitTitreIdentite;
    }

    public void setDateRetraitTitreIdentite(Date dateRetraitTitreIdentite) {
        this.dateRetraitTitreIdentite = dateRetraitTitreIdentite;
    }

    public Boolean getAEteGenere() {
        return aEteGenere;
    }

    public void setAEteGenere(Boolean aEteGenere) {
        this.aEteGenere = aEteGenere;
    }

    public Boolean getMailFinProductionAEteEnvoyeAvecSucces() {
        return mailFinProductionAEteEnvoyeAvecSucces;
    }

    public void setMailFinProductionAEteEnvoyeAvecSucces(Boolean mailFinProductionAEteEnvoyeAvecSucces) {
        this.mailFinProductionAEteEnvoyeAvecSucces = mailFinProductionAEteEnvoyeAvecSucces;
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
        hash += (numTitreIdentite != null ? numTitreIdentite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TitreIdentite)) {
            return false;
        }
        TitreIdentite other = (TitreIdentite) object;
        if ((this.numTitreIdentite == null && other.numTitreIdentite != null) || (this.numTitreIdentite != null && !this.numTitreIdentite.equals(other.numTitreIdentite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TitreIdentite[ numTitreIdentite=" + numTitreIdentite + " ]";
    }
    
}
