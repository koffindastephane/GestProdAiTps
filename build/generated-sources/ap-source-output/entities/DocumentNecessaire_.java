package entities;

import entities.CasDemande;
import entities.TypeDemande;
import entities.TypeDocument;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(DocumentNecessaire.class)
public class DocumentNecessaire_ { 

    public static volatile SingularAttribute<DocumentNecessaire, Integer> numDocumentNecessaire;
    public static volatile SingularAttribute<DocumentNecessaire, TypeDocument> typeDocument;
    public static volatile SingularAttribute<DocumentNecessaire, TypeDemande> typeDemande;
    public static volatile SingularAttribute<DocumentNecessaire, CasDemande> casDemande;

}