# mockserver-netty:3.10.7 bug

Based on the example available at https://bitbucket.org/hascode/mockserver-junit-tutorial.git

## How to reproduce the bug

Run `mvn clean test` and have a look at the test results.

## How to fix it

Go to `pom.xml` and downgrade `mockserver-netty` dependency version to `3.10.4` and watch the tests pass. 