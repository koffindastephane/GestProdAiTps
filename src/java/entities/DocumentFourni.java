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
@Table(name = "DocumentFourni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentFourni.findAll", query = "SELECT d FROM DocumentFourni d")})
public class DocumentFourni implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numDocumentFourni")
    private Integer numDocumentFourni;
    @Size(max = 100)
    @Column(name = "numeroDocumentFourni")
    private String numeroDocumentFourni;
    @Column(name = "dateEtablissement")
    @Temporal(TemporalType.DATE)
    private Date dateEtablissement;
    @Size(max = 100)
    @Column(name = "lieuEtablissement")
    private String lieuEtablissement;
    @Column(name = "dateDelivrance")
    @Temporal(TemporalType.DATE)
    private Date dateDelivrance;
    @Size(max = 100)
    @Column(name = "lieuDelivrance")
    private String lieuDelivrance;
    @Column(name = "datePeremption")
    @Temporal(TemporalType.DATE)
    private Date datePeremption;
    @Column(name = "dateEntreePays")
    @Temporal(TemporalType.DATE)
    private Date dateEntreePays;
    @JoinColumn(name = "demande", referencedColumnName = "numDemande")
    @ManyToOne
    private Demande demande;
    @JoinColumn(name = "typeDocument", referencedColumnName = "numTypeDocument")
    @ManyToOne
    private TypeDocument typeDocument;
    @JoinColumn(name = "etat", referencedColumnName = "numEtat")
    @ManyToOne
    private Etat etat;
    @JoinColumn(name = "photo", referencedColumnName = "numPhoto")
    @ManyToOne
    private Photo photo;

    public DocumentFourni() {
    }

    public DocumentFourni(Integer numDocumentFourni) {
        this.numDocumentFourni = numDocumentFourni;
    }

    public Integer getNumDocumentFourni() {
        return numDocumentFourni;
    }

    public void setNumDocumentFourni(Integer numDocumentFourni) {
        this.numDocumentFourni = numDocumentFourni;
    }

    public String getNumeroDocumentFourni() {
        return numeroDocumentFourni;
    }

    public void setNumeroDocumentFourni(String numeroDocumentFourni) {
        this.numeroDocumentFourni = numeroDocumentFourni;
    }

    public Date getDateEtablissement() {
        return dateEtablissement;
    }

    public void setDateEtablissement(Date dateEtablissement) {
        this.dateEtablissement = dateEtablissement;
    }

    public String getLieuEtablissement() {
        return lieuEtablissement;
    }

    public void setLieuEtablissement(String lieuEtablissement) {
        this.lieuEtablissement = lieuEtablissement;
    }

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public String getLieuDelivrance() {
        return lieuDelivrance;
    }

    public void setLieuDelivrance(String lieuDelivrance) {
        this.lieuDelivrance = lieuDelivrance;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Date getDateEntreePays() {
        return dateEntreePays;
    }

    public void setDateEntreePays(Date dateEntreePays) {
        this.dateEntreePays = dateEntreePays;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
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
        hash += (numDocumentFourni != null ? numDocumentFourni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentFourni)) {
            return false;
        }
        DocumentFourni other = (DocumentFourni) object;
        if ((this.numDocumentFourni == null && other.numDocumentFourni != null) || (this.numDocumentFourni != null && !this.numDocumentFourni.equals(other.numDocumentFourni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentFourni[ numDocumentFourni=" + numDocumentFourni + " ]";
    }
    
}
