#!/usr/bin/env bash

readonly BASEDIR=$(cd "$(dirname "$0")" && pwd) # where the script is located
readonly CALLDIR=$(pwd)                         # where it was called from

ask_if_empty () {
    local value="$1"
    local default="$2"
    local message="$3"
    local options="${4:-}"  # pass "-s" for passwords
    if [[ -z "$value" ]]; then
        read $options -p "$message [$default] " value
    fi
    echo "${value:-$default}"
}

error () {
    local msg=$1
    echo "Error: $msg"
    exit 1
}

cd "$BASEDIR/.."

type=$(ask_if_empty "${1:-}" "patch" "Enter the type of release (major, minor, patch):" "")
[[ $type != "major" && $type != "minor" && $type != "patch" ]] && error "Invalid release type '$type'"

version=$(sbt -Dsbt.log.noformat=true -Drelease=$type 'show version' | tail -1 | cut -d' ' -f2)
sbt clean test
[[ $? != 0 ]] && error "Stopping release of $type version $version"

git tag v$version
git push origin master --tags
sbt universal:packageBin githubRelease
