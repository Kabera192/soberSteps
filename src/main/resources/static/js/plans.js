document.addEventListener('DOMContentLoaded', function() {
  const plansTableBody = document.getElementById('plans-table-body');
  let expandedRow = null;

  const plans = [
    {
      name: "Plan A",
      startDate: "2024-01-01",
      endDate: "2024-06-01",
      completion: 75,
      status: "In Progress",
      details: {
        alcoholFreeDays: "2 per Week",
        alcoholLimit: "No more than 2 drinks/day",
        planDuration: "6 Months",
        healthyActivities: "Running, Yoga"
      }
    },
    {
      name: "Plan B",
      startDate: "2023-01-01",
      endDate: "2023-12-31",
      completion: 100,
      status: "Completed",
      details: {
        alcoholFreeDays: "3 per Week",
        alcoholLimit: "No more than 1 drink/day",
        planDuration: "1 Year",
        healthyActivities: "Swimming, Meditation"
      }
    }
  ];

  const renderPlanRows = () => {
    plansTableBody.innerHTML = plans.map((plan, index) => `
      <tr class="plan-row cursor-pointer" data-index="${index}">
        <td class="px-6 py-4 border-b">${plan.name}</td>
        <td class="px-6 py-4 border-b">${plan.startDate}</td>
        <td class="px-6 py-4 border-b">${plan.endDate}</td>
        <td class="px-6 py-4 border-b">${plan.completion}%</td>
        <td class="px-1 py-1 rounded text-xs font-semibold bg-indigo-100">${plan.status}</td>
      </tr>
      <tr class="plan-details hidden" id="details-${index}">
        <td colspan="5" class="px-6 py-4">
          <div class="space-y-4">
            <div class="bg-gray-100 p-4 rounded-md">
              <h3 class="text-sm font-bold text-gray-700">Alcohol Free Days</h3>
              <p class="text-xs text-gray-500">${plan.details.alcoholFreeDays}</p>
            </div>
            <div class="bg-gray-100 p-4 rounded-md">
              <h3 class="text-sm font-bold text-gray-700">Alcohol Limit</h3>
              <p class="text-xs text-gray-500">${plan.details.alcoholLimit}</p>
            </div>
            <div class="bg-gray-100 p-4 rounded-md">
              <h3 class="text-sm font-bold text-gray-700">Plan Duration</h3>
              <p class="text-xs text-gray-500">${plan.details.planDuration}</p>
            </div>
            <div class="bg-gray-100 p-4 rounded-md">
              <h3 class="text-sm font-bold text-gray-700">Healthy Activities</h3>
              <p class="text-xs text-gray-500">${plan.details.healthyActivities}</p>
            </div>
          </div>
        </td>
      </tr>
    `).join('');
  };

  const toggleDetails = (index) => {
    const detailsRow = document.getElementById(`details-${index}`);
    if (expandedRow && expandedRow !== detailsRow) {
      expandedRow.classList.add('hidden');
    }
    if (detailsRow.classList.contains('hidden')) {
      detailsRow.classList.remove('hidden');
      expandedRow = detailsRow;
    } else {
      detailsRow.classList.add('hidden');
      expandedRow = null;
    }
  };

  plansTableBody.addEventListener('click', (event) => {
    const row = event.target.closest('.plan-row');
    if (row) {
      const index = row.getAttribute('data-index');
      toggleDetails(index);
    }
  });

  // Initial rendering of plan rows
  renderPlanRows();
});