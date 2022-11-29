let errors = {
    

};

function register(event) {
    event.preventDefault();

    let user = {};

    errors = {};

    user.firstName = document.getElementById("first_name_input");
    user.lastName = document.getElementById("last_name_input");
    user.username = document.getElementById("email_input");

    user.password = document.getElementById("password_input");
    user.passwordConfirm = document.getElementById("password_confirm_input");

    user.phone = document.getElementById("phone_input");

    user.address = document.getElementById("address_input");
    user.city = document.getElementById("city_input");
    user.state = document.getElementById("state_input");
    user.zip = document.getElementById("zip_input");

    user.birthDate = document.getElementById("birth_date_input");


    if (validateUser(user)) {
        console.log("correct!")
        console.log(user);
        window.location.replace("login.html");
    }else{
        console.log("wrong!")
        console.log(user);
        console.log(errors);
    }




}

function validateUser(user) {
    let valid = false;

    if (validateName(user)
        & validateEmail(user)
        & validatePassword(user)
        & validatePhone(user)
        & validateBirthDate(user)
        & validateAddress(user)
        & validateCity(user)
        & validateState(user)
        & validateZip(user)
        ) {
        valid = true;
    }

    return valid;
}

function validateName(user) {
    if (user.firstName.value != '' && user.lastName.value != '') {
        return true;
    } else {
        errors.nameError = "Invalid Username: Must Be Not Blank";
        return false;
    }
}

function validateEmail(user) {
    let valid = String(user.username.value).toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
    if(valid){
        return true;
    }else{
        errors.emailError = "Invalid Email Address";
        return false;
    }
    
    
}

function validatePassword(user){
    let valid = user.password.value == user.passwordConfirm.value;

    if(valid){
        return true;
    }else{
        errors.passwordConfirmError = "Passwords Must Match";
        return false;
    }
}

function validatePhone(user){
    let phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    if(user.phone.value.match(phoneno)){
        return true;
    }else{
        errors.phoneError = "Invalid Phone Number"
        return  false;
    }
}

function validateBirthDate(user){
    if (user.birthDate.value) {
        return true;
    }else{
        errors.birthDateError = "Invalid Birthdate"
        return false;
    }
}

// function validateLocation(user){
//     if (user.address.value != '' 
//         && user.city.value != ''
//         && user.state.value != ''
//         && user.zip.value != '') {
//         return true;
//     } else {
//         error
//         return false;
//     }
// }

function validateAddress(user){
    if (user.address.value != '') {
        return true;
    } else {
        errors.addressError = "Address Must Not Be Blank";
        return false;
    }
}

function validateCity(user){
    if (user.city.value != '') {
        return true;
    } else {
        errors.cityError = "Address Must Not Be Blank";
        return false;
    }
}

function validateState(user){
    if (user.state.value != '') {
        return true;
    } else {
        errors.stateError = "State Must Not Be Blank";
        return false;
    }
}

function validateZip(user){
    if (user.zip.value != '') {
        return true;
    } else {
        errors.zipError = "Zip Must Not Be Blank";
        return false;
    }
}




