package api.com.chj.core.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static ThreadLocal<MessageDigest> messageDigestHolder = new ThreadLocal();
    static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5Util() {
    }

    public static String getMD5Format(String data) {
        try {
            MessageDigest e = (MessageDigest)messageDigestHolder.get();
            if(e == null) {
                e = MessageDigest.getInstance("MD5");
                messageDigestHolder.set(e);
            }

            e.update(data.getBytes());
            byte[] b = e.digest();
            String digestHexStr = "";

            for(int i = 0; i < 16; ++i) {
                digestHexStr = digestHexStr + byteHEX(b[i]);
            }

            return digestHexStr;
        } catch (Exception var5) {
            return null;
        }
    }

    public static String getMD5Format(byte[] data) {
        try {
            MessageDigest e = (MessageDigest)messageDigestHolder.get();
            if(e == null) {
                e = MessageDigest.getInstance("MD5");
                messageDigestHolder.set(e);
            }

            e.update(data);
            byte[] b = e.digest();
            String digestHexStr = "";

            for(int i = 0; i < 16; ++i) {
                digestHexStr = digestHexStr + byteHEX(b[i]);
            }

            return digestHexStr;
        } catch (Exception var5) {
            return null;
        }
    }

    private static String byteHEX(byte ib) {
        char[] ob = new char[]{hexDigits[ib >>> 4 & 15], hexDigits[ib & 15]};
        String s = new String(ob);
        return s;
    }

    static {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            messageDigestHolder.set(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
