package com.goit.gojavaonline.spring.mvc.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PasswordLoader {
    private static final String HASHES_FILE_ENV_VARIABLE = "PASSWORD_HASHES_FILE";
    private static final String HASHES_FILE_NAME = "/adminPasswords.properties";
    private static File passwordHashesFile;

    static {
        String hashesFilePath = System.getenv( HASHES_FILE_ENV_VARIABLE );
        if ( StringUtils.isEmpty( hashesFilePath ) ) {
            URL url = PasswordLoader.class.getResource( HASHES_FILE_NAME );
            try {
                passwordHashesFile = new File( url.toURI() );
            } catch ( URISyntaxException e ) {
                e.printStackTrace();
                passwordHashesFile = new File( HASHES_FILE_NAME );
            }
        } else {
            passwordHashesFile = new File( hashesFilePath );
        }
        System.out.println( "Trying to use admin hashes from [" + passwordHashesFile.getAbsolutePath() + "]" );
    }

    public static String loadPasswordHash( String username ) {
        Map<String, String> hashes = getUserPasswordHashes();
        return hashes.get( username + ".hash" );
    }

    private static Map<String, String> getUserPasswordHashes() {
        if ( passwordHashesFile == null || !passwordHashesFile.exists() ) {
            return Collections.emptyMap();
        }

        Properties props = new Properties();
        try (InputStream stream = new FileInputStream( passwordHashesFile )) {
            props.load( stream );
        } catch ( Exception e ) {
            System.err.println( "Failed to load users credentials from the file [" + passwordHashesFile.getAbsolutePath() + "]" );
            e.printStackTrace( System.err );
        }

        Map<String, String> usersHashes = new HashMap<>();
        for ( Map.Entry<Object, Object> entry : props.entrySet() ) {
            usersHashes.put( (String) entry.getKey(), (String) entry.getValue() );
        }

        return usersHashes;
    }
}