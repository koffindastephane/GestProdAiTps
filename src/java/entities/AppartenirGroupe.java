/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "AppartenirGroupe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppartenirGroupe.findAll", query = "SELECT a FROM AppartenirGroupe a")})
public class AppartenirGroupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numAppartenirGroupe")
    private Integer numAppartenirGroupe;
    @JoinColumn(name = "utilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateur;
    @JoinColumn(name = "groupe", referencedColumnName = "numGroupe")
    @ManyToOne
    private Groupe groupe;

    public AppartenirGroupe() {
    }

    public AppartenirGroupe(Integer numAppartenirGroupe) {
        this.numAppartenirGroupe = numAppartenirGroupe;
    }

    public Integer getNumAppartenirGroupe() {
        return numAppartenirGroupe;
    }

    public void setNumAppartenirGroupe(Integer numAppartenirGroupe) {
        this.numAppartenirGroupe = numAppartenirGroupe;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numAppartenirGroupe != null ? numAppartenirGroupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppartenirGroupe)) {
            return false;
        }
        AppartenirGroupe other = (AppartenirGroupe) object;
        if ((this.numAppartenirGroupe == null && other.numAppartenirGroupe != null) || (this.numAppartenirGroupe != null && !this.numAppartenirGroupe.equals(other.numAppartenirGroupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AppartenirGroupe[ numAppartenirGroupe=" + numAppartenirGroupe + " ]";
    }
    
}
