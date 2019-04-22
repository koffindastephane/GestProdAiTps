package entities;

import entities.Correspondre;
import entities.DemandeEnCoursDUtilisation;
import entities.DocumentNecessaire;
import entities.Recu;
import entities.Varier;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(TypeDemande.class)
public class TypeDemande_ { 

    public static volatile SingularAttribute<TypeDemande, Date> dateEntreeEnVigueur;
    public static volatile SingularAttribute<TypeDemande, String> symboleActuel;
    public static volatile SingularAttribute<TypeDemande, Integer> numTypeDemande;
    public static volatile SingularAttribute<TypeDemande, String> nomTypeDemande;
    public static volatile ListAttribute<TypeDemande, Recu> recuList;
    public static volatile ListAttribute<TypeDemande, DocumentNecessaire> documentNecessaireList;
    public static volatile SingularAttribute<TypeDemande, Integer> tarifActuel;
    public static volatile ListAttribute<TypeDemande, Correspondre> correspondreList;
    public static volatile ListAttribute<TypeDemande, DemandeEnCoursDUtilisation> demandeEnCoursDUtilisationList;
    public static volatile ListAttribute<TypeDemande, Varier> varierList;
    public static volatile SingularAttribute<TypeDemande, Integer> dureeValiditeActuel;

}