package com.tsse.repository

import com.tsse.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by boydhogerheijde on 30/05/2017.
 */
interface RoleRepository : JpaRepository<Role, Long>, ByNameApi<Role>
