#!/usr/bin/env bash

readonly BASEDIR=$(cd "$(dirname "$0")" && pwd) # where the script is located
readonly CALLDIR=$(pwd)                         # where it was called from

cd "$BASEDIR/.."

sbt universal:packageBin
ls -l ./target/universal/
