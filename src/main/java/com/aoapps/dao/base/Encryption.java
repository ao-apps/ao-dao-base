/*
 * ao-dao-base - Simple data access objects framework base for implementations.
 * Copyright (C) 2011, 2013, 2015, 2016, 2019, 2020, 2021, 2022  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-dao-base.
 *
 * ao-dao-base is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-dao-base is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-dao-base.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.dao.base;

import com.aoapps.lang.exception.WrappedException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Provides encryption routines.
 */
public final class Encryption {

  /** Make no instances. */
  private Encryption() {
    throw new AssertionError();
  }

  // TODO: Use Strings.convertToHex?
  private static String hexEncode(byte[] bytes) {
    int len = bytes.length;
    StringBuilder sb = new StringBuilder(len * 2);
    for (int c = 0; c < len; c++) {
      int b = bytes[c];
      sb.append(hexChars[(b >> 4) & 0xf]);
      sb.append(hexChars[b & 0xf]);
    }
    return sb.toString();
  }

  /**
   * Performs a one-way hash of the plaintext value using SHA-1.
   *
   * @exception  WrappedException  if any problem occurs.
   *
   * @deprecated  Use salted algorithm, update database of stored passwords as passwords are validated
   *
   * @see  com.aoapps.security.HashedPassword for proper password hashing
   * @see  com.aoapps.security.HashedKey for stronger hashing
   */
  @Deprecated(forRemoval = true)
  // TODO: Return base64 URL safe, no padding?
  public static String hash(String plaintext) throws WrappedException {
    try {
      return hexEncode(MessageDigest.getInstance("SHA-1").digest(plaintext.getBytes(StandardCharsets.UTF_8)));
    } catch (NoSuchAlgorithmException e) {
      throw new WrappedException(e);
    }
  }

  /**
   * Note: This is not a {@linkplain SecureRandom#getInstanceStrong() strong instance} to avoid blocking.
   */
  private static final SecureRandom secureRandom = new SecureRandom();

  private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

  /**
   * Generates a random key.
   */
  public static String generateKey() {
    byte[] bytes = new byte[32];
    secureRandom.nextBytes(bytes);
    char[] chars = new char[64];
    for (int c = 0; c < 32; c++) {
      byte b = bytes[c];
      chars[c * 2] = hexChars[(b & 255) >>> 4];
      chars[c * 2 + 1] = hexChars[b & 15];
    }
    return new String(chars);
  }

  /*
  public static void main(String[] args) {
    //args = new String[] {"test"};
    if (args.length == 0) {
      System.err.println("usage: "+Encryption.class.getName()+" plaintext ...");
      System.exit(1);
    } else {
      for (String arg : args) {
        System.out.println(arg+'\t'+hash(arg));
      }
    }
  }
   */
}
