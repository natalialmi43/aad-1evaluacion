package edu.iesam.aad_1evaluacion.core.serialization

interface JsonSerializer<T> {
    fun toJson(obj: T): String
    fun fromJson(json: String): T
}
