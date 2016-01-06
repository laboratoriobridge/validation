/// <reference path="../../typings/tsd.d.ts"/>

import assert = require('assert');

import * as Validation from '../';

/**
 * Os testes unitários das Rules estão no Java (jUnit)
 */

// Dummy rules for testing
var NotARule = {
  message: "Value should not contain letter 'a'",
  isValid: function(value) {
    if (!value) return true;
    return value.indexOf('a') === -1;
  }
};
var NotBRule = {
  message: "Value should not contain letter 'b'",
  isValid: function(value) {
    if (!value) return true;
    return value.indexOf('b') === -1;
  }
};

// Dummy validator (always fail)
var BailValidator = function() {};
BailValidator.prototype.validate = function(value, resultContext) {
  if (!resultContext) resultContext = new Validation.Result();
  resultContext.addError(value, "Bailed!");
  return resultContext;
};

describe('isRule', function() {
  it('should identify whether a ComposeType is a rule or not', function() {
    assert.ok(Validation.isRule(NotARule));
    assert.ok(Validation.isRule(NotBRule));
    assert.ok(!Validation.isRule(false));
    assert.ok(!Validation.isRule(true));
    assert.ok(!Validation.isRule(BailValidator));
    assert.ok(!Validation.isRule([BailValidator]));
  });
});

describe('Result', function() {
  describe('#errorMessages()', function() {
    it('should return a flat array of all error messages of result', function() {
      let r = new Validation.Result();
      r.addError('test', 'Error msg 1');
      r.addError('test', 'Error msg 2');
      assert.deepEqual(r.errorMessages(), ['Error msg 1', 'Error msg 2']);
    });
  });
});

describe('ResultHash', function() {
  describe('#isValid()', function() {
    it('should be invalid if some result is invalid', () => {
      let result = new Validation.ResultHash();
      assert.ok(result.isValid());
      result.add('one', new Validation.Result());
      assert.ok(result.isValid());

      let errorResult = new Validation.Result();
      errorResult.addError('', 'error 1');

      result.add('two', errorResult);
      assert.ok(!result.isValid());
      assert.deepEqual(result.results['two'], errorResult);
    });
  });

  describe('#forEachResult', function() {
    it('should iterate through results', function() {
      let result = new Validation.ResultHash();
      result.add('one', new Validation.Result());
      let errorResult = new Validation.Result();
      errorResult.addError('', 'error 1');
      result.add('two', errorResult);

      let rs = '';
      let keys = '';
      result.forEachResult(function(r, key) {
        if (r.errors.length > 0)
          rs += r.errors[0].message;
        keys += key;
      });

      assert.equal(rs, 'error 1');
      assert.equal(keys, 'onetwo');
    });
  });
});

describe('RuleValidator', function() {
  it('should validate with a single rule', function() {
    let v = new Validation.RuleValidator(NotARule);
    assert.equal(v.validate('bcd').errors.length, 0);
    assert.equal(v.validate('abc').errors.length, 1);
  });
});

describe('Compose validator', function() {
  it('should accept Rules', function() {
    let c = new Validation.Compose([NotARule, NotBRule]);
    assert.ok(c.validate("test").isValid());
    assert.ok(!c.validate("ab").isValid());
  });

  it('should accept another validators', function() {
    let c = new Validation.Compose([new BailValidator()]);
    assert.ok(!c.validate("test").isValid());
  });

  it('should accept mixed validators and rules', function() {
    let c = new Validation.Compose([NotBRule, new BailValidator()]);
    assert.equal(c.validate("test").errors.length, 1);
    assert.equal(c.validate("testb").errors.length, 2);
  });

  it('should accept nested validators and rules', function() {
    let c = new Validation.Compose([[NotBRule, NotARule, []], [new BailValidator()]]);
    assert.equal(c.validate("test").errors.length, 1);
    assert.equal(c.validate("test_ab").errors.length, 3);
  });
});

describe('Condition validator', function() {
  it('should validate just when condition is true', function() {
    let v1 = new Validation.Condition(true, [NotARule]);
    assert.equal(v1.validate("abc").errors.length, 1);

    let v2 = new Validation.Condition(false, [NotARule]);
    assert.equal(v2.validate("abc").errors.length, 0);

    let v3 = new Validation.Condition(true, [NotARule, [new BailValidator()]]);
    assert.equal(v3.validate("abc").errors.length, 2);
  });
});

describe('validate', function() {
  it('should accept array of rules', function() {
    assert.ok(Validation.validate("test", [NotARule]).isValid());
    assert.ok(!Validation.validate("testa", [NotARule]).isValid());

    assert.ok(Validation.validate("c", [NotARule, NotBRule]).isValid());
    assert.ok(!Validation.validate("bc", [NotARule, NotBRule]).isValid());
    assert.ok(!Validation.validate("abc", [NotARule, NotBRule]).isValid());
  });

  it('should accept a validator', function() {
    let validator = new Validation.Compose([NotARule]);
    assert.ok(Validation.validate("bc", [validator]).isValid());
    assert.equal(Validation.validate("abc", [validator]).errors.length, 1);
  });

  it('should accept nested validators and rules', function() {
    let rules = [[NotARule, NotBRule], [], [[new BailValidator()]]];
    assert.equal(Validation.validate("ok", rules).errors.length, 1);
    assert.equal(Validation.validate("a_ok", rules).errors.length, 2);
    assert.equal(Validation.validate("a_o_b_k", rules).errors.length, 3);

    let result = Validation.validate("abc", [
      new Validation.Condition(true, [NotARule]),
      new Validation.Condition(false, [new BailValidator()]),
      NotBRule
    ]);

    assert.equal(result.errors.length, 2);
  });
});
