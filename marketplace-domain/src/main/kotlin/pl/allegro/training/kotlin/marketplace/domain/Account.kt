package pl.allegro.training.kotlin.marketplace.domain

// deklaracja id / typ zamieniona kolejność
// nie ma Integer i int
// reprezentacja na JVM
// typ nullowalny, wartość domyślna
// klasa standardowo final, open
data class Account2(
        val id: String,
        val login: String,
        val password: String,
        val phoneNumber: String? = null,
        val bankAccounts: Array<String>
) {
    init {
        // isNullOrEmpty to extension function
        if(!phoneNumber.isValidPhoneNumber()) {
            // tworzenie obiektu nie wymaga new
            throw InvalidAccount("Invalid phone number: $phoneNumber")
        }
    }

    private fun String?.isValidPhoneNumber() = true
}

// spring aspecty wyjątek
// nie trzeba body
// nie ma słówka extends
class InvalidAccount(message: String) : Exception(message)

//cwiczenia
// dodanie pol email, lista adresów do wysyłki, nr telefonu
