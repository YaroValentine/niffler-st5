package guru.qa.niffler.config;


public class DockerConfig implements Config {

    @Override
    public String getDBHost() {
        return "niffler-all-db";
    }

    @Override
    public String getDBLogin() {
        return "postgres";
    }

    @Override
    public String getDBPassword() {
        return "secret";
    }

    @Override
    public int getDBPort() {
        return 5432;
    }

    @Override
    public String getSpendUrl() {
        return "niffler-spend";
    }

    @Override
    public String getFrontUrl() {
        return "http://niffler-frontend:3000";
    }

    @Override
    public String getUserdataUrl() {
        return null;
    }

    @Override
    public String getCurrencyGrpcAddress() {
        return "niffler-currency";
    }

    @Override
    public int getCurrencyGrpcPort() {
        return 8092;
    }

    @Override
    public String getAuthUrl() {
        return "http://niffler-auth:9000";
    }

    @Override
    public String getUserdataGrpcAddress() {
        return "niffler-userdata";
    }

    @Override
    public int getUserdataGrpcPort() {
        return 8090;
    }
}
