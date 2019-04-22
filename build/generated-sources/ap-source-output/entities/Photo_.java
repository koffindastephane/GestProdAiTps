package entities;

import entities.Demande;
import entities.DocumentFourni;
import entities.TypePhoto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2019-04-13T14:24:24")
@StaticMetamodel(Photo.class)
public class Photo_ { 

    public static volatile SingularAttribute<Photo, Integer> widthPhoto;
    public static volatile SingularAttribute<Photo, String> cheminPhoto;
    public static volatile SingularAttribute<Photo, Integer> sizePhoto;
    public static volatile ListAttribute<Photo, Demande> demandeList;
    public static volatile SingularAttribute<Photo, Integer> heightPhoto;
    public static volatile SingularAttribute<Photo, TypePhoto> typePhoto;
    public static volatile SingularAttribute<Photo, Integer> lastModifiedPhoto;
    public static volatile ListAttribute<Photo, DocumentFourni> documentFourniList;
    public static volatile SingularAttribute<Photo, String> nomPhoto;
    public static volatile SingularAttribute<Photo, String> numPhoto;

}