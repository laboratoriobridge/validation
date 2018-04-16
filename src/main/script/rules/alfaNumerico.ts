import { match } from './match'

const REGEX = /([^A-Za-z0-9áéíóúÁÉÍÓÚçÇâêôÂÊÔõãÕÃäöüÄÖÜàÀ ])/

export const alfaNumerico = [
    match(REGEX),
]
