#!/usr/bin/env bash
# Generate a lot of dummy/test JSON data to a temp file.

set -eu

mkdir -p tmp
DATA_FILE=tmp/json_data
LINES=1000000

# Truncate (if it exists) and create the data file (if it does not already exist)
> "$DATA_FILE"

time for (( i = 0; i < $LINES; i++ )); do
  printf '{ "field_a": "%d", "field_b": "value_b" }\n' $i >> "$DATA_FILE"
done

echo "Generated a test file of JSON dummy data at $DATA_FILE."
wc -l "$DATA_FILE"
ls -lh "$DATA_FILE"
