package hu.karsany.vau.project.datamodel.generator.script;

import hu.karsany.vau.common.Generator;
import hu.karsany.vau.project.Project;

import java.io.File;

public class InstallScriptGenerator implements Generator {
    private final Project pm;

    public InstallScriptGenerator(Project pm) {
        this.pm = pm;
    }

    @Override
    public String toString() {
        return getInstallFileList("table") + getInstallFileList("sequence") + getInstallFileList("procedure") + getInstallFileList("package");
    }

    private String getInstallFileList(String dir) {
        StringBuilder sb = new StringBuilder();

        File tablesToInstall = new File(pm.getProjectPath().toString() + "/target/src/" + dir);
        if (tablesToInstall.exists()) {

            for (File file : tablesToInstall.listFiles()) {
                sb.append("@./" + dir + "/" + file.getName() + "\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getFileName() {
        return "install.sql";
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.INSTALL_SCRIPT;
    }
}
