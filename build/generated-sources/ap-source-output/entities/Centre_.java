package entities;

import entities.Commune;
import entities.Demande;
import entities.PlageRecus;
import entities.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Centre.class)
public class Centre_ { 

    public static volatile SingularAttribute<Centre, String> numCentre;
    public static volatile ListAttribute<Centre, Demande> demandeList;
    public static volatile SingularAttribute<Centre, Integer> valeurCompteurTps;
    public static volatile SingularAttribute<Centre, Commune> commune;
    public static volatile SingularAttribute<Centre, Integer> valeurCompteurAi;
    public static volatile SingularAttribute<Centre, String> nomCentre;
    public static volatile ListAttribute<Centre, PlageRecus> plageRecusList;
    public static volatile ListAttribute<Centre, Utilisateur> utilisateurList;

}