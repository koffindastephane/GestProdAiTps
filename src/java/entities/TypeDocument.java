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
import javax.persistence.Id;
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
@Table(name = "TypeDocument")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeDocument.findAll", query = "SELECT t FROM TypeDocument t")})
public class TypeDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numTypeDocument")
    private Integer numTypeDocument;
    @Size(max = 100)
    @Column(name = "nomTypeDocument")
    private String nomTypeDocument;
    @OneToMany(mappedBy = "typeDocument")
    private List<DocumentNecessaire> documentNecessaireList;
    @OneToMany(mappedBy = "typeDocument")
    private List<DocumentFourni> documentFourniList;

    public TypeDocument() {
    }

    public TypeDocument(Integer numTypeDocument) {
        this.numTypeDocument = numTypeDocument;
    }

    public Integer getNumTypeDocument() {
        return numTypeDocument;
    }

    public void setNumTypeDocument(Integer numTypeDocument) {
        this.numTypeDocument = numTypeDocument;
    }

    public String getNomTypeDocument() {
        return nomTypeDocument;
    }

    public void setNomTypeDocument(String nomTypeDocument) {
        this.nomTypeDocument = nomTypeDocument;
    }

    @XmlTransient
    public List<DocumentNecessaire> getDocumentNecessaireList() {
        return documentNecessaireList;
    }

    public void setDocumentNecessaireList(List<DocumentNecessaire> documentNecessaireList) {
        this.documentNecessaireList = documentNecessaireList;
    }

    @XmlTransient
    public List<DocumentFourni> getDocumentFourniList() {
        return documentFourniList;
    }

    public void setDocumentFourniList(List<DocumentFourni> documentFourniList) {
        this.documentFourniList = documentFourniList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numTypeDocument != null ? numTypeDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeDocument)) {
            return false;
        }
        TypeDocument other = (TypeDocument) object;
        if ((this.numTypeDocument == null && other.numTypeDocument != null) || (this.numTypeDocument != null && !this.numTypeDocument.equals(other.numTypeDocument))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeDocument[ numTypeDocument=" + numTypeDocument + " ]";
    }
    
}
