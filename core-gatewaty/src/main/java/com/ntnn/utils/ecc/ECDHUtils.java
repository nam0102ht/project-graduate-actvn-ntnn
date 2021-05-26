package com.ntnn.utils.ecc;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.util.encoders.Hex;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.openssl.PEMKeyPair;
import org.spongycastle.openssl.PEMParser;
import org.spongycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ECDHUtils {
    public static SecureRandom random = new SecureRandom();

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    public static void main(String[] args) throws IOException {
        byte[] iv = generateIVBytes();
        String plainText = "3032303039323030303030303030303030303030303030303030303030303032303030303030303030313530303030303030303730353230323132313965794a30655841694f694a4b563151694c434a68624763694f694a46557a49314e694a392e65794a6a59584a6b546e5674596d5679496a6f694f5463774e4445794d4459324d6a59794e7a453449697769626d46745a534936496b356e64586c6c626942575957346751534973496d6c68644349364d5459794d4451774e446b304d58302e48517a776e4d75457954316257494576446d365f54574f35784d7a7966673843395073786c424834635648707a4b656c575876374659384a554562594e425f694945744d6f53737741305669656d3542486a624b3251";
        String cipher = encrypt(plainText, iv);
        System.out.println("Original plaintext message: " + cipher);
        System.out.println("Spec: "+Hex.toHexString(iv));
        System.out.println(decrypt("c3170e8293a0301d167562f6e39c520d39970e33165827c4e4b8b58b654c2729025e94bfc4ebb67a4b5365ba55a993f34bcf1b8c11fd8278068d80a51203047b3e56eaeaa4bae585f427e13def244cd578dfe1c06c727b7ff6450bb0a649c46bb70481e71999f1c4ba30233c46eca0dfb8de49563718a8af6471965602019e87c8b8f58ce14004d53fba7e2e40e0f5fa8fa129322fe06cf0bbf8ecb56e5c576fe6becf58b2368b7c9931120f9ac468a4d424c3dae1f7e829f274f320d263707ed12e6e00f96a25d7620b86f4a10e5720b345f2514b056578f71f1212cd216c7bbc9c3857b2122c9883fd411ff7af0400696a3f2a20239f712f1931240c5102f7e4f87245faed80bfe057721fdfcf548f4a8bed7d2b90ac8db7cf2d63bcb640e6715ce975f008ba811acd575a41ef6a727d66676845c4e9c2129858ba2da9f68f56b19bfeec4b793b6fa5a49062002b8f81977c73e2408fbf8e5a2e0cdc087609a699c3983a4f202877c3753362519fb01b25d220ef291c71fca1a9de556f8de86d37af8574732f4626849a44e77c29f2d5876c774549cd6cefd1406403abb13d28e8bb366123247c775a8a28d12aba479370665caf6c1437d15beb05d0209d5e20c028da54dad7f8d948042ce8925193e4d98e1778e6340d72d359b82b825e70fa621eef08891ec267ae622ccf1fbfa4bc03086632f7600ec19c72347855f7fd453914c1f7fce55b2e3e910894ea42f9759dc462107df421e2068d1b843c0fc784f664ffae955ab8c4688fe4f94bd6c03c60f00b19cc784c38513f36aa54b596", Hex.decode("65337b590eb154c99178a96020b99518")));
    }

    public static String encrypt(String plainText, byte[] iv) throws IOException {
        Reader rdrClient = new StringReader("-----BEGIN EC PRIVATE KEY-----\n" +
                "MHcCAQEEIJs2gLL8T2WeidFZMZvH1C7yM0i8ohgSKNCahzeObw6ooAoGCCqGSM49\n" +
                "AwEHoUQDQgAE1cPgPaZs1FVrLa+XSQvNJAF8L4LO5uTIDkauGrG1lDgUG0nBwt7r\n" +
                "N3NgCP7BpZU/zHvpiQ98cUy01Bc5ua8W1g==\n" +
                "-----END EC PRIVATE KEY-----");
        Reader rdrServer = new StringReader("-----BEGIN PUBLIC KEY-----\n" +
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE2Qw4Alw6w70mBe8D636QIv7smGpE\n" +
                "AdR3y1U+wKFgeNQ70xa4OrBKYiHNC9+E7GRqH6uobJtVpsTEYwaUeClx/w==\n" +
                "-----END PUBLIC KEY-----");
        Object parsedServer = new PEMParser(rdrServer).readObject();
        PublicKey keyPairServer = new JcaPEMKeyConverter().getPublicKey((SubjectPublicKeyInfo) parsedServer);
        Object parsedClient = new PEMParser(rdrClient).readObject();
        PrivateKey keyPairClient = new JcaPEMKeyConverter().getPrivateKey(((PEMKeyPair) parsedClient).getPrivateKeyInfo());
        SecretKey secretKey = generateSharedSecret(keyPairClient, keyPairServer);
        return encryptString(secretKey, plainText, iv);
    }

    public static String decrypt(String cipherText, byte[] randomSpec) throws IOException {
        Reader rdrClient = new StringReader("-----BEGIN PUBLIC KEY-----\n" +
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE1cPgPaZs1FVrLa+XSQvNJAF8L4LO\n" +
                "5uTIDkauGrG1lDgUG0nBwt7rN3NgCP7BpZU/zHvpiQ98cUy01Bc5ua8W1g==\n" +
                "-----END PUBLIC KEY-----");
        Reader rdrServer = new StringReader("-----BEGIN EC PRIVATE KEY-----\n" +
                "MHcCAQEEIKTv/hKAgFEBDqV7FWw50zl8JUZVyK9KirugK8067ZG8oAoGCCqGSM49\n" +
                "AwEHoUQDQgAE2Qw4Alw6w70mBe8D636QIv7smGpEAdR3y1U+wKFgeNQ70xa4OrBK\n" +
                "YiHNC9+E7GRqH6uobJtVpsTEYwaUeClx/w==\n" +
                "-----END EC PRIVATE KEY-----");
        Object parsedServer = new PEMParser(rdrServer).readObject();
        PrivateKey keyPairServer = new JcaPEMKeyConverter().getPrivateKey((((PEMKeyPair) parsedServer).getPrivateKeyInfo()));
        Object parsedClient = new PEMParser(rdrClient).readObject();
        PublicKey keyPairClient = new JcaPEMKeyConverter().getPublicKey((SubjectPublicKeyInfo) parsedClient);
        SecretKey secretKey = generateSharedSecret(keyPairServer, keyPairClient);
        return decryptString(secretKey, cipherText, randomSpec);
    }

    public static SecretKey generateSharedSecret(PrivateKey privateKey,
                                                 PublicKey publicKey) {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH", "BC");
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(publicKey, true);

            SecretKey key = keyAgreement.generateSecret("AES");
            return key;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptString(SecretKey key, String plainText, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes());
            return Hex.toHexString(cipherText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptString(SecretKey key, String cipherText, byte[] iv) {
        try {
            Key decryptionKey = new SecretKeySpec(key.getEncoded(),
                    key.getAlgorithm());
            byte[] cipherTextBytes = Hex.decode(cipherText);
            byte[] plainText;

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] decryptedText = cipher.doFinal(cipherTextBytes);

            return new String(decryptedText, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException  e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] generateIVBytes() {
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);

        return ivBytes;
    }
}
