package managedBeans;

import entities.Demande;
import entities.DemandeEnCoursDUtilisation;
import entities.DocumentFourni;
import entities.DocumentNecessaire;
import entities.Etat;
import entities.Evoluer;
import entities.Photo;
import entities.Rdv;
import entities.Status;
import entities.TypeDemande;
import entities.TypeDocument;
import entities.TypePhoto;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import sessionBeans.SB;

@SessionScoped
@Named("mB_Controle")
public class MB_Controle implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Connexion mbCon;

    @Inject
    MB_GestionFichiers mbGf;

    @Inject
    MB_GestionUpload mbGu;

    public MB_Controle() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /////////////////////////////
    private Part photoPart;

    ////// TypeDemande
    private int numTypeDemande;
    private TypeDemande typeDemande;

    ////// Status
    private int numStatus;
    private Status status;

    ////// Demande
    private String numDemande;

    private String numDemandeNontrouvee;

    // Found from numDemande
    private Demande demande;

    // Rdv
    private Rdv rdv;

    // TypeDocument
    private TypeDocument typeDocument;

    private int numDocumentNecessaire;
    private int[] numsDocumentNecessaires;

    //Pour afficher Premiers documents fournis
    private DocumentFourni dfExt;
    //   private DocumentFourni dfPhotocopExt;
    private DocumentFourni dfJugSup;
    //   private DocumentFourni dfPhotocopJugSup;
    private DocumentFourni dfCertificatNat;
    private DocumentFourni dfPass;
    private DocumentFourni dfVisa;
    private DocumentFourni dfCachetEntree;

    /// Pour saisir nouveau(x) document(s) fourni 
    private DocumentFourni documentFourni;
    private DocumentFourni dfDoc;
    private String numeroDoc;
    private Date dateEtablissementDoc;
    private Date dateDelivranceDoc;
    private String lieuDelivranceDoc;
    private Date dateEntreePays;

    //
    private String motifRejet;

    //
    private Evoluer evoluer;

    private Evoluer evoluerFinTraitement;
    //
    private boolean affichageBoutonsRadio = false;
    private boolean affichageSelectionDocsFournis = false;
    private boolean affichageSelectionUnDocFourni = false;
    private boolean affichageSaisieInfosDocFourni = false;

    ////////////
    private DocumentFourni selectedDocFourniPourEtat;
    private DocumentFourni selectedDocFourniPourInfosDoc;
    private DocumentFourni selectedDocFourni;

    private int numEtatSelectedDocFourni;
    private int numDocumentFourniPourEtat;

    //////////////
    // Photo
    private Photo photo;

    // TypePhoto
    private TypePhoto typePhoto;

