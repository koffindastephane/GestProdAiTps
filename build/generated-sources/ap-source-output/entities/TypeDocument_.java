package entities;

import entities.DocumentFourni;
import entities.DocumentNecessaire;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(TypeDocument.class)
public class TypeDocument_ { 

    public static volatile SingularAttribute<TypeDocument, Integer> numTypeDocument;
    public static volatile ListAttribute<TypeDocument, DocumentNecessaire> documentNecessaireList;
    public static volatile SingularAttribute<TypeDocument, String> nomTypeDocument;
    public static volatile ListAttribute<TypeDocument, DocumentFourni> documentFourniList;

}