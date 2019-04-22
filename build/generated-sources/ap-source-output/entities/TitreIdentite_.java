package entities;

import entities.Demande;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(TitreIdentite.class)
public class TitreIdentite_ { 

    public static volatile SingularAttribute<TitreIdentite, Integer> numTitreIdentite;
    public static volatile SingularAttribute<TitreIdentite, Date> dateRetraitTitreIdentite;
    public static volatile SingularAttribute<TitreIdentite, Boolean> aEteGenere;
    public static volatile SingularAttribute<TitreIdentite, Boolean> mailFinProductionAEteEnvoyeAvecSucces;
    public static volatile SingularAttribute<TitreIdentite, String> numeroTitreIdentite;
    public static volatile SingularAttribute<TitreIdentite, Date> dateEtablissementTitreIdentite;
    public static volatile SingularAttribute<TitreIdentite, Demande> demande;
    public static volatile SingularAttribute<TitreIdentite, Date> dateExpirationTitreIdentite;

}