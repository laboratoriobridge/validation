import {RuleType} from '../validation';

export var Required: RuleType = {
  message: "Campo requerido",
  isValid: (value: any): boolean => {
    return !!value;
  }
};
