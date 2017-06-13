package com.tsse.repository

import com.tsse.domain.model.Authority
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by boydhogerheijde on 30/05/2017.
 */
interface AuthorityRepository : JpaRepository<Authority, Long>, ByNameApi<Authority>
