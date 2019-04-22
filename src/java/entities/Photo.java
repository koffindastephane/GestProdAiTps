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
@Table(name = "Photo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p")})
public class Photo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "numPhoto")
    private String numPhoto;
    @Size(max = 100)
    @Column(name = "nomPhoto")
    private String nomPhoto;
    @Size(max = 255)
    @Column(name = "cheminPhoto")
    private String cheminPhoto;
    @Column(name = "lastModifiedPhoto")
    private Integer lastModifiedPhoto;
    @Column(name = "sizePhoto")
    private Integer sizePhoto;
    @Column(name = "heightPhoto")
    private Integer heightPhoto;
    @Column(name = "widthPhoto")
    private Integer widthPhoto;
    @JoinColumn(name = "typePhoto", referencedColumnName = "numTypePhoto")
    @ManyToOne
    private TypePhoto typePhoto;
    @OneToMany(mappedBy = "photo")
    private List<DocumentFourni> documentFourniList;
    @OneToMany(mappedBy = "photo")
    private List<Demande> demandeList;

    public Photo() {
    }

    public Photo(String numPhoto) {
        this.numPhoto = numPhoto;
    }

    public String getNumPhoto() {
        return numPhoto;
    }

    public void setNumPhoto(String numPhoto) {
        this.numPhoto = numPhoto;
    }

    public String getNomPhoto() {
        return nomPhoto;
    }

    public void setNomPhoto(String nomPhoto) {
        this.nomPhoto = nomPhoto;
    }

    public String getCheminPhoto() {
        return cheminPhoto;
    }

    public void setCheminPhoto(String cheminPhoto) {
        this.cheminPhoto = cheminPhoto;
    }

    public Integer getLastModifiedPhoto() {
        return lastModifiedPhoto;
    }

    public void setLastModifiedPhoto(Integer lastModifiedPhoto) {
        this.lastModifiedPhoto = lastModifiedPhoto;
    }

    public Integer getSizePhoto() {
        return sizePhoto;
    }

    public void setSizePhoto(Integer sizePhoto) {
        this.sizePhoto = sizePhoto;
    }

    public Integer getHeightPhoto() {
        return heightPhoto;
    }

    public void setHeightPhoto(Integer heightPhoto) {
        this.heightPhoto = heightPhoto;
    }

    public Integer getWidthPhoto() {
        return widthPhoto;
    }

    public void setWidthPhoto(Integer widthPhoto) {
        this.widthPhoto = widthPhoto;
    }

    public TypePhoto getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(TypePhoto typePhoto) {
        this.typePhoto = typePhoto;
    }

    @XmlTransient
    public List<DocumentFourni> getDocumentFourniList() {
        return documentFourniList;
    }

    public void setDocumentFourniList(List<DocumentFourni> documentFourniList) {
        this.documentFourniList = documentFourniList;
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
        hash += (numPhoto != null ? numPhoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if ((this.numPhoto == null && other.numPhoto != null) || (this.numPhoto != null && !this.numPhoto.equals(other.numPhoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Photo[ numPhoto=" + numPhoto + " ]";
    }
    
}
