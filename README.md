# HDFSTREE

This is a simple standalone tool that emulates the Unix 'tree' command in HDFS.

## USAGE

To use it just copy the tool, add it to your path it's ready to use, it has 3 options: 

* -p: Specifies the root path. This is mandatory.
* -l: Specifies that you want to use the local filesystem instead of HDFS:
* -s: Specifies that you want to see the size (in human readable format) next to each directory. 
* -d: Specifies the maximum depth when displayin the tree.

some examples: 

    hdfstree -p /
    This will print all the HDFS tree. This will take some time...

    hdfstree -p /user/marc/ 
    Print all the directories starting from /user/marc

    hdfstree -l -p /Users/marc/Music -s 
    Print all the directories in the /Users/marc/Music directory in the local filesystem and display the sizes. 

The output is pretty much like the unix tree command. Have an example: 

    └──/Users/marc/work/hdfstree
        ├──.git
        |   ├──branches
        |   ├──hooks
        |   ├──info
        |   ├──logs
        |   |   └──refs
        |   |       ├──heads
        |   |       |   └──mdepalol
        |   |       └──remotes
        |   |           └──origin
        |   ├──objects
        ...
    
## BUILDING & SOURCE

Build is done with [gradle](http://www.gradle.org/). To create a ready to use script with the libraries just use: 

    gradle installApp

You'll get the executable script + the libraries in 

    hdfstree/build/install/hdfstree

just add this directory (hdfstree/build/install/hdfstree/bin) to your PATH variable and you are done, also, make sure you have the HADOOP\_HOME environment variable set. The program will complain if you don't have it. 
