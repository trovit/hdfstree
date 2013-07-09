package com.trovit.hdfstree.fsinspectors;

import com.google.common.collect.Lists;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.List;

/**
 * User: mdepalol
 */
public class HDFSInspector implements FSInspector {
  FileSystem fs;

  public HDFSInspector() {
    // get a hadoop conf.
    Configuration conf = new Configuration();
    try {
      fs = FileSystem.get(conf);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public boolean exists(String path) {
    boolean exists = false;
    try {
      exists = fs.exists(new Path(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return exists;

  }

  @Override
  public boolean isDirectory(String path) {
    boolean isDir = false;
    try {
      isDir = !fs.isFile(new Path(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return isDir;
  }

  @Override
  public List<String> listSubDirs(String currentPath) {
    List<String> subdirs = Lists.newArrayList();
    try {
      for (FileStatus fileStatus : fs.listStatus(new Path(currentPath))) {
        if (!fs.isFile(fileStatus.getPath())) {
          subdirs.add(fileStatus.getPath().toString());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return subdirs;
  }
}
