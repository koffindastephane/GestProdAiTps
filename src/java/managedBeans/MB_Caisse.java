package managedBeans;

import entities.CasDemande;
import entities.Centre;
import entities.Correspondre;
import entities.Demande;
import entities.Evoluer;
import entities.Petitionnaire;
import entities.PlageRecus;
import entities.Recu;
import entities.Status;
import entities.TypeDemande;
import entities.TypeDocument;
import entities.Varier;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import sessionBeans.SB;
import traitementsJasper.GenerationRapport;

@SessionScoped
@Named("mB_Caisse")
public class MB_Caisse implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Connexion mbCon;

    public MB_Caisse() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /////////////////////////////
    ////// TypeDemande
    private int numTypeDemande;
    private TypeDemande typeDemande;

    ////// Status
    private int numStatus;
    private Status status;

    /////
    private String adresseIPClient;

    //// Plage de Reçu du Trésor
    private PlageRecus plageRecus;
    private Date dateCreationPlage;
    private Integer numeroDebutPlage;
    private Integer numeroFinPlage;

    private Centre centrePaiement;
    private String numCentrePaiement;

    private Integer numeroRecuChoisi;

    //// Plage chevauchée
    private Integer numeroDebutPlageChevauchee;
    private Integer numeroFinPlageChevauchee;

    //// Plage concernée
    private Integer numeroDebutPlageConcernee;
    private Integer numeroFinPlageConcernee;

    private Integer nombreRecusUtilises;

    ////// Reçu
    private Recu recu;
    private Integer numeroRecu;
    private Date dateVersement;
    private Integer montantPaye;

    ////// CasDemande
    private CasDemande casDemande;
    private Integer numCasDemande;

////// Demande
    private Demande demande;
    private String numDemande;
    private String numDemandeNontrouvee;

    private Date dateCreationDemande;

    private Centre centreTraitement;
    private String numCentreTraitement;

///////// petitionnaire
    private Petitionnaire petitionnaire;
    private int numPetitionnaire;

    private String nom;
    private String prenoms;
    private Date dateNaissance;

    ///////// Reçu
    private int numeroRecuNontrouve;

    //////////
    private Integer docNaissance;

////// Correspondre
    private Correspondre correspondre;

