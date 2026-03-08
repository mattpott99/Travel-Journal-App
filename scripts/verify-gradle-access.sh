#!/usr/bin/env bash
set -euo pipefail

URLS=(
  "https://dl.google.com/dl/android/maven2/"
  "https://repo.maven.apache.org/maven2/"
  "https://services.gradle.org/distributions/"
)

echo "Checking network access needed for Android/Gradle dependency resolution..."

failed=0
for url in "${URLS[@]}"; do
  code=$(curl -s -o /dev/null -w "%{http_code}" "$url" || true)
  if [[ "$code" == "200" || "$code" == "301" || "$code" == "302" ]]; then
    echo "✅ $url -> HTTP $code"
  else
    echo "❌ $url -> HTTP $code"
    failed=1
  fi
done

if [[ $failed -eq 1 ]]; then
  cat <<'MSG'

One or more required repositories are unreachable.
Common fixes:
1) Disable restrictive VPN/proxy/firewall rules for Gradle traffic.
2) In Android Studio: Settings > HTTP Proxy, configure your corporate proxy.
3) In ~/.gradle/gradle.properties set:
   systemProp.http.proxyHost=...
   systemProp.http.proxyPort=...
   systemProp.https.proxyHost=...
   systemProp.https.proxyPort=...
4) Ask your network admin to allow:
   - dl.google.com
   - repo.maven.apache.org
   - services.gradle.org

MSG
  exit 1
fi

echo "All required endpoints are reachable."
