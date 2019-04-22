package entities;

import entities.CasDemande;
import entities.MotifDemande;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(MotiverDemande.class)
public class MotiverDemande_ { 

    public static volatile SingularAttribute<MotiverDemande, Integer> numMotiverDemande;
    public static volatile SingularAttribute<MotiverDemande, MotifDemande> motifDemande;
    public static volatile SingularAttribute<MotiverDemande, CasDemande> casDemande;

}