//// getters and setters
    public Part getPhotoPart() {
        return photoPart;
    }

    public void setPhotoPart(Part photoPart) {
        this.photoPart = photoPart;
    }

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

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Rdv getRdv() {
        return rdv;
    }

    public void setRdv(Rdv rdv) {
        this.rdv = rdv;
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

    public DocumentFourni getDocumentFourni() {
        return documentFourni;
    }

    public void setDocumentFourni(DocumentFourni documentFourni) {
        this.documentFourni = documentFourni;
    }

    public DocumentFourni getDfDoc() {
        return dfDoc;
    }

    public void setDfDoc(DocumentFourni dfDoc) {
        this.dfDoc = dfDoc;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Date getDateEtablissementDoc() {
        return dateEtablissementDoc;
    }

    public void setDateEtablissementDoc(Date dateEtablissementDoc) {
        this.dateEtablissementDoc = dateEtablissementDoc;
    }

    public Date getDateDelivranceDoc() {
        return dateDelivranceDoc;
    }

    public void setDateDelivranceDoc(Date dateDelivranceDoc) {
        this.dateDelivranceDoc = dateDelivranceDoc;
    }

    public String getLieuDelivranceDoc() {
        return lieuDelivranceDoc;
    }

    public void setLieuDelivranceDoc(String lieuDelivranceDoc) {
        this.lieuDelivranceDoc = lieuDelivranceDoc;
    }

    public Date getDateEntreePays() {
        return dateEntreePays;
    }

    public void setDateEntreePays(Date dateEntreePays) {
        this.dateEntreePays = dateEntreePays;
    }

    public String getMotifRejet() {
        return motifRejet;
    }

    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
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

    public boolean isAffichageBoutonsRadio() {
        return affichageBoutonsRadio;
    }

    public void setAffichageBoutonsRadio(boolean affichageBoutonsRadio) {
        this.affichageBoutonsRadio = affichageBoutonsRadio;
    }

    public boolean isAffichageSelectionDocsFournis() {
        return affichageSelectionDocsFournis;
    }

    public void setAffichageSelectionDocsFournis(boolean affichageSelectionDocsFournis) {
        this.affichageSelectionDocsFournis = affichageSelectionDocsFournis;
    }

    public boolean isAffichageSelectionUnDocFourni() {
        return affichageSelectionUnDocFourni;
    }

    public void setAffichageSelectionUnDocFourni(boolean affichageSelectionUnDocFourni) {
        this.affichageSelectionUnDocFourni = affichageSelectionUnDocFourni;
    }

    public boolean isAffichageSaisieInfosDocFourni() {
        return affichageSaisieInfosDocFourni;
    }

    public void setAffichageSaisieInfosDocFourni(boolean affichageSaisieInfosDocFourni) {
        this.affichageSaisieInfosDocFourni = affichageSaisieInfosDocFourni;
    }

    public DocumentFourni getSelectedDocFourniPourEtat() {
        return selectedDocFourniPourEtat;
    }

    public void setSelectedDocFourniPourEtat(DocumentFourni selectedDocFourniPourEtat) {
        this.selectedDocFourniPourEtat = selectedDocFourniPourEtat;
    }

    public DocumentFourni getSelectedDocFourniPourInfosDoc() {
        return selectedDocFourniPourInfosDoc;
    }

    public void setSelectedDocFourniPourInfosDoc(DocumentFourni selectedDocFourniPourInfosDoc) {
        this.selectedDocFourniPourInfosDoc = selectedDocFourniPourInfosDoc;
    }

    public DocumentFourni getSelectedDocFourni() {
        return selectedDocFourni;
    }

    public void setSelectedDocFourni(DocumentFourni selectedDocFourni) {
        this.selectedDocFourni = selectedDocFourni;
    }

    public int getNumEtatSelectedDocFourni() {
        return numEtatSelectedDocFourni;
    }

    public void setNumEtatSelectedDocFourni(int numEtatSelectedDocFourni) {
        this.numEtatSelectedDocFourni = numEtatSelectedDocFourni;
    }

    public int getNumDocumentFourniPourEtat() {
        return numDocumentFourniPourEtat;
    }

    public void setNumDocumentFourniPourEtat(int numDocumentFourniPourEtat) {
        this.numDocumentFourniPourEtat = numDocumentFourniPourEtat;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public TypePhoto getTypePhoto() {
        return typePhoto;
    }

    public void setTypePhoto(TypePhoto typePhoto) {
        this.typePhoto = typePhoto;
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

//////////////////////////////
    public List<DocumentNecessaire> listeAutresDocumentNecessaires() {
        List<DocumentNecessaire> dnsTousDocNec = sb.findByTwoCriteria(new DocumentNecessaire(), "typeDemande", String.valueOf(demande.getRecu().getTypeDemande().getNumTypeDemande()), "casDemande", String.valueOf(demande.getRecu().getCasDemande().getNumCasDemande()));
        List<DocumentNecessaire> dnsAutresDocNec = new ArrayList<>();

        for (DocumentNecessaire dn : dnsTousDocNec) {
            dnsAutresDocNec.add(dn);
        }

        for (DocumentNecessaire dn : dnsTousDocNec) {
            for (DocumentFourni df : this.listeDocumentsFournis()) {
                if (Objects.equals(dn.getTypeDocument().getNumTypeDocument(), df.getTypeDocument().getNumTypeDocument())) {
                    dnsAutresDocNec.remove(dn);

                }
            }
        }

        // Sorting
        Collections.sort(dnsAutresDocNec, new Comparator<DocumentNecessaire>() {

            @Override
            public int compare(DocumentNecessaire dn1, DocumentNecessaire dn2) {
                return dn1.getTypeDocument().getNomTypeDocument().compareTo(dn2.getTypeDocument().getNomTypeDocument());
            }
        });

        return dnsAutresDocNec;
    }

    public List<TypeDocument> listeTypeDocuments() {
        return sb.listAll(new TypeDocument());
    }

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

        return mbG.redirigerUrl("/controle/rechercheDemande.xhtml");
    }

    public String rechercherDemande() throws IOException {
        List<Demande> demandes = sb.findByOneCriteria(new Demande(), "numDemande", "'" + numDemande + "'");
        if (!demandes.isEmpty()) {
            if (demandes.get(0).getRecu().getTypeDemande().getNumTypeDemande() == numTypeDemande) {
                demande = demandes.get(0);

                this.renseignerDocsFournisPourLaDemande();

                /////////// Charger Objet Rdv et Evoluer
                List<Rdv> rdvs = sb.findByOneCriteria(new Rdv(), "demande", "'" + demande.getNumDemande() + "'");
                if (!rdvs.isEmpty()) {
                    rdv = rdvs.get(0);
                }

                List<Evoluer> evoluers = sb.findLastInsertedAtADateAndOneCriteria(new Evoluer(), "dates", new Date(), "demande", "'" + demande.getNumDemande() + "'");
                if (!evoluers.isEmpty()) {
                    evoluer = evoluers.get(0);
                    if (evoluer.getStatus().getNumStatus() < 2) {
                        this.clearInputTextFields();
                        mbG.messageDemandeNonTrouvee();
                        return null;
                    } else if (evoluer.getStatus().getNumStatus() > 2) {
                        this.clearInputTextFields();
                        if (evoluer.getStatus().getNumStatus() != 4) {
                            this.determinerEvoluerFinTraitement(demande.getNumDemande(), 3);
                        } else {
                            this.determinerEvoluerFinTraitement(demande.getNumDemande(), 4);
                        }
                        return mbG.redirigerUrl("priseDecision.xhtml");
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
                    return mbG.redirigerUrl("menuControle.xhtml");
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

    public List<Etat> listerEtats() {
        return sb.listAll(new Etat());
    }

    public void ajouterDocumentsFournis() {

        DocumentFourni df;

        List<DocumentNecessaire> documentNecessaires;
        DocumentNecessaire documentNecessaire;

        Etat etat = null;
        List<Etat> etats = sb.findByOneCriteria(new Etat(), "numEtat", "1");
        if (!etats.isEmpty()) {
            etat = etats.get(0);
        }

        for (int numDocNec : numsDocumentNecessaires) {

            documentNecessaires = sb.findByOneCriteria(new DocumentNecessaire(), "numDocumentNecessaire", String.valueOf(numDocNec));
            if (!documentNecessaires.isEmpty()) {
                documentNecessaire = documentNecessaires.get(0);

                df = new DocumentFourni();
                df.setDemande(demande);
                df.setTypeDocument(documentNecessaire.getTypeDocument());
                df.setEtat(etat);

                sb.create(df);
            }
        }
        this.affichageSelectionDocsFournis = false;
        for (int i = 0; i < numsDocumentNecessaires.length; i++) {
            numsDocumentNecessaires[i] = 0;
        }
    }

    public void passerParametres(DocumentFourni df) {
        selectedDocFourniPourEtat = df;
        numEtatSelectedDocFourni = df.getEtat().getNumEtat();
        this.affichageBoutonsRadio = true;
    }

    public void supprimerUnDocFourni(DocumentFourni df) {
        sb.delete(df);
    }

    public boolean docFourniNonSupprimable(DocumentFourni df) {

        int numDoc = df.getTypeDocument().getNumTypeDocument();

        int numExt = 1;
        //       int numPhotocopExt = 2;
        int numJugSup = 3;
        //       int numPhotocopJugSup = 4;
        int numPhotocopCert = 5;
        int numRecuPaiement = 8;
        int numPhotocopPass = 12;
        int numPhotocopVisa = 13;
        int numPhotocopCachetEntree = 14;

        if ((numDoc == numExt)// || (numDoc == numPhotocopExt)
                || (numDoc == numJugSup)// || (numDoc == numPhotocopJugSup) 
                || (numDoc == numPhotocopCert)
                || (numDoc == numRecuPaiement)
                || (numDoc == numPhotocopPass) || (numDoc == numPhotocopVisa) || (numDoc == numPhotocopCachetEntree)) {
            return true;
        } else {
            return false;

        }
    }

    public void validerMiseAJourEtat() {
        this.updateEtatDocumentsFournis();
        affichageBoutonsRadio = false;
    }

    public void ajouterPhotoDocument(DocumentFourni df) throws IOException {

        List<TypePhoto> typePhotos = sb.findByOneCriteria(new TypePhoto(), "numTypePhoto", "2");
        if (!typePhotos.isEmpty()) {
            typePhoto = typePhotos.get(0);
        }

        String uploads = mbG.getDossierPhotosDocFournis();

        String numPhoto = demande.getNumDemande() + "-" + typePhoto.getSymboleTypePhoto() + "-" + df.getTypeDocument().getNumTypeDocument();

        String cheminAbsoluePhoto = (uploads + "/" + numPhoto + "." + mbG.getTypeFichier());

        mbGu = new MB_GestionUpload();

        mbGf.deleteIfExist(cheminAbsoluePhoto);
        mbGu.sauvegarderFichier(photoPart, cheminAbsoluePhoto);

        int lastModifiedPhoto = (int) mbGu.fileLastModified(cheminAbsoluePhoto);
        int sizePhoto = (int) mbGu.getUploadedFileSize(photoPart);

        System.out.println("last modified = " + lastModifiedPhoto);
        System.out.println("size = " + sizePhoto);

        Photo photo_ = new Photo();
        photo_.setLastModifiedPhoto(lastModifiedPhoto);
        photo_.setSizePhoto(sizePhoto);

        photo_.setTypePhoto(typePhoto);
        photo_.setNumPhoto(numPhoto);
        photo_.setCheminPhoto(cheminAbsoluePhoto);
System.out.println("OK ==> photo_.setCheminPhoto(cheminAbsoluePhoto)");
System.out.println("OK ==> cheminAbsoluePhoto = " + cheminAbsoluePhoto);
        sb.update(photo_);
System.out.println("OK ==> sb.update(photo_)");
        photo = photo_;

        df.setPhoto(photo);
System.out.println("OK ==> df.setPhoto(photo)");
        sb.update(df);
        System.out.println("OK ==> sb.update(df)");
        //    mbG.redirectionVersUrl("controleDocumentsFournis.xhtml");
    }

    public boolean verifierExistenceScanDoc(DocumentFourni df) {
        if (df.getPhoto() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void boutonSelectionnerAutreFichier(DocumentFourni df) throws IOException {
        Photo photoDoc = df.getPhoto();
        df.setPhoto(null);
        sb.update(df);
        sb.delete(photoDoc);
    }

    public String afficherInfosDoc(DocumentFourni df) {
        documentFourni = df;
        affichageSaisieInfosDocFourni = false;
        return mbG.redirigerUrl("infosDoc.xhtml");
    }

    public void ajouterInfosDocument() {
        if (documentFourni.getTypeDocument().getNumTypeDocument() != 14) {
            documentFourni.setNumeroDocumentFourni(numeroDoc);
            if (documentFourni.getTypeDocument().getNumTypeDocument() == 1) {
                documentFourni.setDateEtablissement(dateEtablissementDoc);
            }
            documentFourni.setDateDelivrance(dateDelivranceDoc);
            documentFourni.setLieuDelivrance(lieuDelivranceDoc);
        } else {
            documentFourni.setDateEntreePays(dateEntreePays);
        }
        sb.update(documentFourni);

    }

    public void updateEtatDocumentsFournis() {
        if (numEtatSelectedDocFourni != 0) {
            Etat etat = null;
            List<Etat> etats = sb.findByOneCriteria(new Etat(), "numEtat", String.valueOf(numEtatSelectedDocFourni));
            if (!etats.isEmpty()) {
                etat = etats.get(0);
            }
            selectedDocFourniPourEtat.setEtat(etat);
            sb.update(selectedDocFourniPourEtat);

            numEtatSelectedDocFourni = 0;
        }
    }

    public List<DocumentFourni> listeDocumentsFournis() {

        List<DocumentFourni> dfs = sb.findByOneCriteria(new DocumentFourni(), "demande", "'" + demande.getNumDemande() + "'");

        // Sorting
        Collections.sort(dfs, new Comparator<DocumentFourni>() {

            @Override
            public int compare(DocumentFourni df1, DocumentFourni df2) {
                return df1.getTypeDocument().getNomTypeDocument().compareTo(df2.getTypeDocument().getNomTypeDocument());
            }
        });

        return dfs;
    }

    public boolean affichageBoutonsRadio() {
        affichageBoutonsRadio = !affichageBoutonsRadio;
        return affichageBoutonsRadio;

    }

    public boolean affichageSelectionDocsFournis() {
        affichageSelectionDocsFournis = true;
        return affichageSelectionDocsFournis;
    }

    public boolean nonAffichageSelectionDocsFournis() {
        affichageSelectionDocsFournis = false;
        for (int i = 0; i < numsDocumentNecessaires.length; i++) {
            numsDocumentNecessaires[i] = 0;
        }
        return affichageSelectionDocsFournis;
    }

    public boolean affichageSaisieInfosDocFourni() {
        affichageSaisieInfosDocFourni = !affichageSaisieInfosDocFourni;
        if (affichageSelectionDocsFournis) {
            affichageSelectionDocsFournis = !affichageSelectionDocsFournis;
        }
        if (affichageSelectionUnDocFourni) {
            affichageSelectionUnDocFourni = !affichageSelectionUnDocFourni;
        }
        return affichageSaisieInfosDocFourni;

    }

    /////////////////
    public boolean dossierValidable(int numEtatDoc) {
        if (this.listeDocumentsFournis().isEmpty()) {
            return false;
        } else {
            for (DocumentFourni df : this.listeDocumentsFournis()) {
                if (!(df.getEtat().getNumEtat() == numEtatDoc)) {
                    return false;
                }
            }
            return true;
        }
    }

    public String afficherPhotoDocument(DocumentFourni df) {
        documentFourni = df;
        return mbG.redirigerUrl("photoDocument.xhtml");
    }

    ////
    public void envoyerDemandeALImpression() {
        this.changerStatusDemande(3);
        this.determinerEvoluerFinTraitement(demande.getNumDemande(), 3);
    }

    public void rejeterDemande() throws IOException {
        if (motifRejet != null) {
            if (!motifRejet.isEmpty()) {
                demande.setMotifRejet(motifRejet);
                sb.update(demande);
                this.changerStatusDemande(4);
                this.determinerEvoluerFinTraitement(demande.getNumDemande(), 4);
                
                mbG.redirectionVersUrl("priseDecision.xhtml");
            }
        } else {
            mbG.ajouterMessage("Erreur", "Motif obligatoire");
        }
        
    }

    /////////////////////////////////////////////
    // niveauAvancement peut prendre valeur 3 (vers Saisie) ou 4 (Demande Rejetée)
    public void changerStatusDemande(int niveauAvancement) {
        evoluer = mbG.changerStatusDemande(demande, niveauAvancement);
    }

    public boolean evaluerStatusDemande(int numStatus) {
        return mbG.evaluerStatusDemande(demande, numStatus);
    }

    ///////////////////////////////////
    public void determinerEvoluerFinTraitement(String numDemande, int numStatus) {
        evoluerFinTraitement = mbG.recupererEvoluerFinTraitement(numDemande, numStatus);
    }

///////////////
    public String afficherDocFournis() {
        if (evoluer.getStatus().getNumStatus() == 2) {
            return mbG.redirigerUrl("controleDocumentsFournis.xhtml");
        } else if (evoluer.getStatus().getNumStatus() > 2) {
            return mbG.redirigerUrl("controleDocumentsFournis_frozen");
        } else {
            return mbG.redirigerUrl("pageErreur.xhtml");
        }
    }

////////////////////////////////////////////////////
    public void clearInputTextFields() {

        numDemande = null;

        documentFourni = null;
        dfDoc = null;
        numeroDoc = null;
        dateEtablissementDoc = null;
        dateDelivranceDoc = null;
        lieuDelivranceDoc = null;

        affichageBoutonsRadio = false;
        affichageSelectionDocsFournis = false;
        affichageSelectionUnDocFourni = false;
        
        motifRejet = null;

    }
//////////////////////:

    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        demande = null;
        return mbG.retournerAuMenuGeneral();
    }

    public void afficherDemandesDispoPourControle() throws IOException {
        if (this.listeDemandesDispoPourUnNiveau(2).size() > 0) {
            mbG.redirectionVersUrl("demandeDispoPourControle.xhtml");
        } else {
            mbG.ajouterMessage("info", "Aucune demande n'a été trouvée");
        }
    }

    public String afficherPageSaisieMotifRejet() {
        return mbG.redirigerUrl("saisieMotifRejet.xhtml");
    }

    public String retourPagePriseDecision() {
        motifRejet = null;
        return mbG.redirigerUrl("priseDecision.xhtml");
    }
    
}
