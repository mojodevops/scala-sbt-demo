#!/bin/bash

# demo start script

set -euo pipefail

# resolve links - $0 may be a softlink
PRG="$0"

while [ -L "$PRG" ]; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' >/dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")/"$link"
  fi
done

PRGDIR=$(dirname "$PRG")
cd "$PRGDIR"
bash hello-scala
