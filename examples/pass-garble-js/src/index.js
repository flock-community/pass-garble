import {passgarble} from "@flock/pass-garble";

const form = document.querySelector('form');
const passwordGenerator = new passgarble.PasswordGenerator();

const isCheckboxChecked = fieldName => form.querySelector('[name=' + fieldName + ']').checked;

const getPasswordOptions = () => {
    const length = Number(form.querySelector('[name=password-length]').value)
    const defaultOptions = passgarble.defaultOptions();
    return new passgarble.PasswordGenerationOptions(length,
        isCheckboxChecked('includeLowercase'),
        isCheckboxChecked('includeUppercase'),
        isCheckboxChecked('includeNumbers'),
        isCheckboxChecked('includeSpecial'),
        defaultOptions.specialCharSet);
};

form.addEventListener('submit', (e) => {
    e.preventDefault()
    const options = getPasswordOptions();

    try {
        document.getElementById('generated-password').value = passwordGenerator.generatePassword(options)
        document.getElementById("error").classList.add('hidden');
    } catch (error) {
        console.error("Something went wrong", error)
        document.getElementById('generated-password').value = ""
        document.getElementById("error").classList.remove('hidden');
    }

    //Reset clipboard copy state
    document.getElementById("copy-to-clipboard-success").classList.add('hidden')
    document.getElementById("copy-to-clipboard").classList.remove('hidden');
})
