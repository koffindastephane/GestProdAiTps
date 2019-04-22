package entities;

import entities.Centre;
import entities.SousPrefecture;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Commune.class)
public class Commune_ { 

    public static volatile SingularAttribute<Commune, String> nomCommune;
    public static volatile SingularAttribute<Commune, String> numCommune;
    public static volatile SingularAttribute<Commune, SousPrefecture> sousPrefecture;
    public static volatile ListAttribute<Commune, Centre> centreList;

}