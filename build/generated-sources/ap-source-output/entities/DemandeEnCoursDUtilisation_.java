package entities;

import entities.Connexion;
import entities.TypeDemande;
import entities.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(DemandeEnCoursDUtilisation.class)
public class DemandeEnCoursDUtilisation_ { 

    public static volatile SingularAttribute<DemandeEnCoursDUtilisation, Utilisateur> utilisateur;
    public static volatile SingularAttribute<DemandeEnCoursDUtilisation, String> numDemandeEnCoursDUtilisation;
    public static volatile SingularAttribute<DemandeEnCoursDUtilisation, Date> dateDebutUtilisation;
    public static volatile SingularAttribute<DemandeEnCoursDUtilisation, Connexion> connexion;
    public static volatile SingularAttribute<DemandeEnCoursDUtilisation, TypeDemande> typeDemande;

}