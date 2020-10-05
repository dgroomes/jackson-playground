#!/usr/bin/env bash
# Generate a lot of test data to a temp file. The data is JSON-formatted point data which includes an "x" and "y"
# fields. E.g.
#     { "x": 1, "y": 2 }
#
# Why does this run so slowly? I know 10 million is a lot of records but still this is so slow. It took 24 minutes to
# run on my computer. I think 10 million individual invocations of printf is the problem. Maybe an awk program could be
# used to generate a large file? We could express the interation inside of awk and we wouldn't pay the start up cost 10
# million times and instead just pay once.

set -eu

mkdir -p tmp
DATA_FILE=tmp/json_data
LINES=10000000

# Truncate (if it exists) and create the data file (if it does not already exist)
> "$DATA_FILE"

time for (( i = 0; i < $LINES; i++ )); do
  x=$i
  y=$(($LINES - $x))
  printf '{ "x": "%d", "y": "%d" }\n' $x $y >> "$DATA_FILE"
done

echo "Generated a test file of JSON dummy data at $DATA_FILE."
wc -l "$DATA_FILE"
ls -lh "$DATA_FILE"
