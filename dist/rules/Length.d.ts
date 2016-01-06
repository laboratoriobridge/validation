import { RuleType } from '../validation';
export declare function Length(length: number): RuleType;
export declare function LengthRange(min: number, max: number): RuleType;
export declare function LengthMin(min: number): RuleType;
export declare function LengthMax(max: number): RuleType;
/**
 * Exact length validator
 */
export declare class LengthValidator implements RuleType {
    length: number;
    message: string;
    constructor(length: number);
    isValid(value: string): boolean;
}
/**
 * Validate values between a minimum and maximum length
 */
export declare class LengthRangeValidator implements RuleType {
    min: number;
    max: number;
    message: string;
    constructor(min: number, max: number);
    isValid(value: string): boolean;
}
/**
 * Validates values for a minimum length
 */
export declare class LengthMinValidator implements RuleType {
    min: number;
    message: string;
    constructor(min: number);
    isValid(value: string): boolean;
}
/**
 * Validates values for a maximum length
 */
export declare class LengthMaxValidator implements RuleType {
    max: number;
    message: string;
    constructor(max: number);
    isValid(value: string): boolean;
}
