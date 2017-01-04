#!/usr/bin/env bash

# Fail if any command fails
set -e

# Publish to Maven Central, but only if
# - we're not building a pull request, and
# - we're building from the master branch, and
# - have credentials available in secure env variables.
function publish {
  if [[ $TRAVIS_PULL_REQUEST == "false" && $TRAVIS_BRANCH == "master" && $TRAVIS_SECURE_ENV_VARS == "true" && $(cat version.sbt) =~ "-SNAPSHOT" ]]; then
    sbt publish
  fi
}

publish
