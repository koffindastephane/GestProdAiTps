package managedBeans;

import entities.Demande;
import entities.Petitionnaire;
import entities.TitreIdentite;
import entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import sessionBeans.SB;

@Named("mB_TraitementsEmails")
@SessionScoped
public class MB_TraitementsEmails implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    public MB_TraitementsEmails() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    //////////
    //////////
    private String messageSurEnvoiDuMailCreationCompte;
    /////////
    private Integer nbMailsCreationCompteAEnvoyerInitial;
    private Integer nbMailsCreationCompteAEnvoyerFinal;
    private Integer nbMailsCreationCompteFinalementEnvoyes;

    //////////
    private Integer nbMailsCreationCompteEnAttenteAvecEmailUtilisateur;
    private Integer nbMailsCreationCompteNonEnvoyablesCarSansEmail;
    private Integer nbTotalUtilisateurPasRecuMailCreationCompte;

    //////////
    //////////
    private String messageSurEnvoiDuMailFinProductionTitre;
    /////////
    private Integer nbMailsFinProductionTitreAEnvoyerInitial;
    private Integer nbMailsFinProductionTitreAEnvoyerFinal;
    private Integer nbMailsFinProductionTitreFinalementEnvoyes;

    //////////
    private Integer nbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur;
    private Integer nbMailsFinProductionTitreNonEnvoyablesCarSansEmail;
    private Integer nbTotalMailsFinProductionTitreNonEnvoyes;

    //////////
    private Session session;

    /////////////////////////////
    public String getMessageSurEnvoiDuMailCreationCompte() {
        return messageSurEnvoiDuMailCreationCompte;
    }

    public void setMessageSurEnvoiDuMailCreationCompte(String messageSurEnvoiDuMailCreationCompte) {
        this.messageSurEnvoiDuMailCreationCompte = messageSurEnvoiDuMailCreationCompte;
    }

    public Integer getNbMailsCreationCompteAEnvoyerInitial() {
        return nbMailsCreationCompteAEnvoyerInitial;
    }

    public void setNbMailsCreationCompteAEnvoyerInitial(Integer nbMailsCreationCompteAEnvoyerInitial) {
        this.nbMailsCreationCompteAEnvoyerInitial = nbMailsCreationCompteAEnvoyerInitial;
    }

    public Integer getNbMailsCreationCompteAEnvoyerFinal() {
        return nbMailsCreationCompteAEnvoyerFinal;
    }

    public void setNbMailsCreationCompteAEnvoyerFinal(Integer nbMailsCreationCompteAEnvoyerFinal) {
        this.nbMailsCreationCompteAEnvoyerFinal = nbMailsCreationCompteAEnvoyerFinal;
    }

    public Integer getNbMailsCreationCompteFinalementEnvoyes() {
        return nbMailsCreationCompteFinalementEnvoyes;
    }

    public void setNbMailsCreationCompteFinalementEnvoyes(Integer nbMailsCreationCompteFinalementEnvoyes) {
        this.nbMailsCreationCompteFinalementEnvoyes = nbMailsCreationCompteFinalementEnvoyes;
    }

    public Integer getNbMailsCreationCompteEnAttenteAvecEmailUtilisateur() {
        return nbMailsCreationCompteEnAttenteAvecEmailUtilisateur;
    }

    public void setNbMailsCreationCompteEnAttenteAvecEmailUtilisateur(Integer nbMailsCreationCompteEnAttenteAvecEmailUtilisateur) {
        this.nbMailsCreationCompteEnAttenteAvecEmailUtilisateur = nbMailsCreationCompteEnAttenteAvecEmailUtilisateur;
    }

    public Integer getNbMailsCreationCompteNonEnvoyablesCarSansEmail() {
        return nbMailsCreationCompteNonEnvoyablesCarSansEmail;
    }

    public void setNbMailsCreationCompteNonEnvoyablesCarSansEmail(Integer nbMailsCreationCompteNonEnvoyablesCarSansEmail) {
        this.nbMailsCreationCompteNonEnvoyablesCarSansEmail = nbMailsCreationCompteNonEnvoyablesCarSansEmail;
    }

    public Integer getNbTotalUtilisateurPasRecuMailCreationCompte() {
        return nbTotalUtilisateurPasRecuMailCreationCompte;
    }

    public void setNbTotalUtilisateurPasRecuMailCreationCompte(Integer nbTotalUtilisateurPasRecuMailCreationCompte) {
        this.nbTotalUtilisateurPasRecuMailCreationCompte = nbTotalUtilisateurPasRecuMailCreationCompte;
    }

    public String getMessageSurEnvoiDuMailFinProductionTitre() {
        return messageSurEnvoiDuMailFinProductionTitre;
    }

    public void setMessageSurEnvoiDuMailFinProductionTitre(String messageSurEnvoiDuMailFinProductionTitre) {
        this.messageSurEnvoiDuMailFinProductionTitre = messageSurEnvoiDuMailFinProductionTitre;
    }

    public Integer getNbMailsFinProductionTitreAEnvoyerInitial() {
        return nbMailsFinProductionTitreAEnvoyerInitial;
    }

    public void setNbMailsFinProductionTitreAEnvoyerInitial(Integer nbMailsFinProductionTitreAEnvoyerInitial) {
        this.nbMailsFinProductionTitreAEnvoyerInitial = nbMailsFinProductionTitreAEnvoyerInitial;
    }

    public Integer getNbMailsFinProductionTitreAEnvoyerFinal() {
        return nbMailsFinProductionTitreAEnvoyerFinal;
    }

    public void setNbMailsFinProductionTitreAEnvoyerFinal(Integer nbMailsFinProductionTitreAEnvoyerFinal) {
        this.nbMailsFinProductionTitreAEnvoyerFinal = nbMailsFinProductionTitreAEnvoyerFinal;
    }

    public Integer getNbMailsFinProductionTitreFinalementEnvoyes() {
        return nbMailsFinProductionTitreFinalementEnvoyes;
    }

    public void setNbMailsFinProductionTitreFinalementEnvoyes(Integer nbMailsFinProductionTitreFinalementEnvoyes) {
        this.nbMailsFinProductionTitreFinalementEnvoyes = nbMailsFinProductionTitreFinalementEnvoyes;
    }

    public Integer getNbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur() {
        return nbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur;
    }

    public void setNbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur(Integer nbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur) {
        this.nbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur = nbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur;
    }

    public Integer getNbMailsFinProductionTitreNonEnvoyablesCarSansEmail() {
        return nbMailsFinProductionTitreNonEnvoyablesCarSansEmail;
    }

    public void setNbMailsFinProductionTitreNonEnvoyablesCarSansEmail(Integer nbMailsFinProductionTitreNonEnvoyablesCarSansEmail) {
        this.nbMailsFinProductionTitreNonEnvoyablesCarSansEmail = nbMailsFinProductionTitreNonEnvoyablesCarSansEmail;
    }

    public Integer getNbTotalMailsFinProductionTitreNonEnvoyes() {
        return nbTotalMailsFinProductionTitreNonEnvoyes;
    }

    public void setNbTotalMailsFinProductionTitreNonEnvoyes(Integer nbTotalMailsFinProductionTitreNonEnvoyes) {
        this.nbTotalMailsFinProductionTitreNonEnvoyes = nbTotalMailsFinProductionTitreNonEnvoyes;
    }

    ///////////////////////
    public Properties creerPropertiesSmtp() {
        Properties propsSmtp = new Properties();

        propsSmtp.put("mail.smtp.auth", "true");
        propsSmtp.put("mail.smtp.starttls.enable", "true");
        propsSmtp.put("mail.smtp.host", "smtp.gmail.com");
        propsSmtp.put("mail.smtp.port", "587");

        return propsSmtp;
    }

    public Properties creerPropertiesCredentials() {
        Properties propsCredentials = new Properties();
        propsCredentials.put("username", "moncompteapplication");
        propsCredentials.put("password", "Application");

        return propsCredentials;
    }

    public Session creerSession() {

        if (this.session == null) {
            Properties proprietesSmtp = this.creerPropertiesSmtp();
            Properties proprietesCredentials = this.creerPropertiesCredentials();

            Session session_ = Session.getInstance(proprietesSmtp,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(proprietesCredentials.getProperty("username"), proprietesCredentials.getProperty("password"));
                        }
                    });
            this.session = session_;
        }
        return this.session;
    }

    public Message creerMail(String sender, String receiver, String subject, String messageText, List<String> attachedDocumentsPaths) throws MessagingException {
        Message message = new MimeMessage(this.creerSession());
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receiver));
        message.setSubject(subject);

        /////////////////////////////
        if (!attachedDocumentsPaths.isEmpty()) {
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(messageText);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            for (int i = 0; i < attachedDocumentsPaths.size(); i++) {
                messageBodyPart = new MimeBodyPart();
                String filename = attachedDocumentsPaths.get(i);
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }

            // Send the complete message parts
            message.setContent(multipart);
        } else {
            message.setText(messageText);
        }

        return message;
    }

    ///////////////////////////////////////////
    ////// Mails Creation Compte 
    public Message creerMailCreationCompteUtilisateur(Utilisateur u) throws MessagingException {
        return this.creerMail("moncompteapplication@gmail.com", u.getEmailUtilisateur(), "Creation compte utilisateur pour l'application GestProdCni",
                this.contenuDuMailPourUnUtilisateur(u), new ArrayList());
    }

    public String contenuDuMailPourUnUtilisateur(Utilisateur u) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "Bonjour " + (u.getSexeUtilisateur() == 1 ? "Mr " : "Mme ") + u.getNomUtilisateur() + " "
                + u.getPrenomUtilisateur() + ".\n\n"
                + "Votre compte utilisateur pour l'application GestProdCni a été créé"
                + (u.getDateCreationUtilisateur() != null ? " le " + sdf.format(u.getDateCreationUtilisateur()) : "") + ".\n\n"
                + "Pour vous connecter, veuillez utiliser les paramètres suivants:\n"
                + "Login: " + u.getLogin() + "\n"
                + "Mot de passe: " + u.getPwdInitialementDonneParAdmin() + " (A utiliser seulement pour la première connexion).\n"
                + "A bientôt !";
    }

    ///////////////
    public void envoyerMailCreationCompteAUnDestinataire(Utilisateur u) throws MessagingException {

        if (u.getEmailUtilisateur() != null && !u.getEmailUtilisateur().isEmpty()) {
            if (mbG.verifierSiConnexionInternet()) {
                Session session_ = this.creerSession();
                Transport transport = session_.getTransport("smtp");
                transport.connect();

                Message message;

                try {
                    message = this.creerMailCreationCompteUtilisateur(u);
                    transport.sendMessage(message, message.getAllRecipients());
                    u.setARecuMailCreationCompte(true);
                    sb.update(u);

                    messageSurEnvoiDuMailCreationCompte = "Envoyé avec succes";

                } catch (MessagingException e) {
                    messageSurEnvoiDuMailCreationCompte = "Erreur survenue  lors de l'envoi (MessagingException)";
                }
            } else {
                messageSurEnvoiDuMailCreationCompte = "Ajouté à la liste d'attente car aucune connexion internet detectée";
            }
        } else {
            messageSurEnvoiDuMailCreationCompte = "Ne peut être envoyé car E-mail de l'utilisateur non renseigné)";
        }

        LOGGER.info(mbG.userAndIpStringForMessages(messageSurEnvoiDuMailCreationCompte));
    }

    public String envoyerMailsCreationCompteAPlusieursDestinataires(List<Utilisateur> utilisateurs) throws MessagingException, IOException {

        nbMailsCreationCompteAEnvoyerInitial = sb.utilisateursAyantUnEmailEtNAyantPasRecuMailCreationCompte().size();

        if (!utilisateurs.isEmpty()) {
            if (mbG.verifierSiConnexionInternet()) {
                Session session_ = this.creerSession();
                Transport transport = session_.getTransport("smtp");
                transport.connect();

                Message message;
                for (Utilisateur u : utilisateurs) {
                    try {
                        message = this.creerMailCreationCompteUtilisateur(u);
                        transport.sendMessage(message, message.getAllRecipients());
                        u.setARecuMailCreationCompte(true);
                        sb.update(u);

                    } catch (MessagingException e) {
                        nbMailsCreationCompteAEnvoyerFinal = sb.utilisateursAyantUnEmailEtNAyantPasRecuMailCreationCompte().size();
                        nbMailsCreationCompteFinalementEnvoyes = nbMailsCreationCompteAEnvoyerInitial - nbMailsCreationCompteAEnvoyerFinal;
                        LOGGER.info(mbG.userAndIpStringForMessages("Erreur survenue  lors de l'envoi du mail " + (nbMailsCreationCompteFinalementEnvoyes + 1) + " (total envoye = "
                                + nbMailsCreationCompteFinalementEnvoyes + "/" + nbMailsCreationCompteAEnvoyerInitial));
                        return mbG.redirigerUrl("/emails/resultatEnvoiMailsCreationCompteEnAttente.xhtml");
                    }
                }
                nbMailsCreationCompteAEnvoyerFinal = sb.utilisateursAyantUnEmailEtNAyantPasRecuMailCreationCompte().size();
                nbMailsCreationCompteFinalementEnvoyes = nbMailsCreationCompteAEnvoyerInitial - nbMailsCreationCompteAEnvoyerFinal;
                LOGGER.info(mbG.userAndIpStringForMessages("Les " + nbMailsCreationCompteFinalementEnvoyes + " mails en attente ont ete envoyes avec succes"));
                // transport.close();
                return mbG.redirigerUrl("/emails/resultatEnvoiMailsCreationCompteEnAttente.xhtml");
            } else {
                mbG.ajouterMessage("Erreur", "Aucune connexion internet detectee (NB: Au moins un mail en attente d'envoi)");
                return null;
            }
        } else {
            mbG.ajouterMessage("Info", "Aucun mail de création de compte en attente d'envoi");
            return null;
        }
    }

    public String envoyerMailsCreationCompteEnAttenteAuxDestinataires() throws IOException, MessagingException {

        List<Utilisateur> utilisateurs = sb.utilisateursAyantUnEmailEtNAyantPasRecuMailCreationCompte();

        return this.envoyerMailsCreationCompteAPlusieursDestinataires(utilisateurs);
    }

    public String consulterBilanMailsCreationCompte() {

        nbMailsCreationCompteEnAttenteAvecEmailUtilisateur = sb.utilisateursAyantUnEmailEtNAyantPasRecuMailCreationCompte().size();
        nbMailsCreationCompteNonEnvoyablesCarSansEmail = sb.utilisateursNAyantPasDEmailEtNAyantPasRecuMailCreationCompte().size();
        nbTotalUtilisateurPasRecuMailCreationCompte = sb.utilisateursNAyantPasRecuMailCreationCompte().size();

        return mbG.redirigerUrl("/emails/bilanMailsCreationCompte.xhtml");
    }

    ///////////////////////////////////////////
    ////// Mails Fin Production Titre
    public Message creerMailFinProductionTitre(TitreIdentite titreIdentite) throws MessagingException {
        return this.creerMail("moncompteapplication@gmail.com", titreIdentite.getDemande().getRecu().getPetitionnaire().getEmail(), WordUtils.capitalize(titreIdentite.getDemande().getRecu().getTypeDemande().getNomTypeDemande().toLowerCase()) + " : Fin de production",
                this.contenuDuMailFinProductionTitre(titreIdentite), new ArrayList());
    }

    public String contenuDuMailFinProductionTitre(TitreIdentite titreIdentite) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Bonjour " + titreIdentite.getDemande().getRecu().getPetitionnaire().getNom().toLowerCase() + " "
                + titreIdentite.getDemande().getRecu().getPetitionnaire().getPrenoms().toLowerCase() + " ,\n\n"
                + "Votre " + titreIdentite.getDemande().getRecu().getTypeDemande().getNomTypeDemande().toLowerCase()
                + " est disponible pour retrait au centre suivant: " + titreIdentite.getDemande().getCentreTraitement().getNomCentre().toLowerCase() + "\n"
                + "Date d'établissement: " + sdf.format(titreIdentite.getDateEtablissementTitreIdentite()) + "\n"
                + "Date d'expiration: " + sdf.format(titreIdentite.getDateExpirationTitreIdentite()) + "\n\n"
                + "A bientôt !";
    }

    ///////////////
    public void envoyerMailFinProductionTitrePourUnTitre(TitreIdentite titreIdentite) throws MessagingException {

        if (titreIdentite.getDemande().getRecu().getPetitionnaire().getEmail() != null && !titreIdentite.getDemande().getRecu().getPetitionnaire().getEmail().isEmpty()) {
            if (mbG.verifierSiConnexionInternet()) {
                Session session_ = this.creerSession();
                Transport transport = session_.getTransport("smtp");
                transport.connect();

                Message message;

                try {
                    message = this.creerMailFinProductionTitre(titreIdentite);
                    transport.sendMessage(message, message.getAllRecipients());
                    titreIdentite.setMailFinProductionAEteEnvoyeAvecSucces(true);
                    sb.update(titreIdentite);

                    messageSurEnvoiDuMailFinProductionTitre = "Envoyé avec succes";

                } catch (MessagingException e) {
                    messageSurEnvoiDuMailFinProductionTitre = "Erreur survenue  lors de l'envoi (MessagingException)";
                }
            } else {
                messageSurEnvoiDuMailFinProductionTitre = "Ajouté à la liste d'attente car aucune connexion internet detectée";
            }
        } else {
            messageSurEnvoiDuMailFinProductionTitre = "Ne peut être envoyé car E-mail de l'utilisateur non renseigné)";
        }

        LOGGER.info(mbG.userAndIpStringForMessages(messageSurEnvoiDuMailFinProductionTitre));
    }

    public String envoyerMailsFinProductionTitrePourPlusieursTitres(List<TitreIdentite> titreIdentites) throws MessagingException, IOException {

        nbMailsFinProductionTitreAEnvoyerInitial = sb.titresAvecPetitionnairesAyantUnEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces().size();

        if (!titreIdentites.isEmpty()) {
            if (mbG.verifierSiConnexionInternet()) {
                Session session_ = this.creerSession();
                Transport transport = session_.getTransport("smtp");
                transport.connect();

                Message message;
                for (TitreIdentite ti : titreIdentites) {
                    try {
                        message = this.creerMailFinProductionTitre(ti);
                        transport.sendMessage(message, message.getAllRecipients());
                        ti.setMailFinProductionAEteEnvoyeAvecSucces(true);
                        sb.update(ti);

                    } catch (MessagingException e) {
                        nbMailsFinProductionTitreAEnvoyerFinal = sb.titresAvecPetitionnairesAyantUnEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces().size();
                        nbMailsFinProductionTitreFinalementEnvoyes = nbMailsFinProductionTitreAEnvoyerInitial - nbMailsFinProductionTitreAEnvoyerFinal;
                        LOGGER.info(mbG.userAndIpStringForMessages("Erreur survenue  lors de l'envoi du mail " + (nbMailsFinProductionTitreFinalementEnvoyes + 1) + " (total envoye = "
                                + nbMailsFinProductionTitreFinalementEnvoyes + "/" + nbMailsFinProductionTitreAEnvoyerInitial));
                        return mbG.redirigerUrl("/emails/resultatEnvoiMailsFinProductionTitreEnAttente.xhtml");
                    }
                }
                nbMailsFinProductionTitreAEnvoyerFinal = sb.titresAvecPetitionnairesAyantUnEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces().size();
                nbMailsFinProductionTitreFinalementEnvoyes = nbMailsFinProductionTitreAEnvoyerInitial - nbMailsFinProductionTitreAEnvoyerFinal;
                LOGGER.info(mbG.userAndIpStringForMessages("Les " + nbMailsFinProductionTitreFinalementEnvoyes + " mails de fin de production de titre en attente ont ete envoyes avec succes"));
                // transport.close();
                return mbG.redirigerUrl("/emails/resultatEnvoiMailsFinProductionTitreEnAttente.xhtml");
            } else {
                mbG.ajouterMessage("Erreur", "Aucune connexion internet detectee (NB: Au moins un mail de fin de production de titre en attente d'envoi)");
                return null;
            }
        } else {
            mbG.ajouterMessage("Info", "Aucun mail fin de production de titre en attente de d'envoi");
            return null;
        }
    }

    public String envoyerMailsFinProductionTitrePourLesTitres() throws IOException, MessagingException {

        List<TitreIdentite> titreIdentites = sb.titresAvecPetitionnairesAyantUnEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces();

        return this.envoyerMailsFinProductionTitrePourPlusieursTitres(titreIdentites);
    }

    public String consulterBilanMailsFinProductionTitre() {

        nbMailsFinProductionTitreEnAttenteAvecEmailUtilisateur = sb.titresAvecMailsFinProductionTitreNonEnvoyesAvecSucces().size();
        nbMailsFinProductionTitreNonEnvoyablesCarSansEmail = sb.titresAvecPetitionnairesNAyantPasDEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces().size();
        nbTotalMailsFinProductionTitreNonEnvoyes = sb.titresAvecMailsFinProductionTitreNonEnvoyesAvecSucces().size();

        return mbG.redirigerUrl("/emails/bilanMailsFinProductionTitre.xhtml");
    }

    public void clearInputTextFields() {

        messageSurEnvoiDuMailCreationCompte = null;
        /////////
        nbMailsCreationCompteAEnvoyerInitial = null;
        nbMailsCreationCompteAEnvoyerFinal = null;
        nbMailsCreationCompteFinalementEnvoyes = null;

        //////////
        nbMailsCreationCompteEnAttenteAvecEmailUtilisateur = null;
        nbMailsCreationCompteNonEnvoyablesCarSansEmail = null;
        nbTotalUtilisateurPasRecuMailCreationCompte = null;
    }

}
