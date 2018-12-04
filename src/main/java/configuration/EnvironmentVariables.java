package configuration;

public final class EnvironmentVariables {
    private static final String systemBaseUrl = System.getenv("SYS_BASE_URL");

    //Gatekeeper URL Defs
    private static final String gatekeeperUrl = systemBaseUrl + System.getenv("GATEKEEPER_URL");
    private static final String gatekeeperAuthoriseUrl = gatekeeperUrl + System.getenv("GK_AUTH_URL");
    private static final String gatekeeperTokenUrl = gatekeeperUrl + System.getenv("GK_TOKEN_URL");
    private static final String gatekeeperRevokeUrl = gatekeeperUrl + System.getenv("GK_REVOKE_URL");
    private static final String gatekeeperJWKUrl = gatekeeperUrl + System.getenv("GK_JWK_URL");
    private static final String gatekeeperIntrospectUrl = gatekeeperUrl + System.getenv("GK_INTROSPECT_URL");

    //Aberfitness Info
    private static final String aberfitnessClientId = System.getenv("ABERFITNESS_CLI_ID");
    private static final String aberfitnessClientSecret = System.getenv("ABERFITNESS_CLI_SECRET");

    public static String getAberfitnessClientId() {
        return aberfitnessClientId;
    }

    public static String getAberfitnessClientSecret() {
        return aberfitnessClientSecret;
    }

    public static String getGatekeeperUrl() {
        return gatekeeperUrl;
    }

    public static String getGatekeeperAuthoriseUrl() {
        return gatekeeperAuthoriseUrl;
    }

    public static String getGatekeeperTokenUrl() {
        return gatekeeperTokenUrl;
    }

    public static String getGatekeeperRevokeUrl() {
        return gatekeeperRevokeUrl;
    }

    public static String getGatekeeperJWKUrl() {
        return gatekeeperJWKUrl;
    }

    public static String getGatekeeperIntrospectUrl() {
        return gatekeeperIntrospectUrl;
    }

    public static boolean isAberfitnessDataPresent() {
        return aberfitnessClientId != null && aberfitnessClientSecret != null;
    }

}
