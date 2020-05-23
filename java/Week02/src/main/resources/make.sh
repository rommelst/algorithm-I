#!/bin/bash

cd ../java

echo $'# checkstyle-----------------------------------------------------------' > ../resources/out.txt
checkstyle -algs4 Deque.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 RandomizedQueue.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
checkstyle -algs4 Permutation.java >> ../resources/out.txt



echo $'\n\n\n# pmd-algs4-----------------------------------------------------------' >> ../resources/out.txt
pmd -algs4 Deque.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 RandomizedQueue.java >> ../resources/out.txt

echo $'\n' >> ../resources/out.txt
pmd -algs4 Permutation.java >> ../resources/out.txt




echo $'\n\n\n# spotbugs -----------------------------------------------------------' >> ../resources/out.txt
cd ../../../target/classes/
spotbugs Deque.class Deque\$ArrayIterator.class Deque\$Node.class Deque\$LinkedListIterator.class RandomizedQueue.class RandomizedQueue\$ArrayIterator.class Permutation.class &>> ../../src/main/resources/out.txt
cd ../../src/main/java



echo $'# Copying files-----------------------------------------------------------' >> ../resources/out.txt
zip queues.zip Deque.java RandomizedQueue.java Permutation.java
mv queues.zip /mnt/ix2-200/rommelst/



echo $'# END -----------------------------------------------------------' >> ../resources/out.txt