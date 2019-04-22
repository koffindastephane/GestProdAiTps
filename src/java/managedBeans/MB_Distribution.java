package managedBeans;

import entities.Demande;
import entities.DemandeEnCoursDUtilisation;
import entities.DocumentFourni;
import entities.Evoluer;
import entities.Rdv;
import entities.Status;
import entities.TitreIdentite;
import entities.TypeDemande;
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
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import sessionBeans.SB;
import traitementsJasper.GenerationRapport;

@SessionScoped
@Named("mB_Distribution")
public class MB_Distribution implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Connexion mbCon;

    public MB_Distribution() {
    }
    
    /////////////////////////////
    
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    
    /////////////////////////////

    //TitreIdentite
    private TitreIdentite titreIdentite;

    ////// TypeDemande
    private int numTypeDemande;
    private TypeDemande typeDemande;

    ////// Status
    private int numStatus;
    private Status status;

    //Demande
    private Demande demande;
    private String numDemande;

    private String numDemandeNontrouvee;

    //Rdv
    private Rdv rdv;

    //
    private Evoluer evoluer;

    private Evoluer evoluerFinTraitement;
    //

    //Pour afficher Premiers documents fournis
    private DocumentFourni dfExt;
    //   private DocumentFourni dfPhotocopExt;
    private DocumentFourni dfJugSup;
    //   private DocumentFourni dfPhotocopJugSup;
    private DocumentFourni dfCertificatNat;
    private DocumentFourni dfPass;
    private DocumentFourni dfVisa;
    private DocumentFourni dfCachetEntree;
    
    private String infosQrCode;

    ////getters and setters
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

    public Rdv getRdv() {
        return rdv;
    }

    public void setRdv(Rdv rdv) {
        this.rdv = rdv;
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

        return mbG.redirigerUrl("/distribution/rechercheDemande.xhtml");
    }

    public String rechercherDemande() throws IOException {
        List<Demande> demandes = sb.findByOneCriteria(new Demande(), "numDemande", "'" + numDemande + "'");
        if (!demandes.isEmpty()) {
            if (demandes.get(0).getRecu().getTypeDemande().getNumTypeDemande() == numTypeDemande) {
            demande = demandes.get(0);

            this.renseignerDocsFournisPourLaDemande();

            /////////// Charger Objets
            List<Rdv> rdvs = sb.findByOneCriteria(new Rdv(), "demande", "'" + demande.getNumDemande() + "'");
            if (!rdvs.isEmpty()) {
                rdv = rdvs.get(0);
            }

            List<TitreIdentite> titreIdentites = sb.findByOneCriteria(new TitreIdentite(), "demande", "'" + demande.getNumDemande() + "'");
            if (!titreIdentites.isEmpty()) {
                titreIdentite = titreIdentites.get(0);
            }

            List<Evoluer> evoluers = sb.findLastInsertedAtADateAndOneCriteria(new Evoluer(), "dates", new Date(), "demande", "'" + demande.getNumDemande() + "'");
            if (!evoluers.isEmpty()) {
                evoluer = evoluers.get(0);
                if (evoluer.getStatus().getNumStatus() < 5) {
                    this.clearInputTextFields();
                    mbG.messageDemandeNonTrouvee();
                    return null;
                } else if (evoluer.getStatus().getNumStatus() > 5) {
                    this.clearInputTextFields();
                    this.determinerEvoluerFinTraitement(demande.getNumDemande(), 6);
                    return mbG.redirigerUrl("titreProduit.xhtml");
                }
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
                return mbG.redirigerUrl("menuDistribution.xhtml");
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

    public void confirmerRetraitTitre() {

        List<TitreIdentite> titres = sb.findByOneCriteria(new TitreIdentite(), "demande", "'" + demande.getNumDemande() + "'");
        if (!titres.isEmpty()) {
            titreIdentite = titres.get(0);
            titreIdentite.setDateRetraitTitreIdentite(new Date());

            sb.update(titreIdentite);

            mbG.changerStatusDemande(demande, 6);

            this.determinerEvoluerFinTraitement(demande.getNumDemande(), 6);

        }

    }

    public String determinerCheminTitre() {
        if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 1) {
            return mbG.cheminAi();
        } else {
            return mbG.cheminTps();
        }
    }

    ///////////////////////////////////
    public void determinerEvoluerFinTraitement(String numDemande, int numStatus) {
        evoluerFinTraitement = mbG.recupererEvoluerFinTraitement(numDemande, numStatus);
    }

    //////////////////////////
    public void clearInputTextFields() {
        
        numDemande = null;

    }
    //////////////////////:

    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        demande = null;
        return mbG.retournerAuMenuGeneral();
    }

/////////////////////////////////////////////////////////////
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
        String numeroTitre = titreIdentite.getNumeroTitreIdentite();
        String dateEtabTitre = sdf.format(titreIdentite.getDateEtablissementTitreIdentite());
        String lieuEtabTitre = titreIdentite.getDemande().getCentreTraitement().getNomCentre();
        String dateExpirTitre = sdf.format(titreIdentite.getDateExpirationTitreIdentite());

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
                    cheminFichierJrxml = mbG.getDossierWeb() + "/distribution/rapAiNouvelleDemandeExt.jrxml";
                } else if (docNaissance == 2) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/distribution/rapAiNouvelleDemandeJugSup.jrxml";
                }
            } else if (numCasDemande_ == 2) {
                if (docNaissance == 1) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/distribution/rapAiRenouvellementExt.jrxml";
                } else if (docNaissance == 2) {
                    cheminFichierJrxml = mbG.getDossierWeb() + "/distribution/rapAiRenouvellementJugSup.jrxml";
                }
            }
        } else if (numTypeDemande_ == 2) {
            cheminFichierJrxml = mbG.getDossierWeb() + "/distribution/rapTps.jrxml";
        }

        String cheminFichierImageAi = mbG.getDossierWeb() + "/resources/default/images/imageAi.jpg";
        String cheminFichierImageTps = mbG.getDossierWeb() + "/resources/default/images/imageTps.jpg";

        HashMap hashmap = new HashMap();

        hashmap.put("cheminFichierImageAi", cheminFichierImageAi);
        hashmap.put("cheminFichierImageTps", cheminFichierImageTps);

        hashmap.put("numDemande", demande.getNumDemande());
        hashmap.put("infosQrCode", infosQrCode);
        hashmap.put("numeroFinalDuTitreProduit",  titreIdentite.getNumeroTitreIdentite());
        //      hashmap.put("dateEtablissementTitreIdentite", sdf.format(dateEtablissementTitreIdentite));
        //      hashmap.put("lieuEtablissementTitreIdentite", demande.getCentreTraitement().getCommune().getNomCommune());
        hashmap.put("lieuEtDateEtablissementTitreIdentite", demande.getCentreTraitement().getCommune().getNomCommune() + ", le " + sdf.format(titreIdentite.getDateEtablissementTitreIdentite()));
        hashmap.put("dateExpirationTitreIdentite", sdf.format(titreIdentite.getDateExpirationTitreIdentite()));

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
    
    public void afficherDemandesDispoPourDistribution() throws IOException {
        if(this.listeDemandesDispoPourUnNiveau(5).size() > 0) {
             mbG.redirectionVersUrl("demandeDispoPourDistribution.xhtml");
        } else {
            mbG.ajouterMessage("info", "Aucune demande n'a été trouvée");
        }
    }

}
