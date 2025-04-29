package com.ascenders.securedapi.model

data class Student(private var id: Int,
                   private var _firstname: String,
                   private var _lastname: String,
                    private var _age: Int) {

    var fistname: String
        get() = _firstname
        set(value) {
            _firstname = value
        }

    var lastname: String
        get() = _lastname
        set(value) {
            _lastname = lastname
        }

    var age: Int
        get() = _age
    set(value)  {
        _lastname = lastname
    }
}