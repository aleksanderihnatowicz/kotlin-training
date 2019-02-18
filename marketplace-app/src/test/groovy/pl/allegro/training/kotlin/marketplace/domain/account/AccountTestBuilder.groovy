package pl.allegro.training.kotlin.marketplace.domain.account

class AccountTestBuilder {
    static defaults = [
        id: null,
        login: 'john.doe',
        passwordHash: 'A4B23FE1',
        email: 'john.doe@gmail.com',
        phoneNumber: null,
        status: AccountStatus.ACTIVE,
        version: 0,
        rating: Rating.INITIAL
    ]

    static account(Map args) {
        def allArgs = defaults + args
        return new Account(
            allArgs.id as String,
            allArgs.login as String,
            allArgs.passwordHash as String,
            allArgs.email as String,
            allArgs.phoneNumber as String,
            allArgs.status as AccountStatus,
            allArgs.version as Integer,
            allArgs.rating as Rating
        )
    }
}
