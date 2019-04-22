package entities;

import entities.CasDemande;
import entities.TypeDemande;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Correspondre.class)
public class Correspondre_ { 

    public static volatile SingularAttribute<Correspondre, Integer> nbJoursPourRdv;
    public static volatile SingularAttribute<Correspondre, Integer> numCorrespondre;
    public static volatile SingularAttribute<Correspondre, TypeDemande> typeDemande;
    public static volatile SingularAttribute<Correspondre, CasDemande> casDemande;

}