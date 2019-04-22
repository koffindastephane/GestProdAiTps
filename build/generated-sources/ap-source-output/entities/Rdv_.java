package entities;

import entities.Demande;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Rdv.class)
public class Rdv_ { 

    public static volatile SingularAttribute<Rdv, Date> dateRdv;
    public static volatile SingularAttribute<Rdv, Integer> numRdv;
    public static volatile SingularAttribute<Rdv, Demande> demande;

}