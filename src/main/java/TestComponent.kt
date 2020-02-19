import com.intellij.execution.RunManager
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project
import com.jetbrains.cidr.cpp.execution.*

class TestComponent(project: Project?) : ProjectComponent {
    init {
        println("Below output is from TestComponent!")
        val targets = CMakeBuildConfigurationHelper(project!!).targets;
        targets.forEach { println(it) }
    }
}