function calculateStandardDrinks() {
    const volume = parseFloat(document.getElementById('volume').value);
    const volumeUnit = document.getElementById('volumeUnit').value;
    const percentage = parseFloat(document.getElementById('percentage').value);

    let volumeInLiters;
    if (volumeUnit === 'ml') {
        volumeInLiters = volume / 1000;
    } else if (volumeUnit === 'cl') {
        volumeInLiters = volume / 100;
    } else {
        volumeInLiters = volume;
    }

    const standardDrinks = volumeInLiters * percentage * 0.789;
    document.getElementById('standardDrinkValue').innerText = standardDrinks.toFixed(2);
    document.getElementById('standardDrinkPerBottle').value = standardDrinks.toFixed(2);
    document.getElementById('standardDrinkResult').classList.remove('hidden');
    document.getElementById('consumptionFormSection').classList.remove('hidden');
}

document.getElementById('consumptionForm').addEventListener('submit', function(event) {
    const standardDrinkPerBottle = parseFloat(document.getElementById('standardDrinkPerBottle').value);
    const dailyBottles = parseInt(document.getElementById('dailyBottles').value);
    const weeklyBottles = parseInt(document.getElementById('weeklyBottles').value);
    const monthlyBottles = parseInt(document.getElementById('monthlyBottles').value);

    const dailyDrinks = dailyBottles * standardDrinkPerBottle;
    const weeklyDrinks = weeklyBottles * standardDrinkPerBottle;
    const monthlyDrinks = monthlyBottles * standardDrinkPerBottle;

    document.getElementById('dailyDrinks').value = dailyDrinks.toFixed(2);
    document.getElementById('weeklyDrinks').value = weeklyDrinks.toFixed(2);
    document.getElementById('monthlyDrinks').value = monthlyDrinks.toFixed(2);
});
