document.addEventListener('DOMContentLoaded', function() {
  const profileMenuButton = document.getElementById('profileMenuButton');
  const profileMenu = document.getElementById('profileMenu');

  profileMenuButton.addEventListener('click', () => {
    profileMenu.classList.toggle('hidden');
  });

  window.addEventListener('click', (e) => {
    if (!profileMenuButton.contains(e.target) && !profileMenu.contains(e.target)) {
      profileMenu.classList.add('hidden');
    }
  });
});
