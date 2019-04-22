package managedBeans;

import entities.Connexion;
import entities.Demande;
import entities.DemandeEnCoursDUtilisation;
import entities.Evoluer;
import entities.Recu;
import entities.Status;
import entities.TypeDemande;
import entities.Utilisateur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sessionBeans.SB;

@SessionScoped
@Named("mB_Global")
public class MB_Global implements Serializable {

    @EJB
    private SB sb;

    @Inject
    MB_Connexion mbCon;

    @Inject
    MB_GestionFichiers mbGf;

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /////////////////////////////
    private final String dossierUser = System.getProperty("user.home");
    private final String dossierTempFiles = System.getProperty("java.io.tmpdir");

    private final String dossierNetbeans = dossierUser + "/Documents/NetBeansProjects";
    private final String nomProjet = "GestProdAiTps";
    private final String dossierWeb = dossierNetbeans + "/" + nomProjet + "/web";
    ////////////////
    private final String typeFichier = "jpeg";
    private final String nomSourcePhotoFinal = "photo";

    private final String dossierExterne = "C:/dossierExterneGestProdAiTps";

    private final String dossierPhotos = dossierExterne + "/photos";

    private final String dossierPhotosDocFournis = dossierExterne + "/docsFournis";
    private final String dossierPhotosTemp = dossierPhotos + "/temp";
    private final String dossierPhotosFinal = dossierPhotos + "/final";

    ///////////////////
    public SB getSb() {
        return sb;
    }

    public Logger getLOGGER() {
        return LOGGER;
    }

    public String getDossierUser() {
        return dossierUser;
    }

    public String getDossierTempFiles() {
        return dossierTempFiles;
    }

