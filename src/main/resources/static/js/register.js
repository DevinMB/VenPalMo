function register(event) {
    event.preventDefault();

    let user = {};

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

    }




}

function validateUser(user) {
    let valid = false;

    if (validateName(user)
        && validateEmail(user)
        && validatePassword(user)
        && validatePhone(user)
        && validateLocation(user)
        && validateBirthDate(user)) {
        valid = true;
    }

    return valid;
}

function validateName(user) {
    if (user.firstName.value != '' && user.lastName.value != '') {
        return true;
    } else {
        return false;
    }
}

function validateEmail(user) {
    return String(user.username.value).toLowerCase().match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
}

function validatePassword(user){
    return user.password.value == user.passwordConfirm.value;
}

function validatePhone(user){
    let phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    if(user.phone.value.match(phoneno)){
        return true;
    }else{
        return  false;
    }
}

function validateLocation(user){
    if (user.address.value != '' 
        && user.city.value != ''
        && user.state.value != ''
        && user.zip.value != '') {
        return true;
    } else {
        return false;
    }
}

function validateBirthDate(user){
    if (user.birthDate.value) {
        return true;
    }else{
        return false;
    }
}
