package entities;

import entities.DocumentFourni;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Etat.class)
public class Etat_ { 

    public static volatile SingularAttribute<Etat, String> nomEtat;
    public static volatile ListAttribute<Etat, DocumentFourni> documentFourniList;
    public static volatile SingularAttribute<Etat, Integer> numEtat;

}