var validation_1 = require('./validation');
var result_1 = require('./result');
/**
 * Helper function to check whether or not the object is a rule
 */
function isRule(probablyRule) {
    return !!(probablyRule.message && probablyRule.isValid);
}
exports.isRule = isRule;
var RuleValidator = (function () {
    function RuleValidator(rule) {
        this.rule = rule;
    }
    RuleValidator.prototype.validate = function (value, resultContext) {
        if (resultContext === void 0) { resultContext = new result_1.Result(); }
        validation_1.validateRule(value, this.rule, resultContext);
        return resultContext;
    };
    return RuleValidator;
})();
exports.RuleValidator = RuleValidator;
var Compose = (function () {
    function Compose(rulesAndValidators) {
        this.validators = [];
        this.assignRuleOrValidator(rulesAndValidators);
    }
    Compose.prototype.assignRuleOrValidator = function (rules) {
        for (var _i = 0; _i < rules.length; _i++) {
            var rule = rules[_i];
            var type = rule.constructor.name;
            if (type === 'Array') {
                this.assignRuleOrValidator(rule);
            }
            else if (isRule(rule)) {
                this.validators.push(new RuleValidator(rule));
            }
            else {
                this.validators.push(rule);
            }
        }
    };
    Compose.prototype.validate = function (value, resultContext) {
        if (resultContext === void 0) { resultContext = new result_1.Result(); }
        for (var _i = 0, _a = this.validators; _i < _a.length; _i++) {
            var validator = _a[_i];
            validator.validate(value, resultContext);
        }
        return resultContext;
    };
    return Compose;
})();
exports.Compose = Compose;
var Condition = (function () {
    function Condition(condition, rulesIfTrue) {
        this.condition = condition;
        this.trueValidator = new Compose(rulesIfTrue);
    }
    Condition.prototype.validate = function (value, resultContext) {
        if (resultContext === void 0) { resultContext = new result_1.Result(); }
        if (this.condition) {
            this.trueValidator.validate(value, resultContext);
        }
        return resultContext;
    };
    return Condition;
})();
exports.Condition = Condition;
