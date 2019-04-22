package entities;

import entities.Demande;
import entities.Etat;
import entities.Photo;
import entities.TypeDocument;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(DocumentFourni.class)
public class DocumentFourni_ { 

    public static volatile SingularAttribute<DocumentFourni, Date> dateEntreePays;
    public static volatile SingularAttribute<DocumentFourni, Integer> numDocumentFourni;
    public static volatile SingularAttribute<DocumentFourni, Date> dateDelivrance;
    public static volatile SingularAttribute<DocumentFourni, Photo> photo;
    public static volatile SingularAttribute<DocumentFourni, TypeDocument> typeDocument;
    public static volatile SingularAttribute<DocumentFourni, String> numeroDocumentFourni;
    public static volatile SingularAttribute<DocumentFourni, Demande> demande;
    public static volatile SingularAttribute<DocumentFourni, Date> datePeremption;
    public static volatile SingularAttribute<DocumentFourni, Etat> etat;
    public static volatile SingularAttribute<DocumentFourni, Date> dateEtablissement;
    public static volatile SingularAttribute<DocumentFourni, String> lieuEtablissement;
    public static volatile SingularAttribute<DocumentFourni, String> lieuDelivrance;

}