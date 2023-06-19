package myapp.exception

class UserNotFound(message: String): NotFoundException(message)

class UserAlreadyExists(message: String): DuplicateException(message)