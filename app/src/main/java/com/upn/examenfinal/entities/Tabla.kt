package com.upn.examenfinal.entities


class Tabla {
    var id = 0
    var correo: String
    var usuario: String
    var ticket: String
    var celular: Int

    constructor(correo: String, usuario: String, ticket: String, celular: Int) {
        this.correo = correo
        this.usuario = usuario
        this.ticket = ticket
        this.celular = celular
    }

    constructor(id: Int, correo: String, usuario: String, ticket: String, celular: Int) {
        this.id = id
        this.correo = correo
        this.usuario = usuario
        this.ticket = ticket
        this.celular = celular
    }
}
