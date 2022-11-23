// document.addEventListener("submit", login(event));
function login(event) {
    event.preventDefault();
    let username = document.getElementById("email_input");
    let password = document.getElementById("password_input");

    console.log(username.value);
    console.log(password.value);
    
    window.location.replace("index.html");

}
