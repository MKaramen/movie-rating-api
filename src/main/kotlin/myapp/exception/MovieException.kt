package myapp.exception

class MovieNotFound(message: String) : NotFoundException(message)

class MovieAlreadyExists(message: String): DuplicateException(message)