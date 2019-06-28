package model;

public class LoginSignupResponse {
    private boolean success;
    private String status;
    private String token;

    public boolean getSuccess() {
        return success;
    }

    public String getStatus() {
        return status;
    }
    public String getToken() {
        return token;
    }
}