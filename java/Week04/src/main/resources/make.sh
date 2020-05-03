#!/bin/bash

cd ../java

echo $'# checkstyle-----------------------------------------------------------' > ../resources/out.txt
checkstyle -algs4 Board.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 Solver.java >> ../resources/out.txt



echo $'\n\n\n# pmd-algs4-----------------------------------------------------------' >> ../resources/out.txt
pmd -algs4 Board.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 Solver.java >> ../resources/out.txt




echo $'\n\n\n# spotbugs -----------------------------------------------------------' >> ../resources/out.txt
cd ../../../target/classes/
spotbugs Board.class Solver.class Solver\$Node.class &>> ../../src/main/resources/out.txt
cd ../../src/main/java



echo $'# Copying files-----------------------------------------------------------' >> ../resources/out.txt
zip EightPuzzleProblem.zip Board.java Solver.java
mv EightPuzzleProblem.zip /mnt/ix2-200/rommelst/



echo $'# END -----------------------------------------------------------' >> ../resources/out.txt