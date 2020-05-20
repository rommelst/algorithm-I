#!/bin/bash

cd ../java

echo $'# checkstyle-----------------------------------------------------------' > ../resources/out.txt
checkstyle -algs4 PointSET.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 KdTree.java >> ../resources/out.txt



echo $'\n\n\n# pmd-algs4-----------------------------------------------------------' >> ../resources/out.txt
pmd -algs4 PointSET.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 KdTree.java >> ../resources/out.txt




echo $'\n\n\n# spotbugs -----------------------------------------------------------' >> ../resources/out.txt
cd ../../../target/classes/
spotbugs PointSET.class KdTree.class KdTree\$Node.class &>> ../../src/main/resources/out.txt
cd ../../src/main/java



echo $'# Copying files-----------------------------------------------------------' >> ../resources/out.txt
zip KdTree.zip PointSET.java KdTree.java
mv KdTree.zip /mnt/ix2-200/rommelst/



echo $'# END -----------------------------------------------------------' >> ../resources/out.txt