
package managedBeans;

import entities.Centre;
import entities.PlageRecus;
import entities.Recu;
import entities.Varier;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import sessionBeans.SB;

@SessionScoped
@Named("mB_AgenceComptable")
public class MB_AgenceComptable implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Connexion mbCon;

    public MB_AgenceComptable() {
    }
    
    /////////////////////////////
    
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    
    /////////////////////////////

    //// Plage de Reçu du Trésor
    private PlageRecus plageRecus;
    private Date dateCreationPlage = null;
    private Integer numeroDebutPlage = null;
    private Integer numeroFinPlage = null;

    private Centre centrePaiement = null;
    private String numCentrePaiement;

    //// Plage chevauchée
    private Integer numeroDebutPlageChevauchee = null;
    private Integer numeroFinPlageChevauchee = null;

    //// Plage concernée
    private Integer numeroDebutPlageConcernee = null;
    private Integer numeroFinPlageConcernee = null;

    private Integer nombreRecusUtilises;

    //// TypeDemandeDate 
    private Varier varier;

    //////////    
    private List<Recu> listeRecusDeLaPlage;

    ////////////
    private Integer tarif;

    ////////////
    public PlageRecus getPlageRecus() {
        return plageRecus;
    }

    public void setPlageRecus(PlageRecus plageRecus) {
        this.plageRecus = plageRecus;
    }

    public Date getDateCreationPlage() {
        return dateCreationPlage;
    }

    public void setDateCreationPlage(Date dateCreationPlage) {
        this.dateCreationPlage = dateCreationPlage;
    }

    public Integer getNumeroDebutPlage() {
        return numeroDebutPlage;
    }

    public void setNumeroDebutPlage(Integer numeroDebutPlage) {
        this.numeroDebutPlage = numeroDebutPlage;
    }

    public Integer getNumeroFinPlage() {
        return numeroFinPlage;
    }

    public void setNumeroFinPlage(Integer numeroFinPlage) {
        this.numeroFinPlage = numeroFinPlage;
    }

    public Centre getCentrePaiement() {
        return centrePaiement;
    }

    public void setCentrePaiement(Centre centrePaiement) {
        this.centrePaiement = centrePaiement;
    }

    public String getNumCentrePaiement() {
        return numCentrePaiement;
    }

    public void setNumCentrePaiement(String numCentrePaiement) {
        this.numCentrePaiement = numCentrePaiement;
    }

    public Integer getNumeroDebutPlageChevauchee() {
        return numeroDebutPlageChevauchee;
    }

    public void setNumeroDebutPlageChevauchee(Integer numeroDebutPlageChevauchee) {
        this.numeroDebutPlageChevauchee = numeroDebutPlageChevauchee;
    }

    public Integer getNumeroFinPlageChevauchee() {
        return numeroFinPlageChevauchee;
    }

    public void setNumeroFinPlageChevauchee(Integer numeroFinPlageChevauchee) {
        this.numeroFinPlageChevauchee = numeroFinPlageChevauchee;
    }

    public Integer getNumeroDebutPlageConcernee() {
        return numeroDebutPlageConcernee;
    }

    public void setNumeroDebutPlageConcernee(Integer numeroDebutPlageConcernee) {
        this.numeroDebutPlageConcernee = numeroDebutPlageConcernee;
    }

    public Integer getNumeroFinPlageConcernee() {
        return numeroFinPlageConcernee;
    }

    public void setNumeroFinPlageConcernee(Integer numeroFinPlageConcernee) {
        this.numeroFinPlageConcernee = numeroFinPlageConcernee;
    }

    public Integer getNombreRecusUtilises() {
        return nombreRecusUtilises;
    }

    public void setNombreRecusUtilises(Integer nombreRecusUtilises) {
        this.nombreRecusUtilises = nombreRecusUtilises;
    }

    public Varier getVarier() {
        return varier;
    }

    public void setVarier(Varier varier) {
        this.varier = varier;
    }

    public List<Recu> getListeRecusDeLaPlage() {
        return listeRecusDeLaPlage;
    }

    public void setListeRecusDeLaPlage(List<Recu> listeRecusDeLaPlage) {
        this.listeRecusDeLaPlage = listeRecusDeLaPlage;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    ///////////////////////////////////
    public String creerNouvellePlageRecus() {
        if (numeroDebutPlage > 0 && numeroFinPlage > 0 && numeroDebutPlage <= numeroFinPlage) {

            List<Centre> centres = sb.findByOneCriteria(new Centre(), "numCentre", "'" + numCentrePaiement + "'");
            if (!centres.isEmpty()) {
                centrePaiement = centres.get(0);
            }

            if (this.verifierIntersectionPlage(numeroDebutPlage, numeroFinPlage) == false) {
                PlageRecus plageRecus_ = new PlageRecus();

                dateCreationPlage = new Date();

                plageRecus_.setDateCreationPlageRecus(dateCreationPlage);

                plageRecus_.setNumeroDebutPlageRecus(numeroDebutPlage);
                plageRecus_.setNumeroFinPlageRecus(numeroFinPlage);

                plageRecus_.setNombreRecusUtilises(0);

                List<PlageRecus> plageRecuss = sb.findByTwoCriteria(new PlageRecus(), "centrePaiement", "'" + numCentrePaiement + "'", "statusPlageRecus", "1");
                if (!plageRecuss.isEmpty()) {
                    plageRecus_.setStatusPlageRecus(2);
                } else {
                    plageRecus_.setStatusPlageRecus(1);
                }

                plageRecus_.setCentrePaiement(centrePaiement);

                plageRecus_.setUtilisateurCreateur(mbCon.getUtilisateurConnecte());

                sb.create(plageRecus_);
                plageRecus = plageRecus_;
            } else {
                return mbG.redirigerUrl("chevauchementPlageRecus.xhtml");
            }
        }
        return mbG.redirigerUrl("resultatCreationPlageRecus.xhtml");
    }

    public boolean verifierIntersectionPlage(int numeroDebutPlageRecus, int numeroFinPlageRecus) {
        for (PlageRecus prExistante : listePlageRecus()) {
            if ((numeroDebutPlageRecus <= prExistante.getNumeroFinPlageRecus()
                    && prExistante.getNumeroDebutPlageRecus() <= numeroFinPlageRecus)) {
                numeroDebutPlageChevauchee = prExistante.getNumeroDebutPlageRecus();
                numeroFinPlageChevauchee = prExistante.getNumeroFinPlageRecus();
                return true;
            }
        }
        return false;
    }

    public boolean verifierValiditeNumeroRecuChoisi(int numeroRecuChoisi_, PlageRecus plageRecusActive) {

        if ((numeroRecuChoisi_ >= plageRecusActive.getNumeroDebutPlageRecus()
                && numeroRecuChoisi_ <= plageRecusActive.getNumeroFinPlageRecus())) {
            numeroDebutPlageConcernee = plageRecusActive.getNumeroDebutPlageRecus();
            numeroFinPlageConcernee = plageRecusActive.getNumeroFinPlageRecus();

            System.out.println("verifierValiditeNumeroRecuChoisi(int) ==> vrai");

            return true;
        } else {
            System.out.println("verifierValiditeNumeroRecuChoisi(int) ==> faux");
            return false;
        }
    }

    public boolean verifierDisponibiliteNumeroRecuChoisi(int numeroRecuChoisi_) {

        List<Recu> listeRecus = sb.findByOneCriteria(new Recu(), "numeroRecu", String.valueOf(numeroRecuChoisi_));
        if (!listeRecus.isEmpty()) {
            System.out.println("verifierDisponibiliteNumeroRecuChoisi(int) ==> faux");
            return false;
        } else {
            System.out.println("verifierDisponibiliteNumeroRecuChoisi(int) ==> vrai");
            return true;
        }

    }

    ///////////////////
    public List<PlageRecus> listePlageRecus() {
        return sb.listAll(new PlageRecus());
    }

    public List<Centre> listeCentres() {
        return sb.listAll(new Centre(), " order by nomCentre");
    }

    ///////////////
    public List<Varier> listeVariers() {
        return sb.listAll(new Varier(),
                " where dates in (select r.dates from (select max(dates) 'dates' from varier where typeDemande = 1) r) and typeDemande = 1"
                + " union"
                + " (select * from varier"
                + " where dates in (select r.dates from (select max(dates) 'dates' from varier where typeDemande = 2) r) and typeDemande = 2)");
    }

    public String entrerTarif(Varier varier_) {
        varier = varier_;
        return mbG.redirigerUrl("entreeNouveauTarif.xhtml");
    }

    public void enregistrerNouveauTarif() throws IOException {
        LOGGER.info(mbG.userAndIpStringForMessages("Début enregistrement nouveau tarif pour " + varier.getTypeDemande().getSymboleActuel() +  " (ancien tarif=" + varier.getTarif() +")"));
        
        if(tarif != null && tarif >= 0) {
        Date dateActuelle = new Date();

        Varier varier2 = new Varier();
        varier2.setTarif(tarif);
        varier2.setDates(dateActuelle);
        varier2.setDureeValidite(varier.getDureeValidite());
        varier2.setSymbole(varier.getSymbole());
        varier2.setTypeDemande(varier.getTypeDemande());

        varier2.setUtilisateur(mbCon.getUtilisateurConnecte());
        varier2.setAdresseIP(mbCon.getConnexion().getAdresseIP());

        sb.create(varier2);

        varier.getTypeDemande().setTarifActuel(tarif);
        varier.getTypeDemande().setDateEntreeEnVigueur(dateActuelle);
        sb.update(varier.getTypeDemande());
        
        LOGGER.info(mbG.userAndIpStringForMessages("Fin enregistrement nouveau tarif pour " + varier.getTypeDemande().getSymboleActuel() +  " (positif) : ancien tarif=" + varier.getTarif() +" , nouveau tarif=" + varier2.getTarif()));

        mbG.redirectionVersUrl("tarifsEnVigueur.xhtml");
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez entrer un tarif valide");
            LOGGER.info(mbG.userAndIpStringForMessages("Fin enregistrement nouveau tarif pour " + varier.getTypeDemande().getSymboleActuel() +  " (négatif)"));
        }
        
    }

    public List<PlageRecus> listerPlageRecus(int typePlage) {
        List<PlageRecus> listePlageRecus = sb.findByOneCriteria(new PlageRecus(), "statusPlageRecus", String.valueOf(typePlage));

        // Sorting
        Collections.sort(listePlageRecus, new Comparator<PlageRecus>() {

            @Override
            public int compare(PlageRecus pr1, PlageRecus pr2) {
                return pr1.getCentrePaiement().getNomCentre().compareTo(pr2.getCentrePaiement().getNomCentre());
            }
        });
        
        // Sorting
        Collections.sort(listePlageRecus, new Comparator<PlageRecus>() {

            @Override
            public int compare(PlageRecus pr1, PlageRecus pr2) {
                if (pr1.getNumeroDebutPlageRecus() < pr2.getNumeroDebutPlageRecus()) {
                    return -1;
                } else if (pr1.getNumeroDebutPlageRecus() == pr2.getNumeroDebutPlageRecus()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        
        return listePlageRecus;
    }

    public List<PlageRecus> listePlageRecusActives() {
        return listerPlageRecus(1);
    }
    
    public List<PlageRecus> listePlageRecusEnAttente() {
        return listerPlageRecus(2);
    }

    public List<PlageRecus> listePlageRecusDejaUtilisees() {
        return listerPlageRecus(0);
    }

    public String listeRecusDeLaPlage(PlageRecus plageRecus) {
        int typePlage = plageRecus.getStatusPlageRecus();
        
        listeRecusDeLaPlage = sb.findByTwoCriteria(new Recu(), "plageRecus", String.valueOf(plageRecus.getNumPlageRecus()), "aEteGenere", "1");
        // Sorting
        Collections.sort(listeRecusDeLaPlage, new Comparator<Recu>() {

            @Override
            public int compare(Recu r1, Recu r2) {
                if (r1.getNumeroRecu() < r2.getNumeroRecu()) {
                    return -1;
                } else if (r1.getNumeroRecu() == r2.getNumeroRecu()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        if (typePlage == 1)
        return mbG.redirigerUrl("recusDeLaPlageActive.xhtml");
        else if (typePlage == 0)
            return mbG.redirigerUrl("recusDeLaPlageDejaUtilisee.xhtml");
        else
            return null;
    }
    
    public String supprimerPlageRecus(int numPlageRecus_) {
        LOGGER.info(mbG.userAndIpStringForMessages("Début suppression plage reçus n°" + numPlageRecus_));
        sb.deleteWithOneCriteria(new PlageRecus(), "numPlageRecus", String.valueOf(numPlageRecus_));
        LOGGER.info(mbG.userAndIpStringForMessages("Fin suppression plage reçus n°" + numPlageRecus_ + "(positif)"));
        return mbG.redirigerUrl("plagesRecusEnAttente.xhtml");
    }

    ///////////////////
    public void clearInputTextFields() {

        ///////
        dateCreationPlage = null;
        numeroDebutPlage = null;
        numeroFinPlage = null;

        numeroDebutPlageConcernee = null;
        numeroFinPlageConcernee = null;

        numCentrePaiement = null;
    }
    
    ///////////
    private int valeurFiltreNumeroRecu;

    public int getValeurFiltreNumeroRecu() {
        return valeurFiltreNumeroRecu;
    }

    public void setValeurFiltreNumeroRecu(int valeurFiltreNumeroRecu) {
        this.valeurFiltreNumeroRecu = valeurFiltreNumeroRecu;
    }
    
    public List<PlageRecus> plageRecusContenantLeRecuNumero(int numeroRecu) {
        return sb.findPlageRecusContenantLeRecuNumero(numeroRecu);
    }

    //////////////////////:
    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        plageRecus = null;
        return mbG.retournerAuMenuGeneral();
    }

    //////////////////////:
    public String retournerACreationPlageNumerosRecus() {
        this.clearInputTextFields();
        return mbG.redirigerUrl("creationPlageNumerosRecus.xhtml");
    }
    
    ///////////////
    public boolean validateInputNouveauTarif() {
        if (tarif == null) {
            return false;
        }
        return true;
    }
    
    public boolean validateInputCreationPlageRecus() {
        if ((numeroDebutPlage == null)
                || (numeroFinPlage == null)
                || (numCentrePaiement == null)) {
            return false;
        }
        return true;
    }

}
