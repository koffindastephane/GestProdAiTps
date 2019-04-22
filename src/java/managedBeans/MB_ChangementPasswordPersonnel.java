/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import sessionBeans.SB;

@Named("mB_ChangementPasswordPersonnel")
@SessionScoped
public class MB_ChangementPasswordPersonnel implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Chiffrement mbChiffrement;

    @Inject
    MB_Connexion mbCon;

    public MB_ChangementPasswordPersonnel() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    ////////////////
    private String ancienPasswordUtilisateur;
    private String nouveauPassword1Utilisateur;
    private String nouveauPassword2Utilisateur;

    ///////////
    public String getAncienPasswordUtilisateur() {
        return ancienPasswordUtilisateur;
    }

    public void setAncienPasswordUtilisateur(String ancienPasswordUtilisateur) {
        this.ancienPasswordUtilisateur = ancienPasswordUtilisateur;
    }

    public String getNouveauPassword1Utilisateur() {
        return nouveauPassword1Utilisateur;
    }

    public void setNouveauPassword1Utilisateur(String nouveauPassword1Utilisateur) {
        this.nouveauPassword1Utilisateur = nouveauPassword1Utilisateur;
    }

    public String getNouveauPassword2Utilisateur() {
        return nouveauPassword2Utilisateur;
    }

    public void setNouveauPassword2Utilisateur(String nouveauPassword2Utilisateur) {
        this.nouveauPassword2Utilisateur = nouveauPassword2Utilisateur;
    }

    //////////////////////
    public void modifierPasswordUtilisateur() throws NoSuchAlgorithmException, IOException {

        if (this.validateInputModificationPasswordUtilisateur()) {

            Date dateActuelle = new Date();

            Utilisateur u = mbCon.getUtilisateurConnecte();

            if ((mbChiffrement.sha256(ancienPasswordUtilisateur)).equals(u.getPwd())) {
                if (nouveauPassword1Utilisateur.equals(nouveauPassword2Utilisateur)) {

                    u.setPwd(mbChiffrement.sha256(nouveauPassword1Utilisateur));
                    
                    if(u.getAModifiePwdDonneParAdmin() == false) {
                    u.setAModifiePwdDonneParAdmin(true);
                    u.setDatePremiereModificationPwd(dateActuelle);
                    }

                    u.setDateDerniereModificationPwd(dateActuelle);
                    
                    u.setDateDerniereModificationUtilisateur(dateActuelle);

                    u.setUtilisateurActeurDerniereModification(u);

                    sb.update(u);

                    mbG.redirectionVersUrl("resultatModificationPasswordUtilisateur.xhtml");

                } else {
                    mbG.ajouterMessage("Erreur", "Erreur de confirmation du nouveau mot de passe");
                }
            } else {
                mbG.ajouterMessage("Erreur", "Ancien mot de passe errone");
            }
        }
    }

    ////////////
    public void clearInputTextFields() {

        ancienPasswordUtilisateur = null;
        nouveauPassword1Utilisateur = null;
        nouveauPassword2Utilisateur = null;

    }

    /////////////
    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        return mbG.retournerAuMenuGeneral();
    }

    ///////////
    public boolean validateInputModificationPasswordUtilisateur() {
        if ((ancienPasswordUtilisateur == null)
                || (nouveauPassword1Utilisateur == null)
                || (nouveauPassword2Utilisateur == null)) {
            return false;
        }
        return true;
    }
}
