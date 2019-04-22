package managedBeans;

import entities.AppartenirGroupe;
import entities.Centre;
import entities.Commune;
import entities.Connexion;
import entities.Groupe;
import entities.SousPrefecture;
import entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import sessionBeans.SB;

@Named("mB_Administration")
@SessionScoped
public class MB_Administration implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Chiffrement mbChiffrement;

    @Inject
    MB_Connexion mbCon;

    @Inject
    MB_TraitementsEmails mbTrEmails;

    public MB_Administration() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /////////////////////////////
    /////////
    private Utilisateur utilisateurCree;
    private String nomUtilisateurCree;
    private String prenomUtilisateurCree;
    private String sexeUtilisateurCreeString;
    private String loginUtilisateurCree;
    private String password1UtilisateurCree;
    private String password2UtilisateurCree;
    private String emailUtilisateurCree;
    private String telephoneUtilisateurCree;

    //////////
    private Centre centreTravail;
    private String numCentreTravail;
    ///////

    private List<AppartenirGroupe> appartenirGroupes;

    private ArrayList<Integer> numerosGroupes;

    private int[] numsGroupes;

    ///////
    private Integer resultatCreationUtilisateur;

    ///////////
    public Utilisateur getUtilisateurCree() {
        return utilisateurCree;
    }

    public void setUtilisateurCree(Utilisateur utilisateurCree) {
        this.utilisateurCree = utilisateurCree;
    }

    public String getNomUtilisateurCree() {
        return nomUtilisateurCree;
    }

    public void setNomUtilisateurCree(String nomUtilisateurCree) {
        this.nomUtilisateurCree = nomUtilisateurCree;
    }

    public String getPrenomUtilisateurCree() {
        return prenomUtilisateurCree;
    }

    public void setPrenomUtilisateurCree(String prenomUtilisateurCree) {
        this.prenomUtilisateurCree = prenomUtilisateurCree;
    }

    public String getSexeUtilisateurCreeString() {
        return sexeUtilisateurCreeString;
    }

    public void setSexeUtilisateurCreeString(String sexeUtilisateurCreeString) {
        this.sexeUtilisateurCreeString = sexeUtilisateurCreeString;
    }

    public String getLoginUtilisateurCree() {
        return loginUtilisateurCree;
    }

    public void setLoginUtilisateurCree(String loginUtilisateurCree) {
        this.loginUtilisateurCree = loginUtilisateurCree;
    }

    public String getPassword1UtilisateurCree() {
        return password1UtilisateurCree;
    }

    public void setPassword1UtilisateurCree(String password1UtilisateurCree) {
        this.password1UtilisateurCree = password1UtilisateurCree;
    }

    public String getPassword2UtilisateurCree() {
        return password2UtilisateurCree;
    }

    public void setPassword2UtilisateurCree(String password2UtilisateurCree) {
        this.password2UtilisateurCree = password2UtilisateurCree;
    }

    public String getEmailUtilisateurCree() {
        return emailUtilisateurCree;
    }

    public void setEmailUtilisateurCree(String emailUtilisateurCree) {
        this.emailUtilisateurCree = emailUtilisateurCree;
    }

    public String getTelephoneUtilisateurCree() {
        return telephoneUtilisateurCree;
    }

    public void setTelephoneUtilisateurCree(String telephoneUtilisateurCree) {
        this.telephoneUtilisateurCree = telephoneUtilisateurCree;
    }

    public Centre getCentreTravail() {
        return centreTravail;
    }

    public void setCentreTravail(Centre centreTravail) {
        this.centreTravail = centreTravail;
    }

    public String getNumCentreTravail() {
        return numCentreTravail;
    }

    public void setNumCentreTravail(String numCentreTravail) {
        this.numCentreTravail = numCentreTravail;
    }

    public List<AppartenirGroupe> getAppartenirGroupes() {
        return appartenirGroupes;
    }

    public void setAppartenirGroupes(List<AppartenirGroupe> appartenirGroupes) {
        this.appartenirGroupes = appartenirGroupes;
    }

    public ArrayList<Integer> getNumerosGroupes() {
        return numerosGroupes;
    }

    public void setNumerosGroupes(ArrayList<Integer> numerosGroupes) {
        this.numerosGroupes = numerosGroupes;
    }

    public int[] getNumsGroupes() {
        return numsGroupes;
    }

    public void setNumsGroupes(int[] numsGroupes) {
        this.numsGroupes = numsGroupes;
    }

    public Integer getResultatCreationUtilisateur() {
        return resultatCreationUtilisateur;
    }

    public void setResultatCreationUtilisateur(Integer resultatCreationUtilisateur) {
        this.resultatCreationUtilisateur = resultatCreationUtilisateur;
    }

    public String getNomGroupeCree() {
        return nomGroupeCree;
    }

    public void setNomGroupeCree(String nomGroupeCree) {
        this.nomGroupeCree = nomGroupeCree;
    }

    public String getNomSousPrefectureCreee() {
        return nomSousPrefectureCreee;
    }

    public void setNomSousPrefectureCreee(String nomSousPrefectureCreee) {
        this.nomSousPrefectureCreee = nomSousPrefectureCreee;
    }

    public String getNomCommuneCreee() {
        return nomCommuneCreee;
    }

    public void setNomCommuneCreee(String nomCommuneCreee) {
        this.nomCommuneCreee = nomCommuneCreee;
    }

    public String getNomCentreCree() {
        return nomCentreCree;
    }

    public void setNomCentreCree(String nomCentreCree) {
        this.nomCentreCree = nomCentreCree;
    }

    public String getNumSousPrefectureCreee() {
        return numSousPrefectureCreee;
    }

    public void setNumSousPrefectureCreee(String numSousPrefectureCreee) {
        this.numSousPrefectureCreee = numSousPrefectureCreee;
    }

    public String getNumCommuneCreee() {
        return numCommuneCreee;
    }

    public void setNumCommuneCreee(String numCommuneCreee) {
        this.numCommuneCreee = numCommuneCreee;
    }

    public String getNumCentreCree() {
        return numCentreCree;
    }

    public void setNumCentreCree(String numCentreCree) {
        this.numCentreCree = numCentreCree;
    }

    ////////////////////
    public void gererCasse() {
        nomUtilisateurCree = mbG.ecrireEnMaj(nomUtilisateurCree);
        prenomUtilisateurCree = mbG.ecrireEnMaj(prenomUtilisateurCree);
    }

    ///////////
    public List<Centre> listeCentres() {
        return sb.listAll(new Centre(), " order by nomCentre");
    }

    ////////////
    //////////////// CREATION UTILISATEUR et AFFECTATION AUX GROUPES
    public void creerOuModifierUtilisateurEtAffecterAuxGroupesParAdminCentre() throws NoSuchAlgorithmException, IOException, MessagingException {
        if (numsGroupes.length > 0) {
            this.gererCasse();

            if (this.UtilisateursSontDansMemeCentre(mbCon.getUtilisateurConnecte().getLogin(), loginUtilisateurCree)) {
                if (this.utilisateurAyantDejaCeLogin(loginUtilisateurCree) == null) {
                    this.creerUtilisateur(mbCon.getUtilisateurConnecte().getCentreTravail(), "resultatCreationUtilisateurDuCentre.xhtml");
                } else {
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('btnCreerUtilisateur').jq.click();");
                }
            } else {
                mbG.ajouterMessage("Erreur", "Centres différents");
            }
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez sélectionner au moins un groupe");
        }
    }

    public void creerOuModifierUtilisateurEtAffecterAuxGroupesParAdminGeneral() throws NoSuchAlgorithmException, IOException, MessagingException {
        if (numsGroupes.length > 0) {
            this.gererCasse();

            List<Centre> centres = sb.findByOneCriteria(new Centre(), "numCentre", "'" + numCentreTravail + "'");
            if (!centres.isEmpty()) {
                centreTravail = centres.get(0);
            }

            if (this.utilisateurAyantDejaCeLogin(loginUtilisateurCree) == null) {
                this.creerUtilisateur(centreTravail, "resultatCreationUtilisateur.xhtml");
            } else {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('btnCreerUtilisateur').jq.click();");
            }
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez sélectionner au moins un groupe");
        }
    }
