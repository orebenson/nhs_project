document.addEventListener('DOMContentLoaded', function () {
    const selectElement = document.getElementById('progress-dropdown');
    const ctx = document.getElementById('chartCanvas').getContext('2d');
    let chartInstance = null; // This will hold the chart instance

    // Handler to update the chart whenever the selected metric changes
    selectElement.addEventListener('change', function () {
        fetch(`/diary/progress?metric=${this.value}`)
            .then(response => response.json())
            .then(data => {
                const labels = data.map(entry => entry.date);
                const values = data.map(entry => entry.value);

                // If a chart instance exists, destroy it before creating a new one
                if (chartInstance) {
                    chartInstance.destroy();
                }

                chartInstance = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: this.value.charAt(0).toUpperCase() + this.value.slice(1), // Capitalize first letter
                            data: values,
                            fill: false,
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            })
            .catch(error => console.error('Error loading the data:', error));
    });

    // Trigger the change event on initial load to display the initial chart
    selectElement.dispatchEvent(new Event('change'));
});
