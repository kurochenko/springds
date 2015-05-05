package net.kurochenko.springds.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author kurochenko
 */
public class ProfileProperties {

    public static final String PROFILE_DEFAULT = "default";

    /**
     * Loads settings for default profile
     *
     * @return properties for default profile
     */
    public static Properties getProps()  {
        Properties defaults = new Properties();
        try {
            defaults.load(getProfileResource(PROFILE_DEFAULT));
            return defaults;
        } catch (IOException e) {
            throw new RuntimeException("Default profile does not exist.");
        }
    }

    /**
     * Loads settings according to profile name
     *
     * @param profileName used for loading properties file of same name
     * @return properties for given profile
     * @throws IOException if profile with given name does not exist
     */
    public static Properties getProps(String profileName) throws IOException {
        Properties props = new Properties(getProps());
        InputStream profileResource = getProfileResource(profileName);
        if (profileResource == null) {
            throw new IOException(String.format("Profile '%1$s' does not exist", profileName));
        }
        props.load(profileResource);
        return props;
    }

    private static InputStream getProfileResource(String profileName) {
        return ProfileProperties.class.getClassLoader().getResourceAsStream(String.format("profiles/%1$s.properties", profileName));
    }
}
