package entities;

import entities.Demande;
import entities.Status;
import entities.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Evoluer.class)
public class Evoluer_ { 

    public static volatile SingularAttribute<Evoluer, Integer> numEvoluer;
    public static volatile SingularAttribute<Evoluer, Utilisateur> utilisateur;
    public static volatile SingularAttribute<Evoluer, Date> dates;
    public static volatile SingularAttribute<Evoluer, String> adresseIP;
    public static volatile SingularAttribute<Evoluer, Demande> demande;
    public static volatile SingularAttribute<Evoluer, Status> status;

}