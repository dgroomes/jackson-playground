#!/usr/bin/env bash
# Generate a lot of test data to a temp file. The data is JSON-formatted point data which includes "x" and "y"
# fields. E.g.
#     { "x": 1, "y": 2 }
#
# This script uses an "awk" program to generate the test data which is over 100 times faster than printing inside of a
# bash for loop. To generate 10 million lines, the time difference was 10 seconds compared to 24 *minutes* on my
# computer.

set -eu

mkdir -p tmp
DATA_FILE=tmp/json_data
LINES=10000000

# Truncate (if it exists) and create the data file (if it does not already exist)
>"$DATA_FILE"

jsonTemplate='{ "x": %d, "y": %d }'
time awk \
  -v jsonTemplate="$jsonTemplate" \
  -v numberOfLines=$LINES '
  BEGIN {
    for (i = 1; i <= numberOfLines; i++) {
      x = i;
      y = numberOfLines - x;
      printf jsonTemplate "\n", x, y;
    }
  }
' >>"$DATA_FILE"

echo "Generated a test file of JSON dummy data at $DATA_FILE."
wc -l "$DATA_FILE"
ls -lh "$DATA_FILE"
