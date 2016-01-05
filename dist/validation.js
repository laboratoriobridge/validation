var validators_1 = require('./validators');
var result_1 = require('./result');
/**
 * Validate an array of rules and validators (using a Compose validator as base)
 */
function validate(value, r, resultContext) {
    if (resultContext === void 0) { resultContext = new result_1.Result(); }
    var validator = new validators_1.Compose(r);
    validator.validate(value, resultContext);
    return resultContext;
}
exports.validate = validate;
/**
 * Validate a single rule
 */
function validateRule(value, rule, resultContext) {
    if (resultContext === void 0) { resultContext = new result_1.Result(); }
    if (!rule.isValid(value)) {
        resultContext.addError(value, rule.message);
    }
    return resultContext;
}
exports.validateRule = validateRule;
