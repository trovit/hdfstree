package com.trovit.hdfstree;

import com.trovit.hdfstree.fsinspectors.FSInspector;

import java.util.List;

public class TreeBuilder {

  private final String initialPath;
  private FSInspector fsInspector;
  
  public TreeBuilder(String initialPath, FSInspector inspector) {
    this.initialPath = initialPath;
    this.fsInspector = inspector; 
  }

  public TreeNode buildTree() {
    TreeNode treeNode = new TreeNode(initialPath);

    buildTreeRecursively(treeNode, initialPath, 0);

    return treeNode;
  }

  private void buildTreeRecursively(TreeNode treeNode, String currentPath, int level) {
    // if has sub dirs.
    if (fsInspector.isDirectory(currentPath)) {
      // list the dirs.
      List<String> subdirectories = fsInspector.listSubDirs(currentPath);
      for (String subDir : subdirectories) {
        TreeNode child = treeNode.addChild(subDir);
        buildTreeRecursively(child, fsInspector.addSubdirToCurrent(currentPath, subDir), level + 1);
      }
    }
  }
}