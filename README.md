# pass-garble

Kotlin Multiplatform project for password generation and validation

## Rationale

### iOS/macOS integration

To make kotlin multiplatform work for iOS and macOS,
the [cocoaPods approach was followed](https://kotlinlang.org/docs/native-cocoapods.html#update-podfile-for-xcode), as
opposed to
the [connection the app manually](https://kotlinlang.org/docs/multiplatform-mobile-integrate-in-existing-app.html#connect-the-framework-to-your-ios-project)
.

## Examples

To show how a language/platform can integrate with a kotlin multi-platform library, some examples are available in
the [examples](./examples) directory

1. [Pass garble Android](./examples/pass-garble-android/README.md)
2. [Pass garble JS](./examples/pass-garble-js/README.md)
3. [Pass garble MacOs](./examples/PassGarbleMac/README.md)
4. [Pass garble CLI (JVM)](./examples/pass-garble-cli/README.md)

## Contributing

### Work locally

For local development to work smoothly, especially with
the [Cocoapods integration](https://kotlinlang.org/docs/native-cocoapods.html#set-up-the-environment-to-work-with-cocoapods)
, the following is needed:

1. Install the CocoaPods dependency manager:

```bash
$ sudo gem install cocoapods
```

2. Install the cocoapods-generate plugin:

```bash

$ sudo gem install cocoapods-generate
```

Importing the project, and running a build (`./gradlew build`) should create a `PassGarble.podspec` file.

#### Releasing

##### JS

- There's a `dev.petuska.npm.publish` plugin that should assist in publishing to npm, but it's not working (yet)
- For now: run

```shell
$ ./gradlew publishJsNpmPublicationToNpmJs
```

- Navigate to `build/publications/npm/js`
- Run `npm install`
- Publish: `npm publish --access public`. The `--access` flag is necessary.
