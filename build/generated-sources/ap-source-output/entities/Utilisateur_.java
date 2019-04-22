package entities;

import entities.AppartenirGroupe;
import entities.Centre;
import entities.Connexion;
import entities.DemandeEnCoursDUtilisation;
import entities.Evoluer;
import entities.PlageRecus;
import entities.Recu;
import entities.Utilisateur;
import entities.Varier;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, String> telephoneUtilisateur;
    public static volatile SingularAttribute<Utilisateur, Date> dateDerniereModificationPwd;
    public static volatile SingularAttribute<Utilisateur, Date> dateCreationUtilisateur;
    public static volatile ListAttribute<Utilisateur, Recu> recuList;
    public static volatile SingularAttribute<Utilisateur, Date> datePremiereModificationPwd;
    public static volatile SingularAttribute<Utilisateur, Integer> numUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> login;
    public static volatile ListAttribute<Utilisateur, Varier> varierList;
    public static volatile SingularAttribute<Utilisateur, Integer> sexeUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> pwdInitialementDonneParAdmin;
    public static volatile SingularAttribute<Utilisateur, Boolean> peutSeConnecter;
    public static volatile ListAttribute<Utilisateur, Connexion> connexionList;
    public static volatile SingularAttribute<Utilisateur, Boolean> aRecuMailCreationCompte;
    public static volatile ListAttribute<Utilisateur, Utilisateur> utilisateurList;
    public static volatile SingularAttribute<Utilisateur, Boolean> aModifiePwdDonneParAdmin;
    public static volatile ListAttribute<Utilisateur, DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList;
    public static volatile SingularAttribute<Utilisateur, String> nomUtilisateur;
    public static volatile SingularAttribute<Utilisateur, Centre> centreTravail;
    public static volatile SingularAttribute<Utilisateur, Boolean> peutEtreSupprime;
    public static volatile SingularAttribute<Utilisateur, String> prenomUtilisateur;
    public static volatile ListAttribute<Utilisateur, Utilisateur> utilisateurList1;
    public static volatile ListAttribute<Utilisateur, AppartenirGroupe> appartenirGroupeList;
    public static volatile ListAttribute<Utilisateur, Evoluer> evoluerList;
    public static volatile ListAttribute<Utilisateur, PlageRecus> plageRecusList;
    public static volatile SingularAttribute<Utilisateur, Utilisateur> utilisateurCreateur;
    public static volatile SingularAttribute<Utilisateur, String> emailUtilisateur;
    public static volatile SingularAttribute<Utilisateur, Date> dateDerniereModificationUtilisateur;
    public static volatile SingularAttribute<Utilisateur, String> pwd;
    public static volatile SingularAttribute<Utilisateur, Utilisateur> utilisateurActeurDerniereModification;

}