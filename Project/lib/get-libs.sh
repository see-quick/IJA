#!/usr/bin/env bash

server=http://www.stud.fit.vutbr.cz/~xkunic01/IJA
cd lib
wget https://github.com/google/guava/releases/download/v21.0/guava-21.0.jar  # download google library...
cd ../src/srcGUI/resources/pictures/
wget $server"/pics/BlockAdd_1_1(100x100).png"
wget $server"/pics/BlockAdd_2_1(100x100).png"
wget $server"/pics/BlockConv_T1_T2(100x100).png"
wget $server"/pics/BlockConv_T2_T1(100x100).png"
wget $server"/pics/BlockDiv_2_1(100x100).png"
wget $server"/pics/BlockMod_2_2(100x100).png"
wget $server"/pics/BlockMul_1_1(100x100).png"
wget $server"/pics/BlockSub_1_1(100x100).png"
wget $server"/pics/BlockSub_2_1(100x100).png"