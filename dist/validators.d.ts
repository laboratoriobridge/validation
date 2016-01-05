import { RuleType } from './validation';
import { Result } from './result';
/**
 * Helper function to check whether or not the object is a rule
 */
export declare function isRule(probablyRule: any): boolean;
export interface Validator {
    validate: (value: any, resultContext: Result) => void;
}
export declare class RuleValidator implements Validator {
    rule: RuleType;
    constructor(rule: RuleType);
    validate(value: any, resultContext?: Result): Result;
}
/**
 * Combination of array of rules and validators
 */
export declare type ComposeType = Array<RuleType | Validator | any[]>;
export declare class Compose implements Validator {
    validators: Validator[];
    constructor(rulesAndValidators: ComposeType);
    private assignRuleOrValidator(rules);
    validate(value: any, resultContext?: Result): Result;
}
export declare class Condition implements Validator {
    condition: boolean;
    trueValidator: Compose;
    constructor(condition: boolean, rulesIfTrue: ComposeType);
    validate(value: any, resultContext?: Result): Result;
}
