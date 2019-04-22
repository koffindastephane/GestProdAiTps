package sessionBeans;

import entities.Connexion;
import entities.Demande;
import entities.PlageRecus;
import entities.Utilisateur;
import entities.TitreIdentite;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import managedBeans.MB_Global;

@Stateless
public class SB<E> {

    @Inject
    MB_Global mbG;
    
    @PersistenceContext(unitName = "GestProdAiTpsPU")
    EntityManager em;

    public void create(E entity) {
        em.persist(entity);
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public void delete(E entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }

    public List<E> listAll(E entity) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName(), entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> listAll(E entity, String clauseWhere) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName() + clauseWhere, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findByOneCriteria(E entity, String criteriaName, String criteriaValue) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + criteriaName + " = " + criteriaValue, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findByTwoCriteria(E entity, String criteriaName1, String criteriaValue1,
            String criteriaName2, String criteriaValue2) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + criteriaName1 + " = " + criteriaValue1
                + " and " + criteriaName2 + " = " + criteriaValue2, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findByThreeCriteria(E entity, String criteriaName1, String criteriaValue1,
            String criteriaName2, String criteriaValue2,
            String criteriaName3, String criteriaValue3) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + criteriaName1 + " = " + criteriaValue1
                + " and " + criteriaName2 + " = " + criteriaValue2
                + " and " + criteriaName3 + " = " + criteriaValue3, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findByFourCriteria(E entity, String criteriaName1, String criteriaValue1,
            String criteriaName2, String criteriaValue2,
            String criteriaName3, String criteriaValue3,
            String criteriaName4, String criteriaValue4) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + criteriaName1 + " = " + criteriaValue1
                + " and " + criteriaName2 + " = " + criteriaValue2
                + " and " + criteriaName3 + " = " + criteriaValue3
                + " and " + criteriaName4 + " = " + criteriaValue4, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findByFiveCriteria(E entity, String criteriaName1, String criteriaValue1,
            String criteriaName2, String criteriaValue2,
            String criteriaName3, String criteriaValue3,
            String criteriaName4, String criteriaValue4,
            String criteriaName5, String criteriaValue5) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + criteriaName1 + " = " + criteriaValue1
                + " and " + criteriaName2 + " = " + criteriaValue2
                + " and " + criteriaName3 + " = " + criteriaValue3
                + " and " + criteriaName4 + " = " + criteriaValue4
                + " and " + criteriaName5 + " = " + criteriaValue5, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findBySixCriteria(E entity, String criteriaName1, String criteriaValue1,
            String criteriaName2, String criteriaValue2,
            String criteriaName3, String criteriaValue3,
            String criteriaName4, String criteriaValue4,
            String criteriaName5, String criteriaValue5,
            String criteriaName6, String criteriaValue6) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + criteriaName1 + " = " + criteriaValue1
                + " and " + criteriaName2 + " = " + criteriaValue2
                + " and " + criteriaName3 + " = " + criteriaValue3
                + " and " + criteriaName4 + " = " + criteriaValue4
                + " and " + criteriaName5 + " = " + criteriaValue5
                + " and " + criteriaName6 + " = " + criteriaValue6, entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    // Used for tables binary associated with Date   (ex: finding last inserted Couter)
    public List<E> findLastInsertedAtADate(
            E entity, String nameOfDateField, String valueOfDateFieldAsString) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(d." + nameOfDateField + ") from "
                + entity.getClass().getSimpleName() + " d where d."
                + nameOfDateField + " < '" + valueOfDateFieldAsString + "') ", entity.getClass()
        );
        return q.getResultList();
    }

    public List<E> findLastInsertedAtADate(
            E entity, String nameOfDateField, Date valueOfDateFieldAsDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String valueOfDateFieldAsString = sdf.format(valueOfDateFieldAsDate);

        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(d." + nameOfDateField + ") from "
                + entity.getClass().getSimpleName() + " d where d."
                + nameOfDateField + " < '" + valueOfDateFieldAsString + "') ", entity.getClass()
        );
        return q.getResultList();
    }

    // Used for tables ternary associated with Date   (ex: last inserted Couter)
    public List<E> findLastInsertedAtADateAndOneCriteria(
            E entity, String nameOfDateField, String valueOfDateFieldAsString,
            String nameOfForeignKey, String valueOfForeignKey) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey + " = " + valueOfForeignKey
                + " and " + nameOfDateField + " <= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findLastInsertedAtADateAndOneCriteria(
            E entity, String nameOfDateField, Date valueOfDateFieldAsDate,
            String nameOfForeignKey, String valueOfForeignKey) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String valueOfDateFieldAsString = sdf.format(valueOfDateFieldAsDate);

        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey + " = " + valueOfForeignKey
                + " and " + nameOfDateField + " <= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findLastInsertedAtADateAndTwoCriteria(
            E entity, String nameOfDateField, String valueOfDateFieldAsString,
            String nameOfForeignKey1, String valueOfForeignKey1,
            String nameOfForeignKey2, String valueOfForeignKey2) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey1 + " = " + valueOfForeignKey1
                + " and " + nameOfForeignKey2 + " = " + valueOfForeignKey2
                + " and " + nameOfDateField + " <= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findLastInsertedAtADateAndTwoCriteria(
            E entity, String nameOfDateField, Date valueOfDateFieldAsDate,
            String nameOfForeignKey1, String valueOfForeignKey1,
            String nameOfForeignKey2, String valueOfForeignKey2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String valueOfDateFieldAsString = sdf.format(valueOfDateFieldAsDate);

        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey1 + " = " + valueOfForeignKey1
                + " and " + nameOfForeignKey2 + " = " + valueOfForeignKey2
                + " and " + nameOfDateField + " <= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    // Used for
    public List<E> findLastInsertedAtADateAndThreeCriteria(
            E entity, String nameOfDateField, String valueOfDateFieldAsString,
            String nameOfForeignKey1, String valueOfForeignKey1,
            String nameOfForeignKey2, String valueOfForeignKey2,
            String nameOfForeignKey3, String valueOfForeignKey3) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey1 + " = " + valueOfForeignKey1
                + " and " + nameOfForeignKey2 + " = " + valueOfForeignKey2
                + " and " + nameOfForeignKey3 + " = " + valueOfForeignKey3
                + " and " + nameOfDateField + " <= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public List<E> findLastInsertedAtADateAndThreeCriteria(
            E entity, String nameOfDateField, Date valueOfDateFieldAsDate,
            String nameOfForeignKey1, String valueOfForeignKey1,
            String nameOfForeignKey2, String valueOfForeignKey2,
            String nameOfForeignKey3, String valueOfForeignKey3) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String valueOfDateFieldAsString = sdf.format(valueOfDateFieldAsDate);

        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select max(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey1 + " = " + valueOfForeignKey1
                + " and " + nameOfForeignKey2 + " = " + valueOfForeignKey2
                + " and " + nameOfForeignKey3 + " = " + valueOfForeignKey3
                + " and " + nameOfDateField + " <= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    public void deleteWithOneCriteria(E entity, String criteriaName, String criteriaValue) {
        Query q = em.createNativeQuery("delete from " + entity.getClass().getSimpleName()
                + " where " + criteriaName + " = " + criteriaValue
        );
        q.executeUpdate();
    }

