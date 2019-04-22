package entities;

import entities.CasDemande;
import entities.Demande;
import entities.Petitionnaire;
import entities.PlageRecus;
import entities.TypeDemande;
import entities.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Recu.class)
public class Recu_ { 

    public static volatile SingularAttribute<Recu, Petitionnaire> petitionnaire;
    public static volatile SingularAttribute<Recu, Integer> numRecu;
    public static volatile SingularAttribute<Recu, String> natureRecette;
    public static volatile SingularAttribute<Recu, Boolean> aEteGenere;
    public static volatile SingularAttribute<Recu, Integer> numeroRecu;
    public static volatile SingularAttribute<Recu, Date> dateVersement;
    public static volatile SingularAttribute<Recu, PlageRecus> plageRecus;
    public static volatile SingularAttribute<Recu, Demande> demande;
    public static volatile SingularAttribute<Recu, TypeDemande> typeDemande;
    public static volatile SingularAttribute<Recu, Integer> montantPaye;
    public static volatile SingularAttribute<Recu, CasDemande> casDemande;
    public static volatile SingularAttribute<Recu, Utilisateur> utilisateurCreateur;

}