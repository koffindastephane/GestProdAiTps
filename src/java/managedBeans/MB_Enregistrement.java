package managedBeans;

import entities.Centre;
import entities.Correspondre;
import entities.Demande;
import entities.DemandeEnCoursDUtilisation;
import entities.DocumentFourni;
import entities.Etat;
import entities.Evoluer;
import entities.Photo;
import entities.Rdv;
import entities.Status;
import entities.TypeDemande;
import entities.TypeDocument;
import entities.TypePhoto;
import entities.Varier;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import sessionBeans.SB;
import traitementsJasper.GenerationRapport;

@SessionScoped
@Named("mB_Enregistrement")
public class MB_Enregistrement implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Connexion mbCon;

    @Inject
    MB_PhotoCam mbPhotoCam;

    @Inject
    MB_GestionUpload mbGu;

    @Inject
    MB_GestionFichiers mbGf;

    public MB_Enregistrement() {
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

    ////// Demande
    private String numDemande;
    private String numDemandeNontrouvee;

    private String lieuNaissance;
    private String sexeString;
    private Integer taille;
    private String telephone1;
    private String telephone2;
    private String telephone3;
    private String email;

    private String profession;
    private String domicile;
    private String adressePhysique;
    private String adressePostale;
    private String nationalite;

    private String nomPere;
    private String prenomsPere;
    private Date dateNaissancePere;
    private String lieuNaissancePere;
    private String nationalitePere;

    private String nomMere;
    private String prenomsMere;
    private Date dateNaissanceMere;
    private String lieuNaissanceMere;
    private String nationaliteMere;

    //DocumentFourni
    private DocumentFourni documentFourni;
    private Etat etat;

    private DocumentFourni dfExt;
    //   private DocumentFourni dfPhotocopExt;
    private DocumentFourni dfJugSup;
    //   private DocumentFourni dfPhotocopJugSup;
    private DocumentFourni dfCertificatNat;
    private DocumentFourni dfPass;
    private DocumentFourni dfVisa;
    private DocumentFourni dfCachetEntree;

    ////// Centre de traitement
    private Centre centreTraitement;
    private String numCentreTraitement;

    private Date dateFinEnregistrementDemande;

    private Date dateEntreePays;

    private String numeroExtraitNaissance;
    private String numeroJugementSuppletif;

    //// DocumentFourni
    private DocumentFourni dfDocNais;

    private String numeroDocNais;
    private Date dateEtablissementDocNais;
    private Date dateDelivranceDocNais;
    private String lieuDelivranceDocNais;

    private String numeroPass;
    private Date dateDelivrancePass;
    private String lieuDelivrancePass;

    private String numeroVisa;
    private Date dateDelivranceVisa;
    private String lieuDelivranceVisa;

    private String numeroCertificatNat;
    private Date dateDelivranceCertificatNat;
    private String lieuDelivranceCertificatNat;

    // Found from numDemande
    private Demande demande;   // from demande we can get petitionnaire, typeDemande (cause stored in it)

    // Varier
    private Varier varier;

    // Correspondre
    private Correspondre correspondre;

    // Rdv
    private Rdv rdv;

    // Evoluer
    private Evoluer evoluer;

    private Evoluer evoluerFinTraitement;

    // TypePhoto
    private TypePhoto typePhoto;
    // Photo
    private Photo photo;

    private boolean photoEstPrise = false;

    private boolean photoEstUploadee = false;

    private boolean photoEstPresente = false;

    private Part photoPart;

    /////////////
    private String nomDestinationPhotoFinal;
    String cheminDestinationPhotoFinal;

    private String cheminRepertoireCree;
    private String cheminSourcePhotoFinal;

    boolean resultatDeplacementFichier;

    ////////////////////////Getters and Setters
    public String getCheminRepertoireCree() {
        return cheminRepertoireCree;
    }

    public void setCheminRepertoireCree(String cheminRepertoireCree) {
        this.cheminRepertoireCree = cheminRepertoireCree;
    }

    public String getCheminSourcePhotoFinal() {
        return cheminSourcePhotoFinal;
    }

    public void setCheminSourcePhotoFinal(String cheminSourcePhotoFinal) {
        this.cheminSourcePhotoFinal = cheminSourcePhotoFinal;
    }

    ///Getters and Setters
    public int getNumTypeDemande() {
        return numTypeDemande;
    }

    public void setNumTypeDemande(int numTypeDemande) {
        this.numTypeDemande = numTypeDemande;
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

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getAdressePhysique() {
        return adressePhysique;
    }

    public void setAdressePhysique(String adressePhysique) {
        this.adressePhysique = adressePhysique;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getNomPere() {
        return nomPere;
    }

    public void setNomPere(String nomPere) {
        this.nomPere = nomPere;
    }

    public String getPrenomsPere() {
        return prenomsPere;
    }

    public void setPrenomsPere(String prenomsPere) {
        this.prenomsPere = prenomsPere;
    }

    public Date getDateNaissancePere() {
        return dateNaissancePere;
    }

    public void setDateNaissancePere(Date dateNaissancePere) {
        this.dateNaissancePere = dateNaissancePere;
    }

    public String getLieuNaissancePere() {
        return lieuNaissancePere;
    }

    public void setLieuNaissancePere(String lieuNaissancePere) {
        this.lieuNaissancePere = lieuNaissancePere;
    }

    public String getNationalitePere() {
        return nationalitePere;
    }

    public void setNationalitePere(String nationalitePere) {
        this.nationalitePere = nationalitePere;
    }

    public String getNomMere() {
        return nomMere;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public String getPrenomsMere() {
        return prenomsMere;
    }

    public void setPrenomsMere(String prenomsMere) {
        this.prenomsMere = prenomsMere;
    }

    public Date getDateNaissanceMere() {
        return dateNaissanceMere;
    }

    public void setDateNaissanceMere(Date dateNaissanceMere) {
        this.dateNaissanceMere = dateNaissanceMere;
    }

    public String getLieuNaissanceMere() {
        return lieuNaissanceMere;
    }

    public void setLieuNaissanceMere(String lieuNaissanceMere) {
        this.lieuNaissanceMere = lieuNaissanceMere;
    }

    public String getNationaliteMere() {
        return nationaliteMere;
    }

    public void setNationaliteMere(String nationaliteMere) {
        this.nationaliteMere = nationaliteMere;
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

    public Date getDateFinEnregistrementDemande() {
        return dateFinEnregistrementDemande;
    }

    public void setDateFinEnregistrementDemande(Date dateFinEnregistrementDemande) {
        this.dateFinEnregistrementDemande = dateFinEnregistrementDemande;
    }

    public Date getDateEntreePays() {
        return dateEntreePays;
    }

    public void setDateEntreePays(Date dateEntreePays) {
        this.dateEntreePays = dateEntreePays;
    }

    public String getNumeroExtraitNaissance() {
        return numeroExtraitNaissance;
    }

    public void setNumeroExtraitNaissance(String numeroExtraitNaissance) {
        this.numeroExtraitNaissance = numeroExtraitNaissance;
    }

    public String getNumeroJugementSuppletif() {
        return numeroJugementSuppletif;
    }

    public void setNumeroJugementSuppletif(String numeroJugementSuppletif) {
        this.numeroJugementSuppletif = numeroJugementSuppletif;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSexeString() {
        return sexeString;
    }

    public void setSexeString(String sexeString) {
        this.sexeString = sexeString;
    }

    public DocumentFourni getDfDocNais() {
        return dfDocNais;
    }

    public void setDfDocNais(DocumentFourni dfDocNais) {
        this.dfDocNais = dfDocNais;
    }

    public String getNumeroDocNais() {
        return numeroDocNais;
    }

    public void setNumeroDocNais(String numeroDocNais) {
        this.numeroDocNais = numeroDocNais;
    }

    public Date getDateEtablissementDocNais() {
        return dateEtablissementDocNais;
    }

    public void setDateEtablissementDocNais(Date dateEtablissementDocNais) {
        this.dateEtablissementDocNais = dateEtablissementDocNais;
    }

    public Date getDateDelivranceDocNais() {
        return dateDelivranceDocNais;
    }

    public void setDateDelivranceDocNais(Date dateDelivranceDocNais) {
        this.dateDelivranceDocNais = dateDelivranceDocNais;
    }

    public String getLieuDelivranceDocNais() {
        return lieuDelivranceDocNais;
    }

    public void setLieuDelivranceDocNais(String lieuDelivranceDocNais) {
        this.lieuDelivranceDocNais = lieuDelivranceDocNais;
    }

    public String getNumeroPass() {
        return numeroPass;
    }

    public void setNumeroPass(String numeroPass) {
        this.numeroPass = numeroPass;
    }

    public Date getDateDelivrancePass() {
        return dateDelivrancePass;
    }

    public void setDateDelivrancePass(Date dateDelivrancePass) {
        this.dateDelivrancePass = dateDelivrancePass;
    }

    public String getLieuDelivrancePass() {
        return lieuDelivrancePass;
    }

    public void setLieuDelivrancePass(String lieuDelivrancePass) {
        this.lieuDelivrancePass = lieuDelivrancePass;
    }

    public String getNumeroVisa() {
        return numeroVisa;
    }

    public void setNumeroVisa(String numeroVisa) {
        this.numeroVisa = numeroVisa;
    }

    public Date getDateDelivranceVisa() {
        return dateDelivranceVisa;
    }

    public void setDateDelivranceVisa(Date dateDelivranceVisa) {
        this.dateDelivranceVisa = dateDelivranceVisa;
    }

    public String getLieuDelivranceVisa() {
        return lieuDelivranceVisa;
    }

    public void setLieuDelivranceVisa(String lieuDelivranceVisa) {
        this.lieuDelivranceVisa = lieuDelivranceVisa;
    }

    public String getNumeroCertificatNat() {
        return numeroCertificatNat;
    }

    public void setNumeroCertificatNat(String numeroCertificatNat) {
        this.numeroCertificatNat = numeroCertificatNat;
    }

    public Date getDateDelivranceCertificatNat() {
        return dateDelivranceCertificatNat;
    }

    public void setDateDelivranceCertificatNat(Date dateDelivranceCertificatNat) {
        this.dateDelivranceCertificatNat = dateDelivranceCertificatNat;
    }

    public String getLieuDelivranceCertificatNat() {
        return lieuDelivranceCertificatNat;
    }

    public void setLieuDelivranceCertificatNat(String lieuDelivranceCertificatNat) {
        this.lieuDelivranceCertificatNat = lieuDelivranceCertificatNat;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Varier getVarier() {
        return varier;
    }

    public void setVarier(Varier varier) {
        this.varier = varier;
    }

    public Correspondre getCorrespondre() {
        return correspondre;
    }

    public void setCorrespondre(Correspondre correspondre) {
        this.correspondre = correspondre;
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

    public boolean isPhotoEstPrise() {
        return photoEstPrise;
    }

    public void setPhotoEstPrise(boolean photoEstPrise) {
        this.photoEstPrise = photoEstPrise;
    }

    public boolean isPhotoEstUploadee() {
        return photoEstUploadee;
    }

    public void setPhotoEstUploadee(boolean photoEstUploadee) {
        this.photoEstUploadee = photoEstUploadee;
    }

    public boolean isPhotoEstPresente() {
        return photoEstPresente;
    }

    public void setPhotoEstPresente(boolean photoEstPresente) {
        this.photoEstPresente = photoEstPresente;
    }

    public String getNomDestinationPhotoFinal() {
        return nomDestinationPhotoFinal;
    }

    public void setNomDestinationPhotoFinal(String nomDestinationPhotoFinal) {
        this.nomDestinationPhotoFinal = nomDestinationPhotoFinal;
    }

    public Part getPhotoPart() {
        return photoPart;
    }

    public void setPhotoPart(Part photoPart) throws IOException {
        this.photoPart = photoPart;
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

    ////////////////////////////////////////////////
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

        mbG.supprimerArborescencePhotoDeLUtilisateurConnecte();

        return mbG.redirigerUrl("/enregistrement/rechercheDemande.xhtml");
    }

    ///////////////
    public void gererCasse() {

        lieuNaissance = mbG.ecrireEnMaj(lieuNaissance);

        if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 1) {
            lieuDelivranceDocNais = mbG.ecrireEnMaj(lieuDelivranceDocNais);
            if (demande.getRecu().getCasDemande().getNumCasDemande() == 1) {
                lieuDelivranceCertificatNat = mbG.ecrireEnMaj(lieuDelivranceCertificatNat);
            }
        } else if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 2) {
            lieuDelivrancePass = mbG.ecrireEnMaj(lieuDelivrancePass);
            lieuDelivranceVisa = mbG.ecrireEnMaj(lieuDelivranceVisa);

        }

        domicile = mbG.ecrireEnMaj(domicile);
        profession = mbG.ecrireEnMaj(profession);
        adressePhysique = mbG.ecrireEnMaj(adressePhysique);

        if (adressePostale != null) {
            adressePostale = mbG.ecrireEnMaj(adressePostale);
        }

        if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 2) {
            nationalite = mbG.ecrireEnMaj(nationalite);
        }

        nomPere = mbG.ecrireEnMaj(nomPere);
        prenomsPere = mbG.ecrireEnMaj(prenomsPere);
        lieuNaissancePere = mbG.ecrireEnMaj(lieuNaissancePere);

        nomMere = mbG.ecrireEnMaj(nomMere);
        prenomsMere = mbG.ecrireEnMaj(prenomsMere);
        lieuNaissanceMere = mbG.ecrireEnMaj(lieuNaissanceMere);

    }

    public String rechercherDemande() throws IOException {
        List<Demande> demandes = sb.findByOneCriteria(new Demande(), "numDemande", "'" + numDemande + "'");
        if (!demandes.isEmpty()) {
            if (demandes.get(0).getRecu().getTypeDemande().getNumTypeDemande() == numTypeDemande) {
                demande = demandes.get(0);

                /////////////////
                cheminRepertoireCree = mbG.getDossierPhotosTemp() + "/" + mbCon.getUtilisateurConnecte().getLogin() + "/" + demande.getNumDemande();

                cheminSourcePhotoFinal = cheminRepertoireCree + "/" + mbG.getNomSourcePhotoFinal() + "." + mbG.getTypeFichier();

                ////////
                List<Rdv> rdvs = sb.findByOneCriteria(new Rdv(), "demande", "'" + demande.getNumDemande() + "'");
                if (!rdvs.isEmpty()) {
                    rdv = rdvs.get(0);
                }

                List<Evoluer> evoluers = sb.findLastInsertedAtADateAndOneCriteria(new Evoluer(), "dates", new Date(), "demande", "'" + demande.getNumDemande() + "'");
                if (!evoluers.isEmpty()) {
                    evoluer = evoluers.get(0);
                    if (evoluer.getStatus().getNumStatus() < 1) {
                        this.clearInputTextFields();
                        mbG.messageDemandeNonTrouvee();
                        return null;
                    } else if (evoluer.getStatus().getNumStatus() > 1) {
                        this.clearInputTextFields();
                        this.renseignerDocsFournisPourLaDemande();
                        this.determinerEvoluerFinTraitement(demande.getNumDemande(), 2);
                        mbG.redirectionVersUrl("fixationRdv.xhtml");
                        return null;
                    }
                }
//////
                
                if (mbG.verifierSiDemandeEnCoursUtilisationParUnAutreUtilisateur(demande.getNumDemande(), demande.getRecu().getTypeDemande().getNumTypeDemande()) == false) {

                    DemandeEnCoursDUtilisation demandeEnCoursDUtilisation_ = new DemandeEnCoursDUtilisation();
                    demandeEnCoursDUtilisation_.setNumDemandeEnCoursDUtilisation(demande.getNumDemande());
                    demandeEnCoursDUtilisation_.setUtilisateur(mbCon.getConnexion().getUtilisateur());
                    demandeEnCoursDUtilisation_.setConnexion(mbCon.getConnexion());
                    demandeEnCoursDUtilisation_.setTypeDemande(demande.getRecu().getTypeDemande());
                    demandeEnCoursDUtilisation_.setDateDebutUtilisation(new Date());
                    sb.create(demandeEnCoursDUtilisation_);

                    this.clearInputTextFields();
                    return mbG.redirigerUrl("menuEnregistrement.xhtml");
                    
                } else {
                    return mbG.redirigerUrl(mbG.cheminPageErreurDemandeEnCoursDUtilisation());
                }
////
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
    public List<Demande> listeDemandesDispoPourUnNiveau(int numStatus_) {
        return sb.listerDemandesDispoPourUnNiveau(numStatus_, numTypeDemande, mbCon.getUtilisateurConnecte().getCentreTravail().getNumCentre());
    }
    ///////////////

    public void creerDocumentFourni() {

        List<DocumentFourni> dfs = sb.findByOneCriteria(new DocumentFourni(), "demande", "'" + demande.getNumDemande() + "'");
        if (!dfs.isEmpty()) {
            sb.deleteWithOneCriteria(new DocumentFourni(), "demande", "'" + demande.getNumDemande() + "'");
        }

        int numTypeDocument = 0;

        DocumentFourni recuPaiement_;

        DocumentFourni dfDocNais_;
        DocumentFourni dfCertificatNat_;
        DocumentFourni dfPass_;
        DocumentFourni dfVisa_;
        DocumentFourni dfCachetEntree_;

        Etat etat = null;
        List<Etat> etats = sb.findByOneCriteria(new Etat(), "numEtat", "1");
        if (!etats.isEmpty()) {
            etat = etats.get(0);
        }

        int numExt = 1;
        //    int numPhotocopExt = 2;
        int numJugSup = 3;
        //    int numPhotocopJugSup = 4;
        int numPhotocopCert = 5;
        int numRecuPaiement = 8;
        int numPhotocopPass = 12;
        int numPhotocopVisa = 13;
        int numPhotocopCachetEntree = 14;

        List<TypeDocument> typeDocuments;

        typeDocuments = sb.findByOneCriteria(new TypeDocument(), "numTypeDocument", String.valueOf(numRecuPaiement));
        if (!typeDocuments.isEmpty()) {
            TypeDocument typeDocument_ = typeDocuments.get(0);

            recuPaiement_ = new DocumentFourni();
            recuPaiement_.setTypeDocument(typeDocument_);
            recuPaiement_.setDemande(demande);

            List<Etat> etats_ = sb.findByOneCriteria(new Etat(), "numEtat", "2");
            if (!etats_.isEmpty()) {
                Etat etat_ = etats_.get(0);

                recuPaiement_.setEtat(etat_);

                sb.create(recuPaiement_);
            }
        }

        if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 1) {
            if (demande.getRecu().getCasDemande().getNumCasDemande() == 1) {

                dfCertificatNat_ = new DocumentFourni();
                dfCertificatNat_.setDemande(demande);
                dfCertificatNat_.setEtat(etat);

                typeDocuments = sb.findByOneCriteria(new TypeDocument(), "numTypeDocument", String.valueOf(numPhotocopCert));
                if (!typeDocuments.isEmpty()) {
                    TypeDocument typeDocument_ = typeDocuments.get(0);

                    dfCertificatNat_.setTypeDocument(typeDocument_);
                    dfCertificatNat_.setNumeroDocumentFourni(numeroCertificatNat);
                    dfCertificatNat_.setLieuDelivrance(lieuDelivranceCertificatNat);
                    dfCertificatNat_.setDateDelivrance(dateDelivranceCertificatNat);

                    sb.create(dfCertificatNat_);
                    this.dfCertificatNat = dfCertificatNat_;
                }
            }

            if (demande.getDocJustificatifNais() == 1) {
                numTypeDocument = numExt;
            } else {
                numTypeDocument = numJugSup;
            }

            dfDocNais_ = new DocumentFourni();

            dfDocNais_.setDemande(demande);
            dfDocNais_.setDateEtablissement(dateEtablissementDocNais);

            typeDocuments = sb.findByOneCriteria(new TypeDocument(), "numTypeDocument", String.valueOf(numTypeDocument));
            if (!typeDocuments.isEmpty()) {
                TypeDocument typeDocument_ = typeDocuments.get(0);

                dfDocNais_.setTypeDocument(typeDocument_);

                dfDocNais_.setEtat(etat);

                dfDocNais_.setNumeroDocumentFourni(numeroDocNais);
                dfDocNais_.setLieuDelivrance(lieuDelivranceDocNais);
                dfDocNais_.setDateDelivrance(dateDelivranceDocNais);

                sb.create(dfDocNais_);
                this.dfDocNais = dfDocNais_;
            }
        } else if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 2) {

            typeDocuments = sb.findByOneCriteria(new TypeDocument(), "numTypeDocument", String.valueOf(numPhotocopPass));
            if (!typeDocuments.isEmpty()) {
                TypeDocument typeDocument_ = typeDocuments.get(0);

                dfPass_ = new DocumentFourni();
                dfPass_.setDemande(demande);
                dfPass_.setEtat(etat);
                dfPass_.setTypeDocument(typeDocument_);
                dfPass_.setNumeroDocumentFourni(numeroPass);
                dfPass_.setLieuDelivrance(lieuDelivrancePass);
                dfPass_.setDateDelivrance(dateDelivrancePass);

                sb.create(dfPass_);
                this.dfPass = dfPass_;
            }

            typeDocuments = sb.findByOneCriteria(new TypeDocument(), "numTypeDocument", String.valueOf(numPhotocopVisa));
            if (!typeDocuments.isEmpty()) {
                TypeDocument typeDocument_ = typeDocuments.get(0);

                dfVisa_ = new DocumentFourni();
                dfVisa_.setDemande(demande);
                dfVisa_.setEtat(etat);
                dfVisa_.setTypeDocument(typeDocument_);
                dfVisa_.setNumeroDocumentFourni(numeroVisa);
                dfVisa_.setLieuDelivrance(lieuDelivranceVisa);
                dfVisa_.setDateDelivrance(dateDelivranceVisa);

                sb.create(dfVisa_);
                this.dfVisa = dfVisa_;
            }

            typeDocuments = sb.findByOneCriteria(new TypeDocument(), "numTypeDocument", String.valueOf(numPhotocopCachetEntree));
            if (!typeDocuments.isEmpty()) {
                TypeDocument typeDocument_ = typeDocuments.get(0);

                dfCachetEntree_ = new DocumentFourni();
                dfCachetEntree_.setDemande(demande);
                dfCachetEntree_.setEtat(etat);
                dfCachetEntree_.setTypeDocument(typeDocument_);
                dfCachetEntree_.setDateEntreePays(dateEntreePays);

                sb.create(dfCachetEntree_);
                this.dfCachetEntree = dfCachetEntree_;
            }
        }
    }

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

    public void completerInformations() throws IOException, SQLException, JRException, ParseException {
        if (typeDemande.getNumTypeDemande() == 1) {
            if (this.validateInputCompleterInformationsAi() && this.verifierExistencePhoto()) {
                this.completerInformationsBlock();
            } else {
                if (this.validateInputCompleterInformationsAi() == false && this.verifierExistencePhoto() == false) {
                    mbG.ajouterMessage("Erreur", "Veuillez renseigner tous les champs nécessaires et indiquer la photo du pétitionnaire");
                } else if (this.validateInputCompleterInformationsAi() == false) {
                    mbG.ajouterMessage("Erreur", "Veuillez renseigner tous les champs nécessaires");
                } else if (this.verifierExistencePhoto() == false) {
                    mbG.ajouterMessage("Erreur", "Veuillez indiquer la photo du pétitionnaire");
                }
            }
        } else if (typeDemande.getNumTypeDemande() == 2) {
            if (this.validateInputCompleterInformationsTps() && this.verifierExistencePhoto()) {
                this.completerInformationsBlock();
            } else if (this.validateInputCompleterInformationsTps() == false && this.verifierExistencePhoto() == false) {
                mbG.ajouterMessage("Erreur", "Veuillez renseigner tous les champs nécessaires et indiquer la photo du pétitionnaire");
            } else if (this.validateInputCompleterInformationsTps() == false) {
                mbG.ajouterMessage("Erreur", "Veuillez renseigner tous les champs nécessaires");
            } else if (this.verifierExistencePhoto() == false) {
                mbG.ajouterMessage("Erreur", "Veuillez indiquer la photo du pétitionnaire");
            }
        }
    }

    public void completerInformationsBlock() throws IOException, SQLException, JRException, ParseException {

        if (mbG.validerEmailCorrectOuNonRenseigne(email) == false) {
            mbG.ajouterMessage("Erreur", "Email non valide");
        } else {

            this.gererCasse();
        // infos de Demande

            // Pour déterminer Centre
//        List<Centre> centreTraitements = sb.findByOneCriteria(new Centre(), "numCentre", "'" + numCentreTraitement + "'");
//        if (!centreTraitements.isEmpty()) {
//            centreTraitement = centreTraitements.get(0);
//        }
//        demande.setCentreTraitement(centreTraitement);
            // Pour dateDepotDemande
            // infos de Petitionnaire
            demande.getRecu().getPetitionnaire().setSexe(Integer.parseInt(sexeString));
            demande.getRecu().getPetitionnaire().setLieuNaissance(lieuNaissance);

            if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 1) {
                if (demande.getDocJustificatifNais() == 1) {
                    demande.getRecu().getPetitionnaire().setNumeroExtraitNaissance(numeroExtraitNaissance);
                } else {
                    demande.getRecu().getPetitionnaire().setNumeroJugementSuppletif(numeroJugementSuppletif);
                }
            }

            demande.getRecu().getPetitionnaire().setTaille(taille);

            demande.getRecu().getPetitionnaire().setTelephone1(mbG.retournerNullSiChampVide(telephone1));

            demande.getRecu().getPetitionnaire().setTelephone2(mbG.retournerNullSiChampVide(telephone2));

            demande.getRecu().getPetitionnaire().setTelephone3(mbG.retournerNullSiChampVide(telephone3));

            demande.getRecu().getPetitionnaire().setEmail(mbG.retournerNullSiChampVide(email));

            // infos de Petitionnaire
            demande.getRecu().getPetitionnaire().setProfession(profession);
            demande.getRecu().getPetitionnaire().setDomicile(domicile);
            demande.getRecu().getPetitionnaire().setAdressePhysique(adressePhysique);
            demande.getRecu().getPetitionnaire().setAdressePostale(adressePostale);
            if (demande.getRecu().getTypeDemande().getNumTypeDemande() == 2) {
                demande.getRecu().getPetitionnaire().setNationalite(nationalite);
            } else {
                demande.getRecu().getPetitionnaire().setNationalite("IVOIRIENNE");
            }

            // infos Parents du Petitionnaire
            demande.getRecu().getPetitionnaire().setNomPere(nomPere);
            demande.getRecu().getPetitionnaire().setPrenomsPere(prenomsPere);
            demande.getRecu().getPetitionnaire().setDateNaissancePere(dateNaissancePere);
            demande.getRecu().getPetitionnaire().setLieuNaissancePere(lieuNaissancePere);

            demande.getRecu().getPetitionnaire().setNomMere(nomMere);
            demande.getRecu().getPetitionnaire().setPrenomsMere(prenomsMere);
            demande.getRecu().getPetitionnaire().setDateNaissanceMere(dateNaissanceMere);
            demande.getRecu().getPetitionnaire().setLieuNaissanceMere(lieuNaissanceMere);

            sb.update(demande.getRecu().getPetitionnaire());

            sb.update(demande);

            this.ajouterPhotoPetitionnaire();

            this.creerDocumentFourni();

            this.renseignerDocsFournisPourLaDemande();

            this.fixerRdvEtRendreDemandeDispoPourControle();

            mbG.redirectionVersUrl("fixationRdv.xhtml");

        }

    }

    public boolean verifierExistencePhoto() {
        if (cheminSourcePhotoFinal != null) {
            if (new File(cheminSourcePhotoFinal).exists()) {
                return true;
            }
        }
        return false;
    }

    public void ajouterPhotoPetitionnaire() {

        List<TypePhoto> typePhotos = sb.findByOneCriteria(new TypePhoto(), "numTypePhoto", "1");
        if (!typePhotos.isEmpty()) {
            typePhoto = typePhotos.get(0);
        }

        cheminSourcePhotoFinal = mbG.getDossierPhotosTemp() + "/" + mbCon.getConnexion().getUtilisateur().getLogin() + "/"
                + demande.getNumDemande() + "/" + mbG.getNomSourcePhotoFinal() + "." + mbG.getTypeFichier();

        nomDestinationPhotoFinal = demande.getNumDemande() + "-" + typePhoto.getSymboleTypePhoto();
        cheminDestinationPhotoFinal = mbG.getDossierPhotosFinal() + "/" + nomDestinationPhotoFinal + "." + mbG.getTypeFichier();

        resultatDeplacementFichier = mbGf.deplacerFichier(cheminSourcePhotoFinal, cheminDestinationPhotoFinal);
        if (resultatDeplacementFichier == true) {

            //   int lastModifiedPhoto = (int) mbGu.fileLastModified(cheminAbsoluePhoto);
            //   int sizePhoto = (int) mbGu.getUploadedFileSize(photoPart);
//        System.out.println("last modified = " + lastModifiedPhoto);
//        System.out.println("size = " + sizePhoto);
            photo = new Photo();
            //     photo.setLastModifiedPhoto(lastModifiedPhoto);
            //     photo.setSizePhoto(sizePhoto);

            photo.setTypePhoto(typePhoto);
            photo.setNumPhoto(demande.getNumDemande() + "-" + typePhoto.getSymboleTypePhoto());
            photo.setCheminPhoto(cheminDestinationPhotoFinal);

            if (photo != null) {
                sb.update(photo);

                demande.setPhoto(photo);
                demande.setDateFinEnregistrementDemande(new Date());

                sb.update(demande);
            }

        }

        mbG.supprimerArborescencePhotoDeLUtilisateurConnecte();
        LOGGER.info(this.userIpAndDemandeStringForMessages("Photo pétitionnaire ajoutée"));
    }

    ////////////
    public void creerRdv() throws ParseException {

        Rdv r = new Rdv();

        Date currentDateTime = demande.getDateFinEnregistrementDemande();

        List<Correspondre> correspondres = sb.findByOneCriteria(
                new Correspondre(), "typeDemande", String.valueOf(demande.getRecu().getTypeDemande().getNumTypeDemande()));
        if (!correspondres.isEmpty()) {
            // ignores multiple results
            correspondre = correspondres.get(0);
        }

        int nbJoursPourRdv = correspondre.getNbJoursPourRdv();

        Date rdvDate = mbG.recupererDatePlusNjoursOuvres(currentDateTime, nbJoursPourRdv - 1);

        r.setDateRdv(rdvDate);

        r.setDemande(demande);

        sb.create(r);

        rdv = r;

        LOGGER.info(this.userIpAndDemandeStringForMessages("Rendez-vous n°" + rdv.getNumRdv() + "fixé"));
    }

    ///////////////////////////////////
    public void determinerEvoluerFinTraitement(String numDemande, int numStatus) {
        evoluerFinTraitement = mbG.recupererEvoluerFinTraitement(numDemande, numStatus);
        LOGGER.info(this.userIpAndDemandeStringForMessages("Fin complément des infos pétitionaire (positif). Passage de niveau (enregistrement -> contrôle)."));
    }

///////////////////////////////////
    String username = System.getenv("USERNAME");

    public void genererTicketRdvEnPdf() throws SQLException, JRException, IOException {

        String cheminFichierJrxml = "C:\\Users\\" + username + "\\Documents\\NetBeansProjects\\Projet_AI_TPS\\web\\enregistrement\\ticketRdv.jrxml";

        HashMap hashmap = new HashMap();
        hashmap.put("numDemande", demande.getNumDemande());
        hashmap.put("cheminPhoto", demande.getPhoto().getCheminPhoto());

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_ai_tps", "root", "s");

        String cheminRapportGenere = "C:\\Rapports\\TicketsRdvGeneres\\ticketRdv_demande_" + demande.getNumDemande() + ".pdf";

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdf(cheminFichierJrxml, hashmap, con, cheminRapportGenere);
    }

    public void genererTicketRdvEnPdfStream() throws SQLException, JRException, IOException {

        String cheminFichierJrxml = "C:\\Users\\" + username + "\\Documents\\NetBeansProjects\\Projet_AI_TPS\\web\\enregistrement\\ticketRdv.jrxml";

        HashMap hashmap = new HashMap();
        hashmap.put("numDemande", demande.getNumDemande());
        hashmap.put("cheminPhoto", demande.getPhoto().getCheminPhoto());

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_ai_tps", "root", "s");

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdfStream(cheminFichierJrxml, hashmap, con);
    }

////////////////////////////////////////////////////
    public void fixerRdvEtRendreDemandeDispoPourControle() throws SQLException, JRException, IOException, ParseException {
        this.creerRdv();

        evoluer = mbG.changerStatusDemande(demande, 2);
        this.determinerEvoluerFinTraitement(demande.getNumDemande(), 2);
    }

///////////////
    public void clearInputTextFields() {

        numDemande = null;

        lieuNaissance = null;
        sexeString = null;
        taille = null;
        telephone1 = null;
        telephone2 = null;
        telephone3 = null;
        email = null;

        profession = null;
        domicile = null;
        adressePhysique = null;
        adressePostale = null;
        nationalite = null;

        nomPere = null;
        prenomsPere = null;
        dateNaissancePere = null;
        lieuNaissancePere = null;
        nationalitePere = null;

        nomMere = null;
        prenomsMere = null;
        dateNaissanceMere = null;
        lieuNaissanceMere = null;
        nationaliteMere = null;

        numCentreTraitement = null;

        numeroDocNais = null;
        numeroPass = null;
        numeroVisa = null;
        dateEtablissementDocNais = null;
        dateDelivranceDocNais = null;
        dateDelivrancePass = null;
        dateDelivranceVisa = null;
        lieuDelivranceDocNais = null;
        lieuDelivrancePass = null;
        lieuDelivranceVisa = null;

        numeroCertificatNat = null;
        dateDelivranceCertificatNat = null;
        lieuDelivranceCertificatNat = null;

        dateFinEnregistrementDemande = null;
        dateEntreePays = null;

    }
    //////////////////////:

    public String retournerAuMenuGeneral() {
        mbG.supprimerArborescencePhotoDeLUtilisateurConnecte();
        this.clearInputTextFields();
        demande = null;
        return mbG.retournerAuMenuGeneral();
    }

    ///////////
    public boolean photoEstRetenue() {
        if (new File(cheminSourcePhotoFinal).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public String photoAAfficher() {
        if (new File(cheminSourcePhotoFinal).exists()) {
            return cheminSourcePhotoFinal;
        } else {
            return mbG.getDossierPhotos() + "/absencePhoto." + mbG.getTypeFichier();
        }
    }

    public String labelBoutonPrisePhoto() {
        if (this.verifierExistencePhoto()) {
            return "Reprendre une Photo";
        } else {
            return "Prendre une Photo";
        }
    }

    public String boutonAnnulerPriseouUploadPhoto() {
        mbGf.effacerArborescenceRepertoiresEtFichiers(new File(mbG.getDossierPhotosTemp() + "/" + mbCon.getUtilisateurConnecte().getLogin()));
        this.photoEstPrise = false;
        return mbG.redirigerUrl("complementInfosEtape1SaisieInfos.xhtml");
    }

    public void boutonSelectionnerAutreFichier() throws IOException {
        mbGf.effacerArborescenceRepertoiresEtFichiers(new File(mbG.getDossierPhotosTemp() + "/" + mbCon.getUtilisateurConnecte().getLogin()));
        this.photoEstPrise = false;
    }

    ////////////////
    public String afficherAppareilPhoto() {
        mbGf.effacerArborescenceRepertoiresEtFichiers(new File(cheminRepertoireCree));
        this.photoEstPrise = false;
        mbPhotoCam.setFilename(null);
        return mbG.redirigerUrl("complementInfosEtape2PrisePhoto.xhtml");
    }

    public String afficherUploadeurFichier() {
        mbGf.effacerArborescenceRepertoiresEtFichiers(new File(cheminRepertoireCree));
        this.photoEstPrise = false;
        mbPhotoCam.setFilename(null);
        return mbG.redirigerUrl("complementInfosEtape2UploadPhoto.xhtml");
    }

    ///////////
    public void validerPhotoCam() throws IOException {
        File repertoire = new File(cheminRepertoireCree);
        if (repertoire.exists()) {

            String nomFichier = mbG.getNomSourcePhotoFinal() + "." + mbG.getTypeFichier();

            mbGf.effacerUnFichierParSonNomDansUnRepertoire(cheminRepertoireCree, nomFichier);

            if (mbGf.repertoireEstVide(cheminRepertoireCree) == 0) {
                File dernierePhotoPrise = mbGf.getLatestFilefromDir(cheminRepertoireCree);
                dernierePhotoPrise.renameTo(new File(cheminSourcePhotoFinal));

                mbGf.conserverUnFichierParSonNomDansUnRepertoire(cheminRepertoireCree, nomFichier);

                this.photoEstPrise = true;

                //    this.photoEstPresente = false;
                mbG.redirectionVersUrl("complementInfosSynthese.xhtml");
            }
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez prendre une photo !");
        }

    }

    public String validerPhotoUploadee() throws IOException {
        System.out.println("Debut fonction validerPhotoUploadee()");
        if (this.photoEstPrise) {
            System.out.println("this.photoEstPrise = " + this.photoEstPrise);
            return mbG.redirigerUrl("complementInfosSynthese.xhtml");
        } else {
            System.out.println("this.photoEstPrise = " + this.photoEstPrise);
            mbG.ajouterMessage("Erreur", "Veuillez importer une photo !");
            return null;
        }
    }

    public void uploaderPhoto() throws IOException {

        mbGf.deleteIfExist(cheminSourcePhotoFinal);

        mbGf.creerUnRepertoire(cheminRepertoireCree);

        mbGu.sauvegarderFichier(photoPart, cheminSourcePhotoFinal);

        this.photoEstPrise = true;
    }

    /////////////
    public List<Integer> listePourTaille() {
        List<Integer> listePourTaille_ = new ArrayList<>();
        for (int i = 1; i <= 275; i++) {
            listePourTaille_.add(i);
        }
        return listePourTaille_;
    }

    public void afficherDemandesDispoPourEnregistrement() throws IOException {
        if (this.listeDemandesDispoPourUnNiveau(1).size() > 0) {
            mbG.redirectionVersUrl("demandeDispoPourEnregistrement.xhtml");
        } else {
            mbG.ajouterMessage("info", "Aucune demande n'a été trouvée");
        }
    }

    ///////////////////
    private String userIpAndDemandeStringForMessages(String message) {
        return mbG.userAndIpStringForMessages(" -Demande n°" + demande.getNumDemande() + "- ") + message;
    }

    ////////////// Input validation
    public boolean validateInputCompleterInformationsAi() {

        if (lieuNaissance == null
                || sexeString == null
                || taille == null
                || domicile == null
                || adressePhysique == null
                //                || nomPere == null
                //                || prenomsPere == null
                //                || dateNaissancePere == null
                //                || lieuNaissancePere == null
                //                || nomMere == null
                //                || prenomsMere == null
                //                || dateNaissanceMere == null
                //                || lieuNaissanceMere == null
                || numeroDocNais == null
                || dateEtablissementDocNais == null
                || dateDelivranceDocNais == null
                || lieuDelivranceDocNais == null) {
            return false;
        }
        if (demande.getRecu().getCasDemande().getNumCasDemande() == 1) {
            if (numeroCertificatNat == null
                    || dateDelivranceCertificatNat == null
                    || lieuDelivranceCertificatNat == null) {
                return false;
            }
        }
        return true;
    }

    public boolean validateInputCompleterInformationsTps() {
        if (lieuNaissance == null
                || sexeString == null
                || taille == null
                || domicile == null
                || adressePhysique == null
                || nationalite == null
                //                || nomPere == null
                //                || prenomsPere == null
                //                || dateNaissancePere == null
                //                || lieuNaissancePere == null
                //                || nomMere == null
                //                || prenomsMere == null
                //                || dateNaissanceMere == null
                //                || lieuNaissanceMere == null
                || numeroPass == null
                || numeroVisa == null
                || dateDelivrancePass == null
                || dateDelivranceVisa == null
                || lieuDelivrancePass == null
                || lieuDelivranceVisa == null
                || dateEntreePays == null) {
            return false;
        }
        return true;
    }

}