////////////////////////////
    public List<Demande> listerDemandesDispoPourUnNiveau(int statusActuel, int typeDemande, String centreTraitement) {

        Query q = em.createQuery("select d from Demande d"
                + " where d.statusActuel.numStatus = " + statusActuel
                + " and d.recu.typeDemande.numTypeDemande = " + typeDemande
                + " and d.centreTraitement.numCentre = '" + centreTraitement + "'"
                + " order by d.dateStatusActuel"
        );
        return (List<Demande>) q.getResultList();
    }

////////////////////////////
    public List<Demande> listerDemandesDispoPourUnNiveau(int statusActuel) {

        Query q = em.createQuery("select d from Demande d"
                + " where d.statusActuel.numStatus = " + statusActuel
                + " order by d.recu.typeDemande.numTypeDemande, d.dateStatusActuel"
        );
        return (List<Demande>) q.getResultList();
    }

    public List<Demande> listerDemandesDispoPourUnNiveauEntreDeuxDates(int statusActuel, Date dateDebut, Date dateFin) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateDebutAsString = sdf.format(dateDebut);
        String dateFinAsString = sdf.format(dateFin);

        Query q = em.createQuery("select d from Demande d"
                + " where d.statusActuel.numStatus = " + statusActuel
                + " and d.dateStatusActuel between '" + dateDebutAsString + "' and '" + dateDebutAsString + "'"
                + " order by d.recu.typeDemande.numTypeDemande, d.dateStatusActuel"
        );
        return (List<Demande>) q.getResultList();
    }

    ///////////////////////////
    // Used for tables ternary associated with Date   (ex: last inserted Couter)
    public List<E> findFirstInsertedAtADateAndOneCriteria(
            E entity, String nameOfDateField, String valueOfDateFieldAsString,
            String nameOfForeignKey, String valueOfForeignKey) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select min(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey + " = " + valueOfForeignKey
                + " and " + nameOfDateField + " >= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    // Used for tables ternary associated with Date   (ex: last inserted Couter)
    public List<E> findFirstInsertedAtADateAndTwoCriteria(
            E entity, String nameOfDateField, String valueOfDateFieldAsString,
            String nameOfForeignKey1, String valueOfForeignKey1,
            String nameOfForeignKey2, String valueOfForeignKey2) {
        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select min(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey1 + " = " + valueOfForeignKey1
                + " and " + nameOfForeignKey2 + " = " + valueOfForeignKey2
                + " and " + nameOfDateField + " >= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    // Used for tables ternary associated with Date   (ex: last inserted Couter)
    public List<E> findFirstInsertedAtADateAndTwoCriteria(
            E entity, String nameOfDateField, Date valueOfDateFieldAsDate,
            String nameOfForeignKey1, String valueOfForeignKey1,
            String nameOfForeignKey2, String valueOfForeignKey2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String valueOfDateFieldAsString = sdf.format(valueOfDateFieldAsDate);

        Query q = em.createNativeQuery("select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfDateField + " = (select min(e." + nameOfDateField + ") "
                + "from ( select * from " + entity.getClass().getSimpleName()
                + " where " + nameOfForeignKey1 + " = " + valueOfForeignKey1
                + " and " + nameOfForeignKey2 + " = " + valueOfForeignKey2
                + " and " + nameOfDateField + " >= '" + valueOfDateFieldAsString
                + "') e ) ", entity.getClass()
        );
        return (List<E>) q.getResultList();
    }

    /////////
    public List<PlageRecus> findProchainePlageRecus(String numCentrePaiement) {

        Query q = em.createNativeQuery("select * from PlageRecus "
                + " where numeroDebutPlageRecus = (select min(numeroDebutPlageRecus) from PlageRecus"
                + " where centrePaiement = '" + numCentrePaiement + "'"
                + " and statusPlageRecus=2)"
                + " and centrePaiement = '" + numCentrePaiement + "'"
                + " and statusPlageRecus=2", PlageRecus.class
        );
        return (List<PlageRecus>) q.getResultList();
    }

    public List<PlageRecus> findPlageRecusContenantLeRecuNumero(int numeroRecu) {
        Query q = em.createNativeQuery("select * from PlageRecus where numeroDebutPlageRecus <= " + numeroRecu
                + " and numeroFinPlageRecus >= " + numeroRecu, PlageRecus.class);
        return (List<PlageRecus>) q.getResultList();
    }

    //////////
    public long findNombreUtilisateursDansUnGroupes(int numGroupe_) {
        Query q = em.createNativeQuery("select count(*) from AppartenirGroupe where groupe = " + numGroupe_);
        return (long) q.getSingleResult();
    }

    public long findNombreGroupesDeUnUtilisateur(int numUtilisateur_) {
        Query q = em.createNativeQuery("select count(*) from AppartenirGroupe where utilisateur = " + numUtilisateur_);
        return (long) q.getSingleResult();
    }

    //////////////
    public long nombreUtilisateursSupprimables() {
        Query q = em.createNativeQuery("select count(*) from Utilisateur where peutEtreSupprime = 1");
        return (long) q.getSingleResult();
    }

    ///////
    public int desactiverToutesLesConnexions() {
        Query q = em.createNativeQuery("update Connexion set estActive = 0");
        return q.executeUpdate();
    }

    ////////////
    public List<Utilisateur> utilisateursAyantUnEmailEtNAyantPasRecuMailCreationCompte() {
        Query q = em.createNativeQuery("select * from Utilisateur where aRecuMailCreationCompte = 0 and emailUtilisateur is not null", Utilisateur.class);
        return (List<Utilisateur>) q.getResultList();
    }
    
    public List<Utilisateur> utilisateursNAyantPasDEmailEtNAyantPasRecuMailCreationCompte() {
        Query q = em.createNativeQuery("select * from Utilisateur where aRecuMailCreationCompte = 0 and emailUtilisateur is null", Utilisateur.class);
        return (List<Utilisateur>) q.getResultList();
    }
    
    public List<Utilisateur> utilisateursNAyantPasRecuMailCreationCompte() {
        Query q = em.createNativeQuery("select * from Utilisateur where aRecuMailCreationCompte = 0", Utilisateur.class);
        return (List<Utilisateur>) q.getResultList();
    }
    
    /////////
    
    public List<TitreIdentite> titresAvecPetitionnairesAyantUnEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces() {
        Query q = em.createNativeQuery("select * from TitreIdentite ti, Demande d, Recu r, Petitionnaire p"
                + " where ti.demande = d.numDemande and d.recu = r.numRecu and r.petitionnaire = p.numPetitionnaire"
                + " and ti.mailFinProductionAEteEnvoyeAvecSucces = 0 and p.email is not null", TitreIdentite.class);
        return (List<TitreIdentite>) q.getResultList();
    }
    
    public List<TitreIdentite> titresAvecPetitionnairesNAyantPasDEmailEtMailsFinProductionTitreNonEnvoyesAvecSucces() {
        Query q = em.createNativeQuery("select * from TitreIdentite ti, Demande d, Recu r, Petitionnaire p"
                + " where ti.demande = d.numDemande and d.recu = r.numRecu and r.petitionnaire = p.numPetitionnaire"
                + " and ti.mailFinProductionAEteEnvoyeAvecSucces = 0 and p.email is null", TitreIdentite.class);
        return (List<TitreIdentite>) q.getResultList();
    }
    
    public List<TitreIdentite> titresAvecMailsFinProductionTitreNonEnvoyesAvecSucces() {
        Query q = em.createNativeQuery("select * from TitreIdentite ti, Demande d, Recu r, Petitionnaire p"
                + " where ti.demande = d.numDemande and d.recu = r.numRecu and r.petitionnaire = p.numPetitionnaire"
                + " and ti.mailFinProductionAEteEnvoyeAvecSucces = 0", TitreIdentite.class);
        return (List<TitreIdentite>) q.getResultList();
    }
    
    public List<Connexion> connexionsActivesSurAutresMachinesPourLUtilisateurConnecte(Utilisateur u) {
        Query q = em.createNativeQuery("select * from Connexion where utilisateur = " + u.getNumUtilisateur()
                + " and estActive=1 and adresseIP <> '" + mbG.recupererAdresseIP() + "'", Connexion.class);
        return (List<Connexion>) q.getResultList();
    }

}
