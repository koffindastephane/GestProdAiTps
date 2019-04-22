package entities;

import entities.AvoirRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, Integer> numRole;
    public static volatile ListAttribute<Role, AvoirRole> avoirRoleList;
    public static volatile SingularAttribute<Role, String> nomRole;

}