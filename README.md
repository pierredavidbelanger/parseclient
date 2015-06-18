# ParseClient

A [Parse.com](Parse.com) Java Client.

Ths is a non-official [Parse.com](Parse.com) library that wrap JAX-RS to offer a high level client to [Parse.com REST API](https://www.parse.com/docs/rest/).

This library should be considered beta, and you should expect its API to change until I bump the major version to `1`. After that, I will try to follow [Semantic Versioning](http://semver.org/).

## Summary 

This library offer a flexible application perspective system that let one interact with [Parse.com](Parse.com) anonymously, as a logged in user or as the master, in one or more application, concurrently in the same code. So this library is NOT a clone of the [Parse.com Android client](https://www.parse.com/docs/android/).

This flexibility comes at the expense of a more complex way to interact with the library (ie: no magic static singleton everywhere).

If you are looking for something easier to use, have a look at the excelent also non-official [parse4j](https://github.com/thiagolocatelli/parse4j) library.

## Getting Started

First include this library:

*NOTE: I did not release to Maven Central yet. You will need to clone the project, and `mvn install` it locally.*

```xml
<dependency>
    <groupId>ca.pjer</groupId>
    <artifactId>parseclient</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

### Dependencies

This library as no mandatory dependency as it is based on JAX-RS. But to be able to use it, you will need at least a JAX-RS implementation that have support for JSON.

The easiest way to get started is to include [Jersey](https://jersey.java.net/) client library, with its [Jackson](http://wiki.fasterxml.com/JacksonHome) media plugin, because doing so will enable you to use the out-of-the-box zero configuration utility class to get started quickly.

```xml
<dependency>
    <groupId>org.glassfish.jersey.core</groupId>
    <artifactId>jersey-client</artifactId>
    <version>2.18</version>
</dependency>

<dependency>
    <groupId>org.glassfish.jersey.media</groupId>
    <artifactId>jersey-media-json-jackson</artifactId>
    <version>2.18</version>
</dependency>
```

### First steps

Create a JAX-RS client, fully configured with the conveniently included `SimpleJerseyWithJacksonConfiguration` class:

```java
Client client = ClientBuilder.newClient(new SimpleJerseyWithJacksonConfiguration());

```

Create a ParseClient that wrap this JAX-RS `client`:
 
```java
ParseClient parseClient = ParseClient.create(client);
```

From the `parseClient`, create an application object and provide your `Application ID` and `REST API Key` (in the Settings -> Keys section of your [Parse.com](Parse.com) dashboard):
 
```java
Application application = parseClient.application("...").usingRestApiKey("...");
```

From this `application`, create an anonymous perspective (ie: see and operate on your data only in a way an anonymous user can do):

```java
Perspective anonymousPerspective = application.asAnonymous();
```

From this `anonymousPerspective`, get an `ObjectResources`, a kind of DAO to operate on the `GameScore` class:

```java
ObjectResources<ParseObject> gameScores = anonymousPerspective.withObjects("GameScore");

```

From the `gameScores` DAO, perform a query operation to fetch all objects and print their IDs:

```java
Iterable<ParseObject> parseObjects = gameScores.basicQuery().find();
for (ParseObject parseObject : parseObjects)
    System.out.println(parseObject.getObjectId());

```

Here the complete example:

```java
import ca.pjer.parseclient.*;
import ca.pjer.parseclient.support.SimpleJerseyWithJacksonConfiguration;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ParseClientTest {

	@Test
	public void getStartedTest() {

		Client client = ClientBuilder.newClient(new SimpleJerseyWithJacksonConfiguration());

		ParseClient parseClient = ParseClient.create(client);

		Application application = parseClient.application("...").usingRestApiKey("...");

		Perspective anonymousPerspective = application.asAnonymous();

		ObjectResources<ParseObject> gameScores = anonymousPerspective.withObjects("GameScore");

		Iterable<ParseObject> parseObjects = gameScores.basicQuery().find();
		for (ParseObject parseObject : parseObjects)
			System.out.println(parseObject.getObjectId());
	}
}

```

## Going further

There is more than that. This library already supports aynchrounous operations, replayable operation, signup, login, users and sessions management, batch operations, custom object mapping.

Expect more documentation to come.
