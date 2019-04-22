package entities;

import entities.Recu;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Petitionnaire.class)
public class Petitionnaire_ { 

    public static volatile SingularAttribute<Petitionnaire, String> prenomsPere;
    public static volatile ListAttribute<Petitionnaire, Recu> recuList;
    public static volatile SingularAttribute<Petitionnaire, Date> dateNaissance;
    public static volatile SingularAttribute<Petitionnaire, String> numeroJugementSuppletif;
    public static volatile SingularAttribute<Petitionnaire, String> telephone3;
    public static volatile SingularAttribute<Petitionnaire, String> telephone2;
    public static volatile SingularAttribute<Petitionnaire, Integer> sexe;
    public static volatile SingularAttribute<Petitionnaire, String> telephone1;
    public static volatile SingularAttribute<Petitionnaire, Date> dateNaissancePere;
    public static volatile SingularAttribute<Petitionnaire, String> adressePhysique;
    public static volatile SingularAttribute<Petitionnaire, String> adressePostale;
    public static volatile SingularAttribute<Petitionnaire, String> nomMere;
    public static volatile SingularAttribute<Petitionnaire, String> nom;
    public static volatile SingularAttribute<Petitionnaire, String> prenoms;
    public static volatile SingularAttribute<Petitionnaire, String> lieuNaissance;
    public static volatile SingularAttribute<Petitionnaire, String> lieuNaissanceMere;
    public static volatile SingularAttribute<Petitionnaire, String> nationaliteMere;
    public static volatile SingularAttribute<Petitionnaire, Date> dateNaissanceMere;
    public static volatile SingularAttribute<Petitionnaire, String> numeroExtraitNaissance;
    public static volatile SingularAttribute<Petitionnaire, String> email;
    public static volatile SingularAttribute<Petitionnaire, String> numIdentifiantUnique;
    public static volatile SingularAttribute<Petitionnaire, String> nationalite;
    public static volatile SingularAttribute<Petitionnaire, String> profession;
    public static volatile SingularAttribute<Petitionnaire, String> prenomsMere;
    public static volatile SingularAttribute<Petitionnaire, String> nomPere;
    public static volatile SingularAttribute<Petitionnaire, Integer> numPetitionnaire;
    public static volatile SingularAttribute<Petitionnaire, Integer> taille;
    public static volatile SingularAttribute<Petitionnaire, String> lieuNaissancePere;
    public static volatile SingularAttribute<Petitionnaire, String> nationalitePere;
    public static volatile SingularAttribute<Petitionnaire, String> domicile;

}