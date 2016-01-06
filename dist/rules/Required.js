exports.Required = {
    message: "Campo requerido",
    isValid: function (value) {
        return !!value;
    }
};
