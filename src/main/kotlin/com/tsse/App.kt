package com.tsse

import com.tsse.domain.model.Authority
import com.tsse.domain.model.Role
import com.tsse.domain.model.User
import com.tsse.service.impl.AuthorityService
import com.tsse.service.impl.RoleService
import com.tsse.service.impl.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
open class SportAppApplication(private val authorityService: AuthorityService,
                               private val roleService: RoleService,
                               private val userService: UserService) {

    @Bean
    fun run(): CommandLineRunner {
        return CommandLineRunner {
            createPrivileges()
            createRoles()
            createUsers()
        }
    }

    private fun createPrivileges() {
        createPrivilegeIfNotExisting("ROLE_USER_READ")
        createPrivilegeIfNotExisting("ROLE_USER_WRITE")
    }

    private fun createPrivilegeIfNotExisting(name: String) {
        val entityByName = authorityService.findByName(name)
        if (entityByName == null) {
            val entity = Authority(name)
            authorityService.create(entity)
        }
    }

    private fun createRoles() {
        val canRoleRead = authorityService.findByName("ROLE_USER_READ")
        val canRoleWrite = authorityService.findByName("ROLE_USER_WRITE")

        val authorities = HashSet<Authority>()
        authorities.add(canRoleRead!!)
        authorities.add(canRoleWrite!!)

        createRoleIfNotExisting("ROLE_USER", HashSet())
        createRoleIfNotExisting("ROLE_ADMIN", authorities)
    }

    private fun createRoleIfNotExisting(name: String, authorities: Set<Authority>) {
        val entityByName = roleService.findByName(name)
        if (entityByName == null) {
            val entity = Role(name)
            entity.authorities = authorities
            roleService.create(entity)
        }
    }

    private fun createUsers() {
        val roleAdmin = roleService.findByName("ROLE_ADMIN")
        val roleUser = roleService.findByName("ROLE_USER")

        val admin = HashSet<Role>()
        val user = HashSet<Role>()

        admin.add(roleAdmin!!)
        user.add(roleUser!!)

        createUserIfNotExisting("admin", admin)
        createUserIfNotExisting("user", user)
    }

    private fun createUserIfNotExisting(loginName: String, roles: Set<Role>) {
        val entityByName = userService.findByName(loginName)
        if (entityByName == null) {
            val entity = User(loginName, "password", roles)
            userService.create(entity)
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SportAppApplication::class.java, *args)
}
