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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "PlageRecus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlageRecus.findAll", query = "SELECT p FROM PlageRecus p")})
public class PlageRecus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numPlageRecus")
    private Integer numPlageRecus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreationPlageRecus")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationPlageRecus;
    @Column(name = "dateDerniereUtilisationPlageRecus")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereUtilisationPlageRecus;
    @Column(name = "dateFinPlageRecus")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinPlageRecus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroDebutPlageRecus")
    private int numeroDebutPlageRecus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroFinPlageRecus")
    private int numeroFinPlageRecus;
    @Column(name = "nombreRecusUtilises")
    private Integer nombreRecusUtilises;
    @Column(name = "statusPlageRecus")
    private Integer statusPlageRecus;
    @JoinColumn(name = "centrePaiement", referencedColumnName = "numCentre")
    @ManyToOne(optional = false)
    private Centre centrePaiement;
    @JoinColumn(name = "utilisateurCreateur", referencedColumnName = "numUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur utilisateurCreateur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "plageRecus")
    private List<Recu> recuList;

    public PlageRecus() {
    }

    public PlageRecus(Integer numPlageRecus) {
        this.numPlageRecus = numPlageRecus;
    }

    public PlageRecus(Integer numPlageRecus, Date dateCreationPlageRecus, int numeroDebutPlageRecus, int numeroFinPlageRecus) {
        this.numPlageRecus = numPlageRecus;
        this.dateCreationPlageRecus = dateCreationPlageRecus;
        this.numeroDebutPlageRecus = numeroDebutPlageRecus;
        this.numeroFinPlageRecus = numeroFinPlageRecus;
    }

    public Integer getNumPlageRecus() {
        return numPlageRecus;
    }

    public void setNumPlageRecus(Integer numPlageRecus) {
        this.numPlageRecus = numPlageRecus;
    }

    public Date getDateCreationPlageRecus() {
        return dateCreationPlageRecus;
    }

    public void setDateCreationPlageRecus(Date dateCreationPlageRecus) {
        this.dateCreationPlageRecus = dateCreationPlageRecus;
    }

    public Date getDateDerniereUtilisationPlageRecus() {
        return dateDerniereUtilisationPlageRecus;
    }

    public void setDateDerniereUtilisationPlageRecus(Date dateDerniereUtilisationPlageRecus) {
        this.dateDerniereUtilisationPlageRecus = dateDerniereUtilisationPlageRecus;
    }

    public Date getDateFinPlageRecus() {
        return dateFinPlageRecus;
    }

    public void setDateFinPlageRecus(Date dateFinPlageRecus) {
        this.dateFinPlageRecus = dateFinPlageRecus;
    }

    public int getNumeroDebutPlageRecus() {
        return numeroDebutPlageRecus;
    }

    public void setNumeroDebutPlageRecus(int numeroDebutPlageRecus) {
        this.numeroDebutPlageRecus = numeroDebutPlageRecus;
    }

    public int getNumeroFinPlageRecus() {
        return numeroFinPlageRecus;
    }

    public void setNumeroFinPlageRecus(int numeroFinPlageRecus) {
        this.numeroFinPlageRecus = numeroFinPlageRecus;
    }

    public Integer getNombreRecusUtilises() {
        return nombreRecusUtilises;
    }

    public void setNombreRecusUtilises(Integer nombreRecusUtilises) {
        this.nombreRecusUtilises = nombreRecusUtilises;
    }

    public Integer getStatusPlageRecus() {
        return statusPlageRecus;
    }

    public void setStatusPlageRecus(Integer statusPlageRecus) {
        this.statusPlageRecus = statusPlageRecus;
    }

    public Centre getCentrePaiement() {
        return centrePaiement;
    }

    public void setCentrePaiement(Centre centrePaiement) {
        this.centrePaiement = centrePaiement;
    }

    public Utilisateur getUtilisateurCreateur() {
        return utilisateurCreateur;
    }

    public void setUtilisateurCreateur(Utilisateur utilisateurCreateur) {
        this.utilisateurCreateur = utilisateurCreateur;
    }

    @XmlTransient
    public List<Recu> getRecuList() {
        return recuList;
    }

    public void setRecuList(List<Recu> recuList) {
        this.recuList = recuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numPlageRecus != null ? numPlageRecus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlageRecus)) {
            return false;
        }
        PlageRecus other = (PlageRecus) object;
        if ((this.numPlageRecus == null && other.numPlageRecus != null) || (this.numPlageRecus != null && !this.numPlageRecus.equals(other.numPlageRecus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PlageRecus[ numPlageRecus=" + numPlageRecus + " ]";
    }
    
}
