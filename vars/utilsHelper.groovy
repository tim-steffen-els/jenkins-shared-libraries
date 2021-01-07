def checkIfNotEmpty(string){
    return !checkIfEmpty(string)
}


def checkIfEmpty(string){
    boolean isEmpty = false
    if(string == null){
        log.info 'String is null'
        isEmpty = true
    } else if (string.trim().size() == 0){
        log.info 'String is empty'
        isEmpty = true
    }
    return isEmpty
}


def descriptionBuilder(Map config){
    description = ""
    if(config.containsKey('branch')){
        if(checkIfNotEmpty(config.containsKey('branch'))){
            description = description + "Branch: ${config.branch} ,"
        }
    }
    if(config.containsKey('tag')){
        if(checkIfNotEmpty(config.containsKey('tag'))){
            description = description + "Tag: ${config.tag} ,"
        }
    }
    return description
}
