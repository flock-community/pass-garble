# pass-garble
Kotlin Multiplatform project for password generation and validation

```shell
#TODO
````


## Releasing

### JS

- There's a `dev.petuska.npm.publish` plugin that should assist in publishing to npm, but it's not working (yet)
- For now: run

```shell
./gradlew publishJsNpmPublicationToNpmJs

```

- Navigate to `build/publications/npm/js`
- Run `npm install`
- Publish: `npm publish --access public`. The `--access` flag is necessary.
