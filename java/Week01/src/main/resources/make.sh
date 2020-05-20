#!/bin/bash

cd ../java

echo $'# checkstyle-----------------------------------------------------------' > ../resources/out.txt
checkstyle -algs4 Percolation.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 PercolationStats.java >> ../resources/out.txt



echo $'\n\n\n# pmd-algs4-----------------------------------------------------------' >> ../resources/out.txt
pmd -algs4 Percolation.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 PercolationStats.java >> ../resources/out.txt




echo $'\n\n\n# spotbugs -----------------------------------------------------------' >> ../resources/out.txt
cd ../../../target/classes/
spotbugs Percolation.class PercolationStats.class &>> ../../src/main/resources/out.txt
cd ../../src/main/java



echo $'# Copying files-----------------------------------------------------------' >> ../resources/out.txt
zip Percolation.zip Percolation.java PercolationStats.java
mv Percolation.zip /mnt/ix2-200/rommelst/



echo $'# END -----------------------------------------------------------' >> ../resources/out.txt