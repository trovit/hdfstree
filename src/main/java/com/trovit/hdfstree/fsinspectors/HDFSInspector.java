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
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.List;

public class HDFSInspector implements FSInspector {
  FileSystem fs;

  public HDFSInspector() {
    try {
      Configuration conf = getHadoopConf();
      fs = FileSystem.get(conf);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

  @Override
  public boolean isDirectory(String path) {
    boolean isDir = false;
    try {
      isDir = fs.getFileStatus(new Path(path)).isDir();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return isDir;
  }

  @Override
  public List<String> list(String currentPath) {
    List<String> subfiles = Lists.newArrayList();
    try {
      for (FileStatus fileStatus : fs.listStatus(new Path(currentPath))) {
        subfiles.add(fileStatus.getPath().getName());
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return subfiles;
  }

  @Override
  public String addSubdirToCurrent(String path, String subdir) {
    Path current = new Path(path, subdir);
    return current.toString();
  }

  @Override
  public long getFileSize(String file) {
    Path current = new Path(file);
    try {
      return fs.getFileStatus(current).getLen();
    } catch (IOException e) {
      System.out.println("Cannot read file: " + current.toString());
      return 0;
    }
  }

  private Configuration getHadoopConf() throws Exception {
    Configuration conf = new Configuration();
    String hadoop_home = System.getenv("HADOOP_HOME");
    if (hadoop_home == null) {
      throw new Exception("HADOOP_HOME is not defined in the system.");
    }
    conf.addResource(new Path(hadoop_home+"/conf/hdfs-site.xml"));
    conf.addResource(new Path(hadoop_home+"/conf/mapred-site.xml"));
    conf.addResource(new Path(hadoop_home+"/conf/core-site.xml"));
    return conf;
  }
}
