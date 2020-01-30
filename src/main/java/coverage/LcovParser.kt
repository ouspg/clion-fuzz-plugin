package coverage

import net.zero9178.cov.data.CoverageData
import net.zero9178.cov.data.CoverageFileData
import net.zero9178.cov.data.CoverageFunctionData
import net.zero9178.cov.data.FunctionLineData
import utils.ArrayMathUtils
import java.io.File

class LcovCoverageGenerator (private val directory:String,
                             private val lcovFile : String) {
    companion object {
        @JvmStatic
        fun parseLcovInfo(filePath: String) : ArrayList<Coverage> {
            val data: ArrayList<Coverage> = ArrayList()
            val file = File(filePath)
            if(!file.exists()){
                println("File doesn't exists!!!")
                return data;
            }
            var item: Coverage = createNewItem()
            for (rawLine in file.readLines()) {
                val line = rawLine.trim()
                val allParts = line.split(':')
                val metrics = allParts[0].toUpperCase()
                val args = allParts.subList(1, allParts.size).joinToString().trim()
                val parts = arrayOf(args, "")
                when (metrics) {
                    "TN" -> item.title = args
                    "SF" -> item.file = args
                    "FNF" -> item.functions.found = args.toInt()
                    "FNH" -> item.functions.hit = args.toInt()
                    "LF" -> item.lines.found = args.toInt()
                    "LH" -> item.lines.hit = args.toInt()
                    "DA" -> {val details = args.split(',')
                             val covDetails = LineCoverageInfo(details[1].toInt(), details[0].toInt())
                             item.lines.details.add(covDetails)}
                    "BRF" -> item.branches.found = parts[1].toInt()
                    "BRH" -> item.branches.hit = parts[1].toInt()
                    "FN" -> {val fn = args.split(',')
                             val covDetails = FunctionCoverageInfo(name = fn[1], line = fn[0].toInt(), hit = null)
                             item.functions.details.add(covDetails)}
                    "FNDA" -> {val fn = args.split(',')
                        item.functions.details.forEach {
                            if((it.name == fn[1]) && (it.hit == null)){
                                it.hit = fn[0].toInt()
                                return@forEach
                            }
                        }
                        //TODO add BRDA file
                    }
                }
                if (line.indexOf("end_of_record") > -1) {
                    item.let { data.add(it) }
                    item = createNewItem()
                }
            }
            return data
        }
        private fun createNewItem() : Coverage{
            return Coverage(CoverageInfoCollection(0, 0, ArrayList()),
                    CoverageInfoCollection(0, 0, ArrayList()),
                    CoverageInfoCollection(0, 0, ArrayList()),
                    "",
                    "")
        }

        @JvmStatic
        fun printCoverageHighLevelData(coverageCollection : ArrayList<Coverage>){
            coverageCollection.forEach {
                println(it.functions.hit.toString() +  " " + it.functions.found.toString())
                println(it.branches.hit.toString() +  " " + it.branches.found.toString())
                println(it.lines.hit.toString() +  " " + it.lines.found.toString())
                println("File is this: " + it.file)
                println("Title is this: " + it.title)
            }
        }
        @JvmStatic
        fun printCoverageLowLevelData(coverageCollection : ArrayList<Coverage>){
            coverageCollection.forEach {
                it.branches.details.forEach { it2 ->
                    println(it2.block.toString() + " " + it2.branch.toString() + " " + it2.hit.toString() + " " + it2.line.toString())
                }
                it.functions.details.forEach { it2 ->
                    println(it2.name + " " + it2.hit.toString() + " " + it2.line.toString())
                }
                it.lines.details.forEach { it2 ->
                    println(it2.hit.toString() + " " + it2.line.toString())
                }
            }
        }
        @JvmStatic
        fun getCoverageDataFromCoverage(coverage : ArrayList<Coverage>) : CoverageData? {
            val covFileDataMap = HashMap<String, CoverageFileData>()
            coverage.forEach { file ->
                val allStartingLinesOfFunctions = file.getAllFunctionLines()
                val covFunctionDataMap = HashMap<String, CoverageFunctionData>()
                file.functions.details.forEach { function ->
                    val nextGreaterNumberOfStartingLine = ArrayMathUtils.getNextGreaterNumber(function.line, allStartingLinesOfFunctions)
                    val funcLineData = HashMap<Int, Long>()
                    file.lines.details.forEach { line ->
                        if(function.line != nextGreaterNumberOfStartingLine) {
                            if ((line.line >= function.line) && (line.line < nextGreaterNumberOfStartingLine))
                                funcLineData[line.line] = line.hit.toLong()
                        }
                        else if ((line.line >= function.line)){
                            funcLineData[line.line] = line.hit.toLong()
                        }
                    }
                    //Don't know where the function ends so just using Integer.MAX_VALUE!!!
                    val covFunctionData = CoverageFunctionData(function.line, Integer.MAX_VALUE, function.name, FunctionLineData(funcLineData), emptyList())
                    covFunctionDataMap[function.name] = covFunctionData
                }
                covFileDataMap[file.file] = CoverageFileData(file.file, covFunctionDataMap)
            }
            return CoverageData(covFileDataMap, hasBranchCoverage = false, containsExternalSources = false)
        }
    }
}

data class FunctionCoverageInfo(var hit: Int?, val line: Int, val name: String)
data class BranchCoverageInfo(val block: Int, val branch: Int, val line: Int, val hit: Int)
data class LineCoverageInfo(val hit: Int, val line: Int)
data class CoverageInfoCollection<T>(var found: Int, var hit: Int, var details: ArrayList<T>)
data class Coverage(val branches: CoverageInfoCollection<BranchCoverageInfo>,
                            val functions: CoverageInfoCollection<FunctionCoverageInfo>,
                            val lines: CoverageInfoCollection<LineCoverageInfo>,
                            var title: String,
                            var file: String) {
    fun getAllFunctionLines() : ArrayList<Int>{
        val arr = ArrayList<Int>()
        functions.details.forEach {
            arr.add(it.line)
        }
        return arr
    }
}
