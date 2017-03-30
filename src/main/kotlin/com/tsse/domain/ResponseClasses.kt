package com.tsse.domain

import java.util.*


/**
 * File containing all response models.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
data class ApiErrorResponse(val uri: String?, val message: String?, val timeStamp: Date)
