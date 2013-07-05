package com.trovit.hdfstree.displayers;

import com.trovit.hdfstree.TreeNode;

/**
 * User: mdepalol
 */
public class ConsoleDisplayer implements Displayer {
  Prefix prefix;

  @Override
  public void display(TreeNode tree) {
    prefix = new Prefix();
    displayNode(tree, 0);
  }

  public void displayNode(TreeNode node, int level) {
    String prefixString = prefix.getPrefix(level);
    System.out.println(prefixString + node.getPath());
    if (node.hasChildren()) {
      for (TreeNode subTree : node.getChildren()) {
        displayNode(subTree, level + 1);
      }
    }
  }

  class Prefix {
    private String getPrefix(int level) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < level; i++) {
        sb.append("    ");
      }
      sb.append("-> ");

      return sb.toString();
    }

  }

}