////////////

    public void creerAppartenirGroupes() {

        AppartenirGroupe agu;

        List<Groupe> groupes;
        Groupe groupe;

        int[] tableauNumsGroupesAParcourir = numsGroupes;
        for (int numGroupe_ : tableauNumsGroupesAParcourir) {

            groupes = sb.findByOneCriteria(new Groupe(), "numGroupe", String.valueOf(numGroupe_));
            if (!groupes.isEmpty()) {
                groupe = groupes.get(0);

                agu = new AppartenirGroupe();
                agu.setGroupe(groupe);
                agu.setUtilisateur(utilisateurCree);
                sb.create(agu);
            }
        }

    }

    ////////////
    public void clearInputTextFields() {

        //////////
        numCentreTravail = null;

        nomUtilisateurCree = null;
        prenomUtilisateurCree = null;
        sexeUtilisateurCreeString = null;
        loginUtilisateurCree = null;
        password1UtilisateurCree = null;
        password2UtilisateurCree = null;
        emailUtilisateurCree = null;

        resultatCreationUtilisateur = null;
        ////////

        nomGroupeCree = null;
        nomSousPrefectureCreee = null;
        nomCommuneCreee = null;
        nomCentreCree = null;

        numSousPrefectureCreee = null;
        numCommuneCreee = null;
        numCentreCree = null;

    }

    ////////////////////
    public List<Utilisateur> listerUtilisateurs() {
        return sb.listAll(new Utilisateur(), " order by centreTravail, nomUtilisateur, prenomUtilisateur, login");
    }

    public List<Utilisateur> listerUtilisateursDuCentre() {
        return sb.listAll(new Utilisateur(), " where centreTravail = '" + mbCon.getUtilisateurConnecte().getCentreTravail().getNumCentre() + "' order by centreTravail, nomUtilisateur, prenomUtilisateur, login");
    }

    ////////////
    public List<Centre> listerCentres() {
        return sb.listAll(new Centre(), " order by commune, nomCentre");
    }

    public List<Commune> listerCommunes() {
        return sb.listAll(new Commune(), " order by sousPrefecture, nomCommune");
    }

    public List<SousPrefecture> listerSousPrefectures() {
        return sb.listAll(new SousPrefecture(), " order by nomSousPrefecture");
    }

    /////////////
    public List<Connexion> listerConnexions() {
        return sb.listAll(new Connexion(), " order by dateDebut, dateFin");
    }

    public List<Connexion> listerConnexionsDuCentre() {
        return sb.listAll(new Connexion(), " where utilisateur in (select numUtilisateur from Utilisateur where centreTravail = '" + mbCon.getUtilisateurConnecte().getCentreTravail().getNumCentre() + "') order by dateDebut");
    }
