export class LateCheckInValidationError extends Error {
    constructor(){
        super('O chekc in só pode ser validado até 20 minutos de sua criação.')
    }
}