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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author STEPHANE
 */
@Entity
@Table(name = "Rdv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rdv.findAll", query = "SELECT r FROM Rdv r")})
public class Rdv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numRdv")
    private Integer numRdv;
    @Column(name = "dateRdv")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRdv;
    @JoinColumn(name = "demande", referencedColumnName = "numDemande")
    @ManyToOne
    private Demande demande;

    public Rdv() {
    }

    public Rdv(Integer numRdv) {
        this.numRdv = numRdv;
    }

    public Integer getNumRdv() {
        return numRdv;
    }

    public void setNumRdv(Integer numRdv) {
        this.numRdv = numRdv;
    }

    public Date getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(Date dateRdv) {
        this.dateRdv = dateRdv;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numRdv != null ? numRdv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rdv)) {
            return false;
        }
        Rdv other = (Rdv) object;
        if ((this.numRdv == null && other.numRdv != null) || (this.numRdv != null && !this.numRdv.equals(other.numRdv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rdv[ numRdv=" + numRdv + " ]";
    }
    
}
