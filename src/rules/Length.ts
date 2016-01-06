import {RuleType} from '../validation';

export function Length(length: number): RuleType {
    return new LengthValidator(length);
}

export function LengthRange(min: number, max: number): RuleType {
    return new LengthRangeValidator(min, max);
}

export function LengthMin(min: number): RuleType {
  return new LengthMinValidator(min);
}

export function LengthMax(max: number): RuleType {
  return new LengthMaxValidator(max);
}

/**
 * Exact length validator
 */
export class LengthValidator implements RuleType {
  public length: number;
  public message: string;

  public constructor(length: number) {
    this.length = length;
    this.message = `Valor deve possuir ${this.length} caracteres`;
  }

  public isValid(value: string) {
    if (!value) return true;
    return value.length == this.length;
  }
}

/**
 * Validate values between a minimum and maximum length
 */
export class LengthRangeValidator implements RuleType {
  public min: number;
  public max: number;
  public message: string;

  public constructor(min: number, max: number) {
    this.min = min;
    this.max = max;
    this.message = `Valor deve possuir de ${this.min} a ${this.max} caracteres`;
  }

  public isValid(value: string) {
    if (!value) return true;
    return value.length <= this.max && value.length >= this.min;
  }
}


/**
 * Validates values for a minimum length
 */
export class LengthMinValidator implements RuleType {
  public min: number;
  public message: string;

  public constructor(min: number) {
    this.min = min;
    this.message = `Valor deve possuir no mínimo ${this.min} caracteres`;
  }

  public isValid(value: string) {
    if (!value) return true;
    return value.length >= this.min;
  }
}

/**
 * Validates values for a maximum length
 */
export class LengthMaxValidator implements RuleType {
  public max: number;
  public message: string;

  public constructor(max: number) {
    this.max = max;
    this.message = `Valor deve possuir no máximo ${this.max} caracteres`;
  }

  public isValid(value: string) {
    if (!value) return true;
    return value.length <= this.max;
  }
}
