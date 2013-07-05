package com.trovit.hdfstree;

import com.google.common.collect.Lists;

import java.util.List;

public class TreeNode {

  private List<TreeNode> children;
  private String path;

  public TreeNode(String path) {
    this.path = path;
    children = Lists.newArrayList();
  }

  public void setChildren(List<TreeNode> children) {
    this.children = children;
  }

  public String getPath() {
    return path;
  }

  public List<TreeNode> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    return (children.size() > 0);
  }

  public TreeNode addChild(String subDir) {
    TreeNode newChild = new TreeNode(subDir);
    children.add(newChild);
    return newChild;
  }

  public int getChildrenSize() {
    return children.size();
  }
}