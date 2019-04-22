package entities;

import entities.AppartenirGroupe;
import entities.AvoirRole;
import entities.NiveauAdministration;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Groupe.class)
public class Groupe_ { 

    public static volatile SingularAttribute<Groupe, String> nomGroupe;
    public static volatile SingularAttribute<Groupe, NiveauAdministration> niveauAdministration;
    public static volatile SingularAttribute<Groupe, Integer> numGroupe;
    public static volatile ListAttribute<Groupe, AvoirRole> avoirRoleList;
    public static volatile ListAttribute<Groupe, AppartenirGroupe> appartenirGroupeList;

}