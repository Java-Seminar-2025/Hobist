package gio.hobist.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class MessageEncryption {

   private static KeyPair keys ;
   private static Cipher cipher;


    static{
        try {
            keys=load();
            cipher = Cipher.getInstance("RSA");
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("failed to get instance for Message encription",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    //M.G: keys are hardcoded for now
    private static KeyPair load() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
       var privateKeyStr="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";
       var publicKeyStr="MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEA1nOkEP2rc9xMZ/uGJ04GtKiKsy7OO2grElRgpHVFUfo8EXREf2ixbh1rkb9qbdplQH55Sz+PeNmjhwSRm7S5d8RxDXgW8XMd0qO1B+3FlpeIslgdMJasnXbArCdz+4hpayzXvY1FY8OTHFhSEy0wZbbJLJeCScrPw9X4FLoYCKrU1100wVRMMz3IxKo0pBX2OM62ba4i5Pa8L8RoeI5PTguHtIV0uYq9auulYxwJPV42h0UDP+82Sk+wODMm+a20/cHi+zjqr8eeImMLvMze/ey3Q0nVQynzEMHj1bXKWj4foeYDDdjAbxDtV4+ghQaLxlDcG/k+6GhtvIz3fVd8CnFUfRyWzak5mO0eDx/X8LVv0dGyWb5oHXWU6y8fDHJH6r/qlhsjZi5mvaX0NqySlAkow4l91PFhGwOeXwSmg09E0oO52TjmT0qPa6MH1oLCSHmnwMlwJyBf7QsY+kPxLmectmcOS+pyEfwABPIEVNYdecsHWLE+Y7LQ6YVLYAv/AgMBAAE=";

        var keyFactory=KeyFactory.getInstance("RSA");

        byte[] publicKeyBytes= Base64.getDecoder().decode(publicKeyStr);
        var publicKey=keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

        byte[] privateKeyBytes= Base64.getDecoder().decode(privateKeyStr);
        var privateKey=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

        return new KeyPair(publicKey,privateKey);
    }

    public static byte[] encrypt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE,keys.getPublic());
        var encryptedMessage=cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encode(encryptedMessage);//M.G: This format fits in db
    }

    public static String decrypt(byte[] message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE,keys.getPrivate());
        var encryptedMessage=Base64.getDecoder().decode(message);
        return new String(cipher.doFinal(encryptedMessage));
    }
}
