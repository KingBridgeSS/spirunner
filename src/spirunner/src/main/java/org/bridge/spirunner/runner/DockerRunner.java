package org.bridge.spirunner.runner;

import org.bridge.spirunner.domain.Submission;

import java.io.File;
import java.io.IOException;

import static org.bridge.spirunner.runner.Utils.*;

public class DockerRunner{
    private String code;
    private String requirements;


    public DockerRunner(String code, String requirements) {
        this.code = code;
        this.requirements = requirements;
    }

    public DockerRunner() {
    }
    public DockerRunner(Submission submission) {
        this.code=submission.code;
        this.requirements=submission.requirements;
    }
    public Results run() throws IOException {
        // get random string
        String salt=randomString(16).toLowerCase();
        String dir="/tmp/spirunner_tmp_"+salt;
        System.out.println("tmp dir: "+dir);
        // generate user.py requirements.txt to dir
        makeDir(dir);
        makeFile(this.code,dir+"/user.py");
        makeFile(this.requirements,dir+"/requirements.txt");
        // copy dockerfile, py.sh
        String dockerFileLocation=dir+"/Dockerfile";
        String shFileLocation=dir+"/py.sh";
        copyFile("Dockerfile",dockerFileLocation);
        copyFile("py.sh",dir+"/py.sh");
        // generate image name and container name
        String imageName="img"+salt;
        String containerName="ctn"+salt;
        // replace __img__  __ctn__ in py.sh to imageName    containerName
        String shContent=readFile(shFileLocation);
        shContent=shContent.replaceAll("__img__",imageName).replaceAll("__ctn__",containerName);
        makeFile(shContent,shFileLocation);
        // if requirements.txt is null, comment the php3 install command line in dockerfile
        if(this.requirements==""){
            String dockerfileContent=readFile(dockerFileLocation);
            dockerfileContent=dockerfileContent.replaceAll("pip3 install -r requirements.txt","#pip3 install -r requirements.txt");
            makeFile(dockerfileContent,dockerFileLocation);
        }
        // chmod
        Runtime.getRuntime().exec("chmod +x "+shFileLocation);
        // create p process to run the sh script
        ProcessBuilder pb = new ProcessBuilder(shFileLocation);
        pb.redirectErrorStream(true);//stderr>stdout
        pb.directory(new File(dir));
        Process process = pb.start();
        // get systemLogs
        String systemLogs = new String(process.getInputStream().readAllBytes());
        System.out.println("systemLogs: \n"+systemLogs);
        // get dockerLogs
        String dockerLogs=readFile(dir+"/output.log");
        System.out.println("dockerLogs: \n"+dockerLogs);
        // delete tmp file
        deleteDir(dir);
        return new Results(dockerLogs,systemLogs);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