//////////////////////////////

    public List<Groupe> listerGroupes() {
        return sb.listAll(new Groupe(), " order by nomGroupe");
    }

    public List<Groupe> listerGroupesDuCentre() {
        return sb.listAll(new Groupe(), " where niveauAdministration >= 2 order by nomGroupe");
    }

    //////////////
    public long nombreUtilisateursDansUnGroupes(int numGroupe_) {
        return sb.findNombreUtilisateursDansUnGroupes(numGroupe_);
    }

    public long nombreGroupesDeUnUtilisateur(int numUtilisateur_) {
        return sb.findNombreGroupesDeUnUtilisateur(numUtilisateur_);
    }

    ////////////
    private String nomGroupeCree;
    private String nomSousPrefectureCreee;
    private String nomCommuneCreee;
    private String nomCentreCree;

    private String numSousPrefectureCreee;
    private String numCommuneCreee;
    private String numCentreCree;

    public void creerGroupe() {
        if (this.validateInputCreationGroupe()) {
            Groupe groupe = new Groupe();
            groupe.setNomGroupe(nomGroupeCree);
            sb.create(groupe);

        } else {
            mbG.ajouterMessage("erreur", "Veuillez renseigner tous les champs nécessaires");
        }
    }

    public void creerSousPrefecture() {
        if (this.validateInputCreationSousPrefecture()) {
            SousPrefecture sp = new SousPrefecture();
            sp.setNumSousPrefecture(numSousPrefectureCreee);
            sp.setNomSousPrefecture(nomSousPrefectureCreee);
            sb.create(sp);

        } else {
            mbG.ajouterMessage("erreur", "Veuillez renseigner tous les champs nécessaires");
        }

    }

    public void creerCommune(String numSousPrefecture_) {
        if (this.validateInputCreationCommune()) {
            SousPrefecture sp = null;
            List<SousPrefecture> sps = sb.findByOneCriteria(new SousPrefecture(), "numSousPrefecture", "'" + numSousPrefecture_ + "'");
            if (!sps.isEmpty()) {
                sp = sps.get(0);
            }
            Commune com = new Commune();
            com.setNumCommune(numCommuneCreee);
            com.setNomCommune(nomCommuneCreee);
            com.setSousPrefecture(sp);
            sb.create(com);

        } else {
            mbG.ajouterMessage("erreur", "Veuillez renseigner tous les champs nécessaires");
        }

    }

    public void creerCentre(String numCommune_) {
        if (this.validateInputCreationCentre()) {
            Commune com = null;
            List<Commune> coms = sb.findByOneCriteria(new Commune(), "numCommune", "'" + numCommune_ + "'");
            if (!coms.isEmpty()) {
                com = coms.get(0);
            }
            Centre centre = new Centre();
            centre.setNumCentre(numCentreCree);
            centre.setNomCentre(nomCentreCree);
            centre.setValeurCompteurAi(0);
            centre.setValeurCompteurTps(0);
            centre.setCommune(com);
            sb.create(centre);
        } else {
            mbG.ajouterMessage("erreur", "Veuillez renseigner tous les champs nécessaires");
        }

    }

    //////////////////////
    public void supprimerObjet(Object objet) {
        sb.delete(objet);
    }

    public boolean utilisateurEstSuperUtilisateur(Utilisateur utilisateur_) {
        List<AppartenirGroupe> ag = sb.findByTwoCriteria(new AppartenirGroupe(), "utilisateur", String.valueOf(utilisateur_.getNumUtilisateur()), "groupe", "1");
        if (!ag.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

//    public boolean utilisateurEstDesactivable(Utilisateur utilisateur_) {
//        if (utilisateur_.getPeutEtreSupprime()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    public boolean utilisateurEstActif(Utilisateur utilisateur_) {
        if (utilisateur_.getPeutSeConnecter()) {
            return true;
        } else {
            return false;
        }
    }

    public void activerOuDesactiverUtilisateur(Utilisateur utilisateurConnecte, Utilisateur utilisateurConcerne) {

        List<Utilisateur> utilisateurConnectes_ = sb.findByOneCriteria(new Utilisateur(), "numUtilisateur", String.valueOf(utilisateurConnecte.getNumUtilisateur()));
        if (!utilisateurConnectes_.isEmpty()) {
            utilisateurConnecte = utilisateurConnectes_.get(0);
        }

        if (utilisateurConnecte.getPeutSeConnecter()) {
            if (utilisateurConcerne.getNumUtilisateur() != utilisateurConnecte.getNumUtilisateur()) {

                if (this.utilisateurEstSuperUtilisateur(utilisateurConcerne) == false) {

                    if (this.utilisateurEstActif(utilisateurConcerne)) {
                        utilisateurConcerne.setPeutSeConnecter(false);
                    } else {
                        utilisateurConcerne.setPeutSeConnecter(true);
                    }
                    sb.update(utilisateurConcerne);
                } else {
                    if (this.utilisateurEstSuperUtilisateur(utilisateurConnecte)) {
                        if (this.utilisateurEstActif(utilisateurConcerne)) {
                            utilisateurConcerne.setPeutSeConnecter(false);
                        } else {
                            utilisateurConcerne.setPeutSeConnecter(true);
                        }
                        sb.update(utilisateurConcerne);
                    } else {
                        mbG.ajouterMessage("Alerte", "Vous n'etes pas autorise à effectuer cette action !");
                    }
                }
            } else {
                mbG.ajouterMessage("Alerte", "Action sur soi-meme non autorisee !");
            }
        } else {
            mbG.ajouterMessage("Alerte", "Action impossible car vous avez ete desactive pendant cette session !");
        }
    }

    public String libelleBoutonActiverOuDesactiverUtilisateur(Utilisateur utilisateur_) {
        if (this.utilisateurEstActif(utilisateur_)) {
            return "Actif";
        } else {
            return "Inactif";
        }
    }

    public String couleurBoutonActiverOuDesactiverUtilisateur(Utilisateur utilisateur_) {
        if (this.utilisateurEstActif(utilisateur_)) {
            return "lightgreen";
        } else {
            return "red";
        }
    }

    /////////////
    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        return mbG.retournerAuMenuGeneral();
    }

    ///////////
    public boolean validateInputCreationUtiisateurGeneral() {
        if ((nomUtilisateurCree == null)
                || (prenomUtilisateurCree == null)
                || (loginUtilisateurCree == null)
                || (password1UtilisateurCree == null)
                || (password2UtilisateurCree == null)) {
            return false;
        }
        return true;
    }

    public boolean validateInputCreationUtilisateurCentre() {
        if ((numCentreTravail == null)
                || (nomUtilisateurCree == null)
                || (prenomUtilisateurCree == null)
                || (sexeUtilisateurCreeString == null)
                || (loginUtilisateurCree == null)
                || (password1UtilisateurCree == null)
                || (password2UtilisateurCree == null)) {
            return false;
        }
        return true;
    }

    public boolean validateInputCreationGroupe() {
        if (nomGroupeCree == null) {
            return false;
        }
        return true;
    }

    public boolean validateInputCreationSousPrefecture() {
        if ((numSousPrefectureCreee == null)
                || (nomSousPrefectureCreee == null)) {
            return false;
        }
        return true;
    }

    public boolean validateInputCreationCommune() {
        if ((numCommuneCreee == null)
                || (nomCommuneCreee == null)
                || (numSousPrefectureCreee == null)) {
            return false;
        }
        return true;
    }

    public boolean validateInputCreationCentre() {
        if ((numCentreCree == null)
                || (nomCentreCree == null)
                || (numCommuneCreee == null)) {
            return false;
        }
        return true;
    }

    /////////////
    public String libelleBoutonRetourAuMenuAdminGeneral() {
        return "Retour au menu administration générale";
    }

    public String retournerMenuAdminGeneral() {
        return mbG.redirigerUrl("/autre/administrationGenerale/menuAdministrationGenerale.xhtml");
    }

    public String libelleBoutonRetourAuMenuAdminCentre() {
        return "Retour au menu administration de centre";
    }

    public String retournerMenuAdminCentre() {
        return mbG.redirigerUrl("/autre/administrationDeCentre/menuAdministrationDeCentre.xhtml");
    }

    //////////////////
    //////////////////
    /////////////////
    public Utilisateur utilisateurAyantDejaCeLogin(String login) {
        List<Utilisateur> utilisateurs = sb.findByOneCriteria(new Utilisateur(), "login", "'" + login + "'");
        if (!utilisateurs.isEmpty()) {
            return utilisateurs.get(0);
        } else {
            return null;
        }
    }

    public boolean UtilisateursSontDansMemeCentre(String login1, String login2) {
        List<Utilisateur> utilisateurs1 = sb.findByOneCriteria(new Utilisateur(), "login", "'" + login1 + "'");
        if (!utilisateurs1.isEmpty()) {
            Utilisateur u1 = utilisateurs1.get(0);
            List<Utilisateur> utilisateurs2 = sb.findByOneCriteria(new Utilisateur(), "login", "'" + login2 + "'");
            if (!utilisateurs2.isEmpty()) {
                Utilisateur u2 = utilisateurs2.get(0);
                return u1.getCentreTravail().getNumCentre().equals(u2.getCentreTravail().getNumCentre());
            }
        }
        return false;
    }

    public void creerUtilisateur(Centre centreTravail_, String pageToGoto) throws NoSuchAlgorithmException, IOException, MessagingException {

        Date dateActuelle = new Date();

        Utilisateur u = new Utilisateur();

        u.setCentreTravail(centreTravail_);

        u.setNomUtilisateur(nomUtilisateurCree);
        u.setPrenomUtilisateur(prenomUtilisateurCree);
        u.setLogin(loginUtilisateurCree);
        u.setSexeUtilisateur(Integer.parseInt(sexeUtilisateurCreeString));

        u.setPwdInitialementDonneParAdmin(password1UtilisateurCree);
        u.setPwd(mbChiffrement.sha256(password1UtilisateurCree));

        if (emailUtilisateurCree != null && !emailUtilisateurCree.isEmpty()) {
            u.setEmailUtilisateur(emailUtilisateurCree);
        }

        if (telephoneUtilisateurCree != null && !telephoneUtilisateurCree.isEmpty()) {
            u.setTelephoneUtilisateur(telephoneUtilisateurCree);
        }

        u.setARecuMailCreationCompte(false);
        u.setAModifiePwdDonneParAdmin(false);

        u.setPeutSeConnecter(true);
        u.setPeutEtreSupprime(true);
        u.setDateCreationUtilisateur(dateActuelle);
        u.setUtilisateurCreateur(mbCon.getUtilisateurConnecte());

        sb.create(u);
        utilisateurCree = u;

        this.creerAppartenirGroupes();

        mbTrEmails.envoyerMailCreationCompteAUnDestinataire(utilisateurCree);

        this.resultatCreationUtilisateur = 1;
        mbG.redirectionVersUrl(pageToGoto);
    }

    public void modifierUtilisateur(Centre centreTravail_, String pageToGoto) throws NoSuchAlgorithmException, IOException {

        Date dateActuelle = new Date();

        Utilisateur u;
        List<Utilisateur> utilisateurs = sb.findByOneCriteria(new Utilisateur(), "login", "'" + loginUtilisateurCree + "'");
        if (!utilisateurs.isEmpty()) {

            u = utilisateurs.get(0);

            u.setCentreTravail(centreTravail_);

            u.setNomUtilisateur(nomUtilisateurCree);
            u.setPrenomUtilisateur(prenomUtilisateurCree);
            u.setSexeUtilisateur(Integer.parseInt(sexeUtilisateurCreeString));
            u.setLogin(loginUtilisateurCree);
            u.setPwd(mbChiffrement.sha256(password1UtilisateurCree));

            if (emailUtilisateurCree != null && !emailUtilisateurCree.isEmpty()) {
                u.setEmailUtilisateur(emailUtilisateurCree);
            }

            if (telephoneUtilisateurCree != null && !telephoneUtilisateurCree.isEmpty()) {
                u.setTelephoneUtilisateur(telephoneUtilisateurCree);
            }

            u.setDateDerniereModificationUtilisateur(dateActuelle);

            u.setUtilisateurActeurDerniereModification(mbCon.getUtilisateurConnecte());

            sb.update(u);
            utilisateurCree = u;

            sb.deleteWithOneCriteria(new AppartenirGroupe(), "utilisateur", String.valueOf(utilisateurCree.getNumUtilisateur()));

            this.creerAppartenirGroupes();
            this.resultatCreationUtilisateur = 2;
            mbG.redirectionVersUrl(pageToGoto);
        }
    }

    /////
    public void terminerConnexion(Connexion connexion) {
        connexion.setEstActive(false);
        connexion.setDateFin(new Date());
        connexion.setEstTermineeNormalement(0);
        sb.update(connexion);
    }

}
