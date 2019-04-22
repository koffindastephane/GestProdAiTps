package entities;

import entities.DemandeEnCoursDUtilisation;
import entities.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Connexion.class)
public class Connexion_ { 

    public static volatile SingularAttribute<Connexion, Boolean> estActive;
    public static volatile SingularAttribute<Connexion, Integer> estTermineeNormalement;
    public static volatile SingularAttribute<Connexion, Date> dateDebut;
    public static volatile SingularAttribute<Connexion, Utilisateur> utilisateur;
    public static volatile SingularAttribute<Connexion, Integer> numConnexion;
    public static volatile ListAttribute<Connexion, DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList;
    public static volatile SingularAttribute<Connexion, String> adresseIP;
    public static volatile SingularAttribute<Connexion, Date> dateFin;

}