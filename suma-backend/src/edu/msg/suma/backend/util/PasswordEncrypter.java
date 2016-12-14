package edu.msg.suma.backend.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.msg.suma.backend.service.ServiceException;

public class PasswordEncrypter {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncrypter.class);

	public static String encrypt(String pass, String salt) {

		byte[] initialBytes;

		try {

			initialBytes = (pass + salt).getBytes(PropertyProvider.INSTANCE.getProperty("encrypt.encoding"));
			MessageDigest algorithm = MessageDigest.getInstance(PropertyProvider.INSTANCE.getProperty("encrypt.agorithm"));
			algorithm.reset();
			algorithm.update(initialBytes);
			byte[] hashedBytes = algorithm.digest();

			LOGGER.info("Hash completed");

			return new String(hashedBytes);
		} catch (UnsupportedEncodingException e) {

			LOGGER.error("Unsupported encoding");
			throw new ServiceException("Unsupported encoding");
		} catch (NoSuchAlgorithmException e) {

			LOGGER.error("Algorithm error", e);
			throw new ServiceException("Algorythm error", e);
		}

	}
}
