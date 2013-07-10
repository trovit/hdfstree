package com.trovit.hdfstree.fsinspectors;

import java.util.List;

public interface FSInspector {
  boolean exists(String path);

  boolean isDirectory(String path);

  List<String> listSubDirs(String currentPath);

  String addSubdirToCurrent(String path, String subdir);
}
