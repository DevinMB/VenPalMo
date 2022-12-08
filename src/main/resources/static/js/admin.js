// Create an array of items for the dropdown menu
var userList = ["user1@example.com", "user2@example.com", "user3@example.com"];

// Get the select and input elements
var select = document.getElementById("user-list");
var input = document.getElementById("filter");

// Create and append the options to the select element
for (var i = 0; i < userList.length; i++) {
  var option = document.createElement("option");
  option.value = userList[i];
  option.text = userList[i];
  select.appendChild(option);
}

// Listen for changes to the input element
input.addEventListener("input", function() {
  // Get the current value of the input field
  var filterValue = input.value;

  // Loop through all the options in the select element
  for (var i = 0; i < select.options.length; i++) {
    var option = select.options[i];

    // If the option text does not contain the filter value, hide it
    if (option.text.indexOf(filterValue) == -1) {
      option.style.display = "none";
    } else {
      option.style.display = "block";
    }
  }
});
