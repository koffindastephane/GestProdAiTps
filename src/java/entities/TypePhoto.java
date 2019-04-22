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
@Table(name = "TypePhoto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypePhoto.findAll", query = "SELECT t FROM TypePhoto t")})
public class TypePhoto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numTypePhoto")
    private Integer numTypePhoto;
    @Size(max = 100)
    @Column(name = "nomTypePhoto")
    private String nomTypePhoto;
    @Size(max = 5)
    @Column(name = "symboleTypePhoto")
    private String symboleTypePhoto;
    @OneToMany(mappedBy = "typePhoto")
    private List<Photo> photoList;

    public TypePhoto() {
    }

    public TypePhoto(Integer numTypePhoto) {
        this.numTypePhoto = numTypePhoto;
    }

    public Integer getNumTypePhoto() {
        return numTypePhoto;
    }

    public void setNumTypePhoto(Integer numTypePhoto) {
        this.numTypePhoto = numTypePhoto;
    }

    public String getNomTypePhoto() {
        return nomTypePhoto;
    }

    public void setNomTypePhoto(String nomTypePhoto) {
        this.nomTypePhoto = nomTypePhoto;
    }

    public String getSymboleTypePhoto() {
        return symboleTypePhoto;
    }

    public void setSymboleTypePhoto(String symboleTypePhoto) {
        this.symboleTypePhoto = symboleTypePhoto;
    }

    @XmlTransient
    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numTypePhoto != null ? numTypePhoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypePhoto)) {
            return false;
        }
        TypePhoto other = (TypePhoto) object;
        if ((this.numTypePhoto == null && other.numTypePhoto != null) || (this.numTypePhoto != null && !this.numTypePhoto.equals(other.numTypePhoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypePhoto[ numTypePhoto=" + numTypePhoto + " ]";
    }
    
}
