#!/bin/bash

cd ../java

echo $'# checkstyle-----------------------------------------------------------' > ../resources/out.txt
checkstyle -algs4 Point.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 BruteCollinearPoints.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 FastCollinearPoints.java >> ../resources/out.txt



echo $'\n\n\n# pmd-algs4-----------------------------------------------------------' >> ../resources/out.txt
pmd -algs4 Point.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 BruteCollinearPoints.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 FastCollinearPoints.java >> ../resources/out.txt



echo $'\n\n\n# spotbugs -----------------------------------------------------------' >> ../resources/out.txt
cd ../../../target/classes/
spotbugs Point\$SlopeComparator.class Point.class BruteCollinearPoints.class FastCollinearPoints.class LineSegment.class &>> ../../src/main/resources/out.txt
cd ../../src/main/java



echo $'# Copying files-----------------------------------------------------------' >> ../resources/out.txt
zip CollinearPoint.zip Point.java BruteCollinearPoints.java FastCollinearPoints.java
mv CollinearPoint.zip /mnt/ix2-200/rommelst/



echo $'# END -----------------------------------------------------------' >> ../resources/out.txt