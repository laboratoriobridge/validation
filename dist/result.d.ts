export interface RuleError {
    /**
     * Value which originated the error
     */
    value: any;
    /**
     * Error message
     */
    message: string;
}
/**
 * Result of a validation for a single value (might contain multiple errors)
 */
export declare class Result {
    errors: RuleError[];
    constructor();
    addError(value: any, message: string): void;
    /**
     * Check whether the result is valid (doesn't contain any error)
     */
    isValid(): boolean;
    /**
     * Return a flat string array with all error messages
     */
    errorMessages(): string[];
}
/**
 * Collection of validation results for named properties
 */
export declare class ResultHash {
    private _valid;
    results: {
        [key: string]: Result;
    };
    constructor();
    add(key: string, result: Result): void;
    isValid(): boolean;
    /**
     * Iterates through results
     */
    forEachResult(callback: (result: Result, key: string) => any): void;
}
