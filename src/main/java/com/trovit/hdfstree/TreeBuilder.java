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
package com.trovit.hdfstree;

import com.trovit.hdfstree.fsinspectors.FSInspector;

import java.util.List;

/**
 * This class builds the tree directory.
 */
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