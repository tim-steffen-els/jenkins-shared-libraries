
def info(message) {
    standardFormat("INFO", message)
}

def warning(message) {
    standardFormat("WARNING", message)
}

def error(message) {
    standardFormat("ERROR", message)
}

private def standardFormat(premessage, message){
    echo "${premessage}: ${message}"
}

