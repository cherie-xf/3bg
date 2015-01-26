package ca.esystem.framework.util;

import java.io.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.*;
import java.security.spec.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.crypto.provider.SunJCE;

import java.io.Serializable;


public class EncryptKit
{
     byte[] encryptKey;
     DESedeKeySpec spec;
     SecretKeyFactory keyFactory;
     SecretKey theKey;
     Cipher cipher;
     IvParameterSpec IvParameters;

     public   EncryptKit()
     {
         try
         {
             try{ 
            	 Cipher c = Cipher.getInstance("DESede"); 
             }catch (Exception e)
             {
                 System.err.println("Installling SunJCE provider.");
                 Provider sunjce = new com.sun.crypto.provider.SunJCE();
                 Security.addProvider(sunjce);
             }
             encryptKey = "Jesus is my lord and savor".getBytes();

             spec = new DESedeKeySpec(encryptKey);

             keyFactory = SecretKeyFactory.getInstance("DESede");

             theKey = keyFactory.generateSecret(spec);

             cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

             IvParameters =
                     new IvParameterSpec(new byte[]{12,34,56,78,90,87,65,43} );
         }
         catch (Exception exc)
         {
        	 System.err.println(exc);
         }
     }

     public byte[] encrypt(String password)
     {
         String encrypted_password = null;
         byte[] encrypted_pwd = null;

         try
         {
             cipher.init(Cipher.ENCRYPT_MODE,theKey,IvParameters);
             byte[] plainttext = password.getBytes();
             encrypted_pwd = cipher.doFinal(plainttext);
             encrypted_password = new String(encrypted_pwd);
         }
         catch(Exception ex)
         {
         }
         return encrypted_pwd;
     }
     
     public String encryptAsString(String plaintext){
    	 String encrystr="";
   	     byte[] encrybyte = encrypt(plaintext);
	   	 for(int i=0;i<encrybyte.length;i++){
	   	    encrystr +=encrybyte[i]+"|";
	   	 }
	   	return encrystr;
     }
     

     public String decrypt(byte[] password)
     {
         String decrypted_password = null;
         try
         {

             cipher.init(Cipher.DECRYPT_MODE,theKey,IvParameters);

             byte[] decryptedPassword = password;

             byte[] decrypted_pwd = cipher.doFinal(decryptedPassword);

             decrypted_password = new String(decrypted_pwd);
         }
         catch(Exception ex)
         {

         }
         return decrypted_password;
     }
     
     public String decryptFromString(String encryptStr){
    	  String decrystr="";
	   	  StringTokenizer token = new StringTokenizer(encryptStr,"|");
		  List list = new ArrayList();
		  for(int i=0;token.hasMoreTokens();i++){
			  list.add(i, token.nextToken());
		  }
	      byte[] encrybyte2 = new byte[list.size()];
		  for(int i=0;i<list.size();i++){
			  int unit = Integer.parseInt((String)list.get(i));
			  encrybyte2[i] = (byte)unit;
		  }
		  decrystr = decrypt(encrybyte2);
		  return decrystr;
     }
     
     public static String EncodewithMD5(String dataStr) throws Exception
     {
    	 return DigestUtils.md5Hex(dataStr);	
     }
    
}