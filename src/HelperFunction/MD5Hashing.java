package HelperFunction;

import java.security.MessageDigest;

/**
 * Created by William Lee on 2017-03-29.
 */
public class MD5Hashing {

    public String getHashedPwd(String password){
        String hashedPwd= "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            hashedPwd = hexString.toString();
        }
        catch (Exception e){
            System.out.println("Error in hashing");
        }
        return hashedPwd;
    }
}
