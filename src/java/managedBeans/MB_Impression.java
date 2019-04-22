package managedBeans;

import entities.Demande;
import entities.DemandeEnCoursDUtilisation;
import entities.DocumentFourni;
import entities.Etat;
import entities.Evoluer;
import entities.Photo;
import entities.Rdv;
import entities.Status;
import entities.TitreIdentite;
import entities.TypeDemande;
import entities.TypeDocument;
import entities.TypePhoto;
import entities.Varier;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import sessionBeans.SB;
import traitementsJasper.GenerationRapport;

@SessionScoped
@Named("mB_Impression")
public class MB_Impression implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_GestionFichiers mbGf;

    @Inject
    MB_Connexion mbCon;

    @Inject
    MB_TraitementsEmails mbTrEmails;

    public MB_Impression() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /////////////////////////////
    ////Titre
    private String numeroFinalDuTitreProduit;
    private TitreIdentite titreIdentite;

    ////// TypeDemande
    private int numTypeDemande;
    private TypeDemande typeDemande;

    ////// Status
    private int numStatus;
    private Status status;

    ////// Demande
    private String numDemande;
    private Demande demande;

    private String numDemandeNontrouvee;

    // TypeDocument
    private TypeDocument typeDocument;

    private int numDocumentNecessaire;
    private int[] numsDocumentNecessaires;

    //DocumentFourni
    private DocumentFourni documentFourni;
    private Etat etat; //// DocumentFourni

    //Pour afficher Premiers documents fournis
    private DocumentFourni dfExt;
    //   private DocumentFourni dfPhotocopExt;
    private DocumentFourni dfJugSup;
    //   private DocumentFourni dfPhotocopJugSup;
    private DocumentFourni dfCertificatNat;
    private DocumentFourni dfPass;
    private DocumentFourni dfVisa;
    private DocumentFourni dfCachetEntree;

    //
    private Evoluer evoluer;

    private Evoluer evoluerFinTraitement;
    //

    //
    private Varier varier;

    // Rdv
    private Rdv rdv;

    // TypePhoto
    private TypePhoto typePhoto;
    // Photo
    private Photo photo;

    ////
    private Date dateExpirationTitreIdentite;
    private Date dateEtablissementTitreIdentite;

    //////////
    private boolean titreEstEdite = false;

    /////////
    private String cheminTitreGenere;

    private String urlTitreGenere;

    private String infosQrCode;

    //// getters and setters
    public String getNumeroFinalDuTitreProduit() {
        return numeroFinalDuTitreProduit;
    }

    public void setNumeroFinalDuTitreProduit(String numeroFinalDuTitreProduit) {
        this.numeroFinalDuTitreProduit = numeroFinalDuTitreProduit;
    }

    public TitreIdentite getTitreIdentite() {
        return titreIdentite;
    }

    public void setTitreIdentite(TitreIdentite titreIdentite) {
        this.titreIdentite = titreIdentite;
    }

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

    public String getNumDemande() {
        return numDemande;
    }

    public void setNumDemande(String numDemande) {
        this.numDemande = numDemande;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public String getNumDemandeNontrouvee() {
        return numDemandeNontrouvee;
    }

    public void setNumDemandeNontrouvee(String numDemandeNontrouvee) {
        this.numDemandeNontrouvee = numDemandeNontrouvee;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }

    public int getNumDocumentNecessaire() {
        return numDocumentNecessaire;
    }

    public void setNumDocumentNecessaire(int numDocumentNecessaire) {
        this.numDocumentNecessaire = numDocumentNecessaire;
    }

    public int[] getNumsDocumentNecessaires() {
        return numsDocumentNecessaires;
    }

    public void setNumsDocumentNecessaires(int[] numsDocumentNecessaires) {
        this.numsDocumentNecessaires = numsDocumentNecessaires;
    }

    public DocumentFourni getDocumentFourni() {
        return documentFourni;
    }

    public void setDocumentFourni(DocumentFourni documentFourni) {
        this.documentFourni = documentFourni;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public DocumentFourni getDfExt() {
        return dfExt;
    }

    public void setDfExt(DocumentFourni dfExt) {
        this.dfExt = dfExt;
    }

    public DocumentFourni getDfJugSup() {
        return dfJugSup;
    }

    public void setDfJugSup(DocumentFourni dfJugSup) {
        this.dfJugSup = dfJugSup;
    }

    public DocumentFourni getDfCertificatNat() {
        return dfCertificatNat;
    }

    public void setDfCertificatNat(DocumentFourni dfCertificatNat) {
        this.dfCertificatNat = dfCertificatNat;
    }

    public DocumentFourni getDfPass() {
        return dfPass;
    }

    public void setDfPass(DocumentFourni dfPass) {
        this.dfPass = dfPass;
    }

    public DocumentFourni getDfVisa() {
        return dfVisa;
    }

    public void setDfVisa(DocumentFourni dfVisa) {
        this.dfVisa = dfVisa;
    }

    public DocumentFourni getDfCachetEntree() {
        return dfCachetEntree;
    }

    public void setDfCachetEntree(DocumentFourni dfCachetEntree) {
        this.dfCachetEntree = dfCachetEntree;
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

    public Varier getVarier() {
        return varier;
    }

    public void setVarier(Varier varier) {
        this.varier = varier;
    }

    public Rdv getRdv() {
        return rdv;
    }

    public void setRdv(Rdv rdv) {
        this.rdv = rdv;
    }

    public TypePhoto getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(TypePhoto typePhoto) {
        this.typePhoto = typePhoto;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Date getDateExpirationTitreIdentite() {
        return dateExpirationTitreIdentite;
    }

    public void setDateExpirationTitreIdentite(Date dateExpirationTitreIdentite) {
        this.dateExpirationTitreIdentite = dateExpirationTitreIdentite;
    }

    public Date getDateEtablissementTitreIdentite() {
        return dateEtablissementTitreIdentite;
    }

    public void setDateEtablissementTitreIdentite(Date dateEtablissementTitreIdentite) {
        this.dateEtablissementTitreIdentite = dateEtablissementTitreIdentite;
    }

    public boolean isTitreEstEdite() {
        return titreEstEdite;
    }

    public void setTitreEstEdite(boolean titreEstEdite) {
        this.titreEstEdite = titreEstEdite;
    }

    public String getCheminTitreGenere() {
        return cheminTitreGenere;
    }

    public void setCheminTitreGenere(String cheminTitreGenere) {
        this.cheminTitreGenere = cheminTitreGenere;
    }

    public String getUrlTitreGenere() {
        return urlTitreGenere;
    }

    public void setUrlTitreGenere(String urlTitreGenere) {
        this.urlTitreGenere = urlTitreGenere;
    }

    public String getInfosQrCode() {
        return infosQrCode;
    }

    public void setInfosQrCode(String infosQrCode) {
        this.infosQrCode = infosQrCode;
    }

///////////////////////////////////////////////////////////////////
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

        return mbG.redirigerUrl("/impression/rechercheDemande.xhtml");
    }

    public String rechercherDemande() throws IOException {
        List<Demande> demandes = sb.findByOneCriteria(new Demande(), "numDemande", "'" + numDemande + "'");
        if (!demandes.isEmpty()) {
            if (demandes.get(0).getRecu().getTypeDemande().getNumTypeDemande() == numTypeDemande) {
                demande = demandes.get(0);

                this.renseignerDocsFournisPourLaDemande();

                /////////// Charger Objet Rdv
                List<Rdv> rdvs = sb.findByOneCriteria(new Rdv(), "demande", "'" + demande.getNumDemande() + "'");
                if (!rdvs.isEmpty()) {
                    rdv = rdvs.get(0);
                }

                List<Evoluer> evoluers = sb.findLastInsertedAtADateAndOneCriteria(new Evoluer(), "dates", new Date(), "demande", "'" + demande.getNumDemande() + "'");
                if (!evoluers.isEmpty()) {
                    evoluer = evoluers.get(0);
                    if (evoluer.getStatus().getNumStatus() < 3 || evoluer.getStatus().getNumStatus() == 4) {
                        this.clearInputTextFields();
                        mbG.messageDemandeNonTrouvee();
                        return null;
                    } else if (evoluer.getStatus().getNumStatus() >= 5) {
                        this.clearInputTextFields();
                        this.determinerEvoluerFinTraitement(demande.getNumDemande(), 5);
                        return mbG.redirigerUrl("impressionDuTitre.xhtml");
                    }
                }

                List<Varier> variers = sb.findLastInsertedAtADateAndOneCriteria(new Varier(), "dates", new Date(), "typeDemande", String.valueOf(demande.getRecu().getTypeDemande().getNumTypeDemande()));
                if (!variers.isEmpty()) {
                    varier = variers.get(0);
                }

                if (mbG.verifierSiDemandeEnCoursUtilisationParUnAutreUtilisateur(demande.getNumDemande(), demande.getRecu().getTypeDemande().getNumTypeDemande()) == false) {

                    DemandeEnCoursDUtilisation demandeEnCoursDUtilisation_ = new DemandeEnCoursDUtilisation();
                    demandeEnCoursDUtilisation_.setNumDemandeEnCoursDUtilisation(demande.getNumDemande());
                    demandeEnCoursDUtilisation_.setUtilisateur(mbCon.getConnexion().getUtilisateur());
                    demandeEnCoursDUtilisation_.setConnexion(mbCon.getConnexion());
                    demandeEnCoursDUtilisation_.setTypeDemande(demande.getRecu().getTypeDemande());
                    demandeEnCoursDUtilisation_.setDateDebutUtilisation(new Date());
                    sb.create(demandeEnCoursDUtilisation_);

                    this.clearInputTextFields();
                    return mbG.redirigerUrl("menuImpression.xhtml");
                } else {
                    return mbG.redirigerUrl(mbG.cheminPageErreurDemandeEnCoursDUtilisation());
                }

            } else {
                numDemandeNontrouvee = numDemande;
                this.clearInputTextFields();
                mbG.messageAutreTypeDemandeTrouvee(typeDemande);
                return null;
            }

        } else {
            numDemandeNontrouvee = numDemande;
            this.clearInputTextFields();
            mbG.messageDemandeNonTrouvee();
            return null;
        }
    }

    public String rechercherDemande(String numDem) throws IOException {
        numDemande = numDem;
        return this.rechercherDemande();
    }

    ///////////////
    public List<Demande> listeDemandesDispoPourUnNiveau(int numStatus) {
        return sb.listerDemandesDispoPourUnNiveau(numStatus, numTypeDemande, mbCon.getUtilisateurConnecte().getCentreTravail().getNumCentre());
    }
    ///////////////

    ///////////////
    public void renseignerDocsFournisPourLaDemande() {
        int numExt = 1;
        //    int numPhotocopExt = 2;
        int numJugSup = 3;
        //    int numPhotocopJugSup = 4;
        int numPhotocopCert = 5;
        int numPhotocopPass = 12;
        int numPhotocopVisa = 13;
        int numPhotocopCachetEntree = 14;

        List<DocumentFourni> dfs = sb.findByOneCriteria(new DocumentFourni(), "demande", "'" + demande.getNumDemande() + "'");
        if (!dfs.isEmpty()) {
            for (DocumentFourni df : dfs) {
                if (df.getTypeDocument().getNumTypeDocument() == numExt) {
                    dfExt = df;
                } //                else if (df.getTypeDocument().getNumTypeDocument() == numPhotocopExt) {
                //                    dfPhotocopExt = df;
                //                }
                else if (df.getTypeDocument().getNumTypeDocument() == numJugSup) {
                    dfJugSup = df;
                } //                else if (df.getTypeDocument().getNumTypeDocument() == numPhotocopJugSup) {
                //                    dfPhotocopJugSup = df;
                //                }
                else if (df.getTypeDocument().getNumTypeDocument() == numPhotocopCert) {
                    dfCertificatNat = df;
                } else if (df.getTypeDocument().getNumTypeDocument() == numPhotocopPass) {
                    dfPass = df;
                } else if (df.getTypeDocument().getNumTypeDocument() == numPhotocopVisa) {
                    dfVisa = df;
                } else if (df.getTypeDocument().getNumTypeDocument() == numPhotocopCachetEntree) {
                    dfCachetEntree = df;
                }
            }
        }
    }

    public List<DocumentFourni> listeDocumentsFournis() {
        return sb.findByOneCriteria(new DocumentFourni(), "demande", "'" + demande.getNumDemande() + "'");
    }

    public String afficherInfosDoc(DocumentFourni df) {
        documentFourni = df;
        return mbG.redirigerUrl("infosDoc.xhtml");
    }

    public String menuCompleterInfos() {
        if (demande.getStatusActuel().getNumStatus() == 3) {
            return mbG.redirigerUrl("menuImpression.xhtml");
        } else if (demande.getStatusActuel().getNumStatus() >= 5) {
            return mbG.redirigerUrl("impressionDuTitre.xhtml");

        } else {
            return mbG.redirigerUrl("pageErreur.xhtml");
        }
    }

    public void affecterDateEtabEtdateExpirPourTitreGenere() {
        dateEtablissementTitreIdentite = new Date();

        int nbMoisDeValiditeTitre = varier.getDureeValidite();
        dateExpirationTitreIdentite = mbG.recupererDateActuellePlusNMoisNjours(nbMoisDeValiditeTitre, -1);
    }

    public String construireInfosQrCode() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Integer sexe = demande.getRecu().getPetitionnaire().getSexe();
        String dateNaisPrefix = null;

        String nom = demande.getRecu().getPetitionnaire().getNom();
        String prenoms = demande.getRecu().getPetitionnaire().getPrenoms();
        if (sexe == 'M') {
            dateNaisPrefix = "Né le ";
        } else if (sexe == 'F') {
            dateNaisPrefix = "Née le ";
        }

        String dateNais = sdf.format(demande.getRecu().getPetitionnaire().getDateNaissance());

        String lieuNais = demande.getRecu().getPetitionnaire().getLieuNaissance();
        String profession = demande.getRecu().getPetitionnaire().getProfession();
        String domicile = demande.getRecu().getPetitionnaire().getDomicile();

        String symboleTitre = demande.getRecu().getTypeDemande().getSymboleActuel();
        String numeroTitre = numeroFinalDuTitreProduit;
        String dateEtabTitre = sdf.format(dateEtablissementTitreIdentite);
        String lieuEtabTitre = demande.getCentreTraitement().getNomCentre();
        String dateExpirTitre = sdf.format(dateExpirationTitreIdentite);

        String dateEtabPrefix = null;
        if (symboleTitre.equals("AI")) {
            dateEtabPrefix = "Etablie le ";
        } else if (symboleTitre.equals("TPS")) {
            dateEtabPrefix = "Etabli le ";
        }

        String infosTitre = symboleTitre + " N°" + numeroTitre + "\n"
                + "dateEtab: " + dateEtabTitre + "\n"
                + "lieuEtab: " + lieuEtabTitre + "\n"
                + "dateExp: " + dateExpirTitre;

        String infosPetitionnaire = "nom: " + nom + "\n"
                + "pren: " + prenoms + "\n"
                + "dateNais: " + dateNais + "\n"
                + "lieuNais: " + lieuNais + "\n"
                + "dom: " + domicile + "\n"
                + "prof: " + profession;

        //////////////////
        /*
         String infosTitre = symboleTitre + " N°" + numeroTitre + "\n"
         + dateEtabPrefix + dateEtabTitre + " à " + lieuEtabTitre + "\n"
         + "Expire le " + dateExpirTitre;

         String infosPetitionnaire = nom + "\n" + prenoms + "\n"
         + dateNaisPrefix + dateNais + " à " + lieuNais + "\n"
         + "Dom: " + domicile + "\n"
         + "Prof: " + profession;
         */
        ////////////
        return infosTitre + "\n"
                + "------------------------" + "\n"
                + infosPetitionnaire;
    }

    public void editerTitreIdentiteEnPdf() throws JRException, IOException, SQLException {

        infosQrCode = this.construireInfosQrCode();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String cheminFichierJrxml = null;
        int numTypeDemande_ = demande.getRecu().getTypeDemande().getNumTypeDemande();
        int numCasDemande_ = demande.getRecu().getCasDemande().getNumCasDemande();

        if (numTypeDemande_ == 1) {
            int docNaissance = demande.getDocJustificatifNais();
            if (numCasDemande_ == 1) {
                if (docNaissance == 1) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiNouvelleDemandeExt.jrxml";
                } else if (docNaissance == 2) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiNouvelleDemandeJugSup.jrxml";
                }
            } else if (numCasDemande_ == 2) {
                if (docNaissance == 1) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiRenouvellementExt.jrxml";
                } else if (docNaissance == 2) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiRenouvellementJugSup.jrxml";
                }
            }
        } else if (numTypeDemande_ == 2) {
            cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapTps.jrxml";
        }

        String cheminFichierImageAi = mbG.getDossierWeb() + "/resources/default/images/imageAi.jpg";
        String cheminFichierImageTps = mbG.getDossierWeb() + "/resources/default/images/imageTps.jpg";

        HashMap hashmap = new HashMap();

        hashmap.put("cheminFichierImageAi", cheminFichierImageAi);
        hashmap.put("cheminFichierImageTps", cheminFichierImageTps);

        hashmap.put("numDemande", demande.getNumDemande());
        hashmap.put("infosQrCode", infosQrCode);
        hashmap.put("numeroFinalDuTitreProduit", numeroFinalDuTitreProduit);
        //      hashmap.put("dateEtablissementTitreIdentite", sdf.format(dateEtablissementTitreIdentite));
        //      hashmap.put("lieuEtablissementTitreIdentite", demande.getCentreTraitement().getCommune().getNomCommune());
        hashmap.put("lieuEtDateEtablissementTitreIdentite", demande.getCentreTraitement().getCommune().getNomCommune() + ", le " + sdf.format(dateEtablissementTitreIdentite));
        hashmap.put("dateExpirationTitreIdentite", sdf.format(dateExpirationTitreIdentite));

        if (numTypeDemande_ == 1) {
            if (demande.getDocJustificatifNais() == 1) {
                hashmap.put("numeroExt", dfExt.getNumeroDocumentFourni());
                hashmap.put("dateEtablissementExt", sdf.format(dfExt.getDateEtablissement()));
                hashmap.put("dateDelivranceExt", sdf.format(dfExt.getDateDelivrance()));
                hashmap.put("lieuDelivranceExt", dfExt.getLieuDelivrance());                
            } else if (demande.getDocJustificatifNais() == 2) {
                hashmap.put("numeroJugSup", dfJugSup.getNumeroDocumentFourni());
                hashmap.put("dateEtablissementJugSup", sdf.format(dfJugSup.getDateEtablissement()));
                hashmap.put("dateDelivranceJugSup", sdf.format(dfJugSup.getDateDelivrance()));
                hashmap.put("lieuDelivranceJugSup", dfJugSup.getLieuDelivrance());
            }

            if (numCasDemande_ == 1) {
                hashmap.put("numeroCertNat", dfCertificatNat.getNumeroDocumentFourni());
                hashmap.put("dateDelivranceCertNat", sdf.format(dfCertificatNat.getDateDelivrance()));
                hashmap.put("lieuDelivranceCertNat", dfCertificatNat.getLieuDelivrance());
            }
        } else if (numTypeDemande_ == 2) {
            hashmap.put("numeroPass", dfPass.getNumeroDocumentFourni());
            hashmap.put("dateDelivrancePass", sdf.format(dfPass.getDateDelivrance()));
            hashmap.put("lieuDelivrancePass", dfPass.getLieuDelivrance());
            hashmap.put("dateEntreePaysCachetEntree", sdf.format(dfCachetEntree.getDateEntreePays()));
        }

        Connection con = mbG.connectionBddAiTps();

        cheminTitreGenere = mbG.dossierTitresGeneres() + "/" + "titre_demande_" + demande.getNumDemande() + ".pdf";

        urlTitreGenere = mbG.urlContextApp() + mbG.dossierTitresGeneres().substring(2) + "/" + "titre_demande_" + demande.getNumDemande() + ".pdf";

        mbGf.deleteIfExist(cheminTitreGenere);

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdf(cheminFichierJrxml, hashmap, con, cheminTitreGenere);
    }

    public void editerTitreIdentiteEnPdfStream() throws JRException, IOException, SQLException {

        infosQrCode = this.construireInfosQrCode();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String cheminFichierJrxml = null;
        int numTypeDemande_ = demande.getRecu().getTypeDemande().getNumTypeDemande();
        int numCasDemande_ = demande.getRecu().getCasDemande().getNumCasDemande();

        if (numTypeDemande_ == 1) {
            int docNaissance = demande.getDocJustificatifNais();
            if (numCasDemande_ == 1) {
                if (docNaissance == 1) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiNouvelleDemandeExt.jrxml";
                } else if (docNaissance == 2) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiNouvelleDemandeJugSup.jrxml";
                }
            } else if (numCasDemande_ == 2) {
                if (docNaissance == 1) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiRenouvellementExt.jrxml";
                } else if (docNaissance == 2) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapAiRenouvellementJugSup.jrxml";
                }
            }
        } else if (numTypeDemande_ == 2) {
            cheminFichierJrxml = mbG.getDossierWeb() + "/impression/rapTps.jrxml";
        }

        String cheminFichierImageAi = mbG.getDossierWeb() + "/resources/default/images/imageAi.jpg";
        String cheminFichierImageTps = mbG.getDossierWeb() + "/resources/default/images/imageTps.jpg";

        HashMap hashmap = new HashMap();

        hashmap.put("cheminFichierImageAi", cheminFichierImageAi);
        hashmap.put("cheminFichierImageTps", cheminFichierImageTps);

        hashmap.put("numDemande", demande.getNumDemande());
        hashmap.put("infosQrCode", infosQrCode);
        hashmap.put("numeroFinalDuTitreProduit", numeroFinalDuTitreProduit);
        //      hashmap.put("dateEtablissementTitreIdentite", sdf.format(dateEtablissementTitreIdentite));
        //      hashmap.put("lieuEtablissementTitreIdentite", demande.getCentreTraitement().getCommune().getNomCommune());
        hashmap.put("lieuEtDateEtablissementTitreIdentite", demande.getCentreTraitement().getCommune().getNomCommune() + ", le " + sdf.format(dateEtablissementTitreIdentite));
        hashmap.put("dateExpirationTitreIdentite", sdf.format(dateExpirationTitreIdentite));

        if (numTypeDemande_ == 1) {
            if (demande.getDocJustificatifNais() == 1) {
                hashmap.put("numeroExt", dfExt.getNumeroDocumentFourni());
                hashmap.put("dateEtablissementExt", sdf.format(dfExt.getDateEtablissement()));
                hashmap.put("dateDelivranceExt", sdf.format(dfExt.getDateDelivrance()));
                hashmap.put("lieuDelivranceExt", dfExt.getLieuDelivrance());
            } else if (demande.getDocJustificatifNais() == 2) {
                hashmap.put("numeroJugSup", dfJugSup.getNumeroDocumentFourni());
                hashmap.put("dateEtablissementJugSup", sdf.format(dfJugSup.getDateEtablissement()));
                hashmap.put("dateDelivranceJugSup", sdf.format(dfJugSup.getDateDelivrance()));
                hashmap.put("lieuDelivranceJugSup", dfJugSup.getLieuDelivrance());
            }

            if (numCasDemande_ == 1) {
                hashmap.put("numeroCertNat", dfCertificatNat.getNumeroDocumentFourni());
                hashmap.put("dateDelivranceCertNat", sdf.format(dfCertificatNat.getDateDelivrance()));
                hashmap.put("lieuDelivranceCertNat", dfCertificatNat.getLieuDelivrance());
            }
        } else if (numTypeDemande_ == 2) {
            hashmap.put("numeroPass", dfPass.getNumeroDocumentFourni());
            hashmap.put("dateDelivrancePass", sdf.format(dfPass.getDateDelivrance()));
            hashmap.put("lieuDelivrancePass", dfPass.getLieuDelivrance());
            hashmap.put("dateEntreePaysCachetEntree", sdf.format(dfCachetEntree.getDateEntreePays()));
        }

        Connection con = mbG.connectionBddAiTps();

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdfStream(cheminFichierJrxml, hashmap, con);
    }

    public void editerTitre() throws JRException, IOException, SQLException {

        if (this.validateInputEditionTitre()) {
            this.creerTitre();
            //    this.editerTitreIdentiteEnPdf();
            this.titreEstEdite = true;
        } else {
            mbG.ajouterMessage("erreur", "Veuillez renseigner tous les champs nécessaires");
        }
    }

    public void creerTitre() {

        this.affecterDateEtabEtdateExpirPourTitreGenere();

        TitreIdentite titre_;

        List<TitreIdentite> titreIdentites = sb.findByOneCriteria(new TitreIdentite(), "demande", "'" + demande.getNumDemande() + "'");
        if (!titreIdentites.isEmpty()) {

            titre_ = titreIdentites.get(0);

            titre_.setDateEtablissementTitreIdentite(dateEtablissementTitreIdentite);
            titre_.setDateExpirationTitreIdentite(dateExpirationTitreIdentite);

            titre_.setNumeroTitreIdentite(numeroFinalDuTitreProduit);
            titre_.setAEteGenere(false);

            sb.update(titre_);
        } else {
            titre_ = new TitreIdentite();

            titre_.setDateEtablissementTitreIdentite(dateEtablissementTitreIdentite);
            titre_.setDateExpirationTitreIdentite(dateExpirationTitreIdentite);
            titre_.setDemande(demande);

            titre_.setNumeroTitreIdentite(numeroFinalDuTitreProduit);
            titre_.setAEteGenere(false);
            titre_.setMailFinProductionAEteEnvoyeAvecSucces(false);

            sb.create(titre_);
        }
        titreIdentite = titre_;
    }

    public void genererTitre() {

        TitreIdentite titre = null;

        List<TitreIdentite> titreIdentites = sb.findByOneCriteria(new TitreIdentite(), "demande", "'" + demande.getNumDemande() + "'");
        if (!titreIdentites.isEmpty()) {
            titre = titreIdentites.get(0);
        }

        if (titreEstEdite == true && titre.getAEteGenere() == false) {
            mbG.changerStatusDemande(demande, 5);
            this.determinerEvoluerFinTraitement(demande.getNumDemande(), 5);

            try {
                mbTrEmails.envoyerMailFinProductionTitrePourUnTitre(titre);

          //      titre.setMailFinProductionAEteEnvoyeAvecSucces(true);
          //      sb.update(titre);
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void annulerTitreEdite() {
        this.clearInputTextFields();
    }

    public String resultatEnvoiMail() {

        TitreIdentite titre;

        List<TitreIdentite> titreIdentites = sb.findByOneCriteria(new TitreIdentite(), "demande", "'" + demande.getNumDemande() + "'");
        if (!titreIdentites.isEmpty()) {
            titre = titreIdentites.get(0);

            if (titre.getMailFinProductionAEteEnvoyeAvecSucces() == true) {
                return "E-mail de notification envoyé avec succès";
            } else {
                return "Echec d'envoi de l'e-mail de notification";
            }
        } else {
            return "ERREUR";
        }
    }

    ///////////////////////////////////
    public void determinerEvoluerFinTraitement(String numDemande, int numStatus) {
        evoluerFinTraitement = mbG.recupererEvoluerFinTraitement(numDemande, numStatus);
    }

    ///////////////
    public String afficherDocFournis() {
        return mbG.redirigerUrl("controleDocumentsFournis_frozen.xhtml");
    }

    //////////
    public void clearInputTextFields() {

        numDemande = null;

        titreEstEdite = false;
        
        numeroFinalDuTitreProduit = null;

    }
//////////////////////

    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        demande = null;
        return mbG.retournerAuMenuGeneral();
    }

    public void afficherDemandesDispoPourImpression() throws IOException {
        if (this.listeDemandesDispoPourUnNiveau(3).size() > 0) {
            mbG.redirectionVersUrl("demandeDispoPourImpression.xhtml");
        } else {
            mbG.ajouterMessage("info", "Aucune demande n'a été trouvée");
        }
    }

    ////////////// Input validation
    public boolean validateInputEditionTitre() {
        if (numeroFinalDuTitreProduit == null) {
            return false;
        } else {
            return true;
        }
    }
}
