package com.trovit.hdfstree;

import com.trovit.hdfstree.displayers.ConsoleDisplayer;
import com.trovit.hdfstree.displayers.Displayer;
import com.trovit.hdfstree.fsinspectors.HDFSInspector;
import com.trovit.hdfstree.fsinspectors.LocalFSInspector;

public class HdfsTree {

  public static void main(String ...args) {
    TreeBuilder treeBuilder;
    if (args[0].startsWith("hdfs://")) {
      treeBuilder = new TreeBuilder(args[0], new HDFSInspector());
    } else {
      treeBuilder = new TreeBuilder(args[0], new LocalFSInspector());
    }
    TreeNode tree = treeBuilder.buildTree();
    Displayer displayer = new ConsoleDisplayer();
    displayer.display(tree);
  }

}