package managedBeans;

import entities.Connexion;
import entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sessionBeans.SB;

@SessionScoped
@Named("mB_Connexion")
public class MB_Connexion implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Chiffrement mbChiffrement;

    public MB_Connexion() {
    }

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    private Connexion connexion;

    /////////
    private Utilisateur utilisateurConnecte;
    private String nomUtilisateurConnecte;
    private String prenomUtilisateurConnecte;
    private String loginEntrePourConnexion;
    private String passwordEntrePourConnexion;

    private String messageErreurConnexion;

    private boolean finSessionADejaEteAppelee;

    /// getters and setters
    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public void setUtilisateurConnecte(Utilisateur utilisateurConnecte) {
        this.utilisateurConnecte = utilisateurConnecte;
    }

    public String getNomUtilisateurConnecte() {
        return nomUtilisateurConnecte;
    }

    public void setNomUtilisateurConnecte(String nomUtilisateurConnecte) {
        this.nomUtilisateurConnecte = nomUtilisateurConnecte;
    }

    public String getPrenomUtilisateurConnecte() {
        return prenomUtilisateurConnecte;
    }

    public void setPrenomUtilisateurConnecte(String prenomUtilisateurConnecte) {
        this.prenomUtilisateurConnecte = prenomUtilisateurConnecte;
    }

    public String getLoginEntrePourConnexion() {
        return loginEntrePourConnexion;
    }

    public void setLoginEntrePourConnexion(String loginEntrePourConnexion) {
        this.loginEntrePourConnexion = loginEntrePourConnexion;
    }

    public String getPasswordEntrePourConnexion() {
        return passwordEntrePourConnexion;
    }

    public void setPasswordEntrePourConnexion(String passwordEntrePourConnexion) {
        this.passwordEntrePourConnexion = passwordEntrePourConnexion;
    }

    public String getMessageErreurConnexion() {
        return messageErreurConnexion;
    }

    public void setMessageErreurConnexion(String messageErreurConnexion) {
        this.messageErreurConnexion = messageErreurConnexion;
    }

    public boolean isFinSessionADejaEteAppelee() {
        return finSessionADejaEteAppelee;
    }

    public void setFinSessionADejaEteAppelee(boolean finSessionADejaEteAppelee) {
        this.finSessionADejaEteAppelee = finSessionADejaEteAppelee;
    }

    ////////////////CONNEXION DE L'UTILISATEUR
    public boolean verifierSiCredentialsValide() throws NoSuchAlgorithmException, UnknownHostException, SocketException {
        List<Utilisateur> utilisateurs = sb.findByTwoCriteria(new Utilisateur(), "login", "'" + loginEntrePourConnexion + "'", "pwd", "'" + mbChiffrement.sha256(passwordEntrePourConnexion) + "'");
        if (!utilisateurs.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String seConnecterAvecJaasApresVerificationCredentials() throws NoSuchAlgorithmException, UnknownHostException, SocketException, ServletException, IOException {
        if (this.validateInputConnexion()) {

            if (this.verifierSiCredentialsValide() == true) {
                if (this.connexion == null) {
                    LOGGER.info(mbG.defineUserAndIpStringForMessages(loginEntrePourConnexion, mbG.recupererAdresseIP(), "Connexion réussie"));
                    return this.seConnecterAvecJaas();
                } else {
                    if (connexion.getUtilisateur().getLogin().equalsIgnoreCase(loginEntrePourConnexion)) {
                        LOGGER.info(mbG.userAndIpStringForMessages("Connexion répétée"));
                        return mbG.retournerAuMenuGeneral();
                    } else {
                        messageErreurConnexion = "Vous etes sur le point de deconnecter l'utilisateur \"" + this.getUtilisateurConnecte().getLogin() + "\"";
                        LOGGER.info(mbG.userAndIpStringForMessages(messageErreurConnexion));

                        return mbG.redirigerUrl("/deconnexionAutreUtilisateur.xhtml");
                    }
                }
            } else {
                LOGGER.info(mbG.defineUserAndIpStringForMessages(loginEntrePourConnexion, mbG.recupererAdresseIP(), "Erreur de connexions (Parametres non valides)"));
                messageErreurConnexion = "Parametres de connexion non valides";
                return mbG.redirigerUrl(mbG.cheminPageErreurConnexion());
            }
        } else {
            LOGGER.info("Veuillez entrer login et mot de passe !");
            mbG.ajouterMessage("Erreur", "Veuillez entrer login et mot de passe !");
            return null;
        }
    }

    private String seConnecterAvecJaas() throws NoSuchAlgorithmException, UnknownHostException, SocketException, IOException {
        HttpServletRequest request = mbG.getRequest();

        try {
            request.logout();
            LOGGER.info("REQUEST LOGOUT CORRECT");
            request.login(loginEntrePourConnexion, passwordEntrePourConnexion);
            LOGGER.info("REQUEST LOGIN CORRECT");
            List<Utilisateur> utilisateurs = sb.findByOneCriteria(new Utilisateur(), "login", "'" + loginEntrePourConnexion + "'");
            if (!utilisateurs.isEmpty()) {
                utilisateurConnecte = utilisateurs.get(0);
            }

            if (utilisateurConnecte.getPeutSeConnecter()) {
                String adresseIP = this.verifierSiUtilisateurDejaConnecte(utilisateurConnecte.getNumUtilisateur());
                if (adresseIP == null) {
                    this.seConnecter();
                    return mbG.retournerAuMenuGeneral();
                } else {
                    messageErreurConnexion = "Utilisateur deja connecte sur une autre machine (IP:" + adresseIP + ")";
                    LOGGER.info(mbG.userAndIpStringForMessages(messageErreurConnexion));
                    return mbG.redirigerUrl(mbG.cheminPageErreurConnexion());
                }
            } else {
                messageErreurConnexion = "Utilisateur desactive";
                LOGGER.info(mbG.userAndIpStringForMessages(messageErreurConnexion));
                return mbG.redirigerUrl(mbG.cheminPageErreurConnexion());
            }

        } catch (ServletException e) {
            messageErreurConnexion = "Erreur survenue : " + e.getMessage();
            LOGGER.error(messageErreurConnexion);
            return mbG.redirigerUrl(mbG.cheminPageErreurConnexion());
        }
    }

    private void seConnecter() throws NoSuchAlgorithmException, UnknownHostException, SocketException {

        List<Utilisateur> utilisateurs = sb.findByTwoCriteria(new Utilisateur(), "login", "'" + loginEntrePourConnexion + "'", "pwd", "'" + mbChiffrement.sha256(passwordEntrePourConnexion) + "'");
        if (!utilisateurs.isEmpty()) {
            utilisateurConnecte = utilisateurs.get(0);

            this.clearInputTextFields();

            Connexion connexion_ = new Connexion();
            connexion_.setUtilisateur(utilisateurConnecte);
            connexion_.setAdresseIP(this.recupererAdresseIP());
            connexion_.setDateDebut(new Date());
            connexion_.setEstActive(true);
            sb.create(connexion_);
            connexion = connexion_;

            LOGGER.info(mbG.userAndIpStringForMessages("Objet Connexion cree"));
        } else {
            this.clearInputTextFields();
        }

    }/////////////////////

    public String verifierSiUtilisateurDejaConnecte(int numUtilisateur) {
        List<Connexion> connexions = sb.connexionsActivesSurAutresMachinesPourLUtilisateurConnecte(this.getUtilisateurConnecte());
        if (!connexions.isEmpty()) {
            LOGGER.info(mbG.defineUserAndIpStringForMessages(connexions.get(0).getUtilisateur().getLogin(), connexions.get(0).getAdresseIP(), "Utilisateur deja connecte"));
            return connexions.get(0).getAdresseIP();
        } else {
            return null;
        }
    }

    //////////////////
    public boolean verifierSiConnexionActuelleEstActive(Connexion connexion_) {
        List<Connexion> connexions = sb.findByTwoCriteria(new Connexion(), "numConnexion", String.valueOf(connexion_.getNumConnexion()), "estActive", "1");
        if (!connexions.isEmpty()) {
            return true;
        } else {
            mbG.fermerSession();
            return false;
        }
    }

    //////////////////
    public String verifierSiUtilisateurConnecteAModifiePwdDonneParAdmin() {
        if (this.utilisateurConnecte.getAModifiePwdDonneParAdmin()) {
            return mbG.retournerAuMenuGeneral();
        } else {
            return mbG.redirigerUrl("/changementPasswordPersonnel/modificationMotDePasseParUtilisateur.xhtml");
        }
    }
    //////

    public String recupererAdresseIP() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    //////////////////
    public String retournerALoginPage() throws ServletException {
        this.clearInputTextFields();
        mbG.getRequest().logout();
        return mbG.loginPage();
    }

    //////////////////////
    public String recupererAdresseIPOtherWay() throws UnknownHostException {
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

    public String recupererAdresseMacServer() throws UnknownHostException, SocketException {
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] mac = network.getHardwareAddress();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }

        return sb.toString();
    }
////////////////////////////////////////

    public String recupererIdSession() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        return session.getId();
    }

    ///////////////
    public void clearInputTextFields() {

        nomUtilisateurConnecte = null;
        prenomUtilisateurConnecte = null;
        loginEntrePourConnexion = null;
        passwordEntrePourConnexion = null;
    }

    public boolean validateInputConnexion() {
        if (loginEntrePourConnexion == null
                || passwordEntrePourConnexion == null) {
            return false;
        }
        return true;
    }
}
