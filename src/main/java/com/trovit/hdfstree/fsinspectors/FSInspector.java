package com.trovit.hdfstree.fsinspectors;

import java.util.List;

/**
 * User: mdepalol
 */
public interface FSInspector {
  boolean exists(String path);

  boolean isDirectory(String path);

  List<String> listSubDirs(String currentPath);
}
