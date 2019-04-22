package entities;

import entities.Groupe;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(NiveauAdministration.class)
public class NiveauAdministration_ { 

    public static volatile SingularAttribute<NiveauAdministration, String> nomNiveauAdministration;
    public static volatile ListAttribute<NiveauAdministration, Groupe> groupeList;
    public static volatile SingularAttribute<NiveauAdministration, Integer> numNiveauAdministration;

}