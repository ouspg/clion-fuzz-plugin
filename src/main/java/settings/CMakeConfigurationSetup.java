package settings;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.cmake.CMakeSettings;
import com.jetbrains.cidr.cpp.cmake.model.CMakeApiProfileGenerationResult;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeProfileInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CMakeConfigurationSetup {
    public CMakeConfigurationSetup(Project project){
        CMakeSettings cMakeSettings = CMakeSettings.getInstance(project);
        List<CMakeSettings.Profile> cmakeProfiles = cMakeSettings.getProfiles();
        CMakeSettings.Profile cMakeProfile = new CMakeSettings.Profile("AFL-NEW-GAME",
                cmakeProfiles.get(0).getBuildType(), cmakeProfiles.get(0).getToolchainName(),
                "-D CMAKE_C_COMPILER=/usr/bin/afl-gcc -D CMAKE_CXX_COMPILER=/usr/bin/afl-g++",
                cmakeProfiles.get(0).getPassSystemEnvironment(), cmakeProfiles.get(0).getAdditionalEnvironment(),
                cmakeProfiles.get(0).getGenerationDir(), cmakeProfiles.get(0).getBuildOptions());
            //cMakeProfile.
        List<CMakeSettings.Profile> cmakeProfilesNewModifiable = new ArrayList<>(cmakeProfiles);
        cmakeProfilesNewModifiable.add(cMakeProfile);
        CMakeSettings.getInstance(project).setProfiles(cmakeProfilesNewModifiable);
        cmakeProfiles = cMakeSettings.getProfiles();
        System.out.println("Size of sent list: " + cmakeProfilesNewModifiable.size());
       System.out.println("Cmake profile name or object location: " + cmakeProfiles.size());
    }

}
