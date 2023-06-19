package myapp.exception

open class NotFoundException(message: String) : NoSuchElementException(message)

open class DuplicateException(message: String): Exception(message)