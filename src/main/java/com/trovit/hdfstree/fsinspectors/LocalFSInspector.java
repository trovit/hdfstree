package com.trovit.hdfstree.fsinspectors;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class LocalFSInspector implements FSInspector {

  @Override
  public boolean exists(String path) {
    boolean exists = false;
    File file = new File(path);
    if (file != null && file.exists()) {
      exists = true;
    }
    return exists;

  }

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
  public List<String> listSubDirs(String currentPath) {
    List<String> subdirs = Lists.newArrayList();
    File file = new File(currentPath);
    if (file.canRead()) {
      List<File> subFiles = Arrays.asList(file.listFiles());
      for (File subFile : subFiles) {
        if (subFile.isDirectory()) {
          subdirs.add(subFile.getName());
        }
      }
    } else {
      System.out.println("Don't have permission to read: " + file.toString() + ". Omitting.");
    }
    return subdirs;
  }

  @Override
  public String addSubdirToCurrent(String path, String subdir) {
    return path + "/" + subdir;
  }
}
