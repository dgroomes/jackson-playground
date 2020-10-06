#!/usr/bin/env bash
# Generate a lot of test data to a temp file. The data is JSON-formatted point data which includes "x" and "y"
# fields. E.g.
#     { "x": 1, "y": 2 }
#
# This script uses a Perl program to generate the test data which is over 100 times faster than printing inside of a
# bash for loop. To generate 10 million lines, the time difference was 9 seconds compared to 24 *minutes* on my
# computer.

set -eu

mkdir -p tmp
DATA_FILE=tmp/json_data
export NUMBER_OF_LINES=$(( 100 * 1000 * 1000 ))

time perl -f <(cat <<'EOF'
    $json_template='{ "x": %d, "y": %d }';
    $number_of_lines = $ENV{NUMBER_OF_LINES};

    for ($i = 1; $i <= $number_of_lines; $i++) {
      $x = $i;
      $y = $number_of_lines - $i;
      printf $json_template . "\n", $x, $y;
    }
EOF
) > "$DATA_FILE"

echo "Generated a test file of JSON dummy data at $DATA_FILE."
wc -l "$DATA_FILE"
ls -lh "$DATA_FILE"
