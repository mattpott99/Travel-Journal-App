#!/usr/bin/env bash
set -euo pipefail

# Recreate gradle-wrapper.jar without evaluating this Android project.
# Useful when binary files are excluded from commits.

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
TMP_DIR="$(mktemp -d)"
trap 'rm -rf "$TMP_DIR"' EXIT

pushd "$TMP_DIR" >/dev/null
printf '' > settings.gradle.kts
gradle wrapper --gradle-version 8.7 --no-validate-url --no-daemon >/dev/null
popd >/dev/null

mkdir -p "$ROOT_DIR/gradle/wrapper"
cp "$TMP_DIR/gradle/wrapper/gradle-wrapper.jar" "$ROOT_DIR/gradle/wrapper/gradle-wrapper.jar"

echo "Recreated $ROOT_DIR/gradle/wrapper/gradle-wrapper.jar"
