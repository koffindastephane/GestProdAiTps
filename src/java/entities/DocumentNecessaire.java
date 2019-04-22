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
@Table(name = "DocumentNecessaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentNecessaire.findAll", query = "SELECT d FROM DocumentNecessaire d")})
public class DocumentNecessaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numDocumentNecessaire")
    private Integer numDocumentNecessaire;
    @JoinColumn(name = "typeDemande", referencedColumnName = "numTypeDemande")
    @ManyToOne
    private TypeDemande typeDemande;
    @JoinColumn(name = "typeDocument", referencedColumnName = "numTypeDocument")
    @ManyToOne
    private TypeDocument typeDocument;
    @JoinColumn(name = "casDemande", referencedColumnName = "numCasDemande")
    @ManyToOne
    private CasDemande casDemande;

    public DocumentNecessaire() {
    }

    public DocumentNecessaire(Integer numDocumentNecessaire) {
        this.numDocumentNecessaire = numDocumentNecessaire;
    }

    public Integer getNumDocumentNecessaire() {
        return numDocumentNecessaire;
    }

    public void setNumDocumentNecessaire(Integer numDocumentNecessaire) {
        this.numDocumentNecessaire = numDocumentNecessaire;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public CasDemande getCasDemande() {
        return casDemande;
    }

    public void setCasDemande(CasDemande casDemande) {
        this.casDemande = casDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDocumentNecessaire != null ? numDocumentNecessaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentNecessaire)) {
            return false;
        }
        DocumentNecessaire other = (DocumentNecessaire) object;
        if ((this.numDocumentNecessaire == null && other.numDocumentNecessaire != null) || (this.numDocumentNecessaire != null && !this.numDocumentNecessaire.equals(other.numDocumentNecessaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentNecessaire[ numDocumentNecessaire=" + numDocumentNecessaire + " ]";
    }
    
}
