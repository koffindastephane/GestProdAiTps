package entities;

import entities.Demande;
import entities.Evoluer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile ListAttribute<Status, Demande> demandeList;
    public static volatile SingularAttribute<Status, Integer> numStatus;
    public static volatile SingularAttribute<Status, String> nomStatus;
    public static volatile ListAttribute<Status, Evoluer> evoluerList;

}