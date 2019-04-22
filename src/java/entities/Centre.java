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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Centre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centre.findAll", query = "SELECT c FROM Centre c")})
public class Centre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "numCentre")
    private String numCentre;
    @Size(max = 100)
    @Column(name = "nomCentre")
    private String nomCentre;
    @Column(name = "valeurCompteurAi")
    private Integer valeurCompteurAi;
    @Column(name = "valeurCompteurTps")
    private Integer valeurCompteurTps;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centrePaiement")
    private List<PlageRecus> plageRecusList;
    @OneToMany(mappedBy = "centreTravail")
    private List<Utilisateur> utilisateurList;
    @JoinColumn(name = "commune", referencedColumnName = "numCommune")
    @ManyToOne
    private Commune commune;
    @OneToMany(mappedBy = "centreTraitement")
    private List<Demande> demandeList;

    public Centre() {
    }

    public Centre(String numCentre) {
        this.numCentre = numCentre;
    }

    public String getNumCentre() {
        return numCentre;
    }

    public void setNumCentre(String numCentre) {
        this.numCentre = numCentre;
    }

    public String getNomCentre() {
        return nomCentre;
    }

    public void setNomCentre(String nomCentre) {
        this.nomCentre = nomCentre;
    }

    public Integer getValeurCompteurAi() {
        return valeurCompteurAi;
    }

    public void setValeurCompteurAi(Integer valeurCompteurAi) {
        this.valeurCompteurAi = valeurCompteurAi;
    }

    public Integer getValeurCompteurTps() {
        return valeurCompteurTps;
    }

    public void setValeurCompteurTps(Integer valeurCompteurTps) {
        this.valeurCompteurTps = valeurCompteurTps;
    }

    @XmlTransient
    public List<PlageRecus> getPlageRecusList() {
        return plageRecusList;
    }

    public void setPlageRecusList(List<PlageRecus> plageRecusList) {
        this.plageRecusList = plageRecusList;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    @XmlTransient
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCentre != null ? numCentre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centre)) {
            return false;
        }
        Centre other = (Centre) object;
        if ((this.numCentre == null && other.numCentre != null) || (this.numCentre != null && !this.numCentre.equals(other.numCentre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Centre[ numCentre=" + numCentre + " ]";
    }
    
}
