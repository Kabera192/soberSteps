document.addEventListener('DOMContentLoaded', function() {
  const questionContainer = document.getElementById('question-container');
  const progressBar = document.getElementById('progress-bar');
  const nextButtons = document.querySelectorAll('.next-btn');
  const submitButton = document.querySelector('.submit-btn');
  let currentQuestionIndex = 0;
  const totalQuestions = document.querySelectorAll('.question-div').length;

  function updateProgressBar() {
    const progress = ((currentQuestionIndex + 1) / totalQuestions) * 100;
    progressBar.style.width = progress + '%';
  }

  nextButtons.forEach((button, index) => {
    button.addEventListener('click', function() {
      const currentQuestion = questionContainer.children[currentQuestionIndex];
      currentQuestion.classList.add('slide-out');
      currentQuestion.addEventListener('animationend', function() {
        currentQuestion.classList.add('hidden');
        currentQuestion.classList.remove('slide-out');
        currentQuestionIndex++;
        if (currentQuestionIndex < totalQuestions) {
          const nextQuestion = questionContainer.children[currentQuestionIndex];
          nextQuestion.classList.remove('hidden');
          nextQuestion.classList.add('slide-in');
          nextQuestion.addEventListener('animationend', function() {
            nextQuestion.classList.remove('slide-in');
          });
        }
        if (currentQuestionIndex === totalQuestions - 1) {
          submitButton.classList.remove('hidden');
        }
        updateProgressBar();
      }, { once: true });
    });
  });

  // Initialize
  updateProgressBar();
});
