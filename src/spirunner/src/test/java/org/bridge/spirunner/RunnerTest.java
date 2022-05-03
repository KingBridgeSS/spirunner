package org.bridge.spirunner;

import org.bridge.spirunner.runner.DockerRunner;
import org.bridge.spirunner.runner.Utils;

import java.io.File;
import java.io.IOException;

public class RunnerTest{

    public static void main(String[] args) throws IOException {
        DockerRunner runner=new DockerRunner("print('hello, world!')","");
        runner.run();
//        System.out.println( Utils.execCmd("id"));
//        String sh="/tmp/spirunner_tmp_htaqxvwpy5d63o65/py.sh";
//        ProcessBuilder pb = new ProcessBuilder(sh);
//        pb.redirectErrorStream(true);
//        pb.directory(new File("/tmp/spirunner_tmp_htaqxvwpy5d63o65"));
//        Process process = pb.start();
//
//        String result = new String(process.getInputStream().readAllBytes());
//        System.out.println(result);
    }
}