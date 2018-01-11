import * as Util from '../Util'

export function match(regex: RegExp) {
    return (value: string) => {
        if (!Util.isEmpty(value) && !regex.test(value)) {
            return 'Um ou mais caracteres informados não são permitidos para esse campo'
        }
    }
}
