package entities;

import entities.Centre;
import entities.Recu;
import entities.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(PlageRecus.class)
public class PlageRecus_ { 

    public static volatile SingularAttribute<PlageRecus, Integer> numeroFinPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Integer> nombreRecusUtilises;
    public static volatile ListAttribute<PlageRecus, Recu> recuList;
    public static volatile SingularAttribute<PlageRecus, Date> dateCreationPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Integer> statusPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Date> dateDerniereUtilisationPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Date> dateFinPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Integer> numeroDebutPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Centre> centrePaiement;
    public static volatile SingularAttribute<PlageRecus, Integer> numPlageRecus;
    public static volatile SingularAttribute<PlageRecus, Utilisateur> utilisateurCreateur;

}