package entities;

import entities.TypeDemande;
import entities.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Varier.class)
public class Varier_ { 

    public static volatile SingularAttribute<Varier, Integer> numVarier;
    public static volatile SingularAttribute<Varier, Integer> tarif;
    public static volatile SingularAttribute<Varier, Utilisateur> utilisateur;
    public static volatile SingularAttribute<Varier, String> symbole;
    public static volatile SingularAttribute<Varier, Date> dates;
    public static volatile SingularAttribute<Varier, String> adresseIP;
    public static volatile SingularAttribute<Varier, Integer> dureeValidite;
    public static volatile SingularAttribute<Varier, TypeDemande> typeDemande;

}