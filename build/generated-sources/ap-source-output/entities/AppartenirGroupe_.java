package entities;

import entities.Groupe;
import entities.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(AppartenirGroupe.class)
public class AppartenirGroupe_ { 

    public static volatile SingularAttribute<AppartenirGroupe, Utilisateur> utilisateur;
    public static volatile SingularAttribute<AppartenirGroupe, Groupe> groupe;
    public static volatile SingularAttribute<AppartenirGroupe, Integer> numAppartenirGroupe;

}