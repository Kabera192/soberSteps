document.addEventListener('DOMContentLoaded', function() {
  const surveyForm = document.getElementById('survey-form');
  const questionContainer = document.getElementById('question-container');
  const prevButton = document.getElementById('prev-btn');
  const nextButton = document.getElementById('next-btn');

  const questions = [
    {
      question: "How often do you drink alcohol?",
      options: ["Daily", "Weekly", "Monthly", "Rarely"]
    },
    {
      question: "How many drinks do you typically have in a session?",
      options: ["1-2", "3-4", "5-6", "7+"]
    },
    {
      question: "Have you ever felt guilty about drinking?",
      options: ["Never", "Sometimes", "Often", "Always"]
    },
    {
      question: "Do you ever drink in the morning to get rid of a hangover?",
      options: ["Never", "Rarely", "Sometimes", "Often"]
    }
  ];

  let currentQuestionIndex = 0;

  const renderQuestion = (index) => {
    const questionData = questions[index];
    const questionHTML = `
      <div class="question">
        <p class="text-lg font-medium text-gray-700 mb-4">${questionData.question}</p>
        <div class="flex flex-col space-y-2">
          ${questionData.options.map(option => `
            <label class="inline-flex items-center">
              <input type="radio" name="question${index + 1}" value="${option}" class="form-radio text-indigo-600">
              <span class="ml-2">${option}</span>
            </label>
          `).join('')}
        </div>
      </div>
    `;
    questionContainer.innerHTML = questionHTML;
  };

  const updateButtons = () => {
    prevButton.disabled = currentQuestionIndex === 0;
    nextButton.textContent = currentQuestionIndex === questions.length - 1 ? 'Submit' : 'Next';
  };

  nextButton.addEventListener('click', function() {
    if (currentQuestionIndex < questions.length - 1) {
      currentQuestionIndex++;
      renderQuestion(currentQuestionIndex);
      updateButtons();
    } else {
      alert('Survey submitted!');
    }
  });

  prevButton.addEventListener('click', function() {
    if (currentQuestionIndex > 0) {
      currentQuestionIndex--;
      renderQuestion(currentQuestionIndex);
      updateButtons();
    }
  });

  // Initialize the first question
  renderQuestion(currentQuestionIndex);
  updateButtons();
});