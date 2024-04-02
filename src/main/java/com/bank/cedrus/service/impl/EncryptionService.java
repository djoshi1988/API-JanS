package com.bank.cedrus.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.bank.cedrus.util.DecryptionUtil;
import com.bank.cedrus.util.EncryptionUtil;

@Component
@Service
public class EncryptionService {

	@Value("${private.key.path:classpath:private.key}")
    private Resource privateKeyResource;

    @Value("${public.key.path:classpath:Jansuraksha_public.crt}")
    private Resource publicKeyResource;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Autowired
    private DecryptionUtil decryptionUtil;

    public String encrypt(String plainText) throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException, NoSuchProviderException, IOException {
       
        return encryptionUtil.encrypt(plainText, loadKey(privateKeyResource), loadKey(publicKeyResource));
    }

    public String decrypt(String encryptedText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidAlgorithmParameterException, NoSuchProviderException, InvalidKeySpecException, CertificateException, IOException {
    
        return decryptionUtil.decrypt(encryptedText, loadKey(privateKeyResource), loadKey(publicKeyResource));
    }

    public String loadKey(Resource keyResource) throws IOException {
        try (Reader reader = new InputStreamReader(keyResource.getInputStream())) {
            return FileCopyUtils.copyToString(reader);
        }
    }
}
