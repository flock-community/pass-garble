# Pass Garble JVM CLI Example implementation

This example shows how to integrate a kotlin multiplatform library into a jvm cli application

It makes use of [pico cli](https://picocli.info/)to create the CLI tool, and [GraalVM](https://www.graalvm.org/) to turn it into a native image. 


## Getting started

1. Run `./gradlew build`
2. Run `./gradlew nativeImage`
3. Get yourself a password 
``` 
./app/build/graal/pass-garble-cli \
   -l 10 \
   --upper=false \
   --special=false
```
4.  Or get some help:
``` 
./app/build/graal/pass-garble-cli --help
```


### Link with root project
As a default, this example uses a published version of the root project. Be sure to run `gradle publishToMavenLocal` in the root project

1. Run a full build of the root project (`./gradlew build` should do the trick)
2. Publish the build to maven local: `./gradlew publishToMavenLocal`
