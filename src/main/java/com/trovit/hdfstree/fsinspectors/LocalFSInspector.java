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

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class LocalFSInspector implements FSInspector {

  @Override
  public boolean isDirectory(String path) {
    boolean isDir = false;
    File file = new File(path);
    if (file != null && file.isDirectory()) {
      isDir = true;
    }
    return isDir;
  }

  @Override
  public List<String> list(String currentPath) {
    List<String> subs = Lists.newArrayList();
    File file = new File(currentPath);
    if (file.canRead()) {
      List<File> subFiles = Arrays.asList(file.listFiles());
      for (File subFile : subFiles) {
        subs.add(subFile.getName());
      }
    } else {
      System.out.println("Don't have permission to read: " + file.toString() + ". Omitting.");
    }
    return subs;
  }

  @Override
  public String addSubdirToCurrent(String path, String subdir) {
    return path + "/" + subdir;
  }

  @Override
  public long getFileSize(String path) {
    File file = new File(path);
    if (file.exists() && file.canRead()) {
      return file.length();
    } else {
      return 0;
    }
  }
}
