Java SDK to interact with Bouncer's API.

https://www.usebouncer.com

API documentation can be found at https://docs.usebouncer.com/

```java

private BouncerClient client = BouncerClient.getInstance()
    .withApiKey("<YOUR_API_KEY>");

try {

  EmailRecord result = client.verifyEmail("hello@usebouncer.com");

  System.out.println(result);

} catch (BouncerException e) {
  System.out.println(e.getMessage());
}

```
