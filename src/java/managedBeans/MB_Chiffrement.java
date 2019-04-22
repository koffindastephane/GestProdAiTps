/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeans;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;

@SessionScoped
@Named("mB_Chiffrement")
public class MB_Chiffrement implements Serializable {
    
    public MB_Chiffrement() {
    }

    public String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb_ = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb_.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb_.toString();
    }

    public String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb_ = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb_.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb_.toString();
    }

}
