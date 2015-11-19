package com.holydota.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CodecUtil {

    private volatile static CodecUtil instanse;

    private Base64                    base64 = new Base64();

    private Md5                       md5    = new Md5();

    private Sha1                      sha1   = new Sha1();

    //私有构造方法,保证单例不被破坏
    private CodecUtil() {
    }

    public static CodecUtil getInstance() {
        if (instanse == null) {
            synchronized (CodecUtil.class) {
                if (instanse == null) {
                    instanse = new CodecUtil();
                }
            }
        }
        return instanse;
    }

    public Base64 useBase64() {
        return base64;
    }

    public Md5 useMd5() {
        return md5;
    }

    public Sha1 useSha1() {
        return sha1;
    }

    public static class Base64 {
        private Base64() {
        }

        public String encodeStr(String plainText) {
            byte[] b = org.apache.commons.codec.binary.Base64.encodeBase64(plainText.getBytes(), true);
            String s = new String(b);
            return s;
        }

        public String decodeStr(String encodeStr) {
            byte[] b = org.apache.commons.codec.binary.Base64.decodeBase64(encodeStr.getBytes());
            String s = new String(b);
            return s;
        }
    }

    public static class Md5 {
        private Md5() {

        }

        public String encodeStr(String plainText) {
            String s = DigestUtils.md5Hex(plainText);
            return s;
        }
    }

    public static class Sha1 {
        private Sha1() {

        }

        public String encodeStr(String plainText) {
            String s = DigestUtils.sha1Hex(plainText);
            return s;
        }
    }

}
