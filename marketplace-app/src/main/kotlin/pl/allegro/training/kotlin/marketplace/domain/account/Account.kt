package pl.allegro.training.kotlin.marketplace.domain.account

import pl.allegro.training.kotlin.marketplace.infrastructure.isValidEmail
import pl.allegro.training.kotlin.marketplace.infrastructure.repository.Identifiable

// @Piotrek
// 1a. Zaczynamy z class Account (bez data, interfejsu i pola rating), typy null/not-null, domyślne parametry
// nie ma srednikow, Array, IntArray, typealias to id
// int duzy to int maly, clasa finalna i publiczna, dostęp do pól, psvm jest top level, nie ma new
// 1b. Tworzymy obiekt w main, co to jest primary constructor, wiele klas publicznych w jednym pliku
// struktura pakietowa, opowiedzenie o typach zwracanych z funkcji (Unit, ostatnie expression definiuje typ)

// @Konrad
// 2a. Dodajemy walidację w init, wytłumaczenie konstruktora
// 2b. Pokazać isValidEmail, string templates
// 2c. Omowic plik Validation.kt
// 2d. Opowiedziec o wyjatkach, nie trzeba ich deklarowac
// pokazac try sam i jako expression, przypisać do val Account?

// @Piotrek
// 3a. Pokazanie data classy, zmieni sie toString, hashCode i equals (opowiedziec o operatorach równości),
// pokazac copy na prostym przykładzie
// może dziedziczyc ale nie z data classy, named parameters,

typealias AccountId = String

// dodać enum do accounta (active, blocked)
// dodać testy do walidacji, data class == normalna klasa
// pokazać test buildera na przykładzie Account
data class Account(
        override val id: AccountId? = null,
        val login: String,
        val passwordHash: String,
        val email: String,
        val phoneNumber: String? = null,
        val addresses: List<Address>,
        val status: AccountStatus = AccountStatus.ACTIVE,
        val version: Int = 0,
        val rating: Rating = Rating.INITIAL
) : Identifiable<AccountId> { // dopiero w 4 kroku

    init {
        if(!isValidEmail(email)) {
            // no new operator upon object construction
            throw InvalidAccountException("Invalid email: $email")
        }
    }
}

enum class AccountStatus {
    ACTIVE, BLOCKED
}

open class Address(
        val street: String,
        val city: String,
        val zipCode: String
)


