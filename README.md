# HDFSTREE

This is a simple standalone tool that emulates the Unix 'tree' command in HDFS.

## USAGE

To use it just copy the tool, add it to your path and: 

    hdfstree -p /
    This will print all the HDFS tree

    hdfstree -p /user/marc/ 
    Print all the directories starting from /user/marc

    hdfstree -l -p /Users/marc/Music
    Print all the directories in the local filesystem.

## BUILDING & SOURCE

Build is done with [gradle](http://www.gradle.org/). To create a ready to use script with the libraries just use: 

    gradle installApp

You'll get the executable script + the libraries in 

    hdfstree/build/install/hdfstree


