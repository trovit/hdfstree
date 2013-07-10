package com.trovit.hdfstree;

import com.trovit.hdfstree.displayers.ConsoleDisplayer;
import com.trovit.hdfstree.displayers.Displayer;
import com.trovit.hdfstree.fsinspectors.FSInspector;
import com.trovit.hdfstree.fsinspectors.HDFSInspector;
import com.trovit.hdfstree.fsinspectors.LocalFSInspector;
import org.apache.commons.cli.*;

public class HdfsTree {

  public static void main(String ...args) {
    Options options = new Options();
    options.addOption("l", false, "Use local filesystem.");
    options.addOption("p", true, "Path used as root for the tree.");

    CommandLineParser parser = new PosixParser();

    TreeBuilder treeBuilder;
    FSInspector fsInspector = null;
    String rootPath = null;

    try {
      CommandLine cmd = parser.parse( options, args);

      // local or hdfs.
      if (cmd.hasOption("l")) {
        fsInspector = new LocalFSInspector();
      } else {
        fsInspector = new HDFSInspector();
      }

      // check that it has the root path.
      if (cmd.hasOption("p")) {
        rootPath = cmd.getOptionValue("p");
      } else {
        throw new ParseException("Mandatory option (-p) is not specified.");
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp( "hdfstree", options );
      System.exit(1);
    }

    treeBuilder = new TreeBuilder(rootPath, fsInspector);
    TreeNode tree = treeBuilder.buildTree();
    Displayer displayer = new ConsoleDisplayer();
    displayer.display(tree);

  }
}