// Get the select element
const selectElement = document.getElementById('user_list');

// Add an event listener to the select element to listen for changes
selectElement.addEventListener('change', (event) => {
  // When the selected option changes, get the selected option element
  const selectedOption = event.target.selectedOptions[0];

  const deleteUserButton = document.getElementById("delete_user");
  const promoteUserButton = document.getElementById("promote_user");

  // Get the value (user.id) of the selected option
  const userId = selectedOption.value;

  // Set the href attribute of the a elements to include the userId
  promoteUserButton.setAttribute('href', `/admin/promote/${userId}`);
  deleteUserButton.setAttribute('href', `/admin/delete/${userId}`);

  // Add the a elements to the page
  adminButtons.appendChild(promoteLink);
  adminButtons.appendChild(deleteLink);
  
});