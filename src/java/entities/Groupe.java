/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Groupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g")})
public class Groupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numGroupe")
    private Integer numGroupe;
    @Size(max = 100)
    @Column(name = "nomGroupe")
    private String nomGroupe;
    @JoinColumn(name = "niveauAdministration", referencedColumnName = "numNiveauAdministration")
    @ManyToOne
    private NiveauAdministration niveauAdministration;
    @OneToMany(mappedBy = "groupe")
    private List<AvoirRole> avoirRoleList;
    @OneToMany(mappedBy = "groupe")
    private List<AppartenirGroupe> appartenirGroupeList;

    public Groupe() {
    }

    public Groupe(Integer numGroupe) {
        this.numGroupe = numGroupe;
    }

    public Integer getNumGroupe() {
        return numGroupe;
    }

    public void setNumGroupe(Integer numGroupe) {
        this.numGroupe = numGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public NiveauAdministration getNiveauAdministration() {
        return niveauAdministration;
    }

    public void setNiveauAdministration(NiveauAdministration niveauAdministration) {
        this.niveauAdministration = niveauAdministration;
    }

    @XmlTransient
    public List<AvoirRole> getAvoirRoleList() {
        return avoirRoleList;
    }

    public void setAvoirRoleList(List<AvoirRole> avoirRoleList) {
        this.avoirRoleList = avoirRoleList;
    }

    @XmlTransient
    public List<AppartenirGroupe> getAppartenirGroupeList() {
        return appartenirGroupeList;
    }

    public void setAppartenirGroupeList(List<AppartenirGroupe> appartenirGroupeList) {
        this.appartenirGroupeList = appartenirGroupeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numGroupe != null ? numGroupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.numGroupe == null && other.numGroupe != null) || (this.numGroupe != null && !this.numGroupe.equals(other.numGroupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupe[ numGroupe=" + numGroupe + " ]";
    }
    
}
