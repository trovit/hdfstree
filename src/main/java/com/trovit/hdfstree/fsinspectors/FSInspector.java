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
package com.trovit.hdfstree.fsinspectors;

import java.util.List;

public interface FSInspector {
  /**
   * Checks if a file exists.
   * @param path Path to check.
   * @return True iff the file already exists.
   */
  boolean exists(String path);

  /**
   * Checks if the file contained in the path is a directory.
   * @param path Path to check.
   * @return True iff the file is a directory and exists.
   */
  boolean isDirectory(String path);

  /**
   * Gets the names (not total path) of the subdirectories for a given path.
   * @param currentPath
   */
  List<String> listSubDirs(String currentPath);

  /**
   * Gets the string representation of a path + its subdirectory.
   * @param path Original path.
   * @param subdir subdirectory for the original path.
   * @return A valid path with the subdirectory.
   */
  String addSubdirToCurrent(String path, String subdir);
}
