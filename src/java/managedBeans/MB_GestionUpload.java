package managedBeans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

@SessionScoped
@Named("mB_GestionUpload")
public class MB_GestionUpload implements Serializable {

    //////////////////////////////    
    public MB_GestionUpload() {
    }

    /////////////////::
//    private static String getFilename(Part part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
//            }
//        }
//        return null;
//    }
    public String getUploadedFileName(Part photoPart) {
        return photoPart.getSubmittedFileName();
    }

    public int getUploadedFileSize(Part photoPart) {
        return (int) photoPart.getSize();
    }

//    public void save(Part part, String destination) throws IOException {
//
//        try (InputStream is = part.getInputStream();
//                OutputStream os = new FileOutputStream(destination);) {
//
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = is.read(buffer)) > 0) {
//                os.write(buffer, 0, length);
//            }
//        }
//    }
    
    public void sauvegarderFichier(Part photoPart, String cheminDestination) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(photoPart.getInputStream());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(cheminDestination))) {
            int bytesRead = 0;
            final byte[] chunck = new byte[1024];
            while ((bytesRead = bufferedInputStream.read(chunck)) != -1) {
                bufferedOutputStream.write(chunck, 0, bytesRead);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload successfully ended!"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload failed!"));
        }
    }

    public long fileLastModified(String cheminFichier) {
        File file = new File(cheminFichier);
        return file.lastModified();
    }

    /////////////////////////////
}
