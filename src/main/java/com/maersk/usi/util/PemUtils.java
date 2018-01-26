package com.maersk.usi.util;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.springframework.core.io.ClassPathResource;

public class PemUtils {

    private static byte[] parsePEMFile(InputStream pemStream) throws IOException {
        PemReader reader = new PemReader(new InputStreamReader(pemStream));
        PemObject pemObject = reader.readPemObject();
        return pemObject.getContent();
    }

    private static PublicKey getPublicKey(byte[] keyBytes, String algorithm) {
        PublicKey publicKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            publicKey = kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the public key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the public key");
        }

        return publicKey;
    }

    private static PrivateKey getPrivateKey(byte[] keyBytes, String algorithm) {
        PrivateKey privateKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the private key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the private key");
        }

        return privateKey;
    }

    public static PublicKey readPublicKeyFromFile(String filepath, String algorithm) throws IOException {
//        byte[] bytes = PemUtils.parsePEMFile(new File(filepath));
        ClassPathResource res = new ClassPathResource(filepath);
        byte[] bytes = PemUtils.parsePEMFile(res.getInputStream());
        return PemUtils.getPublicKey(bytes, algorithm);
    }

    public static PrivateKey readPrivateKeyFromFile(String filepath, String algorithm) throws IOException {
        //byte[] bytes = PemUtils.parsePEMFile(new File(filepath));
        ClassPathResource res = new ClassPathResource(filepath);
        byte[] bytes = PemUtils.parsePEMFile(res.getInputStream());
        return PemUtils.getPrivateKey(bytes, algorithm);
    }

}
