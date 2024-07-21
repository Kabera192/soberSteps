document.addEventListener('DOMContentLoaded', function() {
  const questionsContainer = document.getElementById('questions-container');
  const addQuestionBtn = document.getElementById('add-question-btn');
  let questionCount = 0;

  addQuestionBtn.addEventListener('click', function() {
    questionCount++;
    const questionDiv = document.createElement('div');
    questionDiv.classList.add('question-div', 'bg-gray-100', 'p-4', 'rounded-lg', 'shadow-md');
    questionDiv.innerHTML = `
      <div class="mb-4">
        <label for="question${questionCount}" class="block text-sm font-medium text-gray-700">Question ${questionCount}</label>
        <input type="text" id="question${questionCount}" name="questions[${questionCount}].text" required class="mt-1 p-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500">
      </div>
      <div class="answers-container" data-question="${questionCount}">
        <!-- Answers will be dynamically added here -->
      </div>
      <div class="mt-4 text-right">
        <button type="button" class="add-answer-btn px-4 py-2 bg-indigo-500 text-white rounded-md" data-question="${questionCount}">Add Answer</button>
      </div>
    `;
    questionsContainer.appendChild(questionDiv);

    const addAnswerBtn = questionDiv.querySelector('.add-answer-btn');
    addAnswerBtn.addEventListener('click', function() {
      const questionId = this.getAttribute('data-question');
      const answersContainer = document.querySelector(`.answers-container[data-question="${questionId}"]`);
      const answerCount = answersContainer.children.length;
      const answerDiv = document.createElement('div');
      answerDiv.classList.add('answer-div', 'bg-white', 'p-2', 'rounded-md', 'shadow-sm', 'mt-2');
      answerDiv.innerHTML = `
        <div class="mb-2">
          <label for="answer${questionId}-${answerCount}" class="block text-sm font-medium text-gray-700">Answer ${answerCount}</label>
          <input type="text" id="answer${questionId}-${answerCount}" name="questions[${questionId}].answers[${answerCount}].text" required class="mt-1 p-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500">
        </div>
        <div class="mb-2">
          <label for="weight${questionId}-${answerCount}" class="block text-sm font-medium text-gray-700">Weight</label>
          <input type="number" id="weight${questionId}-${answerCount}" name="questions[${questionId}].answers[${answerCount}].weight" required class="mt-1 p-2 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500">
        </div>
      `;
      answersContainer.appendChild(answerDiv);
    });
  });
});