package com.google.api.client.util;

import java.security.cert.Certificate;
import java.util.Iterator;
import java.security.cert.CertificateFactory;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.GeneralSecurityException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.KeyStore;

public final class SecurityUtils
{
    public static KeyStore getDefaultKeyStore() throws KeyStoreException {
        return KeyStore.getInstance(KeyStore.getDefaultType());
    }
    
    public static KeyStore getJavaKeyStore() throws KeyStoreException {
        return KeyStore.getInstance("JKS");
    }
    
    public static KeyStore getPkcs12KeyStore() throws KeyStoreException {
        return KeyStore.getInstance("PKCS12");
    }
    
    public static void loadKeyStore(final KeyStore keyStore, final InputStream inputStream, final String s) throws IOException, GeneralSecurityException {
        try {
            keyStore.load(inputStream, s.toCharArray());
        }
        finally {
            inputStream.close();
        }
    }
    
    public static PrivateKey getPrivateKey(final KeyStore keyStore, final String s, final String s2) throws GeneralSecurityException {
        return (PrivateKey)keyStore.getKey(s, s2.toCharArray());
    }
    
    public static PrivateKey loadPrivateKeyFromKeyStore(final KeyStore keyStore, final InputStream inputStream, final String s, final String s2, final String s3) throws IOException, GeneralSecurityException {
        loadKeyStore(keyStore, inputStream, s);
        return getPrivateKey(keyStore, s2, s3);
    }
    
    public static KeyFactory getRsaKeyFactory() throws NoSuchAlgorithmException {
        return KeyFactory.getInstance("RSA");
    }
    
    public static Signature getSha1WithRsaSignatureAlgorithm() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA1withRSA");
    }
    
    public static Signature getSha256WithRsaSignatureAlgorithm() throws NoSuchAlgorithmException {
        return Signature.getInstance("SHA256withRSA");
    }
    
    public static byte[] sign(final Signature signature, final PrivateKey privateKey, final byte[] array) throws InvalidKeyException, SignatureException {
        signature.initSign(privateKey);
        signature.update(array);
        return signature.sign();
    }
    
    public static boolean verify(final Signature signature, final PublicKey publicKey, final byte[] array, final byte[] array2) throws InvalidKeyException, SignatureException {
        signature.initVerify(publicKey);
        signature.update(array2);
        try {
            return signature.verify(array);
        }
        catch (SignatureException ex) {
            return false;
        }
    }
    
    public static X509Certificate verify(final Signature signature, final X509TrustManager x509TrustManager, final List<String> list, final byte[] array, final byte[] array2) throws InvalidKeyException, SignatureException {
        CertificateFactory x509CertificateFactory;
        try {
            x509CertificateFactory = getX509CertificateFactory();
        }
        catch (CertificateException ex) {
            return null;
        }
        final X509Certificate[] array3 = new X509Certificate[list.size()];
        int n = 0;
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decodeBase64(iterator.next()));
            try {
                final Certificate generateCertificate = x509CertificateFactory.generateCertificate(byteArrayInputStream);
                if (!(generateCertificate instanceof X509Certificate)) {
                    return null;
                }
                array3[n++] = (X509Certificate)generateCertificate;
            }
            catch (CertificateException ex2) {
                return null;
            }
        }
        try {
            x509TrustManager.checkServerTrusted(array3, "RSA");
        }
        catch (CertificateException ex3) {
            return null;
        }
        if (verify(signature, array3[0].getPublicKey(), array, array2)) {
            return array3[0];
        }
        return null;
    }
    
    public static CertificateFactory getX509CertificateFactory() throws CertificateException {
        return CertificateFactory.getInstance("X.509");
    }
    
    public static void loadKeyStoreFromCertificates(final KeyStore keyStore, final CertificateFactory certificateFactory, final InputStream inputStream) throws GeneralSecurityException {
        int n = 0;
        final Iterator<? extends Certificate> iterator = certificateFactory.generateCertificates(inputStream).iterator();
        while (iterator.hasNext()) {
            keyStore.setCertificateEntry(String.valueOf(n), (Certificate)iterator.next());
            ++n;
        }
    }
    
    private SecurityUtils() {
        super();
    }
}
