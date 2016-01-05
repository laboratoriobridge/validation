import { Validator, Compose, ComposeType } from './validators';
import { RuleError, Result } from './result';

export interface RuleType {
  isValid: (value: any) => boolean;
  message: string;
}

/**
 * Validate an array of rules and validators (using a Compose validator as base)
 */
export function validate(value: any, r: ComposeType, resultContext: Result = new Result()): Result {
  let validator = new Compose(r);
  validator.validate(value, resultContext);
  return resultContext;
}

/**
 * Validate a single rule
 */
export function validateRule(value: any, rule: RuleType, resultContext: Result = new Result()): Result {
  if (!rule.isValid(value)) {
    resultContext.addError(value, rule.message);
  }
  return resultContext;
}
