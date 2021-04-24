Description
---
Sample SOAP web service developed in Spring Boot 2.x.

Service exposes few country information such as population, capital and currency.
Those info are hard-coded for just 3 countries (Spain, Poland and United Kingdom).

Run
---
In a shell :

```
$ mvn springboot:run --spring.profiles.active=dev
```

WSDL
----

http://localhost:8081/ws/countries.wsdl


Request example
----

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getCountryRequest>
         <gs:name>Spain</gs:name>
      </gs:getCountryRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
Response example
---
```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:getCountryResponse xmlns:ns2="http://spring.io/guides/gs-producing-web-service">
         <ns2:country>
            <ns2:name>Spain</ns2:name>
            <ns2:population>46704316</ns2:population>
            <ns2:capital>Madrid</ns2:capital>
            <ns2:currency>EUR</ns2:currency>
         </ns2:country>
      </ns2:getCountryResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

Advanced Config
---
It's possible to change response timing using properties/env variables:

| Property | Environment Variable | Description |
| ---      | ---                  | ---         |
| **response.delay.in.mills** | **RESPONSE_DELAY_IN_MILLS** | response time in milliseconds (defaults to 0) |
| **response.random.delay** | **RESPONSE_RANDOM_DELAY** | response time uniformly distributed in (0, ${response.delay.in.mills}) range (defaults to false) |
