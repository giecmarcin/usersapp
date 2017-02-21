package com.mgiec.services;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Converter {
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    public static byte[] base64Decode(String s) throws IOException {
        return new BASE64Decoder().decodeBuffer(s);
    }
}
