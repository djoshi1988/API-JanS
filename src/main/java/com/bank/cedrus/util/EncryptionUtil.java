package com.bank.cedrus.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
  private static final int GCM_TAG_LENGTH = 16;
  
  private static final String TRANSFORMATION = "AES/GCM/NoPadding";
  
  private static final String ALGO_AES = "AES";
  
  private static final String ALGO_RSA = "RSA";
  
  private static final String ENCODING = "UTF-8";
  
  private static final String HEADER_ENC_ALGO = "RSA/ECB/OAEPPADDING";
  
  private static final String SIGNATURE = "SHA256withRSA";
  
  private static final String SEPARATOR = ":";
  
  public String encrypt(String plainText, String privateKey, String publicKey) throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException, NoSuchProviderException {
    String dynamicKey = UUID.randomUUID().toString().replaceAll("-", "");
    byte[] iv = getIVFromAESKey(dynamicKey.getBytes());
    byte[] encrypt = encryptRequestBodyAES256(plainText.getBytes(), dynamicKey.getBytes(), iv);
    String encryptionRequestBody = Base64.getEncoder().encodeToString(encrypt);
    String digitalSignature = signSHA256RSA(encryptionRequestBody, privateKey);
    String headerKey = getEncryptHeader(dynamicKey, publicKey);
    return Base64.getEncoder().encodeToString((headerKey + ":" + encryptionRequestBody + ":" + digitalSignature).getBytes());
  }
  
  private static byte[] getIVFromAESKey(byte[] encoded) {
    return Arrays.copyOfRange(encoded, 0, 16);
  }
  
  private byte[] encryptRequestBodyAES256(byte[] plaintext, byte[] key, byte[] IV) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
    GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, IV);
    cipher.init(1, keySpec, gcmParameterSpec);
    return cipher.doFinal(plaintext);
  }
  
  private String signSHA256RSA(String input, String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
    byte[] b1 = DatatypeConverter.parseBase64Binary(privateKeyStr);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    Signature privateSignature = Signature.getInstance("SHA256withRSA");
    privateSignature.initSign(kf.generatePrivate(spec));
    byte[] bytes = input.getBytes("UTF-8");
    privateSignature.update(bytes);
    byte[] s = privateSignature.sign();
    return Base64.getEncoder().encodeToString(s);
  }
  
  private String getEncryptHeader(String keyPlainText, String publicKeyStr) {
    try {
      byte[] keyByteArr = keyPlainText.getBytes();
      PublicKey key = getPublicKey(publicKeyStr);
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPADDING");
      cipher.init(1, key);
      byte[] encryptedByte = cipher.doFinal(keyByteArr);
      return Base64.getEncoder().encodeToString(encryptedByte);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    } 
  }
  
  PublicKey getPublicKey(String publicKeyStr) {
    PublicKey publicKey = null;
    try {
      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
      BufferedReader br = new BufferedReader(new StringReader(publicKeyStr));
      String line = null;
      StringBuilder keyBuffer = new StringBuilder();
      while ((line = br.readLine()) != null) {
        if (!line.startsWith("-"))
          keyBuffer.append(line); 
      } 
      Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(keyBuffer.toString())));
      publicKey = certificate.getPublicKey();
    } catch (Exception var8) {
      var8.printStackTrace();
    } 
    return publicKey;
  }
}
