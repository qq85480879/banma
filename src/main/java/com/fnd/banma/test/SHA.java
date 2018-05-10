package com.fnd.banma.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

/*48位SHA加密 不可逆*/
public class SHA {

    private static final String KEY = "SHA";

    private static Logger logger = LoggerFactory.getLogger(SHA.class);

    public static String getResule(String input){

        try{
            BigInteger sha = null;

            byte[] inputData = input.getBytes();

            MessageDigest messageDigest = MessageDigest.getInstance(KEY);

            messageDigest.update(inputData);

            sha = new BigInteger(messageDigest.digest());

            return sha.toString(32);
        } catch (Exception e){

            logger.error("加密异常" + e.getMessage());

            return input;
        }

    }


}
