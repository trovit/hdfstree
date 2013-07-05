package com.trovit.hdfstree;

import com.trovit.hdfstree.displayers.ConsoleDisplayer;
import com.trovit.hdfstree.displayers.Displayer;
import com.trovit.hdfstree.fsinspectors.LocalFSInspector;

public class HdfsTree {

  public static void main(String ...args) {
    TreeBuilder treeBuilder = new TreeBuilder(args[0], new LocalFSInspector());
    TreeNode tree = treeBuilder.buildTree();
    Displayer displayer = new ConsoleDisplayer();
    displayer.display(tree);
  }

}