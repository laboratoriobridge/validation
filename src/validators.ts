import { RuleType, validateRule } from './validation';
import { Result } from './result';

/**
 * Helper function to check whether or not the object is a rule
 */
export function isRule(probablyRule: any): boolean {
  return !!(probablyRule.message && probablyRule.isValid);
}

export interface Validator {
  validate: (value: any, resultContext: Result) => void;
}

export class RuleValidator implements Validator {
  public rule: RuleType;

  public constructor(rule: RuleType) {
    this.rule = rule;
  }

  public validate(value: any, resultContext: Result = new Result()): Result {
    validateRule(value, this.rule, resultContext);
    return resultContext;
  }
}

/**
 * Combination of array of rules and validators
 */
export type ComposeType = Array<RuleType | Validator | any[]>;

export class Compose implements Validator {
  public validators: Validator[];

  public constructor(rulesAndValidators: ComposeType) {
    this.validators = [];
    this.assignRuleOrValidator(rulesAndValidators);
  }

  private assignRuleOrValidator(rules: ComposeType) {
    for (var rule of rules) {
      let type = (rule.constructor as any).name;

      if (type === 'Array') {    // Nested rules or validators
        this.assignRuleOrValidator(rule as any[]);
      } else if (isRule(rule)) { // Rule
        this.validators.push(new RuleValidator(rule as RuleType));
      } else {                   // Validator
        this.validators.push(rule as Validator);
      }
    }
  }

  public validate(value: any, resultContext: Result = new Result()): Result {
    for (var validator of this.validators)
      validator.validate(value, resultContext);

    return resultContext;
  }
}

export class Condition implements Validator {
  public condition: boolean;
  public trueValidator: Compose;

  public constructor(condition: boolean, rulesIfTrue: ComposeType) {
    this.condition = condition;
    this.trueValidator = new Compose(rulesIfTrue);
  }

  public validate(value: any, resultContext: Result = new Result()): Result {
    if (this.condition) {
      this.trueValidator.validate(value, resultContext);
    }
    return resultContext;
  }
}
