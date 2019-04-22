package entities;

import entities.Correspondre;
import entities.DocumentNecessaire;
import entities.MotiverDemande;
import entities.Recu;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(CasDemande.class)
public class CasDemande_ { 

    public static volatile SingularAttribute<CasDemande, Integer> numCasDemande;
    public static volatile SingularAttribute<CasDemande, String> nomCasDemande;
    public static volatile ListAttribute<CasDemande, Recu> recuList;
    public static volatile ListAttribute<CasDemande, DocumentNecessaire> documentNecessaireList;
    public static volatile ListAttribute<CasDemande, Correspondre> correspondreList;
    public static volatile ListAttribute<CasDemande, MotiverDemande> motiverDemandeList;

}