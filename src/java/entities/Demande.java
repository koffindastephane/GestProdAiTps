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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")})
public class Demande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "numDemande")
    private String numDemande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCreationDemande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreationDemande;
    @Column(name = "dateFinEnregistrementDemande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinEnregistrementDemande;
    @Size(max = 255)
    @Column(name = "motifRejet")
    private String motifRejet;
    @Column(name = "dateStatusActuel")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStatusActuel;
    @Column(name = "docJustificatifNais")
    private Integer docJustificatifNais;
    @OneToMany(mappedBy = "demande")
    private List<Evoluer> evoluerList;
    @OneToMany(mappedBy = "demande")
    private List<TitreIdentite> titreIdentiteList;
    @OneToMany(mappedBy = "demande")
    private List<Rdv> rdvList;
    @OneToMany(mappedBy = "demande")
    private List<DocumentFourni> documentFourniList;
    @JoinColumn(name = "statusActuel", referencedColumnName = "numStatus")
    @ManyToOne
    private Status statusActuel;
    @JoinColumn(name = "recu", referencedColumnName = "numRecu")
    @OneToOne(optional = false)
    private Recu recu;
    @JoinColumn(name = "centreTraitement", referencedColumnName = "numCentre")
    @ManyToOne
    private Centre centreTraitement;
    @JoinColumn(name = "photo", referencedColumnName = "numPhoto")
    @ManyToOne
    private Photo photo;

    public Demande() {
    }

    public Demande(String numDemande) {
        this.numDemande = numDemande;
    }

    public Demande(String numDemande, Date dateCreationDemande) {
        this.numDemande = numDemande;
        this.dateCreationDemande = dateCreationDemande;
    }

    public String getNumDemande() {
        return numDemande;
    }

    public void setNumDemande(String numDemande) {
        this.numDemande = numDemande;
    }

    public Date getDateCreationDemande() {
        return dateCreationDemande;
    }

    public void setDateCreationDemande(Date dateCreationDemande) {
        this.dateCreationDemande = dateCreationDemande;
    }

    public Date getDateFinEnregistrementDemande() {
        return dateFinEnregistrementDemande;
    }

    public void setDateFinEnregistrementDemande(Date dateFinEnregistrementDemande) {
        this.dateFinEnregistrementDemande = dateFinEnregistrementDemande;
    }

    public String getMotifRejet() {
        return motifRejet;
    }

    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
    }

    public Date getDateStatusActuel() {
        return dateStatusActuel;
    }

    public void setDateStatusActuel(Date dateStatusActuel) {
        this.dateStatusActuel = dateStatusActuel;
    }

    public Integer getDocJustificatifNais() {
        return docJustificatifNais;
    }

    public void setDocJustificatifNais(Integer docJustificatifNais) {
        this.docJustificatifNais = docJustificatifNais;
    }

    @XmlTransient
    public List<Evoluer> getEvoluerList() {
        return evoluerList;
    }

    public void setEvoluerList(List<Evoluer> evoluerList) {
        this.evoluerList = evoluerList;
    }

    @XmlTransient
    public List<TitreIdentite> getTitreIdentiteList() {
        return titreIdentiteList;
    }

    public void setTitreIdentiteList(List<TitreIdentite> titreIdentiteList) {
        this.titreIdentiteList = titreIdentiteList;
    }

    @XmlTransient
    public List<Rdv> getRdvList() {
        return rdvList;
    }

    public void setRdvList(List<Rdv> rdvList) {
        this.rdvList = rdvList;
    }

    @XmlTransient
    public List<DocumentFourni> getDocumentFourniList() {
        return documentFourniList;
    }

    public void setDocumentFourniList(List<DocumentFourni> documentFourniList) {
        this.documentFourniList = documentFourniList;
    }

    public Status getStatusActuel() {
        return statusActuel;
    }

    public void setStatusActuel(Status statusActuel) {
        this.statusActuel = statusActuel;
    }

    public Recu getRecu() {
        return recu;
    }

    public void setRecu(Recu recu) {
        this.recu = recu;
    }

    public Centre getCentreTraitement() {
        return centreTraitement;
    }

    public void setCentreTraitement(Centre centreTraitement) {
        this.centreTraitement = centreTraitement;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDemande != null ? numDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.numDemande == null && other.numDemande != null) || (this.numDemande != null && !this.numDemande.equals(other.numDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Demande[ numDemande=" + numDemande + " ]";
    }
    
}
