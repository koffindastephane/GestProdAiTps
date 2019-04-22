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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u")})
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numUtilisateur")
    private Integer numUtilisateur;
    @Size(max = 100)
    @Column(name = "nomUtilisateur")
    private String nomUtilisateur;
    @Size(max = 100)
    @Column(name = "prenomUtilisateur")
    private String prenomUtilisateur;
    @Column(name = "sexeUtilisateur")
    private Integer sexeUtilisateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "pwdInitialementDonneParAdmin")
    private String pwdInitialementDonneParAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "pwd")
    private String pwd;
    @Size(max = 75)
    @Column(name = "emailUtilisateur")
    private String emailUtilisateur;
    @Size(max = 25)
    @Column(name = "telephoneUtilisateur")
    private String telephoneUtilisateur;
    @Column(name = "aRecuMailCreationCompte")
    private Boolean aRecuMailCreationCompte;
    @Column(name = "aModifiePwdDonneParAdmin")
    private Boolean aModifiePwdDonneParAdmin;
    @Column(name = "datePremiereModificationPwd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePremiereModificationPwd;
    @Column(name = "dateDerniereModificationPwd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereModificationPwd;
    @Column(name = "peutSeConnecter")
    private Boolean peutSeConnecter;
    @Column(name = "peutEtreSupprime")
    private Boolean peutEtreSupprime;
    @Column(name = "dateCreationUtilisateur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationUtilisateur;
    @Column(name = "dateDerniereModificationUtilisateur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereModificationUtilisateur;
    @OneToMany(mappedBy = "utilisateur")
    private List<Evoluer> evoluerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateurCreateur")
    private List<PlageRecus> plageRecusList;
    @OneToMany(mappedBy = "utilisateur")
    private List<Connexion> connexionList;
    @JoinColumn(name = "centreTravail", referencedColumnName = "numCentre")
    @ManyToOne
    private Centre centreTravail;
    @OneToMany(mappedBy = "utilisateurCreateur")
    private List<Utilisateur> utilisateurList;
    @JoinColumn(name = "utilisateurCreateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateurCreateur;
    @OneToMany(mappedBy = "utilisateurActeurDerniereModification")
    private List<Utilisateur> utilisateurList1;
    @JoinColumn(name = "utilisateurActeurDerniereModification", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur utilisateurActeurDerniereModification;
    @OneToMany(mappedBy = "utilisateur")
    private List<AppartenirGroupe> appartenirGroupeList;
    @OneToMany(mappedBy = "utilisateur")
    private List<Varier> varierList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateurCreateur")
    private List<Recu> recuList;
    @OneToMany(mappedBy = "utilisateur")
    private List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList;

    public Utilisateur() {
    }

    public Utilisateur(Integer numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }

    public Utilisateur(Integer numUtilisateur, String login, String pwdInitialementDonneParAdmin, String pwd) {
        this.numUtilisateur = numUtilisateur;
        this.login = login;
        this.pwdInitialementDonneParAdmin = pwdInitialementDonneParAdmin;
        this.pwd = pwd;
    }

    public Integer getNumUtilisateur() {
        return numUtilisateur;
    }

    public void setNumUtilisateur(Integer numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public Integer getSexeUtilisateur() {
        return sexeUtilisateur;
    }

    public void setSexeUtilisateur(Integer sexeUtilisateur) {
        this.sexeUtilisateur = sexeUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwdInitialementDonneParAdmin() {
        return pwdInitialementDonneParAdmin;
    }

    public void setPwdInitialementDonneParAdmin(String pwdInitialementDonneParAdmin) {
        this.pwdInitialementDonneParAdmin = pwdInitialementDonneParAdmin;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getTelephoneUtilisateur() {
        return telephoneUtilisateur;
    }

    public void setTelephoneUtilisateur(String telephoneUtilisateur) {
        this.telephoneUtilisateur = telephoneUtilisateur;
    }

    public Boolean getARecuMailCreationCompte() {
        return aRecuMailCreationCompte;
    }

    public void setARecuMailCreationCompte(Boolean aRecuMailCreationCompte) {
        this.aRecuMailCreationCompte = aRecuMailCreationCompte;
    }

    public Boolean getAModifiePwdDonneParAdmin() {
        return aModifiePwdDonneParAdmin;
    }

    public void setAModifiePwdDonneParAdmin(Boolean aModifiePwdDonneParAdmin) {
        this.aModifiePwdDonneParAdmin = aModifiePwdDonneParAdmin;
    }

    public Date getDatePremiereModificationPwd() {
        return datePremiereModificationPwd;
    }

    public void setDatePremiereModificationPwd(Date datePremiereModificationPwd) {
        this.datePremiereModificationPwd = datePremiereModificationPwd;
    }

    public Date getDateDerniereModificationPwd() {
        return dateDerniereModificationPwd;
    }

    public void setDateDerniereModificationPwd(Date dateDerniereModificationPwd) {
        this.dateDerniereModificationPwd = dateDerniereModificationPwd;
    }

    public Boolean getPeutSeConnecter() {
        return peutSeConnecter;
    }

    public void setPeutSeConnecter(Boolean peutSeConnecter) {
        this.peutSeConnecter = peutSeConnecter;
    }

    public Boolean getPeutEtreSupprime() {
        return peutEtreSupprime;
    }

    public void setPeutEtreSupprime(Boolean peutEtreSupprime) {
        this.peutEtreSupprime = peutEtreSupprime;
    }

    public Date getDateCreationUtilisateur() {
        return dateCreationUtilisateur;
    }

    public void setDateCreationUtilisateur(Date dateCreationUtilisateur) {
        this.dateCreationUtilisateur = dateCreationUtilisateur;
    }

    public Date getDateDerniereModificationUtilisateur() {
        return dateDerniereModificationUtilisateur;
    }

    public void setDateDerniereModificationUtilisateur(Date dateDerniereModificationUtilisateur) {
        this.dateDerniereModificationUtilisateur = dateDerniereModificationUtilisateur;
    }

    @XmlTransient
    public List<Evoluer> getEvoluerList() {
        return evoluerList;
    }

    public void setEvoluerList(List<Evoluer> evoluerList) {
        this.evoluerList = evoluerList;
    }

    @XmlTransient
    public List<PlageRecus> getPlageRecusList() {
        return plageRecusList;
    }

    public void setPlageRecusList(List<PlageRecus> plageRecusList) {
        this.plageRecusList = plageRecusList;
    }

    @XmlTransient
    public List<Connexion> getConnexionList() {
        return connexionList;
    }

    public void setConnexionList(List<Connexion> connexionList) {
        this.connexionList = connexionList;
    }

    public Centre getCentreTravail() {
        return centreTravail;
    }

    public void setCentreTravail(Centre centreTravail) {
        this.centreTravail = centreTravail;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(List<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    public Utilisateur getUtilisateurCreateur() {
        return utilisateurCreateur;
    }

    public void setUtilisateurCreateur(Utilisateur utilisateurCreateur) {
        this.utilisateurCreateur = utilisateurCreateur;
    }

    @XmlTransient
    public List<Utilisateur> getUtilisateurList1() {
        return utilisateurList1;
    }

    public void setUtilisateurList1(List<Utilisateur> utilisateurList1) {
        this.utilisateurList1 = utilisateurList1;
    }

    public Utilisateur getUtilisateurActeurDerniereModification() {
        return utilisateurActeurDerniereModification;
    }

    public void setUtilisateurActeurDerniereModification(Utilisateur utilisateurActeurDerniereModification) {
        this.utilisateurActeurDerniereModification = utilisateurActeurDerniereModification;
    }

    @XmlTransient
    public List<AppartenirGroupe> getAppartenirGroupeList() {
        return appartenirGroupeList;
    }

    public void setAppartenirGroupeList(List<AppartenirGroupe> appartenirGroupeList) {
        this.appartenirGroupeList = appartenirGroupeList;
    }

    @XmlTransient
    public List<Varier> getVarierList() {
        return varierList;
    }

    public void setVarierList(List<Varier> varierList) {
        this.varierList = varierList;
    }

    @XmlTransient
    public List<Recu> getRecuList() {
        return recuList;
    }

    public void setRecuList(List<Recu> recuList) {
        this.recuList = recuList;
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
        hash += (numUtilisateur != null ? numUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.numUtilisateur == null && other.numUtilisateur != null) || (this.numUtilisateur != null && !this.numUtilisateur.equals(other.numUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Utilisateur[ numUtilisateur=" + numUtilisateur + " ]";
    }
    
}
