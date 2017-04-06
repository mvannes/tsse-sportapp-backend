package com.tsse

/**
 * Generic responsebody including a message and the result object itself.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
class ResponseBody<T>() {

    var msg: String = ""
    var response: T? = null

}