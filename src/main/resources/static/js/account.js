document.addEventListener('DOMContentLoaded', function() {
  const togglePassword = document.getElementById('toggle-password');
  const passwordInput = document.getElementById('password');
  const eyeIcon = document.getElementById('eye-icon');

  togglePassword.addEventListener('click', function () {
    const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordInput.setAttribute('type', type);
    eyeIcon.setAttribute('d', type === 'password' ? 'M2.5 10a7.5 7.5 0 0114.833 0A7.5 7.5 0 012.5 10zm7.5-4a4 4 0 100 8 4 4 0 000-8z' : 'M2.5 10a7.5 7.5 0 0114.833 0A7.5 7.5 0 012.5 10zm7.5-4a4 4 0 100 8 4 4 0 000-8z');
  });

//  const form = document.getElementById('create-account-form');
//  form.addEventListener('submit', function(event) {
//    event.preventDefault();
//    // Add form validation logic here if needed
//    alert('Account created successfully!');
//  });
});