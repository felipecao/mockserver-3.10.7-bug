# MockServer JUnit Tutorial

Examples how to mock HTTP servers for test using Java, [JUnit] and [MockServer].

Please feel free to take a look at my blog at [www.hascode.com] for the full tutorial.

**Running Tests**

Run the tests using your IDE of choice or using Maven:

```
mvn test
```

**Start standalone mock server**

Simply start Maven with the designated profile __"start-mockserver"__

```
mvn -Pstart-mockserver test
```

----

**2016 Micha Kops / hasCode.com**

   [MockServer]:http://www.mock-server.com/
   [www.hascode.com]:http://www.hascode.com/
   [JUnit]:http://junit.org/
