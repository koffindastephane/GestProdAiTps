package managedBeans;

import entities.Demande;
import entities.Status;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import sessionBeans.SB;
import traitementsJasper.GenerationRapport;

@javax.enterprise.context.SessionScoped
@Named("mB_Statistiques")
public class MB_Statistiques implements Serializable {

    @EJB
    SB sb;

    @Inject
    MB_Global mbG;

    @Inject
    MB_Connexion mbCon;

    public MB_Statistiques() {
    }

    /////////////////////////////
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    /////////////////////////////
    private Date date;
    private Date dateDebut;
    private Date dateFin;

    private boolean dateSimple = false;
    private boolean datesPeriode = false;

    /////////getters ans setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isDateSimple() {
        return dateSimple;
    }

    public void setDateSimple(boolean dateSimple) {
        this.dateSimple = dateSimple;
    }

    public boolean isDatesPeriode() {
        return datesPeriode;
    }

    public void setDatesPeriode(boolean datesPeriode) {
        this.datesPeriode = datesPeriode;
    }

    // -----------Formattage Date--------
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    ///////////////////////////
    public void selectionDateSimple() {
        this.clearInputTextFields();
        if (dateSimple == false) {
            dateSimple = true;
            datesPeriode = false;
        } else {
            dateSimple = false;
        }
    }

    public void selectionDatesPeriode() {
        this.clearInputTextFields();
        if (datesPeriode == false) {
            datesPeriode = true;
            dateSimple = false;
        } else {
            datesPeriode = false;
        }
    }

    ////////////////////////////
    public void genererRapportEnPdfStream(String nomRapport) throws SQLException, JRException, IOException {

        String cheminFichierJrxml = mbG.getDossierWeb() + "/autre/statistiques/" + nomRapport + ".jrxml";

        HashMap hashmap = new HashMap();

        this.clearInputTextFields();

        Connection con = mbG.connectionBddAiTps();

        GenerationRapport gr = new GenerationRapport();
        gr.exporterJrxmlEnPdfStream(cheminFichierJrxml, hashmap, con);
    }

    public void genererRapport1DateEnPdfStream(String nomRapport) throws SQLException, JRException, IOException {

        if (date != null) {

            String cheminFichierJrxml = mbG.getDossierWeb() + "/autre/statistiques/" + nomRapport + ".jrxml";

            HashMap hashmap = new HashMap();
            hashmap.put("date", date);
            hashmap.put("dateAsString", sdf2.format(date));

            this.clearInputTextFields();

            Connection con = mbG.connectionBddAiTps();

            GenerationRapport gr = new GenerationRapport();
            gr.exporterJrxmlEnPdfStream(cheminFichierJrxml, hashmap, con);
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez entrer une date");
        }
    }

    public void genererRapport2DatesEnPdfStream(String nomRapport) throws SQLException, JRException, IOException {

        if (dateDebut != null && dateFin != null) {

            if (!dateDebut.after(dateFin)) {

                String cheminFichierJrxml = mbG.getDossierWeb() + "/autre/statistiques/" + nomRapport + ".jrxml";

                HashMap hashmap = new HashMap();
                hashmap.put("dateDebut", dateDebut);
                hashmap.put("dateFin", dateFin);
                hashmap.put("dateDebutAsString", sdf2.format(dateDebut));
                hashmap.put("dateFinAsString", sdf2.format(dateFin));

                this.clearInputTextFields();

                Connection con = mbG.connectionBddAiTps();

                GenerationRapport gr = new GenerationRapport();
                gr.exporterJrxmlEnPdfStream(cheminFichierJrxml, hashmap, con);

            } else {
                mbG.ajouterMessage("Erreur", "dateDebut ne doit pas être supérieure à dateFin");
            }
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez entrer une période");
        }
    }

    ///////////////
    public List<Demande> listeDemandesDispoPourUnNiveauEntreDeuxDates(int numStatus) {
        return sb.listerDemandesDispoPourUnNiveauEntreDeuxDates(numStatus, dateDebut, dateFin);
    }

    ///////////////////
    public void clearInputTextFields() {
        date = null;
        dateDebut = null;
        dateFin = null;

        //   this.datesPeriode = false;
        //   this.dateSimple = false;
    }

    //////////////////////:
    public String retournerAuMenuGeneral() {
        this.clearInputTextFields();
        return mbG.retournerAuMenuGeneral();
    }

    ////////////////////////////
    private int numStatus;

    public int getNumStatus() {
        return numStatus;
    }

    public void setNumStatus(int numStatus) {
        this.numStatus = numStatus;
    }

    public List<Status> listeStatus() {

        List<Status> listeStatus = sb.listAll(new Status(), " WHERE numStatus <> 0");

        // Sorting
        Collections.sort(listeStatus, new Comparator<Status>() {

            @Override
            public int compare(Status st1, Status st2) {
                return st1.getNumStatus().compareTo(st2.getNumStatus());
            }
        });

        return listeStatus;
    }

    public void genererRapport1Date1NiveauEnPdfStream(String nomRapport) throws SQLException, JRException, IOException {

        if (date != null) {

            String cheminFichierJrxml = mbG.getDossierWeb() + "/autre/statistiques/" + nomRapport + ".jrxml";

            HashMap hashmap = new HashMap();
            hashmap.put("date", date);
            hashmap.put("dateAsString", sdf2.format(date));
            hashmap.put("niveau", numStatus);

            this.clearInputTextFields();

            Connection con = mbG.connectionBddAiTps();

            GenerationRapport gr = new GenerationRapport();
            gr.exporterJrxmlEnPdfStream(cheminFichierJrxml, hashmap, con);
        } else {
            mbG.ajouterMessage("Erreur", "Veuillez entrer une date");
        }
    }
//////////////////

    public List<Demande> listerDemandes() {
        return sb.listAll(new Demande(), " order by statusActuel");
    }

    ////////////
    public String dateMinimumPourDateFin() {
        if (dateDebut == null) {
            return mbG.dateMinimalDuCalendrierPourStatistiques();
        } else {
            return sdf3.format(dateDebut);
        }
    }
}
