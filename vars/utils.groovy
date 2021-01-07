def checkIfNotEmpty(string){
    return !checkIfNotEmpty(string)
}


def checkIfEmpty(string){
    boolean isEmpty = true
    if(string == null){
        log.info 'String is null'
        isEmpty = true
    } else if (string.trim().size() == 0){
        log.info 'String is empty'
        isEmpty = true
    }
    return isEmpty
}