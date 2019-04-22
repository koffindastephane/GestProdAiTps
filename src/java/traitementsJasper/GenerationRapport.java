package traitementsJasper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class GenerationRapport {
    
    public void exporterJrxmlEnPdf(String cheminJrxml, HashMap hashMap, Connection con, String cheminRapportGenere) throws SQLException, FileNotFoundException, JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(cheminJrxml);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, con);

        JasperExportManager.exportReportToPdfFile(jasperPrint, cheminRapportGenere);
    }

    public <E> void exporterJasperEnPdfStream(String cheminFichierJasper, Map parametres, Connection con) throws IOException, SQLException, JRException {

        JasperPrint jasperPrint = JasperFillManager.fillReport(cheminFichierJasper, parametres, con);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
    }

    public <E> void exporterJrxmlEnPdfStream(String cheminFichierJrxml, Map parametres, Connection con) throws IOException, SQLException, JRException {

        JasperReport report = JasperCompileManager.compileReport(cheminFichierJrxml);

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametres, con);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        }
    }

    public void exporterJrxmlEnExcel(String cheminJrxml, HashMap hashMap, Connection con, String cheminRapportGenere) throws SQLException, FileNotFoundException, JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(cheminJrxml);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, con);

        OutputStream output = new FileOutputStream(new File(cheminRapportGenere));

// coding For Excel:
        JRXlsExporter exporterXLS = new JRXlsExporter();
        exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
        exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
//exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporterXLS.exportReport();
    }
}
