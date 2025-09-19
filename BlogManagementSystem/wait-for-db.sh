#!/usr/bin/env bash
# wait-for-db.sh: waits for PostgreSQL before starting app

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

until pg_isready -h "$host" -p "$port" > /dev/null 2>&1; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 2
done

>&2 echo "Postgres is up - executing command"
exec $cmd
