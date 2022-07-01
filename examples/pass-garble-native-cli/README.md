# Pass Garble Native CLI Example implementation

This example shows how to integrate a kotlin multiplatform library into a native cli application

## Getting started

1. Run `./gradlew build`
3. Get yourself a password `./build/bin/native/releaseExecutable/pass-garble-native-cli.kexe`
4.  Or get yourself one in debug mode `./build/bin/native/debugExecutable/pass-garble-native-cli.kexe`


### Link with root project
As a default, this example uses a published version of the root project. Be sure to run `gradle publishToMavenLocal` in the root project

1. Run a full build of the root project (`./gradlew build` should do the trick)
2. Publish the build to maven local: `./gradlew publishToMavenLocal`
