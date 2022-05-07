# Pass Garble JS Example implementation

This example shows how to integrate a kotlin multiplatform library into a web application


## Getting started

1. Run `npm install`
2. Run `npm start`. Pass garble JS should run on port 1234

### Link with root project
As a default, the example is linked to a published version of `@flock/pass-garble`. To see local changes, it is possible to link with the local version of the lib.

1. Run a full build of the root project (`./gradle build` should do the trick)
2. Link with the local npm package
   1. From the root of the project, navigate to the package located in `build/publications/npm/js`
   2. `npm link`
   3. From the current folder, link using `npm link pass-garble`
