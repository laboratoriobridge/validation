function Length(length) {
    return new LengthValidator(length);
}
exports.Length = Length;
function LengthRange(min, max) {
    return new LengthRangeValidator(min, max);
}
exports.LengthRange = LengthRange;
function LengthMin(min) {
    return new LengthMinValidator(min);
}
exports.LengthMin = LengthMin;
function LengthMax(max) {
    return new LengthMaxValidator(max);
}
exports.LengthMax = LengthMax;
/**
 * Exact length validator
 */
var LengthValidator = (function () {
    function LengthValidator(length) {
        this.length = length;
        this.message = "Valor deve possuir " + this.length + " caracteres";
    }
    LengthValidator.prototype.isValid = function (value) {
        if (!value)
            return true;
        return value.length == this.length;
    };
    return LengthValidator;
})();
exports.LengthValidator = LengthValidator;
/**
 * Validate values between a minimum and maximum length
 */
var LengthRangeValidator = (function () {
    function LengthRangeValidator(min, max) {
        this.min = min;
        this.max = max;
        this.message = "Valor deve possuir de " + this.min + " a " + this.max + " caracteres";
    }
    LengthRangeValidator.prototype.isValid = function (value) {
        if (!value)
            return true;
        return value.length <= this.max && value.length >= this.min;
    };
    return LengthRangeValidator;
})();
exports.LengthRangeValidator = LengthRangeValidator;
/**
 * Validates values for a minimum length
 */
var LengthMinValidator = (function () {
    function LengthMinValidator(min) {
        this.min = min;
        this.message = "Valor deve possuir no m\u00EDnimo " + this.min + " caracteres";
    }
    LengthMinValidator.prototype.isValid = function (value) {
        if (!value)
            return true;
        return value.length >= this.min;
    };
    return LengthMinValidator;
})();
exports.LengthMinValidator = LengthMinValidator;
/**
 * Validates values for a maximum length
 */
var LengthMaxValidator = (function () {
    function LengthMaxValidator(max) {
        this.max = max;
        this.message = "Valor deve possuir no m\u00E1ximo " + this.max + " caracteres";
    }
    LengthMaxValidator.prototype.isValid = function (value) {
        if (!value)
            return true;
        return value.length <= this.max;
    };
    return LengthMaxValidator;
})();
exports.LengthMaxValidator = LengthMaxValidator;
