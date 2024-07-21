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

function openModal() {
      document.getElementById('modal').classList.remove('hidden');
}

  function closeModal() {
      document.getElementById('modal').classList.add('hidden');
}

function openLogModal() {
    document.getElementById('logModal').classList.remove('hidden');
}

function closeLogModal() {
    document.getElementById('logModal').classList.add('hidden');
}