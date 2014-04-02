#! /bin/sh
## shoutout to dj guseng ##
filepath=`pwd`
echo "Working directory is "$filepath
cd ..
echo "Creating temporary folder and copying compiler"
mkdir tmpkomp
mkdir tmpkomp/src
cp -R $filepath/src tmpkomp/
cp $filepath/src/gen/SnappyJava.g4 tmpkomp/src/gen/SnappyJava.g4
mkdir tmpkomp/lib
cp -R $filepath/lib tmpkomp/
cp $filepath/DESC tmpkomp/DESC
cp $filepath/build.xml tmpkomp/build.xml
cp $filepath/report.pdf tmpkomp/report.pdf
echo "--------------------------"
echo "Submitting to Tigris"
#sh $filepath/tigrissubmit.sh tmpkomp
 
echo "--------------------------"
 
echo "Deleting temporary folder"
#rm -r tmpkomp