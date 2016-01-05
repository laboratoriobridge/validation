import { ComposeType } from './validators';
import { Result } from './result';
export interface RuleType {
    isValid: (value: any) => boolean;
    message: string;
}
/**
 * Validate an array of rules and validators (using a Compose validator as base)
 */
export declare function validate(value: any, r: ComposeType, resultContext?: Result): Result;
/**
 * Validate a single rule
 */
export declare function validateRule(value: any, rule: RuleType, resultContext?: Result): Result;
