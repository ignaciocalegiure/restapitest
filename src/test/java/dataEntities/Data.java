package dataEntities;

public class Data {

    private String apiUrl;

    private int statusCode;

    private Name expectedResponse;

    private Name[] expectedMultipleResponse;

    public Name[] getExpectedMultipleResponse() {
        return expectedMultipleResponse;
    }

    public void setExpectedMultipleResponse(Name[] expectedMultipleResponse) {
        this.expectedMultipleResponse = expectedMultipleResponse;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Name getExpectedResponse() {
        return expectedResponse;
    }

    public void setExpectedResponse(Name expectedResponse) {
        this.expectedResponse = expectedResponse;
    }
}
