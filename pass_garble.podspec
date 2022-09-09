Pod::Spec.new do |spec|
    spec.name                     = 'pass_garble'
    spec.version                  = '0.0.3-SNAPSHOT'
    spec.homepage                 = 'https://github.com/flock-community/pass-garble'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'PassGarble - a kotlin multiplatform password generator'
    spec.vendored_frameworks      = 'build/cocoapods/framework/PassGarbleMacOS.framework'
    spec.libraries                = 'c++'
    spec.osx.deployment_target = '11.0'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':',
        'PRODUCT_MODULE_NAME' => 'PassGarbleMacOS',
    }
                
    spec.script_phases = [
        {
            :name => 'Build pass_garble',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end