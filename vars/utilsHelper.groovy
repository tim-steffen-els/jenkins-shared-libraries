def checkIfNotEmpty(string) {
    return !checkIfEmpty(string)
}


def checkIfEmpty(string) {
    boolean isEmpty = false
    if(string == null) {
        log.info "String is null"
        isEmpty = true
    } else if (string.trim().size() == 0) {
        log.info "String is empty"
        isEmpty = true
    }
    return isEmpty
}


def stringToMap(string) {
    Map map =[:]
    if(checkIfNotEmpty(string)){
        string.split(',').each {item ->
            def keyAndValue = item.split(':')
            log.info " adding ${keyAndValue[0]}, ${keyAndValue[1]}"
            map[keyAndValue[0], keyAndValue[1]]
        }
    }
    return map
}

/**
 * This is a builder method used to create consistent descriptions for runs.
 *  - Current options for map: branch, tag
 *
 * @param config - A map to provide variables
 * @return
 */
def descriptionBuilder(Map config) {
    description = ""
    if(config.containsKey('branch')) {
        if(checkIfNotEmpty(config.branch)) {
            description = "${description} | Branch: ${config.branch} |"
        }
    }
    if(config.containsKey('tag')) {
        if(checkIfNotEmpty(config.tag)) {
            description = "${description}  | Tag: ${config.tag} |"
        }
    }
    return description
}
