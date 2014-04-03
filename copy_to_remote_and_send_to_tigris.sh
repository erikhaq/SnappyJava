#! /bin/sh
## shoutout to dj guseng ##
## remember to change username ##
filepath=`pwd`
echo "Working directory is "$filepath
cd ..
echo "Creating temporary folder and copying compiler"
ssh erikhaq@u-shell.csc.kth.se 'mkdir ~/tmp/tmpkomp'
ssh erikhaq@u-shell.csc.kth.se 'mkdir ~/tmp/tmpkomp/src'
scp -r $filepath/src erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/
scp $filepath/SnappyJava.g4 erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/SnappyJava.g4
ssh erikhaq@u-shell.csc.kth.se 'mkdir ~/tmp/tmpkomp/lib'
scp -r $filepath/lib erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/
scp $filepath/DESC erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/DESC
scp $filepath/build.xml erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/build.xml
scp $filepath/report.pdf erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/report.pdf
scp $filepath/tigrissubmit.sh erikhaq@u-shell.csc.kth.se:~/tmp/tmpkomp/tigrissubmit.sh
echo "--------------------------"
echo "Submitting to Tigris"
ssh erikhaq@u-shell.csc.kth.se 'sh ~/tmp/tmpkomp/tigrissubmit.sh ~/tmp/tmpkomp'
 
echo "--------------------------"
 
echo "Deleting temporary folder"
ssh erikhaq@u-shell.csc.kth.se 'rm -r ~/tmp/tmpkomp'