    public String getDossierNetbeans() {
        return dossierNetbeans;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public String getDossierWeb() {
        return dossierWeb;
    }

    public String getTypeFichier() {
        return typeFichier;
    }

    public String getNomSourcePhotoFinal() {
        return nomSourcePhotoFinal;
    }

    public String getDossierExterne() {
        return dossierExterne;
    }

    public String getDossierPhotos() {
        return dossierPhotos;
    }

    public String getDossierPhotosDocFournis() {
        return dossierPhotosDocFournis;
    }

    public String getDossierPhotosTemp() {
        return dossierPhotosTemp;
    }

    public String getDossierPhotosFinal() {
        return dossierPhotosFinal;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static String getEMAIL_PATTERN() {
        return EMAIL_PATTERN;
    }

    /////////////////////
    public MB_Global() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public HttpSession getSession() {
        HttpSession session
                = this
                .getRequest()
                .getSession();
        if (session == null) {
            throw new RuntimeException("Sorry. Got a null session from faces context");
        }
        return session;
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
        if (request == null) {
            throw new RuntimeException("Sorry. Got a null request from faces context");
        }
        return request;
    }

    public String cheminPageErreurDemandeEnCoursDUtilisation() {
        return "erreurDemandeEnCoursDUtilisation.xhtml";
    }

    public String cheminPageErreurConnexion() {
        return "/erreurConnexion.xhtml";
    }

    public String ecrireEnMaj(String mot) {
        if (!mot.isEmpty()) {
            return mot.toUpperCase().trim();
        } else {
            return null;
        }
    }

    public Evoluer changerStatusDemande(Demande demande, int niveauAvancement) {
        Evoluer e = new Evoluer();

        List<Status> statuss = sb.findByOneCriteria(new Status(), "numStatus", String.valueOf(niveauAvancement));

        if (!statuss.isEmpty()) {
            // ignores multiple results
            Status s = statuss.get(0);

            Date dateActuelle = new Date();

            demande.setDateStatusActuel(dateActuelle);
            demande.setStatusActuel(s);
            sb.update(demande);

            e.setDemande(demande);
            e.setStatus(s);
            e.setUtilisateur(mbCon.getUtilisateurConnecte());
            e.setAdresseIP(mbCon.getConnexion().getAdresseIP());

            e.setDates(dateActuelle);

            sb.create(e);

        }
        return e;
    }

    public boolean evaluerStatusDemande(Demande demande, int numStatus) {
        List<Evoluer> evoluers = sb.findByTwoCriteria(new Evoluer(), "demande", "'" + demande.getNumDemande() + "'", "status", String.valueOf(numStatus));
        if (!evoluers.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /////////////////////////
    public Evoluer recupererEvoluerFinTraitement(String numDemande, int numStatus) {
        List<Evoluer> evoluers = sb.findByTwoCriteria(new Evoluer(), "demande", "'" + numDemande + "'", "status", String.valueOf(numStatus));
        if (!evoluers.isEmpty()) {
            return evoluers.get(0);
        } else {
            return null;
        }
    }

////////////
    public String recupererAdresseIP() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    //////////////////
    public String retournerAuMenuGeneral() {

        if (mbCon.getUtilisateurConnecte().getAModifiePwdDonneParAdmin()) {
            this.supprimerEventuellementDemandeEnCoursDUtilisationParUnUtilisateur();
            return this.redirigerUrl("/accesApplication/menuGeneral.xhtml");
        } else {
            return this.redirigerUrl("/changementPasswordPersonnel/modificationMotDePasseParUtilisateur.xhtml");
        }
    }

    public String retournerARechercheDemande() {
        this.supprimerEventuellementDemandeEnCoursDUtilisationParUnUtilisateur();
        return this.redirigerUrl("rechercheDemande.xhtml");
    }

    public String redirigerUrl(String url) {
        return url + "?faces-redirect=true";
    }

    /*  
     @PostConstruct
     public void initialiserValeurApresDemarrageServeur() {
        
     /////// désactiver toutes les connexions (même celles qui étaient réstées actives)
     sb.desactiverToutesLesConnexions();
     }
     */
    public void invaliderSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    @PreDestroy
    public void fermerSession() {

        if (!mbCon.isFinSessionADejaEteAppelee()) {

            if (mbCon.getConnexion() != null) {

                LOGGER.info("Fermeture de session en cours pour utilisateur \"" + mbCon.getConnexion().getUtilisateur().getLogin() + "\"");

                Connexion connexion = mbCon.getConnexion();

                connexion.setDateFin(new Date());
                connexion.setEstActive(false);
                sb.update(connexion);

                LOGGER.info("Fin de la connexion n°" + connexion.getNumConnexion() + " (DateFin indiquée, estActive=false)  Connexion terminée");

                this.supprimerEventuellementDemandeEnCoursDUtilisationParUnUtilisateur();
                this.supprimerArborescencePhotoDeLUtilisateurConnecte();

                // Pour recus crée et non générés par cette utilisateur
                List<Recu> recus = sb.findByTwoCriteria(new Recu(), "utilisateurCreateur", String.valueOf(mbCon.getUtilisateurConnecte().getNumUtilisateur()), "aEteGenere", "0");
                if (!recus.isEmpty()) {
                    for (int i = 0; i < recus.size(); i++) {
                        sb.delete(recus.get(i));
                    }
                }

                try {
                    LOGGER.info(this.userAndIpStringForMessages("Fermeture de session réussie"));
                    mbCon.setFinSessionADejaEteAppelee(true);
                    this.getRequest().logout();
                    this.invaliderSession();
                    this.redirectionVersUrl(this.urlContextApp());
                } catch (IOException | ServletException e) {
                    LOGGER.error(this.userAndIpStringForMessages(e.getClass() + " lors de la fermeture de session: " + e.getMessage()));
                    mbCon.setFinSessionADejaEteAppelee(true);
                    this.invaliderSession();
                }
            } else if (mbCon.getUtilisateurConnecte() == null) {
                try {
                    LOGGER.info("Fermeture de session réussie. L'objet Connexion était null.");
                    mbCon.setFinSessionADejaEteAppelee(true);
                    this.getRequest().logout();
                    this.invaliderSession();
                    this.redirectionVersUrl(this.urlContextApp());
                } catch (IOException | ServletException e) {
                    LOGGER.error(this.userAndIpStringForMessages(e.getClass() + " lors de la fermeture de session: " + e.getMessage()));
                    mbCon.setFinSessionADejaEteAppelee(true);
                    this.invaliderSession();
                }
            }
        }
    }

    public String urlResourceFolder() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String requestURL = request.getRequestURL().toString();
        String url = requestURL.substring(0, requestURL.lastIndexOf("/"));
        return url;
    }

    public String urlContextApp() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        String url = extContext.getRequestScheme()
                + "://" + extContext.getRequestServerName()
                + ":" + extContext.getRequestServerPort()
                + extContext.getRequestContextPath();
        return url;
    }

    public String cheminEntete() {
        return this.urlContextApp() + "/resources/default/images/enteteOni.png";
    }

    public String cheminAi() {
        return this.urlContextApp() + "/resources/default/images/imageAi.jpg";
    }

    public String cheminTps() {
        return this.urlContextApp() + "/resources/default/images/imageTps.jpg";
    }

    public String cheminAppareilPhoto() {
        return this.urlContextApp() + "/resources/default/images/bouton_appareil_photo.png";
    }

    public String scriptCss() {
        return "css/style.css";
    }

    public String scriptJavascript() {
        return "js/script.js";
    }

    public String scriptPrimefacesLocales() {
        return "js/primefacesLocales.js";
    }

    ////////////////
    public void redirectionVersUrl(String url) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + "?faces-redirect=true");
        FacesContext.getCurrentInstance().responseComplete();
    }

