import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ObjectNode


println("Hello world")

updateDependencies('@campuspack/frontend', "3.2.0")

def updateDependencies(dependency, value){
    File file = new File("/Users/steffent/IdeaProjects/campus/jenkins-shared-libraries/resources/cbc-package.json")

    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
    JsonNode locatedNode = mapper.readTree(file)

    JsonNode nodeParent = locatedNode.get("dependencies")
    ((ObjectNode) nodeParent).put(dependency, value)

    println(locatedNode.toPrettyString())
    mapper.writeValue(file, locatedNode)
}