//// TypeDemandeDate 
    private Varier varier;

    //TypeDocument
    private TypeDocument typeDocument;

    //Evoluer
    private Evoluer evoluer;

    private Evoluer evoluerFinTraitement;

    ///////////////////////
    private Date dateCourante;
    //////////////////////

    private String cheminRecuGenere;
    private String urlRecuGenere;

    private Integer tarif;

    ///Getters and Setters
    public int getNumTypeDemande() {
        return numTypeDemande;
    }

    public void setNumTypeDemande(int numTypeDemande) {
        this.numTypeDemande = numTypeDemande;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public int getNumStatus() {
        return numStatus;
    }

    public void setNumStatus(int numStatus) {
        this.numStatus = numStatus;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAdresseIPClient() {
        return adresseIPClient;
    }

    public void setAdresseIPClient(String adresseIPClient) {
        this.adresseIPClient = adresseIPClient;
    }

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

    public Integer getNumeroRecuChoisi() {
        return numeroRecuChoisi;
    }

    public void setNumeroRecuChoisi(Integer numeroRecuChoisi) {
        this.numeroRecuChoisi = numeroRecuChoisi;
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

    public Recu getRecu() {
        return recu;
    }

    public void setRecu(Recu recu) {
        this.recu = recu;
    }

    public Integer getNumeroRecu() {
        return numeroRecu;
    }

    public void setNumeroRecu(Integer numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public CasDemande getCasDemande() {
        return casDemande;
    }

    public void setCasDemande(CasDemande casDemande) {
        this.casDemande = casDemande;
    }

    public Integer getNumCasDemande() {
        return numCasDemande;
    }

    public void setNumCasDemande(Integer numCasDemande) {
        this.numCasDemande = numCasDemande;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public String getNumDemande() {
        return numDemande;
    }

    public void setNumDemande(String numDemande) {
        this.numDemande = numDemande;
    }

    public String getNumDemandeNontrouvee() {
        return numDemandeNontrouvee;
    }

    public void setNumDemandeNontrouvee(String numDemandeNontrouvee) {
        this.numDemandeNontrouvee = numDemandeNontrouvee;
    }

    public Date getDateCreationDemande() {
        return dateCreationDemande;
    }

    public void setDateCreationDemande(Date dateCreationDemande) {
        this.dateCreationDemande = dateCreationDemande;
    }

    public Centre getCentreTraitement() {
        return centreTraitement;
    }

    public void setCentreTraitement(Centre centreTraitement) {
        this.centreTraitement = centreTraitement;
    }

    public String getNumCentreTraitement() {
        return numCentreTraitement;
    }

    public void setNumCentreTraitement(String numCentreTraitement) {
        this.numCentreTraitement = numCentreTraitement;
    }

    public Petitionnaire getPetitionnaire() {
        return petitionnaire;
    }

    public void setPetitionnaire(Petitionnaire petitionnaire) {
        this.petitionnaire = petitionnaire;
    }

    public int getNumPetitionnaire() {
        return numPetitionnaire;
    }

    public void setNumPetitionnaire(int numPetitionnaire) {
        this.numPetitionnaire = numPetitionnaire;
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

    public int getNumeroRecuNontrouve() {
        return numeroRecuNontrouve;
    }

    public void setNumeroRecuNontrouve(int numeroRecuNontrouve) {
        this.numeroRecuNontrouve = numeroRecuNontrouve;
    }

    public Integer getDocNaissance() {
        return docNaissance;
    }

    public void setDocNaissance(Integer docNaissance) {
        this.docNaissance = docNaissance;
    }

    public Correspondre getCorrespondre() {
        return correspondre;
    }

    public void setCorrespondre(Correspondre correspondre) {
        this.correspondre = correspondre;
    }

    public Varier getVarier() {
        return varier;
    }

    public void setVarier(Varier varier) {
        this.varier = varier;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Evoluer getEvoluer() {
        return evoluer;
    }

    public void setEvoluer(Evoluer evoluer) {
        this.evoluer = evoluer;
    }

    public Evoluer getEvoluerFinTraitement() {
        return evoluerFinTraitement;
    }

    public void setEvoluerFinTraitement(Evoluer evoluerFinTraitement) {
        this.evoluerFinTraitement = evoluerFinTraitement;
    }

    public Date getDateCourante() {
        return dateCourante;
    }

    public void setDateCourante(Date dateCourante) {
        this.dateCourante = dateCourante;
    }

    public String getCheminRecuGenere() {
        return cheminRecuGenere;
    }

    public void setCheminRecuGenere(String cheminRecuGenere) {
        this.cheminRecuGenere = cheminRecuGenere;
    }

    public String getUrlRecuGenere() {
        return urlRecuGenere;
    }

    public void setUrlRecuGenere(String urlRecuGenere) {
        this.urlRecuGenere = urlRecuGenere;
    }

    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

    ////////////////////
    public String allerAEtape(int numStatus_, int numTypeDemande_) {
        numTypeDemande = numTypeDemande_;
        numStatus = numStatus_;

        List<TypeDemande> typeDemandes_ = sb.findByOneCriteria(new TypeDemande(), "numTypeDemande", String.valueOf(numTypeDemande_));
        if (!typeDemandes_.isEmpty()) {
            typeDemande = typeDemandes_.get(0);
        }

        List<Status> statuss_ = sb.findByOneCriteria(new Status(), "numStatus", String.valueOf(numStatus_));
        if (!statuss_.isEmpty()) {
            status = statuss_.get(0);
        }

        evoluer = null;

        return this.allerAuMenuCaisse();
    }
    /*
    public String rechercherRecu(String numDem) {
    numDemande = numDem;
    docNaissance = 1;
    return this.rechercherRecu();
    }
     */

    public void rechercherRecu() throws IOException {
        LOGGER.info(mbG.userAndIpStringForMessages("Début recherche du reçu n°" + numeroRecu));
        List<Recu> recus = sb.findByTwoCriteria(new Recu(), "numeroRecu", String.valueOf(numeroRecu), "typeDemande", String.valueOf(numTypeDemande));
        if (!recus.isEmpty()) {
            recu = recus.get(0);

            List<Demande> demandes = sb.findByOneCriteria(new Demande(), "recu", String.valueOf(recu.getNumRecu()));
            if (!demandes.isEmpty()) {
                demande = demandes.get(0);

                List<Evoluer> evoluers = sb.findLastInsertedAtADateAndOneCriteria(new Evoluer(), "dates", new Date(), "demande", "'" + demande.getNumDemande() + "'");
                if (!evoluers.isEmpty()) {
                    evoluer = evoluers.get(0);

                    this.clearInputTextFields();

                    if (evoluer.getStatus().getNumStatus() > 0) {
                        this.determinerEvoluerFinTraitement(demande.getNumDemande(), 1);
                    }
                    LOGGER.info(mbG.userAndIpStringForMessages("Reçu n°" + recu.getNumeroRecu() + " trouvé"));
                    mbG.redirectionVersUrl("editionRecu.xhtml");
                } else {
                    numeroRecuNontrouve = numeroRecu;
                    mbG.ajouterMessage("info", "reçu non trouvé");
                    LOGGER.info(mbG.userAndIpStringForMessages("Reçu n°" + numeroRecu + " non trouvé"));
                    this.clearInputTextFields();
                }
            } else {
                numeroRecuNontrouve = numeroRecu;
                mbG.ajouterMessage("info", "reçu non trouvé");
                LOGGER.info(mbG.userAndIpStringForMessages("Reçu n°" + numeroRecu + " non trouvé"));
                this.clearInputTextFields();
            }
        } else {
            numeroRecuNontrouve = numeroRecu;
            mbG.ajouterMessage("info", "reçu non trouvé");
            LOGGER.info(mbG.userAndIpStringForMessages("Reçu n°" + numeroRecu + " non trouvé"));
            this.clearInputTextFields();
        }
    }

    public void rechercherDemande() throws IOException {
        List<Demande> demandes = sb.findByTwoCriteria(new Demande(), "numDemande", "'" + numDemande + "'", "typeDemande", String.valueOf(numTypeDemande));
        if (!demandes.isEmpty()) {
            demande = demandes.get(0);

            List<Recu> recus = sb.findByOneCriteria(new Recu(), "recu", String.valueOf(demande.getRecu().getNumRecu()));
            if (!recus.isEmpty()) {
                recu = recus.get(0);
            }

            List<Evoluer> evoluers = sb.findLastInsertedAtADateAndOneCriteria(new Evoluer(), "dates", new Date(), "demande", "'" + demande.getNumDemande() + "'");
            if (!evoluers.isEmpty()) {
                evoluer = evoluers.get(0);

                this.clearInputTextFields();

                if (evoluer.getStatus().getNumStatus() > 0) {
                    this.determinerEvoluerFinTraitement(demande.getNumDemande(), 1);
                }
                mbG.redirectionVersUrl("editionRecu.xhtml");
            } else {
                mbG.ajouterMessage("info", "demande non trouvée");
            }
        } else {
            numDemandeNontrouvee = numDemande;
            this.clearInputTextFields();
            mbG.ajouterMessage("info", "demande non trouvée");
        }
    }
    /*
     public String rechercherDemande(String numDem) {
     numDemande = numDem;
     docNaissance = 1;
     return this.rechercherDemande();
     }
     */

    ///////////////
    public List<Demande> listeDemandesDispoPourUnNiveau(int numStatus) {
        return sb.listerDemandesDispoPourUnNiveau(numStatus, numTypeDemande, mbCon.getUtilisateurConnecte().getCentreTravail().getNumCentre());
    }
    ///////////////

    public void gererCasse() {

        nom = mbG.ecrireEnMaj(nom);
        prenoms = mbG.ecrireEnMaj(prenoms);

    }

    public void editerRecu() throws IOException {

        if (this.validateInputCreationRecu()) {

            this.gererCasse();

            this.definirPlageRecuActiveEtNombreRecusUtilises();
            if (plageRecus != null) {
                if (this.verifierValiditeNumeroRecuChoisi(numeroRecuChoisi) == true) {
                    if (verifierDisponibiliteNumeroRecuChoisi(numeroRecuChoisi) == 1) {
                        this.creerPetitionnaire();
                        try {
                            this.creerRecu();
                        } catch (IOException e) {
                            LOGGER.error(mbG.userAndIpStringForMessages("IOException lors de création reçu : " + e.getMessage()));
                        }
                        //            this.creerDemande();
                        //            this.initialiserEvoluer();
                        LOGGER.info(mbG.userAndIpStringForMessages("Création reçu réussie !"));
                        // this.clearInputTextFields();
                        mbG.redirectionVersUrl("editionRecu.xhtml");
                    } else if (verifierDisponibiliteNumeroRecuChoisi(numeroRecuChoisi) == 0) {
                        mbG.ajouterMessage("Erreur", "Le numéro de reçu " + numeroRecuChoisi + " a déjà été utilisé");
                        LOGGER.info(mbG.userAndIpStringForMessages("Le numéro de reçu " + numeroRecuChoisi + " a déjà été utilisé"));
                    } else if (verifierDisponibiliteNumeroRecuChoisi(numeroRecuChoisi) == -1) {
                        mbG.ajouterMessage("Erreur", "Le numéro de reçu " + numeroRecuChoisi + " est en cours d'utilisation par un autre utilisateur");
                        LOGGER.info(mbG.userAndIpStringForMessages("Le numéro de reçu " + numeroRecuChoisi + " est en cours d'utilisation par un autre utilisateur"));
                    }
                } else {
                    mbG.ajouterMessage("Erreur", "N° de reçu (" + numeroRecuChoisi + ") invalide pour le centre n°" + centrePaiement.getNumCentre() + "(" + centrePaiement.getNomCentre() + ").");
                    LOGGER.info(mbG.userAndIpStringForMessages("N° de reçu (" + numeroRecuChoisi + ") invalide pour le centre n°" + centrePaiement.getNumCentre() + "(" + centrePaiement.getNomCentre() + ")."));
                }
            } else {
                this.clearInputTextFields();
                mbG.ajouterMessage("Erreur", "Plus de reçu disponible pour le centre n°" + centrePaiement.getNumCentre() + "(" + centrePaiement.getNumCentre() + ").");
                LOGGER.info(mbG.userAndIpStringForMessages("Plus de reçu disponible pour le centre n°" + centrePaiement.getNumCentre() + "(" + centrePaiement.getNumCentre() + ")."));
            }
        } else {            
            mbG.ajouterMessage("erreur", "Veuillez renseigner tous les champs nécessaires");
            LOGGER.trace(mbG.userAndIpStringForMessages("Veuillez renseigner tous les champs nécessaires"));
        }
    }

    /////////////////////////////////////
    public void creerRecu() throws IOException {
        Recu r = new Recu();

        List<Recu> recus_ = sb.findByThreeCriteria(new Recu(), "numeroRecu", String.valueOf(numeroRecuChoisi), "aEteGenere", "0", "utilisateurCreateur", String.valueOf(mbCon.getUtilisateurConnecte().getNumUtilisateur()));
        if (!recus_.isEmpty()) {
            r = recus_.get(0);
        }

        dateVersement = new Date();

        // Pour typeDemande
        List<TypeDemande> typeDemandes_ = sb.findByOneCriteria(new TypeDemande(), "numTypeDemande", String.valueOf(numTypeDemande));
        if (!typeDemandes_.isEmpty()) {
            // ignores multiple results
            typeDemande = typeDemandes_.get(0);
        }
        r.setTypeDemande(typeDemande);

        r.setNumeroRecu(numeroRecuChoisi);
        r.setDateVersement(dateVersement);
        r.setMontantPaye(typeDemande.getTarifActuel());
        r.setPetitionnaire(petitionnaire);
        r.setUtilisateurCreateur(mbCon.getUtilisateurConnecte());

        // Pour casDemande
        List<CasDemande> casDemandes_ = sb.findByOneCriteria(new CasDemande(), "numCasDemande", String.valueOf(numCasDemande));
        if (!casDemandes_.isEmpty()) {
            // ignores multiple results
            casDemande = casDemandes_.get(0);
        }
        r.setCasDemande(casDemande);

        r.setAEteGenere(false);

        plageRecus.setDateDerniereUtilisationPlageRecus(dateVersement);
        sb.update(plageRecus);
        r.setPlageRecus(plageRecus);

        if (!recus_.isEmpty()) {
            sb.update(r);
        } else {
            sb.create(r);
        }

        recu = r;
        LOGGER.info(mbG.userAndIpStringForMessages("Recu n°" + recu.getNumeroRecu() + " créé"));
    }

    public void creerPetitionnaire() {
        Petitionnaire p = new Petitionnaire();

        p.setNom(nom);
        p.setPrenoms(prenoms);
        p.setDateNaissance(dateNaissance);

        sb.create(p);
        petitionnaire = p;
        LOGGER.info(mbG.userAndIpStringForMessages("Pétitionnaire n°" + petitionnaire.getNumPetitionnaire() + " créé"));
    }

    public List<Correspondre> listeCorrespondre() {
        return sb.findByOneCriteria(new Correspondre(), "typeDemande", String.valueOf(numTypeDemande));
    }

    public void creerDemande() {

        centreTraitement = mbCon.getUtilisateurConnecte().getCentreTravail();

        /// Variables composants le N°Demande
        int valeurCompteur = 0;
        int anneeEnCours;
        String symbole;
        String codeCentreTraitement;

        /// Création d'une Demande vide (pour le moment)
        Demande d = new Demande();

        d.setRecu(recu);

        // Pour dateCreationDemande
        dateCreationDemande = new Date();

        anneeEnCours = dateCreationDemande.getYear() + 1900;

        d.setDateCreationDemande(dateCreationDemande);

        //// Mise à jour de la valeur du compteur
        List<Demande> demandes = (List<Demande>) sb.findLastInsertedAtADate(new Demande(), "dateCreationDemande", dateCreationDemande);
        if (!demandes.isEmpty()) {
            Demande lastInserted = demandes.get(0);

            Date lastInsertedDateTime = lastInserted.getDateCreationDemande();

            if (dateCreationDemande.getYear() == lastInsertedDateTime.getYear()) {
                if (typeDemande.getNumTypeDemande() == 1) {
                    centreTraitement.setValeurCompteurAi(centreTraitement.getValeurCompteurAi() + 1);
                } else if (typeDemande.getNumTypeDemande() == 2) {
                    centreTraitement.setValeurCompteurTps(centreTraitement.getValeurCompteurTps() + 1);
                }
            } else {
                if (typeDemande.getNumTypeDemande() == 1) {
                    centreTraitement.setValeurCompteurAi(1);
                } else if (typeDemande.getNumTypeDemande() == 2) {
                    centreTraitement.setValeurCompteurTps(1);
                }
            }
        } else {
            if (typeDemande.getNumTypeDemande() == 1) {
                centreTraitement.setValeurCompteurAi(centreTraitement.getValeurCompteurAi() + 1);
            } else if (typeDemande.getNumTypeDemande() == 2) {
                centreTraitement.setValeurCompteurTps(centreTraitement.getValeurCompteurTps() + 1);
            }
        }

        sb.update(centreTraitement);
        d.setCentreTraitement(centreTraitement);

        //// Récupération de valeurCompteur
        if (typeDemande.getNumTypeDemande() == 1) {
            valeurCompteur = centreTraitement.getValeurCompteurAi();
        } else if (typeDemande.getNumTypeDemande() == 2) {
            valeurCompteur = centreTraitement.getValeurCompteurTps();
        }

        //// Récupération du symbole AI ou TPS
//        List<Varier> variers = sb.findLastInsertedAtADateAndOneCriteria(
//                new Varier(), "dates", dateCreationDemande, "typeDemande", String.valueOf(numTypeDemande));
//
//        if (!variers.isEmpty()) {
//            varier = variers.get(0);
//        }
        symbole = typeDemande.getSymboleActuel();

        //// Code du centre
        codeCentreTraitement = centreTraitement.getNumCentre();

        //// Détermination du N°Demande
        numDemande = codeCentreTraitement.replace(" ", "") + "-" + String.format("%08d", valeurCompteur) + "-" + symbole + "-" + anneeEnCours;

        d.setNumDemande(numDemande);

        // pour docJustificatifNais
        d.setDocJustificatifNais(docNaissance);

        sb.create(d);

        sb.update(d.getRecu());
        sb.update(d);

        demande = d;

        recu = d.getRecu();
        LOGGER.info(mbG.userAndIpStringForMessages("Demande n°" + demande.getNumDemande() + " créée"));
    }

    public void initialiserEvoluer() {
        //Pour initialiser evoluer                
        evoluer = mbG.changerStatusDemande(demande, 0);
    }

    public List<TypeDemande> listeTypeDemande() {
        return sb.listAll(new TypeDemande());
    }

    public void determinerEvoluerFinTraitement(String numDemande, int numStatus) {
        evoluerFinTraitement = mbG.recupererEvoluerFinTraitement(numDemande, numStatus);
    }

    ///////////////////////////////
    //   String username = System.getenv("USERNAME");
    public void genererRecuEnPdf() throws SQLException, JRException, IOException {

        HashMap hashmap = new HashMap();
        hashmap.put("numDemande", demande.getNumDemande());

        Connection con = mbG.connectionBddAiTps();

        cheminRecuGenere = mbG.dossierRecusGeneres() + "/" + "recu_demande_" + demande.getNumDemande() + ".pdf";

        urlRecuGenere = mbG.urlContextApp() + mbG.dossierRecusGeneres().substring(2) + "/" + "recu_demande_" + demande.getNumDemande() + ".pdf";

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdf(mbG.cheminFichierJrxml(), hashmap, con, cheminRecuGenere);

    }

    public void genererRecuEnPdfStream() throws SQLException, JRException, IOException {

        HashMap hashmap = new HashMap();
        hashmap.put("numDemande", demande.getNumDemande());

        Connection con = mbG.connectionBddAiTps();

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdfStream(mbG.cheminFichierJrxml(), hashmap, con);
        LOGGER.info(mbG.userAndIpStringForMessages("Reçu n°" + recu.getNumeroRecu() + " généré en PDF stream"));
    }

    ///////////////////////////////
    public void genererRecepisseEtRendreDemandeDispoPourEnregistrement() throws SQLException, JRException, IOException {

        this.creerDemande();
        //     this.genererRecuEnPdf();
        evoluer = mbG.changerStatusDemande(demande, 1);
        this.determinerEvoluerFinTraitement(demande.getNumDemande(), 1);
        recu.setAEteGenere(true);
        sb.update(recu);
        plageRecus.setNombreRecusUtilises(plageRecus.getNombreRecusUtilises() + 1);
        this.gererFinPlage(plageRecus);
        sb.update(plageRecus);
        this.clearInputTextFields();
        LOGGER.info(mbG.userAndIpStringForMessages("Récépissé généré et passage de niveau (caisse -> enregistrement)"));
        mbG.redirectionVersUrl("editionRecu.xhtml");
    }

    ///////////////////
    public String annulerPaiement() {
        sb.delete(recu);
        LOGGER.info(mbG.userAndIpStringForMessages("Paiement annulé (reçu n°" + recu.getNumeroRecu() + " - plageReçu n°" + recu.getPlageRecus().getNumPlageRecus() + " - centre n°" + recu.getPlageRecus().getCentrePaiement().getNumCentre() + " (" + recu.getPlageRecus().getCentrePaiement().getNomCentre() + ")"));
        return this.allerAuMenuCaisse();
    }

    ////////////////////        
    public void clearInputTextFields() {

        numeroRecuChoisi = null;
        numeroRecu = null;
        dateVersement = null;
        montantPaye = null;

        nom = null;
        prenoms = null;
        dateNaissance = null;

        numDemande = null;
        numeroRecu = null;

        dateCreationDemande = null;

        numCasDemande = null;

        docNaissance = null;

        ///////
        dateCreationPlage = null;
        numeroDebutPlage = null;
        numeroFinPlage = null;

        numeroDebutPlageConcernee = null;
        numeroFinPlageConcernee = null;

        numCentrePaiement = null;
        numCentreTraitement = null;
    }

//////////////////////
    public String allerAuMenuCaisse() {
        this.clearInputTextFields();
        recu = null;
        demande = null;
        return mbG.redirigerUrl("/caisse/menuCaisse.xhtml");
    }

    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        demande = null;
        return mbG.retournerAuMenuGeneral();
    }

    //////////////////////:
    public String retournerACreationPlageNumerosRecus() {
        this.clearInputTextFields();
        return mbG.redirigerUrl("creationPlageNumerosRecus.xhtml");
    }

    ///////////////////////////////////
    public String creerNouvellePlageRecus() {
        if (numeroDebutPlage > 0 && numeroFinPlage > 0 && numeroDebutPlage <= numeroFinPlage) {
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

                List<Centre> centres = sb.findByOneCriteria(new Centre(), "numCentre", "'" + numCentrePaiement + "'");
                if (!centres.isEmpty()) {
                    centrePaiement = centres.get(0);
                }
                plageRecus_.setCentrePaiement(centrePaiement);

                sb.create(plageRecus_);
                plageRecus = plageRecus_;
                LOGGER.info(mbG.userAndIpStringForMessages("Plage de reçus n°" + plageRecus.getNumPlageRecus() + " créée pour le centre n°" + plageRecus.getCentrePaiement().getNumCentre() + "(" + plageRecus.getCentrePaiement().getNomCentre() + ")"));
            } else {
                LOGGER.info(mbG.userAndIpStringForMessages("Chevauchement Plage de reçus"));
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

    public boolean verifierValiditeNumeroRecuChoisi(int numeroRecuChoisi_) {
        centrePaiement = mbCon.getUtilisateurConnecte().getCentreTravail();

        if ((numeroRecuChoisi_ >= plageRecus.getNumeroDebutPlageRecus()
                && numeroRecuChoisi_ <= plageRecus.getNumeroFinPlageRecus())) {
            numeroDebutPlageConcernee = plageRecus.getNumeroDebutPlageRecus();
            numeroFinPlageConcernee = plageRecus.getNumeroFinPlageRecus();

            System.out.println("verifierValiditeNumeroRecuChoisi(int) ==> vrai");

            return true;
        } else {
            System.out.println("verifierValiditeNumeroRecuChoisi(int) ==> faux");
            return false;
        }
    }

    public int verifierDisponibiliteNumeroRecuChoisi(int numeroRecuChoisi_) {

        List<Recu> listeRecus = sb.findByOneCriteria(new Recu(), "numeroRecu", String.valueOf(numeroRecuChoisi_));
        if (!listeRecus.isEmpty()) {
            Recu recu_ = listeRecus.get(0);
            if (recu_.getAEteGenere() == true) {
                System.out.println("verifierDisponibiliteNumeroRecuChoisi(int) ==> 0");
                return 0;
            } else {
                if (recu_.getUtilisateurCreateur().getNumUtilisateur() != mbCon.getUtilisateurConnecte().getNumUtilisateur()) {
                    System.out.println("verifierDisponibiliteNumeroRecuChoisi(int) ==> -1");
                    return -1;
                } else {
                    System.out.println("verifierDisponibiliteNumeroRecuChoisi(int) ==> 1");
                    return 1;
                }
            }
        } else {
            System.out.println("verifierDisponibiliteNumeroRecuChoisi(int) ==> 1");
            return 1;
        }

    }

    /////////////////////
    public void definirPlageRecuActiveEtNombreRecusUtilises() {

        plageRecus = null;

        List<PlageRecus> plageRecuss;

        List<Centre> centres = sb.findByOneCriteria(new Centre(), "numCentre", "'" + numCentrePaiement + "'");
        if (!centres.isEmpty()) {
            centrePaiement = centres.get(0);
        }

        plageRecuss = sb.findByTwoCriteria(new PlageRecus(), "centrePaiement", "'" + numCentrePaiement + "'", "statusPlageRecus", "1");
        if (!plageRecuss.isEmpty()) {
            plageRecus = plageRecuss.get(0);
        } else {
            plageRecuss = sb.findProchainePlageRecus(numCentrePaiement);
            if (!plageRecuss.isEmpty()) {
                plageRecus = plageRecuss.get(0);

                plageRecus.setStatusPlageRecus(1);

                sb.update(plageRecus);
            }
        }
    }

    ///////////////////////
    public void gererFinPlage(PlageRecus plageRecus_) {
        if (plageRecus_ != null) {
            int nombreRecusUtilises_ = plageRecus_.getNombreRecusUtilises();

            if (nombreRecusUtilises_ == (plageRecus_.getNumeroFinPlageRecus()) - (plageRecus_.getNumeroDebutPlageRecus()) + 1) {
                plageRecus_.setStatusPlageRecus(0);
                plageRecus_.setDateFinPlageRecus(new Date());
                sb.update(plageRecus_);

                List<PlageRecus> plageRecuss = sb.findProchainePlageRecus(plageRecus_.getCentrePaiement().getNumCentre());
                if (!plageRecuss.isEmpty()) {
                    plageRecus_ = plageRecuss.get(0);

                    plageRecus_.setStatusPlageRecus(1);

                    sb.update(plageRecus_);
                }
            }
        }
    }

    ///////////////////
    public List<PlageRecus> listePlageRecus() {
        return sb.listAll(new PlageRecus());
    }

    public List<Centre> listeCentres() {
        return sb.listAll(new Centre());
    }

    ///////////////
    public List<Varier> listeVariers() {
        return sb.listAll(new Varier(),
                " where dates in (select r.dates from (select max(dates) 'dates' from varier where typeDemande = 1) r) and typeDemande = 1"
                + " union"
                + " (select * from varier"
                + " where dates in (select r.dates from (select max(dates) 'dates' from varier where typeDemande = 2) r) and typeDemande = 2)");
    }

    ////////////// Input validation
    public boolean validateInputCreationRecu() {
        if (numeroRecuChoisi == null
                || numCasDemande == null
                || nom == null
                || prenoms == null
                || dateNaissance == null) {
            return false;
        }
        if (numTypeDemande == 1) {
            if (docNaissance == null) {
                return false;
            }
        }
        return true;
    }

}
