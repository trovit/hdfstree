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

import com.trovit.hdfstree.TreeNode;

/**
 * Displays a tree on screen.
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