    /////////////////////////
    public String recupererVariableEnvironnement(String var) {
        return System.getenv(var);
    }

/////////////////////////////////////////////////////////////////
    public String libelleBoutonRetourMenuGeneral() {
        return "Retour au Menu Général";
    }

    public String libelleBoutonRetourRechercheDemande() {
        return "Retour à Recheche demande";
    }

    public String libelleBoutonConsulterDemandeDispo() {
        return "Consulter la liste des demandes disponibles pour traitement";
    }

    public void messageDemandeNonTrouvee() {
        this.ajouterMessage("info", "Demande non trouvée");
    }

    public void messageAutreTypeDemandeTrouvee(TypeDemande typeDemande_) {
        this.ajouterMessage("info", "Demande non trouvee.\nCe numéro correspond à une demande de " + typeDemande_.getSymboleActuel());
    }

    //////////////////
    public Date recupererDateActuelle() {
        return new Date();
    }

    public Date recupererDateActuellePlusNAnnees(int nombreAnnees) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.YEAR, nombreAnnees); // ajouter n annee(s)

        return cal.getTime();
    }

    public Date recupererDateActuellePlusNAnneesNMois(int nombreAnnees, int nombreMois) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.YEAR, nombreAnnees); // ajouter n annee(s)
        cal.add(Calendar.MONTH, nombreMois); // ajouter n mois

        return cal.getTime();
    }

    public Date recupererDateActuellePlusNAnneesNMoisNJours(int nombreAnnees, int nombreMois, int nombreJours) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.YEAR, nombreAnnees); // ajouter n annee(s)
        cal.add(Calendar.MONTH, nombreMois); // ajouter n mois        
        cal.add(Calendar.HOUR_OF_DAY, nombreJours * 24); // ajouter n jours

        return cal.getTime();
    }

    //////////
    public Date recupererDateActuellePlusNMois(int nombreMois) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.MONTH, nombreMois); // ajouter n mois

        return cal.getTime();
    }

    public Date recupererDateActuellePlusNMoisNjours(int nombreMois, int nombreJours) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.MONTH, nombreMois); // ajouter n mois
        cal.add(Calendar.HOUR_OF_DAY, nombreJours * 24); // ajouter n jours

        return cal.getTime();
    }

    //////////
    public Date recupererDateActuellePlusNjours(int nombreJours) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, nombreJours * 24); // ajouter n jours

        return cal.getTime();
    }

    private Date datePlusNjours(Date date_, int nombreJours) {

        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(date_); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, nombreJours * 24); // ajouter n jours

        return cal.getTime();
    }

    public Date recupererDatePlusNjoursOuvres(Date date_, int nombreJoursOuvres) throws ParseException {

        nombreJoursOuvres++;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDebut = sdf.parse(sdf.format(date_));
        Date dateFin = datePlusNjours(dateDebut, nombreJoursOuvres);;

        do {
            nombreJoursOuvres = nombreJoursOuvres - nbJours(dateDebut, dateFin, true, true //lundi
                    , true // mardi
                    , true // mercredi
                    , true // jeudi
                    , true // vendredi
                    , false // samedi
                    , false // dimanche
            );

            dateDebut = datePlusNjours(dateFin, 1);
            dateFin = datePlusNjours(dateFin, nombreJoursOuvres);

        } while (dateDebut.before(dateFin));

        do {
            dateFin = datePlusNjours(dateFin, 1);
        } while (verifierSiJourDeWeekend(dateFin)
                || verifierSiJourFerie(dateFin.getYear(), dateFin));

        return dateFin;
    }

    private boolean verifierSiJourDeWeekend(Date date_) {
        if (date_.getDay() == 6 || date_.getDay() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifierSiJourFerie(int year, Date date_) {
        for (Object jourFerie : getJourFeries(year)) {
            if (((Date) jourFerie).getYear() == date_.getYear()
                    && ((Date) jourFerie).getMonth() == date_.getMonth()
                    && ((Date) jourFerie).getDate() == date_.getDate()) {
                return true;
            }
        }
        return false;
    }

//////// Détermination des jours ouvres
    private int nbJours(Date d1, Date d2, boolean notionJourFerie,
            boolean priseCompteLundi, boolean priseCompteMardi,
            boolean priseCompteMercredi, boolean priseCompteJeudi,
            boolean priseCompteVendredi, boolean priseCompteSamedi,
            boolean priseCompteDimanche) {

        if (d2.compareTo(d1) <= 0) {
            return 0;
        }

        // Tableau des jours a prendre en compte
        Boolean[] joursPrisEncompte = new Boolean[]{priseCompteDimanche,
            priseCompteLundi, priseCompteMardi, priseCompteMercredi,
            priseCompteJeudi, priseCompteVendredi, priseCompteSamedi};

        GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(d1);
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(d2);

        // Récupération des jours fériés
        List joursFeries = new ArrayList();
        for (int i = date1.get(GregorianCalendar.YEAR); i <= date2
                .get(GregorianCalendar.YEAR); i++) {
            joursFeries.addAll(getJourFeries(i));
        }

        // Calcul du nombre de jour
        int nbJour = 0;
        while (date1.before(date2) || date1.equals(date2)) {
            if (!notionJourFerie || !joursFeries.contains(date1.getTime())) {
                if (joursPrisEncompte[date1.get(GregorianCalendar.DAY_OF_WEEK) - 1]) {
                    nbJour++;
                }
            }

            date1.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }

        return nbJour;
    }

// les 2 fonctions utilisées pour le calculs des jours fériés :
    private List getJourFeries(int annee) {
        List datesFeries = new ArrayList();

        // A retiter
        GregorianCalendar aRetirer = new GregorianCalendar(annee, 7, 20);
        datesFeries.add(aRetirer.getTime());

        // Jour de l'an
        GregorianCalendar jourAn = new GregorianCalendar(annee, 0, 1);
        datesFeries.add(jourAn.getTime());

        // Lundi de pacques
        GregorianCalendar pacques = calculLundiPacques(annee);
        datesFeries.add(pacques.getTime());

        // Fete du travail (1er mai)
        GregorianCalendar premierMai = new GregorianCalendar(annee, 4, 1);
        datesFeries.add(premierMai.getTime());

        // Ascension (= pâques + 38 jours)
        GregorianCalendar ascension = new GregorianCalendar(annee,
                pacques.get(GregorianCalendar.MONTH),
                pacques.get(GregorianCalendar.DAY_OF_MONTH));
        ascension.add(GregorianCalendar.DAY_OF_MONTH, 38);
        datesFeries.add(ascension.getTime());

        // Pentecôte (= pâques + 49 jours)
        GregorianCalendar pentecote = new GregorianCalendar(annee,
                pacques.get(GregorianCalendar.MONTH),
                pacques.get(GregorianCalendar.DAY_OF_MONTH));
        pentecote.add(GregorianCalendar.DAY_OF_MONTH, 49);
        datesFeries.add(pentecote.getTime());

        // Fête Nationale (7 août)
        GregorianCalendar septAout = new GregorianCalendar(annee, 7, 7);
        datesFeries.add(septAout.getTime());

        // Assomption
        GregorianCalendar assomption = new GregorianCalendar(annee, 7, 15);
        datesFeries.add(assomption.getTime());

        // La Toussaint
        GregorianCalendar toussaint = new GregorianCalendar(annee, 10, 1);
        datesFeries.add(toussaint.getTime());

        // Noël
        GregorianCalendar noel = new GregorianCalendar(annee, 11, 25);
        datesFeries.add(noel.getTime());

        return datesFeries;
    }

    private GregorianCalendar calculLundiPacques(int annee) {
        int a = annee / 100;
        int b = annee % 100;
        int c = (3 * (a + 25)) / 4;
        int d = (3 * (a + 25)) % 4;
        int e = (8 * (a + 11)) / 25;
        int f = (5 * a + b) % 19;
        int g = (19 * f + c - e) % 30;
        int h = (f + 11 * g) / 319;
        int j = (60 * (5 - d) + b) / 4;
        int k = (60 * (5 - d) + b) % 4;
        int m = (2 * j - k - g + h) % 7;
        int n = (g - h + m + 114) / 31;
        int p = (g - h + m + 114) % 31;
        int jour = p + 1;
        int mois = n;

        GregorianCalendar date = new GregorianCalendar(annee, mois - 1, jour);
        date.add(GregorianCalendar.DAY_OF_MONTH, 1);
        return date;
    }

    ///////////
    public int agePetitionnaireEnAnneesCiviles(Date dateNaissance) {
        Calendar cal1 = Calendar.getInstance(); // creer le calendar
        cal1.setTime(new Date()); // sets calendar time/date
        Calendar cal2 = Calendar.getInstance(); // creer le calendar
        cal2.setTime(dateNaissance); // sets calendar time/date
        return cal1.getTime().getYear() - cal2.getTime().getYear();
    }

    public String intervalDateDuCalendrier(int anneeDebutSouhaitee, int ageMinimumSouhaite) {
        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(new Date()); // sets calendar time/date
        int debut = (cal.getTime().getYear() + 1900) - anneeDebutSouhaitee;
        int fin = ageMinimumSouhaite;

        return "-" + debut + ":-" + fin;
    }

    public String dateMinimalDuCalendrierPourEnrolement() {
        return "01/01/1900";
    }

    public String dateMinimalDuCalendrierPourStatistiques() {
        return "01/07/2014";
    }

    public int recupererAnneeDansDate(Date date_) {
        Calendar cal = Calendar.getInstance(); // creer le calendar
        cal.setTime(date_); // sets calendar time/date
        return cal.getTime().getYear() + 1900;
    }

    ///////////
    ////////////////////////
    // Confirmer Succes
    public void ajouterMessageInfo(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, facesMsg);
    }

    ///////////
    public String requiredMessage() {
        return "**";
    }

    public String loginPage() {
        return this.redirigerUrl("/loginForm.xhtml");
    }

    public String firstPage() {
        return this.redirigerUrl("/menuGeneral.xhtml");
    }

    public String dossierRecusGeneres() {
        return dossierExterne + "/Rapports/RecusGeneres";
    }

    public String dossierTitresGeneres() {
        return dossierExterne + "/Rapports/TitresGeneres";
    //    return dossierTempFiles;
    }

    /////////////
    public String cheminFichierJrxml() {
        return dossierWeb + "/caisse/recu.jrxml";
    }

/////////////
    public Connection connectionBddAiTps() throws SQLException, FileNotFoundException, IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("jdbc_mysql.properties");
        Properties prop = new Properties();
        prop.load(is);
        return DriverManager.getConnection(prop.getProperty("DB_URL"), prop.getProperty("DB_USERNAME"), prop.getProperty("DB_PASSWORD"));
    }

    /////////
    private final Pattern pattern;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean validerEmailCorrectOuNonRenseigne(String email_) {
        Matcher matcher;
        if (email_ != null) {
            if (!email_.isEmpty()) {
                matcher = pattern.matcher(email_);
                return matcher.matches();
            }
        }
        return true;
    }

    /////////////////////
    public boolean verifierSiDemandeEnCoursUtilisationParUnAutreUtilisateur(String numDemande_, int numTypeDemande_) {
        LOGGER.info(this.userAndIpStringForMessages("Début vérif si demande n°" + numDemande_ + "en cours utilisation par autre utilisateur"));
        List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisations = sb.findByTwoCriteria(new DemandeEnCoursDUtilisation(), "numDemandeEnCoursDUtilisation", "'" + numDemande_ + "'", "typeDemande", String.valueOf(numTypeDemande_));
        if (!demandeEnCoursDUtilisations.isEmpty()) {
            Utilisateur utilisateur_ = demandeEnCoursDUtilisations.get(0).getConnexion().getUtilisateur();
            if (!(utilisateur_ == mbCon.getConnexion().getUtilisateur())) {
                LOGGER.info(this.userAndIpStringForMessages("Fin vérif si demande n°" + numDemande_ + "en cours utilisation par autre utilisateur (positif)"));
                return true;
            } else {
                LOGGER.info(this.userAndIpStringForMessages("Fin vérif si demande n°" + numDemande_ + "en cours utilisation par autre utilisateur (négatif)"));
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean verifierSiDemandeEnCoursUtilisationParUnUtilisateur() {
        List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisations = sb.findByOneCriteria(new DemandeEnCoursDUtilisation(), "utilisateur", String.valueOf(mbCon.getUtilisateurConnecte().getNumUtilisateur()));
        if (!demandeEnCoursDUtilisations.isEmpty()) {
            LOGGER.info(this.userAndIpStringForMessages("vérif: Demande (n°" + demandeEnCoursDUtilisations.get(0).getNumDemandeEnCoursDUtilisation() + " en cours d'utilisation par cet utilisateur"));
            return true;
        } else {
            LOGGER.info(this.userAndIpStringForMessages("vérif: Aucune demande n'est en cours d'utilisation par cet utilisateur"));
            return false;
        }
    }

    public void supprimerEventuellementDemandeEnCoursDUtilisationParUnUtilisateur() {
        DemandeEnCoursDUtilisation demandeEnCoursDUtilisation;
        if (this.verifierSiDemandeEnCoursUtilisationParUnUtilisateur() == true) {
            List<DemandeEnCoursDUtilisation> demandeEnCoursDUtilisations = sb.findByOneCriteria(new DemandeEnCoursDUtilisation(), "utilisateur", String.valueOf(mbCon.getUtilisateurConnecte().getNumUtilisateur()));
            if (!demandeEnCoursDUtilisations.isEmpty()) {
                demandeEnCoursDUtilisation = demandeEnCoursDUtilisations.get(0);
                LOGGER.info(this.userAndIpStringForMessages("Début suppression de vérou sur demandeEnCoursDUtilisation n°" + demandeEnCoursDUtilisation.getNumDemandeEnCoursDUtilisation()));
                sb.delete(demandeEnCoursDUtilisation);
                LOGGER.info(this.userAndIpStringForMessages("Fin suppression de vérou (demandeEnCoursDUtilisation n°" + demandeEnCoursDUtilisation.getNumDemandeEnCoursDUtilisation() + ") (positif)"));
            } else {
                LOGGER.info(this.userAndIpStringForMessages("Pas de vérou exercé sur une demande par cet utilisateur"));
            }
        }
    }

    public boolean verifierSiUtilisateurDejaConnecte(int numUtilisateur) {
        List<Connexion> connexions = sb.findByTwoCriteria(new Connexion(), "utilisateur", "'" + numUtilisateur + "'", "estActive", "1");
        if (!connexions.isEmpty()) {
            LOGGER.info(this.defineUserAndIpStringForMessages(connexions.get(0).getUtilisateur().getLogin(), connexions.get(0).getAdresseIP(), "Utilisateur pas encore connecté"));
            return true;
        } else {
            LOGGER.info(this.userAndIpStringForMessages("Utilisateur déjà connecté"));
            return false;
        }
    }

    /////////////////:
    public void ajouterMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //////////////
    public boolean supprimerArborescencePhotoDeLUtilisateurConnecte() {
        //   LOGGER.info(this.userAndIpStringForMessages("Début suppression arborescence photo de l'utilisateur"));
        String arborescenceAEffacer = this.getDossierPhotosTemp() + mbCon.getConnexion().getUtilisateur().getLogin() + "/";
        if (mbGf.effacerArborescenceRepertoiresEtFichiers(new File(arborescenceAEffacer))) {
            //       LOGGER.info(this.userAndIpStringForMessages("Fin suppression arborescence photo de l'utilisateur (positif)"));
            return true;
        } else {
            //      LOGGER.info(this.userAndIpStringForMessages("Fin suppression arborescence photo de l'utilisateur (négatif)"));
            return false;
        }
    }

    //////////
    public String couleurMessagePassageNiveauSuivant() {
        return "darkgreen";
    }

    public String defineUserAndIpStringForMessages(String userLogin, String ipAddress, String message) {
        return "(User:" + userLogin + "/Ip:" + ipAddress + ")  => " + message;
    }

    public String userAndIpStringForMessages(String message) {
        return this.defineUserAndIpStringForMessages(mbCon.getUtilisateurConnecte().getLogin(), this.recupererAdresseIP(), message);
    }

    ///////////////////
    public String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }

    //////////////
    public boolean verifierSiConnexionInternet() {
        try {
            URL url = new URL("http://www.google.com");

            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    ///////////
    public Properties chargerFichierProprietes(String fichierProprietes) throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(fichierProprietes);
        Properties prop = new Properties();
        prop.load(is);
        return prop;
    }

    ////////////
    public String retournerNullSiChampVide(String champ) {
        if (champ != null) {
            if (!champ.isEmpty()) {
                return champ;
            }
        }
        return null;
    }

    ////////////
    public String couleurSiChampsNonRenseigne(String champ) {
        if (champ != null) {
            if (!champ.isEmpty()) {
                return "";
            }
        }
        return "lightgrey";
    }

}
