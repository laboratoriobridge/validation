/**
 * Result of a validation for a single value (might contain multiple errors)
 */
var Result = (function () {
    function Result() {
        this.errors = [];
    }
    Result.prototype.addError = function (value, message) {
        this.errors.push({
            value: value,
            message: message
        });
    };
    /**
     * Check whether the result is valid (doesn't contain any error)
     */
    Result.prototype.isValid = function () {
        return this.errors.length == 0;
    };
    /**
     * Return a flat string array with all error messages
     */
    Result.prototype.errorMessages = function () {
        var arr = [];
        this.errors.forEach(function (error) { return arr.push(error.message); });
        return arr;
    };
    return Result;
})();
exports.Result = Result;
/**
 * Collection of validation results for named properties
 */
var ResultHash = (function () {
    function ResultHash() {
        this._valid = true;
        this.results = {};
    }
    ResultHash.prototype.add = function (key, result) {
        this.results[key] = result;
        if (!result.isValid())
            this._valid = false;
    };
    ResultHash.prototype.isValid = function () {
        return this._valid;
    };
    /**
     * Iterates through results
     */
    ResultHash.prototype.forEachResult = function (callback) {
        var _this = this;
        Object.keys(this.results).forEach(function (key) {
            callback(_this.results[key], key);
        });
    };
    return ResultHash;
})();
exports.ResultHash = ResultHash;
