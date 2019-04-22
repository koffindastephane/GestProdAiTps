package managedBeans;

import java.io.File;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("mB_GestionFichiers")
public class MB_GestionFichiers implements Serializable {

    public MB_GestionFichiers() {
    }

    public boolean deplacerFichier(String cheminSource, String cheminDestination) {

        File fichierSource = new File(cheminSource);

        File fichierDestination = new File(cheminDestination);

        this.deleteIfExist(cheminDestination);

        return fichierSource.renameTo(fichierDestination);
    }

    public void deleteIfExist(String cheminDestination) {
        File file = new File(cheminDestination);
        if (file.exists()) {
            file.delete();
        }
    }

    public File getLatestFilefromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    public int repertoireEstVide(String cheminRepertoire) {
        File repertoire = new File(cheminRepertoire);
        if (repertoire.isDirectory()) {
            if (repertoire.list().length > 0) {
                return 0;   // repertoire non vide
            } else {
                return 1;   // repertoire vide
            }
        } else {
            return 2;   // ce n'est pas un repertoire
        }
    }

    public void conserverUnFichierParSonNomDansUnRepertoire(String cheminRepertoire, String nomFichier) {
        File repertoire = new File(cheminRepertoire);

        for (File f : repertoire.listFiles()) {
            if (f.isFile()) {
                if (!(f.getName().equals(nomFichier))) {
                    f.delete();
                }
            }
        }
    }

    public void effacerUnFichierParSonNomDansUnRepertoire(String cheminRepertoire, String nomFichier) {
        File repertoire = new File(cheminRepertoire);
        
            for (File f : repertoire.listFiles()) {
                if (f.isFile()) {
                    if ((f.getName().equals(nomFichier))) {
                        f.delete();
                    }
                }
            }
    }

    public void creerUnRepertoire(String cheminRepertoire) {
        File repertoire = new File(cheminRepertoire);
        if (!repertoire.exists()) {
            repertoire.mkdirs();
        }
    }

    public boolean effacerArborescenceRepertoiresEtFichiers(File repertoire) {
        if (repertoire.exists()) {
            if (repertoire.isDirectory()) {
                File[] children = repertoire.listFiles();
                boolean success;
                for (int i = 0; i < children.length; i++) {
                    success = effacerArborescenceRepertoiresEtFichiers(children[i]);
                    if (!success) {
                        return false;
                    }
                }
            }
            return repertoire.delete();
        } else {
            return false;
        }
    }

}
