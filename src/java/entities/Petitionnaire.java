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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Petitionnaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petitionnaire.findAll", query = "SELECT p FROM Petitionnaire p")})
public class Petitionnaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numPetitionnaire")
    private Integer numPetitionnaire;
    @Size(max = 50)
    @Column(name = "numIdentifiantUnique")
    private String numIdentifiantUnique;
    @Size(max = 50)
    @Column(name = "numeroExtraitNaissance")
    private String numeroExtraitNaissance;
    @Size(max = 50)
    @Column(name = "numeroJugementSuppletif")
    private String numeroJugementSuppletif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "prenoms")
    private String prenoms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Size(max = 100)
    @Column(name = "lieuNaissance")
    private String lieuNaissance;
    @Column(name = "sexe")
    private Integer sexe;
    @Size(max = 100)
    @Column(name = "nationalite")
    private String nationalite;
    @Column(name = "taille")
    private Integer taille;
    @Size(max = 25)
    @Column(name = "telephone1")
    private String telephone1;
    @Size(max = 25)
    @Column(name = "telephone2")
    private String telephone2;
    @Size(max = 25)
    @Column(name = "telephone3")
    private String telephone3;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "profession")
    private String profession;
    @Size(max = 200)
    @Column(name = "domicile")
    private String domicile;
    @Size(max = 200)
    @Column(name = "adressePhysique")
    private String adressePhysique;
    @Size(max = 100)
    @Column(name = "adressePostale")
    private String adressePostale;
    @Size(max = 100)
    @Column(name = "nomPere")
    private String nomPere;
    @Size(max = 150)
    @Column(name = "prenomsPere")
    private String prenomsPere;
    @Column(name = "dateNaissancePere")
    @Temporal(TemporalType.DATE)
    private Date dateNaissancePere;
    @Size(max = 100)
    @Column(name = "lieuNaissancePere")
    private String lieuNaissancePere;
    @Size(max = 100)
    @Column(name = "nationalitePere")
    private String nationalitePere;
    @Size(max = 100)
    @Column(name = "nomMere")
    private String nomMere;
    @Size(max = 150)
    @Column(name = "prenomsMere")
    private String prenomsMere;
    @Column(name = "dateNaissanceMere")
    @Temporal(TemporalType.DATE)
    private Date dateNaissanceMere;
    @Size(max = 100)
    @Column(name = "lieuNaissanceMere")
    private String lieuNaissanceMere;
    @Size(max = 100)
    @Column(name = "nationaliteMere")
    private String nationaliteMere;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petitionnaire")
    private List<Recu> recuList;

    public Petitionnaire() {
    }

    public Petitionnaire(Integer numPetitionnaire) {
        this.numPetitionnaire = numPetitionnaire;
    }

    public Petitionnaire(Integer numPetitionnaire, String nom, String prenoms, Date dateNaissance) {
        this.numPetitionnaire = numPetitionnaire;
        this.nom = nom;
        this.prenoms = prenoms;
        this.dateNaissance = dateNaissance;
    }

    public Integer getNumPetitionnaire() {
        return numPetitionnaire;
    }

    public void setNumPetitionnaire(Integer numPetitionnaire) {
        this.numPetitionnaire = numPetitionnaire;
    }

    public String getNumIdentifiantUnique() {
        return numIdentifiantUnique;
    }

    public void setNumIdentifiantUnique(String numIdentifiantUnique) {
        this.numIdentifiantUnique = numIdentifiantUnique;
    }

    public String getNumeroExtraitNaissance() {
        return numeroExtraitNaissance;
    }

    public void setNumeroExtraitNaissance(String numeroExtraitNaissance) {
        this.numeroExtraitNaissance = numeroExtraitNaissance;
    }

    public String getNumeroJugementSuppletif() {
        return numeroJugementSuppletif;
    }

    public void setNumeroJugementSuppletif(String numeroJugementSuppletif) {
        this.numeroJugementSuppletif = numeroJugementSuppletif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Integer getSexe() {
        return sexe;
    }

    public void setSexe(Integer sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getAdressePhysique() {
        return adressePhysique;
    }

    public void setAdressePhysique(String adressePhysique) {
        this.adressePhysique = adressePhysique;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getNomPere() {
        return nomPere;
    }

    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    public String getPrenomsPere() {
        return prenomsPere;
    }

    public void setPrenomsPere(String prenomsPere) {
        this.prenomsPere = prenomsPere;
    }

    public Date getDateNaissancePere() {
        return dateNaissancePere;
    }

    public void setDateNaissancePere(Date dateNaissancePere) {
        this.dateNaissancePere = dateNaissancePere;
    }

    public String getLieuNaissancePere() {
        return lieuNaissancePere;
    }

    public void setLieuNaissancePere(String lieuNaissancePere) {
        this.lieuNaissancePere = lieuNaissancePere;
    }

    public String getNationalitePere() {
        return nationalitePere;
    }

    public void setNationalitePere(String nationalitePere) {
        this.nationalitePere = nationalitePere;
    }

    public String getNomMere() {
        return nomMere;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public String getPrenomsMere() {
        return prenomsMere;
    }

    public void setPrenomsMere(String prenomsMere) {
        this.prenomsMere = prenomsMere;
    }

    public Date getDateNaissanceMere() {
        return dateNaissanceMere;
    }

    public void setDateNaissanceMere(Date dateNaissanceMere) {
        this.dateNaissanceMere = dateNaissanceMere;
    }

    public String getLieuNaissanceMere() {
        return lieuNaissanceMere;
    }

    public void setLieuNaissanceMere(String lieuNaissanceMere) {
        this.lieuNaissanceMere = lieuNaissanceMere;
    }

    public String getNationaliteMere() {
        return nationaliteMere;
    }

    public void setNationaliteMere(String nationaliteMere) {
        this.nationaliteMere = nationaliteMere;
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
        hash += (numPetitionnaire != null ? numPetitionnaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Petitionnaire)) {
            return false;
        }
        Petitionnaire other = (Petitionnaire) object;
        if ((this.numPetitionnaire == null && other.numPetitionnaire != null) || (this.numPetitionnaire != null && !this.numPetitionnaire.equals(other.numPetitionnaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Petitionnaire[ numPetitionnaire=" + numPetitionnaire + " ]";
    }
    
}
