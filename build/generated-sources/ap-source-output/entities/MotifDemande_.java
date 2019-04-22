package entities;

import entities.MotiverDemande;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(MotifDemande.class)
public class MotifDemande_ { 

    public static volatile SingularAttribute<MotifDemande, String> nomMotifDemande;
    public static volatile SingularAttribute<MotifDemande, Integer> numMotifDemande;
    public static volatile ListAttribute<MotifDemande, MotiverDemande> motiverDemandeList;

}