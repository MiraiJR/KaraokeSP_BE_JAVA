package com.miraijr.karaoke.shared.constants;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    public static String bearer = "Bearer ";

    @Value("${jwt.secret_key}")
    public static String SECRET_KEY;
}
