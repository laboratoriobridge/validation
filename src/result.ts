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
export class Result {
  public errors: RuleError[];

  public constructor() {
    this.errors = [];
  }

  public addError(value: any, message: string): void {
    this.errors.push({
      value,
      message
    });
  }

  /**
   * Check whether the result is valid (doesn't contain any error)
   */
  public isValid(): boolean {
    return this.errors.length == 0;
  }

  /**
   * Return a flat string array with all error messages
   */
  public errorMessages(): string[] {
    let arr: string[] = [];
    this.errors.forEach((error) => arr.push(error.message));
    return arr;
  }
}

/**
 * Collection of validation results for named properties
 */
export class ResultHash {
  private _valid: boolean;
  public results: { [key: string]: Result; };

  public constructor() {
    this._valid = true;
    this.results = {};
  }

  public add(key: string, result: Result) {
    this.results[key] = result;

    if (!result.isValid())
      this._valid = false;
  }

  public isValid(): boolean {
    return this._valid;
  }

  /**
   * Iterates through results
   */
  public forEachResult(callback: (result: Result, key: string) => any) {
    Object.keys(this.results).forEach((key) => {
      callback(this.results[key], key);
    });
  }
}
