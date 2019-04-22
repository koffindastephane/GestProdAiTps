package entities;

import entities.Centre;
import entities.DocumentFourni;
import entities.Evoluer;
import entities.Photo;
import entities.Rdv;
import entities.Recu;
import entities.Status;
import entities.TitreIdentite;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Demande.class)
public class Demande_ { 

    public static volatile SingularAttribute<Demande, Date> dateStatusActuel;
    public static volatile SingularAttribute<Demande, Date> dateCreationDemande;
    public static volatile ListAttribute<Demande, DocumentFourni> documentFourniList;
    public static volatile SingularAttribute<Demande, Photo> photo;
    public static volatile ListAttribute<Demande, Evoluer> evoluerList;
    public static volatile ListAttribute<Demande, Rdv> rdvList;
    public static volatile SingularAttribute<Demande, String> numDemande;
    public static volatile SingularAttribute<Demande, Integer> docJustificatifNais;
    public static volatile SingularAttribute<Demande, Recu> recu;
    public static volatile SingularAttribute<Demande, Centre> centreTraitement;
    public static volatile ListAttribute<Demande, TitreIdentite> titreIdentiteList;
    public static volatile SingularAttribute<Demande, Status> statusActuel;
    public static volatile SingularAttribute<Demande, Date> dateFinEnregistrementDemande;
    public static volatile SingularAttribute<Demande, String> motifRejet;

}