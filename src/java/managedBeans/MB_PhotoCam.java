package managedBeans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CaptureEvent;

@SessionScoped
@Named("mB_PhotoCam")
public class MB_PhotoCam implements Serializable {

    @Inject
    MB_Global mbG;

    @Inject
    MB_Enregistrement mbEnr;

    @Inject
    MB_Connexion mbCon;
    
    @Inject
    MB_GestionFichiers mbGf;

    private String filename;

    //////////////
    public MB_PhotoCam() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private int index = 0;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    ////////////
    public File recupererDernierePhotoDuRepertoire() {
        return mbGf.getLatestFilefromDir(mbEnr.getCheminRepertoireCree());
    }

    public void oncapture(CaptureEvent captureEvent) throws IOException {
        filename = String.valueOf(index);
        byte[] data = captureEvent.getData();

        //   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //   String newFileName = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo"
        //           + File.separator + "images" + File.separator + "photocam" + File.separator + filename + "." + typeFichier ;
        /////////////
        mbGf.creerUnRepertoire(mbEnr.getCheminRepertoireCree());

        filename = mbEnr.getCheminRepertoireCree() + "/" + filename + "." + mbG.getTypeFichier();

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(filename));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            index = index + 1;
        } catch (IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }
    
}
