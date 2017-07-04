package com.atos.zad2b;

import java.security.MessageDigest;

import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

public class Sha3 {
	public String encrypt(final String password){
		final DigestSHA3 sha3 = new Digest256();

		sha3.update(password.getBytes());

		return hashToString(sha3);

	}

	private String hashToString(MessageDigest hash) {
		return hashToString(hash.digest());
	}

	private String hashToString(byte[] hash) {
		StringBuffer buff = new StringBuffer();

		for (byte b : hash) {
			buff.append(String.format("%02x", b & 0xFF));
		}

		return buff.toString();
	}

}
