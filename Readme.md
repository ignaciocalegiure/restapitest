##Rest Api Testing Example

I will be using [Nationalize](https://nationalize.io/), which  the nationality of a person given their name. It's used for analytics, ad segmenting, demographic statistics etc.

###Frameworks:

* Junit 5
* Assertj
* Apache HttpClient

###Test:

* simple_get_request: test that the api returns information for a single-name request
* test_multiple_response: test that the api can return multiple name information in one request
* test_error_response_{statusCode}: parameterized test, test how the api responds to expected errors
