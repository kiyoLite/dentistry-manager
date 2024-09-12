const getFormDataAsObj = function (form) {
    const formData = new FormData(form);
    const formDataAsObj = Object.fromEntries(formData.entries());
    return formDataAsObj;
};
export { getFormDataAsObj };
