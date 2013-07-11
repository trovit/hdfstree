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

import com.trovit.hdfstree.displayers.ConsoleDisplayer;
import com.trovit.hdfstree.displayers.Displayer;
import com.trovit.hdfstree.fsinspectors.FSInspector;
import com.trovit.hdfstree.fsinspectors.HDFSInspector;
import com.trovit.hdfstree.fsinspectors.LocalFSInspector;
import org.apache.commons.cli.*;

public class HdfsTree {

  public static void main(String ...args) {
    Options options = new Options();
    options.addOption("l", false, "Use local filesystem.");
    options.addOption("p", true, "Path used as root for the tree.");

    CommandLineParser parser = new PosixParser();

    TreeBuilder treeBuilder;
    FSInspector fsInspector = null;
    String rootPath = null;

    try {
      CommandLine cmd = parser.parse( options, args);

      // local or hdfs.
      if (cmd.hasOption("l")) {
        fsInspector = new LocalFSInspector();
      } else {
        fsInspector = new HDFSInspector();
      }

      // check that it has the root path.
      if (cmd.hasOption("p")) {
        rootPath = cmd.getOptionValue("p");
      } else {
        throw new ParseException("Mandatory option (-p) is not specified.");
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp( "hdfstree", options );
      System.exit(1);
    }

    treeBuilder = new TreeBuilder(rootPath, fsInspector);
    TreeNode tree = treeBuilder.buildTree();
    Displayer displayer = new ConsoleDisplayer();
    displayer.setDisplaySize();
    displayer.display(tree);

  }
}