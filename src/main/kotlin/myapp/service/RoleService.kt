package myapp.service

import myapp.model.Role
import myapp.repository.RoleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleService(val db: RoleRepository) {
    fun save(role: Role) = db.save(role)

    fun getRole(id: Long) = db.findByIdOrNull(id)
}