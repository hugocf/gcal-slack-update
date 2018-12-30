#!/usr/bin/env bash

readonly BASEDIR=$(cd "$(dirname "$0")" && pwd) # where the script is located
readonly CALLDIR=$(pwd)                         # where it was called from

cd "$BASEDIR/.."

sbt stage
./target/universal/stage/bin/gcal-slack-update $*
