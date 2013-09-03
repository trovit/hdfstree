/**
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.trovit.hdfstree.displayers;

import com.google.common.collect.Sets;
import com.trovit.hdfstree.TreeNode;

import java.util.Set;

/**
 * Displays a tree on screen.
 */
public class ConsoleDisplayer implements Displayer {
  Prefix prefix;
  int maxDepth = 0;

  private boolean displaySize = false;

  @Override
  public void display(TreeNode tree) {
    prefix = new Prefix();

    // precalculate the sizes.
    if (displaySize) {
      tree.getSize();
    }

    displayNode(tree, 0, true);
  }

  @Override
  public void setDisplaySize() {
    displaySize = true;
  }

  @Override
  public void setMaxDepth(int d) {
    this.maxDepth = d;
  }

  public void displayNode(TreeNode node, int level, boolean isLastChild) {
    if (maxDepth != 0 && maxDepth > level) {
      if (node.isDir()) {
        if (node.getChildrenSize() > 1) {
          prefix.addMarker(level+1);
        }
        String prefixString = prefix.getPrefix(level, isLastChild);
        System.out.print(prefixString + node.getPath());
        if (displaySize) {
          System.out.println( " [ "+getHumanReadableSize(node.getSize())+" ]");
        } else {
          System.out.println();
        }
        if (node.hasChildren()) {
          boolean lastChild;
          int counter = 0;
          for (TreeNode subTree : node.getChildren()) {
            lastChild = ((node.getChildrenSize()-1) == counter);
            displayNode(subTree, level + 1, lastChild);
            counter++;
          }
        }
      }
    }
  }

  /**
   * Gets a nicer representation of the size of a file.
   * This is a java port of the javascript method implemented by John Strickler
   * (http://blog.jbstrickler.com/2011/02/bytes-to-a-human-readable-string/)
   * @param size
   * @return A string with the size.
   */
  private String getHumanReadableSize(long size) {
    String suffix[] = {"bytes", "KB", "MB", "GB", "TB", "PB"};
    int tier = 0;

    while(size >= 1024) {
      size = size / 1024;
      tier++;
    }

    return Math.round(size * 10) / 10 + " " + suffix[tier];
  }

  /**
   * Builds the prefix for each node of the tree.
   */
  class Prefix {
    Set<Integer> markers = Sets.newTreeSet();

    public void addMarker(int marker) {
      markers.add(marker);
    }

    public void removeMarker(int marker) {
      markers.remove(marker);
    }

    private String getPrefix(int level, boolean isLastChild) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < level; i++) {
        if (markers.contains(i)) {
          sb.append("|");
        } else {
          sb.append(" ");
        }
        sb.append("   ");
      }
      if (isLastChild) {
        sb.append("└──");
        removeMarker(level);
      } else {
        sb.append("├──");
      }

      return sb.toString();
    }

  }

}
