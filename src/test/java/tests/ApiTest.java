package tests;

import dataEntities.Data;
import dataEntities.Name;
import helpers.Helper;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiTest {

    public static String BASEURL;

    @BeforeAll()
    public static void setup() throws IOException {
        BASEURL = Helper.getPropertiesFromFile("src/test/resources/config.properties").getProperty("baseURL");
    }

    /**
     * test simple get request, compare status code and expected response
     * all used data in test is specified in json file named same as test case
     * @throws IOException
     */
    @Test()
    public void test_simple_get_request() throws IOException {
        Data test = Helper.convertJsonFileToObject("src/test/resources/files/simpleGetRequest.json", Data.class);
        HttpResponse response = Helper.generateRequestAndExecute(BASEURL + test.getApiUrl());
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(test.getStatusCode());
        Name nameResponse = Helper.convertResponseBodyToObject(response, Name.class);
        assertThat(nameResponse).usingRecursiveComparison().isEqualTo(test.getExpectedResponse());
    }

    /**
     * test array variable on get request, compare status code and expected response
     * all used data in test is specified in json file named same as test case
     * @throws IOException
     */
    @Test()
    public void test_multiple_response() throws IOException {
        Data test = Helper.convertJsonFileToObject("src/test/resources/files/multipleResponse.json", Data.class);
        HttpResponse response = Helper.generateRequestAndExecute(BASEURL + test.getApiUrl());
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(test.getStatusCode());
        Name[] nameResponseList = Helper.convertResponseBodyToObject(response, Name[].class);
        assertThat(nameResponseList).usingRecursiveComparison().isEqualTo(test.getExpectedMultipleResponse());
    }

    /**
     * test expected error response
     * more test cases can be added, create test case json file with same base name and status code expected, then add the  status code on the csv source
     * all used data in test is specified in json file named same as test case
     * @param errorCode
     * @throws IOException
     */
    @ParameterizedTest(name = "test_error_response_{0}")
    @CsvSource({"401","422"})
    public void test_error_response(String errorCode) throws IOException {
        Data test = Helper.convertJsonFileToObject("src/test/resources/files/errorResponse-"+errorCode+".json", Data.class);
        HttpResponse response = Helper.generateRequestAndExecute(BASEURL + test.getApiUrl());
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(test.getStatusCode());
        Name nameResponse = Helper.convertResponseBodyToObject(response, Name.class);
        assertThat(nameResponse).usingRecursiveComparison().isEqualTo(test.getExpectedResponse());
    }

}
